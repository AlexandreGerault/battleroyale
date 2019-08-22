package io.github.alexandregerault.battleroyale.commands;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;
import io.github.alexandregerault.battleroyale.main.GameStates;
import io.github.alexandregerault.battleroyale.tasks.CountdownTask;

public class StartCountdownCommand implements CommandExecutor {

	private BattleRoyale plugin;
	
	public StartCountdownCommand(BattleRoyale plugin_) {
		this.plugin = plugin_;
	}
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		if (plugin.state().equals(GameStates.BATTLE)) {
			src.sendMessage(Text.of(TextColors.RED, "A game is already in progress"));
			return CommandResult.success();
		} else if (!(plugin.state().equals(GameStates.LOBBY))) {
			src.sendMessage(Text.of(TextColors.RED, "The game must be in lobby state to start the countdown"));
			return CommandResult.success();
		}
		
		if (plugin.fighters().size() <= 1) {
			src.sendMessage(Text.of(TextColors.RED, "It must be more than one fighter in order to start a game"));
			return CommandResult.success();
		}
		
		//Defining a random spawn if /brp mks has not been set
		if (plugin.spawn() == null) {
			src.sendMessage(Text.of(TextColors.RED, "No spawn specified: random spawn"));
			Random rand = new Random();
			
			int x = rand.nextInt();
			int z = rand.nextInt(); 
			
			plugin.spawn().add(x, 0, z);
			
		}
		
		int duration = args.<Integer>getOne("coutdown duration").get();
		
		Collection<Player> fighters = plugin.fighters();
		
		plugin.plugin().getLogger().info("Online fighters: " + fighters.size());
		
		spawnPlayers(fighters, 20);		

		plugin.setState(GameStates.COUNTDOWN);
		
		Task task = Task.builder().execute(new CountdownTask(plugin, duration))
		        .interval(1, TimeUnit.SECONDS)
		        .name("Countdown Task").submit(plugin);
		
		src.sendMessage(Text.of(TextColors.GREEN, task.getName() + " task has been started"));
				
		return CommandResult.success();
	}
	
	private void spawnPlayers(Collection<Player> players, int radius) {
		int spawnX = plugin.spawn().getX();
		int spawnZ = plugin.spawn().getZ();
		int j = 0;
		
		for(Player player : players) {
			player.sendMessage(Text.of("You are a fighter! Prepare yourself!"));
			
			//spawn coordinates
			int x = spawnX + (int) Math.round(Math.sin((j*360)/players.size()) * radius),
				y = 255,
				z = spawnZ + (int) Math.round(Math.cos((j*360)/players.size()) * radius);
			
			Location<World> loc = new Location<World>(player.getWorld(), x, y, z);

			player.setLocation(loc);
			
			while(	loc.getBlock().getType().equals(BlockTypes.AIR) ||
					loc.getBlock().getType().equals(BlockTypes.LEAVES) ||
					loc.getBlock().getType().equals(BlockTypes.LEAVES2)) {
				loc = loc.add(0, -1, 0);
				//for non infinite loop
				if (loc.getY() <= 0) break;
			}
			
			player.getInventory().clear();
			player.setLocation(loc.add(0, 1, 0));
			
			player.offer(Keys.GAME_MODE, GameModes.SURVIVAL);
			player.offer(Keys.HEALTH, (double) 20);
			
			Location<World> underBlockLoc = player.getLocation().add(0, -0.5, 0);
			underBlockLoc.setBlockType(BlockTypes.STONE_SLAB);
		
			j++;
		}
		
		return;
	}

}
