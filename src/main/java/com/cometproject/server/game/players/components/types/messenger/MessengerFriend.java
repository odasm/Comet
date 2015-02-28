package com.cometproject.server.game.players.components.types.messenger;

import com.cometproject.server.game.players.PlayerManager;
import com.cometproject.server.game.players.data.PlayerAvatar;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.player.PlayerDao;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MessengerFriend {
    private int userId;

    public MessengerFriend(ResultSet data) throws SQLException {
        this.userId = data.getInt("user_two_id");
    }

    public MessengerFriend(int userId) {
        this.userId = userId;
    }

    public boolean isInRoom() {
        if (!isOnline()) {
            return false;
        }

        Session client = NetworkManager.getInstance().getSessions().getByPlayerId(this.userId);

        // Could have these in 1 statement, but to make it easier to read - lets just leave it like this. :P
        if (client.getPlayer() == null || client.getPlayer().getEntity() == null) {
            return false;
        }

        if (!client.getPlayer().getEntity().isVisible())
            return false;

        return true;
    }

    public PlayerAvatar getAvatar() {
        if(this.getSession() != null && this.getSession().getPlayer() != null) {
            return this.getSession().getPlayer().getData();
        }

        return PlayerDao.getAvatarById(this.userId, PlayerAvatar.USERNAME_FIGURE_MOTTO);
    }

    public int getUserId() {
        return this.userId;
    }

    public boolean isOnline() {
        return PlayerManager.getInstance().isOnline(userId);
    }

    public Session getSession() {
        return NetworkManager.getInstance().getSessions().getByPlayerId(this.userId);
    }
}
