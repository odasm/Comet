package com.cometproject.server.game.rooms.items.types.floor;

import com.cometproject.server.game.rooms.avatars.misc.Position3D;
import com.cometproject.server.game.rooms.entities.GenericEntity;
import com.cometproject.server.game.rooms.items.RoomItemFactory;
import com.cometproject.server.game.rooms.items.RoomItemFloor;
import com.cometproject.server.network.messages.outgoing.room.items.SlideObjectBundleMessageComposer;
import com.cometproject.server.storage.queries.rooms.RoomItemDao;

import java.util.ArrayList;
import java.util.List;

public class RollerFloorItem extends RoomItemFloor {
    public RollerFloorItem(int id, int itemId, int roomId, int owner, int x, int y, double z, int rotation, String data) {
        super(id, itemId, roomId, owner, x, y, z, rotation, data);
    }

    @Override
    public void onEntityStepOn(GenericEntity entity) {
        if (this.ticksTimer < 1) {
            this.setTicks(RoomItemFactory.getProcessTime(2));
        }
    }

    @Override
    public void onEntityStepOff(GenericEntity entity) {

    }

    @Override
    public void onItemAddedToStack(RoomItemFloor floorItem) {
        if (this.ticksTimer < 1) {
            this.setTicks(RoomItemFactory.getProcessTime(2));
        }
    }

    @Override
    public void onTickComplete() {
        this.handleEntities();
        this.handleItems();
    }

    private void handleEntities() {
        Position3D sqInfront = this.squareInfront();

        if (!this.getRoom().getMapping().isValidPosition(sqInfront)) {
            return;
        }

        List<GenericEntity> entities = this.getRoom().getEntities().getEntitiesAt(this.getX(), this.getY());

        for (GenericEntity entity : entities) {
            if (entity.getPosition().getX() != this.getX() && entity.getPosition().getY() != this.getY()) {
                continue;
            }

            if (!this.getRoom().getMapping().isValidStep(entity.getPosition(), sqInfront, true) || !this.getRoom().getEntities().isSquareAvailable(sqInfront.getX(), sqInfront.getY())) {
                this.setTicks(3);
                break;
            }

            if (entity.isWalking()) {
                continue;
            }

            double toHeight = 0d;

            for (RoomItemFloor nextItem : this.getRoom().getItems().getItemsOnSquare(sqInfront.getX(), sqInfront.getY())) {
                if (!nextItem.getDefinition().canSit) {
                    toHeight += nextItem.getDefinition().getHeight();
                }
            }

            entity.updateAndSetPosition(new Position3D(sqInfront.getX(), sqInfront.getY(), toHeight));
            this.getRoom().getEntities().broadcastMessage(SlideObjectBundleMessageComposer.compose(entity.getPosition(), new Position3D(sqInfront.getX(), sqInfront.getY(), toHeight), this.getId(), entity.getVirtualId(), 0));
        }
    }

    private void handleItems() {
        List<RoomItemFloor> floorItems = this.getRoom().getItems().getItemsOnSquare(this.getX(), this.getY());

        if (floorItems.size() == 0) {
            return;
        }

        // quick check illegal use of rollers
        int rollerCount = 0;
        for (RoomItemFloor f : floorItems) {
            if (f instanceof RollerFloorItem) {
                rollerCount++;
            }
        }

        if (rollerCount > 1) {
            this.setTicks(3);
            return;
        }

        Position3D sqInfront = this.squareInfront();

        boolean noItemsOnNext = false;

        for (RoomItemFloor floor : floorItems) {
            if (floor.getX() != this.getX() && floor.getY() != this.getY()) {
                continue;
            }

            //System.out.println(floor.getHeight() + " - " + floor.getDefinition().getInteraction());
            if (floor.getHeight() < 0.5) { continue; }

            double height = floor.getHeight();

            List<RoomItemFloor> itemsSq = this.getRoom().getItems().getItemsOnSquare(sqInfront.getX(), sqInfront.getY());

            if (itemsSq.size() == 0 || noItemsOnNext) {
                System.out.println("HEIGHT SHSASHDA");
                height -= 0.5;
                noItemsOnNext = true;
            }

            if (!this.getRoom().getMapping().isValidStep(new Position3D(floor.getX(), floor.getY(), floor.getHeight()), sqInfront, true) || !this.getRoom().getEntities().isSquareAvailable(sqInfront.getX(), sqInfront.getY())) {
                this.setTicks(3);
                break;
            }

            this.getRoom().getEntities().broadcastMessage(SlideObjectBundleMessageComposer.compose(new Position3D(floor.getX(), floor.getY(), floor.getHeight()), new Position3D(sqInfront.getX(), sqInfront.getY(), height), this.getId(), 0, floor.getId()));

            floor.setX(sqInfront.getX());
            floor.setY(sqInfront.getY());
            floor.setHeight((float)height);

            RoomItemDao.saveItemPosition(floor.getX(), floor.getY(), floor.getHeight(), floor.getRotation(), floor.getId());
        }

        this.getRoom().getMapping().updateTile(this.getX(), this.getY());
        this.getRoom().getMapping().updateTile(sqInfront.getX(), sqInfront.getY());


        for (RoomItemFloor nextItem : this.getRoom().getItems().getItemsOnSquare(sqInfront.getX(), sqInfront.getY())) {
            for (RoomItemFloor floor : floorItems) {
                nextItem.onItemAddedToStack(floor);
            }
        }
    }
}
