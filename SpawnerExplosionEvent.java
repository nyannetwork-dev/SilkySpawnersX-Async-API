package com.silkyspawnersx.api.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Map;
import java.util.Objects;

/**
 * Fired when a spawner is affected by an explosion and SilkySpawnersX processes a drop outcome. The event is informational only and cannot be cancelled.
 */
public class SpawnerExplosionEvent extends Event {

    public enum Result {
        SUCCESS, FAILURE;

        public boolean isSuccess() {
            return this == SUCCESS;
        }
    }

    private static final HandlerList HANDLERS = new HandlerList();

    private final EntityType spawnerType;
    private final Location location;
    private final int quantity;
    private final Result result;
    private final String cause;
    private final Map<String, Object> context;

    // Exposed values: result(enum), spawnerType, Location, quantity(int), cause(entity that tirggered the explosion), context(Map<String, Object>).
    // Context keys: reason(String), world-name(String), drop-rate(double), roll(double), was-spawner-already-tagged(boolean).
    public SpawnerExplosionEvent(Result result, EntityType spawnerType, Location location, int quantity, String cause, Map<String, Object> context) {
        super(!Bukkit.isPrimaryThread());
        this.result = Objects.requireNonNull(result, "result");
        this.spawnerType = spawnerType;
        this.location = location == null ? null : location.clone();
        this.quantity = Math.max(0, quantity);
        this.cause = cause;
        this.context = context;
    }

    public Result getResult() {
        return result;
    }

    public EntityType getSpawnerType() {
        return spawnerType;
    }

    public Location getLocation() {
        return location == null ? null : location.clone();
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCause() {
        return cause;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
