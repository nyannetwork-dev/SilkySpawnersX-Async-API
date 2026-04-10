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
 * Fired whenever a player attempts to craft a spawner through SilkySpawnersX logic. The event is informational only and cannot be cancelled.
 */
public class SpawnerCraftEvent extends Event {

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
    private final EntityType spawnerType;

    private final int quantity;
    private final double totalCharge;
    private final boolean charged;
    private final Map<String, Object> context;

    // Exposed values: result(enum), Player, Location, spawnerType(EntityType), quantity(int), totalCharge(double), charged(boolean), context(Map<String, Object>).
    // Context keys: "reason"(String), "world-name"(String e.g. world_the_nether), "unit-price"(double), "order-amount"(int), "formated-total-charge(string, e.g. <$>50)", "remaining-balance(double)", "is-operator(boolean)",
    // "is-creative(boolean)", "price-modifier(double)".
    public SpawnerCraftEvent(Result result, Player player, Location location, EntityType spawnerType, int quantity, double totalCharge, boolean charged, Map<String, Object> context) {
        super(!Bukkit.isPrimaryThread());
        this.result = Objects.requireNonNull(result, "result");
        this.player = player;
        this.location = location == null ? null : location.clone();
        this.spawnerType = spawnerType;
        this.quantity = Math.max(0, quantity);
        this.totalCharge = Math.max(0.0, totalCharge);
        this.charged = charged;
        this.context = context;
    }

    public Result getResult() {
        return result;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getLocation() {
        return location;
    }

    public EntityType getSpawnerType() {
        return spawnerType;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public boolean wasCharged() {
        return charged;
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
