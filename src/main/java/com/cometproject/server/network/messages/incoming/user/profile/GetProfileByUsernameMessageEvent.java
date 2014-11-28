package com.cometproject.server.network.messages.incoming.user.profile;

import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.players.data.PlayerData;
import com.cometproject.server.game.players.types.PlayerStatistics;
import com.cometproject.server.network.messages.incoming.IEvent;
import com.cometproject.server.network.messages.outgoing.user.profile.LoadProfileMessageComposer;
import com.cometproject.server.network.messages.types.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.groups.GroupDao;
import com.cometproject.server.storage.queries.player.PlayerDao;

import java.util.List;

public class GetProfileByUsernameMessageEvent implements IEvent {
    @Override
    public void handle(Session client, Event msg) throws Exception {
        String username = msg.readString();

        PlayerData data = username.equals(client.getPlayer().getData().getUsername()) ? client.getPlayer().getData() : null;
        PlayerStatistics stats = data != null ? client.getPlayer().getStats() : null;
        List<Integer> groups = data != null ? client.getPlayer().getGroups() : null;

        if (data == null) {
            if (Comet.getServer().getNetwork().getSessions().getByPlayerUsername(username) != null) {
                data = Comet.getServer().getNetwork().getSessions().getByPlayerUsername(username).getPlayer().getData();
                stats = Comet.getServer().getNetwork().getSessions().getByPlayerUsername(username).getPlayer().getStats();
                groups = Comet.getServer().getNetwork().getSessions().getByPlayerUsername(username).getPlayer().getGroups();
            }
        }

        if (data == null) {
            int id = PlayerDao.getIdByUsername(username);
            data = PlayerDao.getDataById(id);
            stats = PlayerDao.getStatisticsById(id);
            groups = GroupDao.getIdsByPlayerId(id);
        }

        if (data == null) {
            return;
        }

        client.send(LoadProfileMessageComposer.compose(data, stats, groups, client.getPlayer().getMessenger().getFriendById(data.getId()) != null, false));

    }
}