package com.cometproject.server.network.messages.outgoing.room.items;

import com.cometproject.server.game.rooms.objects.items.RoomItemWall;
import com.cometproject.server.network.messages.headers.Composers;
import com.cometproject.server.network.messages.types.Composer;


public class UpdateWallItemMessageComposer {
    public static Composer compose(RoomItemWall item, int ownerId, String owner) {
        Composer msg = new Composer(Composers.UpdateRoomWallItemMessageComposer);

        msg.writeString(item.getId());
        msg.writeInt(item.getDefinition().getSpriteId());
        msg.writeString(item.getWallPosition());
        msg.writeString(item.getExtraData());
        msg.writeInt(!item.getDefinition().getInteraction().equals("default") ? 1 : 0);
        msg.writeInt(ownerId);
        msg.writeString(owner);

        return msg;
    }
}
