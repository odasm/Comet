package com.cometproject.server.game.rooms.types.mapping;

import com.cometproject.server.game.rooms.avatars.misc.Position3D;
import com.cometproject.server.game.rooms.items.RoomItemFloor;
import com.cometproject.server.game.rooms.items.types.floor.BedFloorItem;
import com.cometproject.server.game.rooms.items.types.floor.GateFloorItem;
import com.cometproject.server.game.rooms.items.types.floor.MagicStackFloorItem;
import org.apache.log4j.Logger;

public class TileInstance {
    private RoomMapping mappingInstance;
    private Position3D position;

    private RoomEntityMovementNode movementNode;
    private RoomTileStatusType status;

    private boolean canStack;

    private int topItem = 0;
    private double stackHeight = 0d;

    private int originalTopItem = 0;
    private double originalHeight = 0d;

    private Position3D redirect = null;

    public TileInstance(RoomMapping mappingInstance, Position3D position) {
        this.mappingInstance = mappingInstance;
        this.position = position;
        this.reload();
    }

    public void reload() {
        // reset the tile data
        this.redirect = null;
        this.movementNode = RoomEntityMovementNode.OPEN;
        this.status = RoomTileStatusType.NONE;
        this.canStack = true;
        this.topItem = 0;
        this.originalHeight = 0d;
        this.originalTopItem = 0;
        this.stackHeight = 0d;

        double highestHeight = 0d;
        int highestItem = 0;

        Double overrideHeight = null;
        int overrideItem = 0;

        for (RoomItemFloor item : mappingInstance.getRoom().getItems().getItemsOnSquare(this.position.getX(), this.position.getY())) {
            if (item.getDefinition() == null)
                continue;

            final double totalHeight = item.getHeight() + item.getDefinition().getHeight();

            if(totalHeight > highestHeight) {
                highestHeight = totalHeight;
                highestItem = item.getId();
            }

            final boolean isGate = item instanceof GateFloorItem;

            if (!item.getDefinition().canWalk && !isGate) {
                movementNode = RoomEntityMovementNode.CLOSED;
            }

            switch (item.getDefinition().getInteraction().toLowerCase()) {
                case "bed":
                    status = RoomTileStatusType.LAY;
                    movementNode = RoomEntityMovementNode.END_OF_ROUTE;

                    if (item.getRotation() == 2 || item.getRotation() == 6) {
                        this.redirect = new Position3D(item.getX(), this.getPosition().getY());
                    } else if (item.getRotation() == 0 || item.getRotation() == 4) {
                        this.redirect = new Position3D(this.getPosition().getX(), item.getY());
                    }

                    break;

                case "gate":
                    movementNode = item.getExtraData().equals("1") ? RoomEntityMovementNode.OPEN : RoomEntityMovementNode.CLOSED;
                    break;

                case "onewaygate":
                    movementNode = RoomEntityMovementNode.CLOSED;
                    break;
            }

            if (item.getDefinition().canSit) {
                status = RoomTileStatusType.SIT;
                movementNode = RoomEntityMovementNode.END_OF_ROUTE;
            }

            if (item.getDefinition().getInteraction().equals("bed")) {
                status = RoomTileStatusType.LAY;
                movementNode = RoomEntityMovementNode.END_OF_ROUTE;
            }

            if (!item.getDefinition().canStack) {
                this.canStack = false;
            }

            if(item instanceof MagicStackFloorItem) {
                overrideItem = item.getId();
                overrideHeight = ((MagicStackFloorItem) item).getMagicHeight();
            }
        }

        if(overrideHeight != null) {
            this.canStack = true;
            this.stackHeight = overrideHeight;
            this.topItem = overrideItem;

            this.originalHeight = highestHeight;
            this.originalTopItem = highestItem;
        } else {
            this.stackHeight = highestHeight;
            this.topItem = highestItem;
        }

        if(this.stackHeight == 0d)
            this.stackHeight = this.mappingInstance.getModel().getSquareHeight()[this.position.getX()][this.position.getY()];

//        final double tileHeight = this.mappingInstance.getModel().getSquareHeight()[this.position.getX()][this.position.getY()];
//        this.stackHeight += tileHeight;
    }

    public RoomEntityMovementNode getMovementNode() {
        return this.movementNode;
    }

    public double getStackHeight() {
        return this.stackHeight;
    }

    public double getWalkHeight() {
        double height = this.stackHeight;

        RoomItemFloor roomItemFloor = this.mappingInstance.getRoom().getItems().getFloorItem(this.topItem);

        if (roomItemFloor != null && (roomItemFloor.getDefinition().canSit || roomItemFloor instanceof BedFloorItem)) {
            height -= roomItemFloor.getDefinition().getHeight();
        }

        return height;
    }

    public RoomTileStatusType getStatus() {
        return this.status;
    }

    public Position3D getPosition() {
        return this.position;
    }

    public boolean canStack() {
        return this.canStack;
    }

    public int getTopItem() {
        return this.topItem;
    }

    public void setTopItem(int topItem) {
        this.topItem = topItem;
    }

    public Position3D getRedirect() {
        return redirect;
    }

    public void setRedirect(Position3D redirect) {
        this.redirect = redirect;
    }

    public int getOriginalTopItem() {
        return originalTopItem;
    }

    public double getOriginalHeight() {
        return originalHeight;
    }
}
