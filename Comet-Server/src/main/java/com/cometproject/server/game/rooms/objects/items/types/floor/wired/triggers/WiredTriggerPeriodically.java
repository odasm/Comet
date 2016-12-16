package com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers;

import com.cometproject.server.game.rooms.objects.items.RoomItemFactory;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredTriggerItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.events.WiredItemEvent;
import com.cometproject.server.game.rooms.types.Room;


public class WiredTriggerPeriodically extends WiredTriggerItem {
    private static final int PARAM_TICK_LENGTH = 0;

    /**
     * The default constructor
     *
     * @param id       The ID of the item
     * @param itemId   The ID of the item definition
     * @param room     The instance of the room
     * @param owner    The ID of the owner
     * @param x        The position of the item on the X axis
     * @param y        The position of the item on the Y axis
     * @param z        The position of the item on the z axis
     * @param rotation The orientation of the item
     * @param data     The JSON object associated with this item
     */
    public WiredTriggerPeriodically(long id, int itemId, Room room, int owner, String ownerName, int x, int y, double z, int rotation, String data) {
        super(id, itemId, room, owner, ownerName, x, y, z, rotation, data);

        this.getWiredData().getParams().putIfAbsent(PARAM_TICK_LENGTH, 5);

        final WiredItemEvent event = new WiredItemEvent(null, null);

        int ticks = RoomItemFactory.getProcessTime(this.getWiredData().getParams().get(PARAM_TICK_LENGTH) / 2) * 10;

        event.setTotalTicks(ticks);

        this.queueEvent(event);
    }

    @Override
    public boolean suppliesPlayer() {
        return false;
    }

    @Override
    public void onEventComplete(WiredItemEvent event) {
        this.evaluate(null, null);

        // loop
        event.setTotalTicks(RoomItemFactory.getProcessTime(this.getWiredData().getParams().get(PARAM_TICK_LENGTH) / 2));
        this.queueEvent(event);
    }

    @Override
    public void onDataChange() {
        final WiredItemEvent event = new WiredItemEvent(null, null);

        int ticks = RoomItemFactory.getProcessTime(this.getWiredData().getParams().get(PARAM_TICK_LENGTH) / 2);
        event.setTotalTicks(ticks);

        this.queueEvent(event);
    }

    @Override
    public int getInterface() {
        return 6;
    }

    @Override
    public int getMaxEvents() {
        return 10;
    }
}
