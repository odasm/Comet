package com.cometproject.server.game.rooms.items.types.floor.hollywood;

import com.cometproject.server.game.rooms.entities.GenericEntity;
import com.cometproject.server.game.rooms.items.RoomItemFloor;

public class HaloTileFloorItem extends RoomItemFloor {
    public HaloTileFloorItem(int id, int itemId, int roomId, int owner, int x, int y, double z, int rotation, String data) {
        super(id, itemId, roomId, owner, x, y, z, rotation, data);
    }

    @Override
    public void onEntityStepOn(GenericEntity entity) {
        this.setExtraData("1");
        this.sendUpdate();
    }

    @Override
    public void onEntityStepOff(GenericEntity entity) {
        //if (this.ticksTimer < 1) {
        //    this.setTicks(1);
        //}

        this.setExtraData("0");
        this.sendUpdate();
    }

    @Override
    public void onTickComplete() {
        //this.setExtraData("0");
        //this.sendUpdate();
    }
}
