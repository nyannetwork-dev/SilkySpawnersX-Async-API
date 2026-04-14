package com.silkyspawnersx.api.event;

import java.util.Map;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SpawnerTransformCommandEvent extends Event {

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
  private final EntityType requestedEntity;
  private final EntityType heldSpawnerEntity;
  private final double finalPrice;
  private final Map<String, Object> context;

  // Exposed values: result(enum), Player, Location, requestedEntity(EntityType), heldSpawnerEntity(EntityType), finalPrice(double), context(Map<String, Object>)
  // Context keys: reason(String), world-name(String, e.g. world_the_nether), monster-name-from-requested-type(String, e.g. magma cube),
  // monster-name-from-held-spawner(String, e.g. magma cube), final-price(double), charged-amount(Double), was-player-charged(boolean),
  // base-price(double), modifier(double), remaining-balance(String), is-operator (boolean), is-creative(boolean), is-spawner-tagged(boolean).
  public SpawnerTransformCommandEvent(Result result, Player player, Location location, EntityType requestedEntity, EntityType heldSpawnerEntity, double finalPrice, Map<String, Object> context) {
    super(!Bukkit.isPrimaryThread());

    this.result = Objects.requireNonNull(result, "result");
    this.player = player;
    this.location = location;
    this.requestedEntity = requestedEntity;
    this.heldSpawnerEntity = heldSpawnerEntity;
    this.finalPrice = Math.max(0.0, finalPrice);
    this.context = context;
  }

  public Player getPlayer() {
    return player;
  }

  public Location getLocation() {
    return location;
  }

  public EntityType getRequestedEntityType() {
    return requestedEntity;
  }

  public EntityType getEntityTypeFromHeldSpawner() {
    return heldSpawnerEntity;
  }

  public Result getResult() {
    return result;
  }

  public double getFinalPrice() {
    return finalPrice;
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
