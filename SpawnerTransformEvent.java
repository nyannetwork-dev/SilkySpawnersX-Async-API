package com.silkyspawnersx.api.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Map;
import java.util.Objects;

/**
 * Fired when a player attempts to transform an existing spawner using a spawn egg. The event is informational only and cannot be cancelled.
 */
public class SpawnerTransformEvent extends Event {

    public enum Result {
        SUCCESS, FAILURE;

        public boolean isSuccess() {
            return this == SUCCESS;
        }
    }

    private static final HandlerList HANDLERS = new HandlerList();

    private final Result result;
    private final Player player;
    private final Location location;
    private final EntityType entityFromSpawner;
    private final EntityType entityFromEgg;
    private final double price;
    private final Map<String, Object> context;

    // Exposed values: result(enum), Player, Location, entityFromSpawner(EntityType), entityFromEgg(EntityType), price(double), context(Map<String, Object>)
    // Context keys: reason(String), world-name(String, e.g. world_the_nether), monster-name-from-egg(String, e.g. magma cube), monster-name-from-clicked-spawner(String, e.g. magma cube), price(double),
    // charged-amount(Double), was-player-charged(boolean), base-price(double), modifier(double), remaining-balance(String), is-operator (boolean), is-creative(boolean), is-spawner-tagged(boolean).
    public SpawnerTransformEvent(Result result, Player player, Location location, EntityType entityFromSpawner, EntityType entityFromEgg, double price, Map<String, Object> context) {
        super(!Bukkit.isPrimaryThread());

        this.result = Objects.requireNonNull(result, "result");
        this.player = player;
        this.location = location;
        this.entityFromSpawner = entityFromSpawner;
        this.entityFromEgg = entityFromEgg;
        this.price = Math.max(0.0, price);
        this.context = context;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getLocation() {
        return location == null ? null : location.clone();
    }

    public EntityType getEntityFromSpawner() {
        return entityFromSpawner;
    }

    public EntityType getEntityFromEgg() {
        return entityFromEgg;
    }

    public Result getResult() {
        return result;
    }

    public double getPrice() {
        return price;
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
