package com.cometproject.server.network.messages.incoming.room.item;

import com.cometproject.server.game.rooms.items.types.wall.MoodlightWallItem;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.incoming.IEvent;
import com.cometproject.server.network.messages.outgoing.room.items.moodlight.MoodlightMessageComposer;
import com.cometproject.server.network.messages.types.Event;
import com.cometproject.server.network.sessions.Session;

public class UseMoodlightMessageEvent implements IEvent {
    public void handle(Session client, Event msg) {
        Room room = client.getPlayer().getEntity().getRoom();

        if (room == null) {
            return;
        }

        MoodlightWallItem moodlight = room.getItems().getMoodlight();
        if (moodlight == null) {
            return;
        }

        client.send(MoodlightMessageComposer.compose(moodlight));
    }
}
