package com.cometproject.server.network.messages.incoming.user.youtube;

import com.cometproject.server.game.players.components.types.settings.PlaylistItem;
import com.cometproject.server.game.players.types.PlayerSettings;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.network.messages.incoming.IEvent;
import com.cometproject.server.network.messages.outgoing.room.items.UpdateFloorItemMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.youtube.PlayVideoMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.youtube.PlaylistMessageComposer;
import com.cometproject.server.network.messages.types.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.player.PlayerDao;


public class NextVideoMessageEvent implements IEvent {
    @Override
    public void handle(Session client, Event msg) throws Exception {
        int itemId = msg.readInt();
        int direction = msg.readInt(); // 0 = previous, 1 = next

        if(client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null)
            return;

        RoomItemFloor item = client.getPlayer().getEntity().getRoom().getItems().getFloorItem(itemId);

        PlayerSettings playerSettings;

        if (client.getPlayer().getId() != item.getOwner()) {
            return;
        }

        playerSettings = PlayerDao.getSettingsById(item.getOwner());

        if (playerSettings == null) {
            playerSettings = client.getPlayer().getSettings();
        }

        int currentVideoIndex = 0;

        if (item.hasAttribute("video")) {
            String videoAttribute = (String) item.getAttribute("video");
            int playlistSize = playerSettings.getPlaylist().size();

            for (int i = 0; i < playlistSize; i++) {
                if (playerSettings.getPlaylist().get(i).getVideoId().equals(videoAttribute)) {
                    if (direction == 0 && i != 0) {
                        currentVideoIndex = i - 1;
                    } else if (direction == 1 && (playlistSize - 1) > i) {
                        currentVideoIndex = i + 1;
                    }
                }
            }
        }

        if(playerSettings.getPlaylist().size() == 0) return;

        PlaylistItem playlistItem = playerSettings.getPlaylist().get(currentVideoIndex);
        if(playerSettings.getPlaylist() != null) {
            client.send(new PlaylistMessageComposer(itemId, playerSettings.getPlaylist(), currentVideoIndex));
        }

        client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new PlayVideoMessageComposer(itemId, playlistItem.getVideoId(), playlistItem.getDuration()));

        item.setAttribute("video", playlistItem.getVideoId());

        client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new UpdateFloorItemMessageComposer(item));
    }
}
