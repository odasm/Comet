package com.cometproject.server.game.rooms.objects.entities;

import com.cometproject.server.game.rooms.objects.RoomObject;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.objects.misc.Position;
import com.cometproject.server.game.rooms.objects.entities.pathfinding.Pathfinder;
import com.cometproject.server.game.rooms.objects.entities.pathfinding.Square;
import com.cometproject.server.game.rooms.objects.entities.types.BotEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PetEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.mapping.TileInstance;
import com.cometproject.server.network.messages.outgoing.room.avatar.*;
import javolution.util.FastMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class GenericEntity extends RoomObject implements AvatarEntity {
    private RoomEntityType entityType;

    private Position walkingGoal;
    private Position positionToSet;

    private int bodyRotation;
    private int headRotation;

    private List<Square> processingPath;
    private List<Square> walkingPath;

    private Square futureSquare;

    private int stepsToGoal;

    private int idleTime;
    private int signTime;

    private int danceId;

    private PlayerEffect lastEffect;
    private PlayerEffect effect;

    private int handItem;
    private int handItemTimer;

    private boolean needsUpdate;
    private boolean isMoonwalking;
    private boolean overriden;
    private boolean isVisible;

    private boolean cancelNextUpdate;

    private boolean doorbellAnswered;

    private Map<RoomEntityStatus, String> statuses = new FastMap<>();

    public GenericEntity(int identifier, Position startPosition, int startBodyRotation, int startHeadRotation, Room roomInstance) {
        super(identifier, startPosition, roomInstance);

        if (this instanceof PlayerEntity) {
            this.entityType = RoomEntityType.PLAYER;
        } else if (this instanceof BotEntity) {
            this.entityType = RoomEntityType.BOT;
        } else if (this instanceof PetEntity) {
            this.entityType = RoomEntityType.PET;
        }

        this.bodyRotation = startBodyRotation;
        this.headRotation = startHeadRotation;

        this.idleTime = 0;
        this.signTime = 0;
        this.handItem = 0;
        this.handItemTimer = 0;

        this.danceId = 0;

        this.needsUpdate = false;
        this.isMoonwalking = false;
        this.overriden = false;
        this.isVisible = true;
        this.cancelNextUpdate = false;

        this.doorbellAnswered = false;

        this.stepsToGoal = 0;
    }

    public RoomEntityType getEntityType() {
        return this.entityType;
    }

    @Override
    public Position getWalkingGoal() {
        if (this.walkingGoal == null) {
            return this.getPosition();
        } else {
            return this.walkingGoal;
        }
    }

    @Override
    public void setWalkingGoal(int x, int y) {
        if (this.walkingGoal == null) {
            this.walkingGoal = new Position(x, y, 0.0);
        } else {
            this.walkingGoal.setX(x);
            this.walkingGoal.setY(y);
        }
    }

    public int getStepsToGoal() {
        return this.stepsToGoal;
    }

    @Override
    public void moveTo(int x, int y) {
        TileInstance tileInstance = this.getRoom().getMapping().getTile(x, y);

        if(tileInstance == null)
            return;

        // reassign the position values if they're set to redirect
        if(tileInstance.getRedirect() != null) {
            x = tileInstance.getRedirect().getX();
            y = tileInstance.getRedirect().getY();
        }

        if (this.getPositionToSet() != null) {
            this.getRoom().getEntities().getEntitiesAt(this.getPosition().getX(), this.getPosition().getY()).remove(this);
            this.setPosition(this.getPositionToSet());
        }

        // Set the goal we are wanting to achieve
        this.setWalkingGoal(x, y);

        // Create a walking path
        List<Square> path = Pathfinder.getInstance().makePath(this);

        // Check returned path to see if it calculated one
        if (path == null || path.size() == 0) {
            // Reset the goal and return as no path was found
            this.setWalkingGoal(this.getPosition().getX(), this.getPosition().getY());
            return;
        }

        this.stepsToGoal = path.size();

        // UnIdle the user and set the path (if the path has nodes it will mean the user is walking)
        this.unIdle();
        this.setWalkingPath(path);
    }

    @Override
    public Position getPositionToSet() {
        return this.positionToSet;
    }

    @Override
    public void updateAndSetPosition(Position pos) {
        this.positionToSet = pos;
    }

    @Override
    public void markPositionIsSet() {
        this.positionToSet = null;
    }

    public boolean hasPositionToSet() {
        return (this.positionToSet != null);
    }

    @Override
    public int getBodyRotation() {
        return this.bodyRotation;
    }

    @Override
    public void setBodyRotation(int rotation) {
        this.bodyRotation = rotation;
    }

    @Override
    public int getHeadRotation() {
        return this.headRotation;
    }

    @Override
    public void setHeadRotation(int rotation) {
        this.headRotation = rotation;
    }

    @Override
    public List<Square> getProcessingPath() {
        return this.processingPath;
    }

    @Override
    public void setProcessingPath(List<Square> path) {
        this.processingPath = path;
    }

    @Override
    public List<Square> getWalkingPath() {
        return this.walkingPath;
    }

    @Override
    public void setWalkingPath(List<Square> path) {
        if (this.walkingPath != null) {
            this.walkingPath.clear();
        }

        this.walkingPath = path;
    }

    @Override
    public boolean isWalking() {
        return (this.processingPath != null) && (this.processingPath.size() > 0);
    }

    @Override
    public Square getFutureSquare() {
        return this.futureSquare;
    }

    @Override
    public void setFutureSquare(Square square) {
        this.futureSquare = square;
    }

    @Override
    public Map<RoomEntityStatus, String> getStatuses() {
        return this.statuses;
    }

    @Override
    public void addStatus(RoomEntityStatus key, String value) {
        if (this.statuses.containsKey(key)) {
            return;
        }

        this.statuses.put(key, value);
    }

    @Override
    public void removeStatus(RoomEntityStatus status) {
        if (!this.statuses.containsKey(status)) {
            return;
        }

        this.statuses.remove(status);
    }

    @Override
    public boolean hasStatus(RoomEntityStatus key) {
        return this.statuses.containsKey(key);
    }

    @Override
    public void markNeedsUpdate() {
        this.needsUpdate = true;
    }

    public void markUpdateComplete() {
        this.needsUpdate = false;
    }

    @Override
    public boolean needsUpdate() {
        return this.needsUpdate;
    }

    public boolean isMoonwalking() {
        return this.isMoonwalking;
    }

    public void setIsMoonwalking(boolean isMoonwalking) {
        this.isMoonwalking = isMoonwalking;
    }

    @Override
    public int getIdleTime() {
        return this.idleTime;
    }

    @Override
    public boolean isIdleAndIncrement() {
        this.idleTime++;

        if (this.idleTime >= 600) {
            return true;
        }

        return false;
    }

    @Override
    public void resetIdleTime() {
        this.idleTime = 0;
    }

    @Override
    public void setIdle() {
        this.idleTime = 600;
    }

    public boolean handItemNeedsRemove() {
        this.handItemTimer--;

        return this.handItemTimer <= 0;

    }

    public void unIdle() {
        this.resetIdleTime();
        this.getRoom().getEntities().broadcastMessage(IdleStatusMessageComposer.compose(this.getId(), false));
    }

    @Override
    public int getSignTime() {
        return this.signTime;
    }

    @Override
    public void markDisplayingSign() {
        this.signTime = 6;
    }

    @Override
    public boolean isDisplayingSign() {
        this.signTime--;

        if (this.signTime <= 0) {
            if (this.signTime < 0) {
                this.signTime = 0;
            }

            return false;
        } else {
            return true;
        }
    }

    @Override
    public int getDanceId() {
        return this.danceId;
    }

    @Override
    public void setDanceId(int danceId) {
        this.danceId = danceId;
    }

    @Override
    public PlayerEffect getCurrentEffect() {
        return this.effect;
    }

    public void setLastEffect(PlayerEffect lastEffect) {
        this.lastEffect = lastEffect;
    }

    @Override
    public int getHandItem() {
        return this.handItem;
    }

    @Override
    public void carryItem(int id) {
        this.handItem = id;

        this.handItemTimer = 240;
        this.getRoom().getEntities().broadcastMessage(HandItemMessageComposer.compose(this.getId(), handItem));
    }

    @Override
    public int getHandItemTimer() {
        return this.handItemTimer;
    }

    @Override
    public void setHandItemTimer(int time) {
        this.handItemTimer = time;
    }

    @Override
    public void applyEffect(PlayerEffect effect) {
        if (effect == null) {
            this.getRoom().getEntities().broadcastMessage(ApplyEffectMessageComposer.compose(this.getId(), 0));
        } else {
            this.getRoom().getEntities().broadcastMessage(ApplyEffectMessageComposer.compose(this.getId(), effect.getEffectId()));
        }

        if(effect != null && effect.expires()) {
            this.lastEffect = this.effect;
        }

        this.effect = effect;
    }

    public boolean isOverriden() {
        return this.overriden;
    }

    public void setOverriden(boolean overriden) {
        this.overriden = overriden;
    }

    public abstract void joinRoom(Room room, String password);

    protected abstract void finalizeJoinRoom();

    public abstract void leaveRoom(boolean isOffline, boolean isKick, boolean toHotelView);

    protected abstract void finalizeLeaveRoom();

    public abstract boolean onChat(String message);

    public abstract boolean onRoomDispose();

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public void updateVisibility(boolean isVisible) {
        if (isVisible && !this.isVisible) {
            this.getRoom().getEntities().broadcastMessage(AvatarsMessageComposer.compose(this));
        } else {
            this.getRoom().getEntities().broadcastMessage(LeaveRoomMessageComposer.compose(this.getId()));
        }

        this.isVisible = isVisible;
    }

    @Override
    public void warp(Position position) {
        // clear all walking
        this.walkingGoal = null;
        this.walkingPath = null;
        this.processingPath = null;
        this.stepsToGoal = 0;

        this.futureSquare = null;

        this.updateAndSetPosition(position);
        this.markNeedsUpdate();
    }

    public boolean needsUpdateCancel() {
        if(this.cancelNextUpdate) {
            this.cancelNextUpdate = false;
            return true;
        } else {
            return false;
        }
    }

    public void cancelNextUpdate() {
        this.cancelNextUpdate = true;
    }

    public boolean isDoorbellAnswered() {
        return this.doorbellAnswered;
    }

    public void setDoorbellAnswered(boolean b) {
        this.doorbellAnswered = b;
    }

    public PlayerEffect getLastEffect() {
        return lastEffect;
    }
}