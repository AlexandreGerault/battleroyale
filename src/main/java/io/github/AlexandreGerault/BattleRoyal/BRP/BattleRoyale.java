package io.github.AlexandreGerault.BattleRoyal.BRP;

import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.Game;
import org.spongepowered.api.asset.Asset;
import org.spongepowered.api.asset.AssetManager;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;

import com.google.common.collect.Maps;
// Imports for logger
import com.google.inject.Inject;

import io.github.AlexandreGerault.BattleRoyal.registrers.CommandsRegister;
import io.github.AlexandreGerault.BattleRoyal.registrers.EventsRegister;

import java.io.File;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;

@Plugin(id = "brp", name = "Battle Royal", version = "1.0", description = "Battle royal plugin")
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
    
    private final Map<UUID, PlayerData> player_data = Maps.newHashMap();    
    private File schematicsDir;
	

	@Listener
	public void onInit(GameInitializationEvent e){
		CommandsRegister.register(this);
		EventsRegister.register(this);
		
		this.schematicsDir = new File(this.configDir, "structures");
        this.schematicsDir.mkdirs();
	}
	
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Successfully running BRP!");
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
    
    public PlayerData getPlayerData(Player pl) {
        PlayerData data = this.player_data.get(pl.getUniqueId());
        
        if (data == null) {
            data = new PlayerData(pl.getUniqueId());
            this.player_data.put(pl.getUniqueId(), data);
        }
        
        return data;
    }
    
}
