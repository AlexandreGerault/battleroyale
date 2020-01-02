package io.github.alexandregerault.battleroyale.main;

import io.github.alexandregerault.battleroyale.data.*;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.Game;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameRegistryEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;

import com.flowpowered.math.vector.Vector3i;
import com.google.inject.Inject;

import io.github.alexandregerault.battleroyale.registers.CommandsRegister;
import io.github.alexandregerault.battleroyale.registers.EventsRegister;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import org.slf4j.Logger;

@Plugin(id = "brp", name = "Battle Royale", version = "1.0", description = "Battle royal plugin")
public class BattleRoyale {

	@Inject
	private Game game;
	/*
	@Inject
	private AssetManager assets;
	*/
	@Inject
	private PluginContainer container;
	@Inject
    private Logger logger;
    @Inject
    @ConfigDir(sharedRoot = false)
    private File configDir;

    private File schematicsDir;
    private Vector3i spawn;
    private GameState state;

    @Listener
	public void GameRegistryEventRegisterKey(GameRegistryEvent.Register<Key<?>> event) {
	    event.register(ClipboardKeys.CLIPBOARD);
	    event.register(ClipboardKeys.CORNER_ONE);
	    event.register(ClipboardKeys.CORNER_TWO);

	    event.register(PlayerKeys.ROLE);

	    event.register(FighterKeys.KILLS);
	}

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
    	DataRegistration.builder()
    	.dataClass(ClipboardData.class)
    	.immutableClass(ClipboardData.Immutable.class)
    	.builder(new ClipboardData.Builder())
    	.manipulatorId("clipboard")
    	.dataName("Clipboard Data")
    	.buildAndRegister(container);

    	DataRegistration.builder()
    	.dataClass(PlayerData.class)
    	.immutableClass(PlayerData.Immutable.class)
    	.builder(new PlayerData.Builder())
    	.manipulatorId("player")
    	.dataName("Player Data")
    	.buildAndRegister(container);

    	DataRegistration.builder()
    	.dataClass(FighterData.class)
    	.immutableClass(FighterData.Immutable.class)
    	.builder(new FighterData.Builder())
    	.manipulatorId("fighter")
    	.dataName("Fighter Data")
    	.buildAndRegister(container);
    }

	@Listener
	public void onInit(GameInitializationEvent event){
		CommandsRegister.register(this);
		EventsRegister.register(this);

		this.schematicsDir = new File(this.configDir, "structures");
		this.state = GameState.LOBBY;

		if(! this.schematicsDir.mkdirs())  this.logger().info("Config dir already exists");
	}

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Successfully running BRP!");
    }

    @Listener
    public void onServerStop(GameStoppingServerEvent event) {
    }

    public Game game() {
    	return this.game;
    }

    /*
    public Optional<Asset> getAsset(String assetName) {
    	return this.assets.getAsset(assetName);
    }
    */

    public PluginContainer plugin() {
    	return this.container;
    }

    /*
    public File configDir() {
    	return this.configDir;
    }
    */
    
    public File schematicDir() {
    	return this.schematicsDir;
    }
    
    public Vector3i spawn() {
    	return this.spawn;
    }
    
    public void setSpawn(Location<World> location) {
    	this.spawn = location.getPosition().toInt();
    }
    
    public GameState state() {
    	return this.state;
    }
    
    public void setState(GameState state_) {
    	this.state = state_;
    }
    
    public Collection<Player> fighters() {
    	Collection<Player> players = game.getServer().getOnlinePlayers();
    	Collection<Player> fighters = new HashSet<>();
    	
    	for(Player player : players) {
    		if(player.get(PlayerKeys.ROLE).isPresent() && player.get(PlayerKeys.ROLE).get().equals(PlayerRole.FIGHTER)) {
    			fighters.add(player);
    		}
    	}
    	
    	return fighters;
    }

    public Logger logger() {
    	return this.logger;
	}
}
