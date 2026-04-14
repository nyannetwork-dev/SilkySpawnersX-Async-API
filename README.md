# SilkySpawnersX API

The SilkySpawnersX API provides a set of events that allow other plugins to listen to and react to various spawner-related actions handled by SilkySpawnersX. All events are informational only and cannot be cancelled.

## Events

### ⛏️ SpawnerBreakEvent

Fired whenever SilkySpawnersX handles a player breaking a spawner.

**Fields:**
- `result` (Result enum): SUCCESS or FAILURE
- `player` (Player): The player who broke the spawner
- `block` (Block): The spawner block
- `spawnerType` (EntityType): The type of entity the spawner spawns
- `location` (Location): The location of the spawner
- `quantity` (int): The quantity of spawners dropped
- `wasSpawnerAlreadyTagged` (boolean): Whether the spawner was already tagged
- `context` (Map<String, Object>): Additional context information

**Context Keys:**
- `reason` (String)
- `world-name` (String, e.g. world_the_nether)
- `drop-rate` (double)
- `base-rate` (double)
- `drop-bonus` (double)
- `roll` (double)
- `xp-awarded` (int)
- `held-tool-name` (String)
- `held-tool-enchantments` (String)
- `eligible-tools` (String)
- `eligible-enchantments` (String)
- `is-operator` (boolean)

### 🛠️ SpawnerCraftEvent

Fired whenever a player attempts to craft a spawner through SilkySpawnersX logic.

**Fields:**
- `result` (Result enum): SUCCESS or FAILURE
- `player` (Player): The player crafting the spawner
- `location` (Location): The location of crafting (may be null)
- `spawnerType` (EntityType): The type of entity the spawner spawns
- `quantity` (int): The quantity of spawners crafted
- `totalCharge` (double): The total charge amount
- `charged` (boolean): Whether the player was charged
- `context` (Map<String, Object>): Additional context information

**Context Keys:**
- `reason` (String)
- `world-name` (String e.g. world_the_nether)
- `unit-price` (double)
- `order-amount` (int)
- `formated-total-charge` (String, e.g. <$>50)
- `remaining-balance` (double)
- `is-operator` (boolean)
- `is-creative` (boolean)
- `price-modifier` (double)

### 💥 SpawnerExplosionEvent

Fired when a spawner is affected by an explosion and SilkySpawnersX processes a drop outcome.

**Fields:**
- `result` (Result enum): SUCCESS or FAILURE
- `spawnerType` (EntityType): The type of entity the spawner spawns
- `location` (Location): The location of the spawner
- `quantity` (int): The quantity of spawners dropped
- `cause` (String): The entity that triggered the explosion
- `context` (Map<String, Object>): Additional context information

**Context Keys:**
- `reason` (String)
- `world-name` (String, e.g. world_the_nether)
- `drop-rate` (double)
- `roll` (double)
- `was-spawner-already-tagged` (boolean)

### 📦 SpawnerPlaceEvent

Fired whenever SilkySpawnersX handles a player placing a spawner item.

**Fields:**
- `result` (Result enum): SUCCESS or FAILURE
- `reason` (String): The reason for the result
- `player` (Player): The player placing the spawner
- `worldName` (String, e.g. world_the_nether): The name of the world
- `location` (Location): The location where the spawner was placed
- `spawnerType` (EntityType): The type of entity the spawner spawns
- `wasTagApplied` (boolean): Whether a tag was applied
- `wasSpawnerAlreadyTagged` (boolean): Whether the spawner was already tagged
- `gameMode` (GameMode): The player's game mode
- `isOperator` (boolean): Whether the player is an operator

### 🧬 SpawnerTransformEvent

Fired when a player attempts to transform an existing spawner using a spawn egg.

**Fields:**
- `result` (Result enum): SUCCESS or FAILURE
- `player` (Player): The player transforming the spawner
- `location` (Location): The location of the spawner
- `entityFromSpawner` (EntityType): The original entity type of the spawner
- `entityFromEgg` (EntityType): The new entity type from the spawn egg
- `finalPrice` (double): The final price charged for the transformation
- `context` (Map<String, Object>): Additional context information

**Context Keys:**
- `reason` (String)
- `world-name` (String, e.g. world_the_nether)
- `monster-name-from-egg` (String, e.g. magma cube)
- `monster-name-from-clicked-spawner` (String, e.g. magma cube)
- `final-price` (double)
- `charged-amount` (Double)
- `was-player-charged` (boolean)
- `base-price` (double)
- `modifier` (double)
- `remaining-balance` (String)
- `is-operator` (boolean)
- `is-creative` (boolean)
- `is-spawner-tagged` (boolean)

### ⚙️ SpawnerTransformCommandEvent

Fired when a player attempts to transform a spawner via the transform command.

**Fields:**
- `result` (Result enum): SUCCESS or FAILURE
- `player` (Player): The player executing the transform command
- `location` (Location): The location where the transformation was attempted
- `requestedEntity` (EntityType): The entity type requested by the command
- `spawnerInHandEntity` (EntityType): The entity type of the spawner held in hand
- `finalPrice` (double): The final price calculated for the command transformation
- `context` (Map<String, Object>): Additional context information

**Context Keys:**
- `reason` (String)
- `world-name` (String, e.g. world_the_nether)
- `monster-name-from-requested-type` (String, e.g. magma cube)
- `monster-name-from-held-spawner` (String, e.g. magma cube)
- `final-price` (double)
- `charged-amount` (Double)
- `was-player-charged` (boolean)
- `base-price` (double)
- `modifier` (double)
- `remaining-balance` (String)
- `is-operator` (boolean)
- `is-creative` (boolean)
- `is-spawner-tagged` (boolean)

## Usage

To use these events in your plugin, register listeners for the desired events. For example:

```java
@EventHandler
public void onSpawnerBreak(SpawnerBreakEvent event) {
    if (event.getResult().isSuccess()) {
        // Handle successful spawner break
        Player player = event.getPlayer();
        EntityType type = event.getSpawnerType();
        // Your logic here
    }
}
```

All events extend `org.bukkit.event.Event` and follow standard Bukkit event patterns.
