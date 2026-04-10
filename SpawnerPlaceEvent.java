package com.silkyspawnersx.api.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.GameMode;

import java.util.Objects;

/* Fired whenever SilkySpawnersX handles a player placing a spawner item. */
public class SpawnerPlaceEvent extends Event {

    public enum Result {
        SUCCESS, FAILURE;

        public boolean isSuccess() {
            return this == SUCCESS;
        }
    }

    private static final HandlerList HANDLERS = new HandlerList();

    private final String reason;
    private final Player player;
    private final String worldName;
    private final Location location;
    private final EntityType spawnerType;
    private final Result result;
    private final boolean wasTagApplied;
    private final boolean wasSpawnerAlreadyTagged;
    private final GameMode gameMode;
    private final boolean isOperator;

    // Exposed values: result(enum), reason, player, worldName, location, spawnerType, wasSpawnerTagged, tagApplied, wasSpawnerAlreadyTagged.
    public SpawnerPlaceEvent(Result result, String reason, Player player, String worldName, Location location, EntityType spawnerType, boolean wasTagApplied, boolean wasSpawnerAlreadyTagged,
            GameMode gameMode, boolean isOperator) {
        super(!Bukkit.isPrimaryThread());
        this.result = Objects.requireNonNull(result, "result");
        this.reason = reason;
        this.player = player;
        this.worldName = worldName;
        this.location = location == null ? null : location.clone();
        this.spawnerType = spawnerType;
        this.wasTagApplied = wasTagApplied;
        this.wasSpawnerAlreadyTagged = wasSpawnerAlreadyTagged;
        this.gameMode = gameMode;
        this.isOperator = isOperator;
    }

    public Result getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }

    public Player getPlayer() {
        return player;
    }

    public String getWorldName() {
        return worldName;
    }

    public Location getLocation() {
        return location == null ? null : location.clone();
    }

    public EntityType getSpawnerType() {
        return spawnerType;
    }

    public boolean wasTagApplied() {
        return wasTagApplied;
    }

    public boolean wasSpawnerAlreadyTagged() {
        return wasSpawnerAlreadyTagged;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public boolean isOperator() {
        return isOperator;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
