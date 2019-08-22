package io.github.alexandregerault.battleroyale.main;

import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.Game;
import org.spongepowered.api.asset.Asset;
import org.spongepowered.api.asset.AssetManager;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;

import com.flowpowered.math.vector.Vector3i;
// Imports for logger
import com.google.inject.Inject;

import io.github.alexandregerault.battleroyale.data.PlayerKeys;
import io.github.alexandregerault.battleroyale.registrers.CommandsRegister;
import io.github.alexandregerault.battleroyale.registrers.EventsRegister;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.slf4j.Logger;

@Plugin(id = "brp", name = "Battle Royale", version = "1.0", description = "Battle royal plugin")
public class BattleRoyale {
	
	@Inject
	private Game game;
	@Inject
	private AssetManager assets;
	@Inject
	private PluginContainer plugin;
	@Inject
    private Logger logger;
    @Inject
    @ConfigDir(sharedRoot = false)
    private File configDir;
    
    private File schematicsDir;
    private Vector3i spawn;
    private GameState state;
	

	@Listener
	public void onInit(GameInitializationEvent e){
		CommandsRegister.register(this);
		EventsRegister.register(this);
		
		this.schematicsDir = new File(this.configDir, "structures");
        this.schematicsDir.mkdirs();
        this.state = GameStates.LOBBY;
	}
	
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Successfully running BRP!");
    }
    
    @Listener
    public void onServerStop(GameStoppingServerEvent event) {
    	game.getServer().getWorlds().forEach(world -> {
    		world.getPlayers().forEach(player -> {
    			player.kick();
    		});
    		game.getServer().unloadWorld(world);
    		game.getServer().deleteWorld(game.getServer().getWorldProperties(world.getName()).get());
    	});
    }
    
    public Game game() {
    	return this.game;
    }
    
    public Optional<Asset> getAsset(String assetName) {
    	return this.assets.getAsset(assetName);
    }
    
    public PluginContainer plugin() {
    	return this.plugin;
    }
    
    public File configDir() {
    	return this.configDir;
    }
    
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
    	Collection<Player> fighters = new HashSet<Player>();
    	
    	for(Player player : players) {
    		if(player.get(PlayerKeys.ROLE).get().equals(PlayerRoles.FIGHTER)) {
    			fighters.add(player);
    		}
    	}
    	
    	return fighters;
    }
}
