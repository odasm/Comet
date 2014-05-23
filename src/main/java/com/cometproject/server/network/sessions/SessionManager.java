package com.cometproject.server.network.sessions;

import com.cometproject.server.game.CometManager;
import com.cometproject.server.network.messages.types.Composer;
import javolution.util.FastMap;
import javolution.util.FastSet;
import org.jboss.netty.channel.Channel;

import java.util.Map;
import java.util.Set;

public class SessionManager {

    private FastMap<Integer, Session> sessions = new FastMap<Integer, Session>().atomic();
    private FastMap<Integer, Integer> playerIdToSessionId = new FastMap<Integer, Integer>().atomic();

    public boolean add(Channel channel) {
        Session session = new Session(channel);
        channel.setAttachment(session);

        return (this.sessions.putIfAbsent(channel.getId(), session) == null);
    }

    public boolean remove(Channel channel) {
        if (this.sessions.containsKey(channel.getId())) {
            this.sessions.remove(channel.getId());
            return true;
        }

        return false;
    }

    public boolean disconnectByPlayerId(int id) {
        if(!this.playerIdToSessionId.containsKey(id)) {
            return false;
        }

        int sessionId = playerIdToSessionId.get(id);
        Session session = sessions.get(sessionId);

        if(session != null) {
            session.disconnect();
            return true;
        }

        return false;
    }

    public Session getByPlayerId(int id) {
         if(this.playerIdToSessionId.containsKey(id)) {
            int sessionId = this.playerIdToSessionId.get(id);

            return sessions.get(sessionId);
        }

        return null;
    }

    public Set<Session> getByPlayerPermission(String permission) {
        // TODO: Optimize this
        Set<Session> sessions = new FastSet<>();

        int rank = CometManager.getPermissions().getPermissions().get(permission).getRank();

        for (Map.Entry<Integer, Session> session : this.sessions.entrySet()) {
            if (session.getValue().getPlayer() != null) {
                if (session.getValue().getPlayer().getData().getRank() >= rank) {
                    sessions.add(session.getValue());
                }
            }
        }

        return sessions;
    }

    public Session getByPlayerUsername(String username) {
        int playerId = CometManager.getPlayers().getPlayerIdByUsername(username);

        if(playerId == -1)
            return null;

        int sessionId = CometManager.getPlayers().getSessionIdByPlayerId(playerId);

        if(sessionId == -1)
            return null;

        if(this.sessions.containsKey(sessionId))
            return this.sessions.get(sessionId);

        return null;
    }

    public int getUsersOnlineCount() {
        return CometManager.getPlayers().size();
    }

    public Map<Integer, Session> getSessions() {
        return this.sessions.unmodifiable();
    }

    public void broadcast(Composer msg) {
        for (Session client : sessions.values()) {
            client.getChannel().write(msg.get());
        }
    }
}
