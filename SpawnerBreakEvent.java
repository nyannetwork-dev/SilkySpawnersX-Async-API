package com.silkyspawnersx.api.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Map;
import java.util.Objects;

/**
 * Fired whenever SilkySpawnersX handles a player breaking a spawner. The event is informational only and cannot be cancelled.
 */
public class SpawnerBreakEvent extends Event {

    public enum Result {
        SUCCESS, FAILURE;

        public boolean isSuccess() {
            return this == SUCCESS;
        }
    }

    private static final HandlerList HANDLERS = new HandlerList();

    private final Result result;
    private final Player player;
    private final Block block;
    private final EntityType spawnerType;
    private final Location location;
    private final int quantity;
    private final boolean wasSpawnerAlreadyTagged;
    private final Map<String, Object> context;

    // Exposed values: result(enum), Player, Block, spawnerType(EntityType), Location, quantity(int), wasSpawnerAlreadyTagged(boolean), context(Map<String, Object>).
    // Context keys: reason(String), world-name(String), drop-rate(double), base-rate(double), drop-bonus(double), roll(double), xp-awarded(int), held-tool-name(String),
    // held-tool-enchantments(String), eligible-tools(String), eligible-enchantments(String), is-operator(boolean).
    public SpawnerBreakEvent(Result result, Player player, Block block, EntityType spawnerType, Location location, int quantity, boolean wasSpawnerAlreadyTagged, Map<String, Object> context) {
        super(!Bukkit.isPrimaryThread());

        this.result = Objects.requireNonNull(result, "result");
        this.player = player;
        this.block = block;
        this.spawnerType = spawnerType;
        this.location = location;
        this.quantity = Math.max(0, quantity);
        this.wasSpawnerAlreadyTagged = wasSpawnerAlreadyTagged;
        this.context = context;
    }

    public Result getResult() {
        return result;
    }

    public Player getPlayer() {
        return player;
    }

    public Block getBlock() {
        return block;
    }

    public EntityType getSpawnerType() {
        return spawnerType;
    }

    public Location getLocation() {
        return location;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean wasSpawnerAlreadyTagged() {
        return wasSpawnerAlreadyTagged;
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
