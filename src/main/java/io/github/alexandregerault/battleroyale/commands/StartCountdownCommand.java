package io.github.alexandregerault.battleroyale.commands;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.github.alexandregerault.battleroyale.data.FighterData;
import io.github.alexandregerault.battleroyale.data.FighterKeys;
import io.github.alexandregerault.battleroyale.main.GameState;
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
import io.github.alexandregerault.battleroyale.tasks.CountdownTask;

public class StartCountdownCommand implements CommandExecutor {

	private BattleRoyale plugin;
	
	public StartCountdownCommand(BattleRoyale plugin_) {
		this.plugin = plugin_;
	}
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		if (!(plugin.state().equals(GameState.LOBBY))) {
			throw new CommandException(Text.of("The game must be in lobby state to start the countdown"));
		}
		
		if (plugin.fighters().size() <= 1) {
			throw new CommandException(Text.of("It must be more than one fighter in order to start a game"));
		}
		
		if (plugin.spawn() == null) {
			src.sendMessage(Text.of(TextColors.RED, "No spawn specified: random spawn"));
			Random rand = new Random();
			
			int x = rand.nextInt();
			int z = rand.nextInt(); 
			
			plugin.spawn().add(x, 0, z);
			
		}
		
		int duration = args.requireOne("coutdown duration");
		
		Collection<Player> fighters = plugin.fighters();
		
		plugin.plugin().getLogger().info("Online fighters: " + fighters.size());
		
		spawnPlayers(fighters, 20);		

		plugin.setState(GameState.COUNTDOWN);
		
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
			
			int x = spawnX + (int) Math.round(Math.sin( (float) (j*360)/players.size()) * radius),
				y = 255,
				z = spawnZ + (int) Math.round(Math.cos( (float) (j*360)/players.size()) * radius);
			
			Location<World> loc = new Location<>(player.getWorld(), x, y, z);

			player.setLocation(loc);
			
			while(	loc.getBlock().getType().equals(BlockTypes.AIR) ||
					loc.getBlock().getType().equals(BlockTypes.LEAVES) ||
					loc.getBlock().getType().equals(BlockTypes.LEAVES2)) {
				loc = loc.add(0, -1, 0);

				if (loc.getY() <= 0) break;
			}
			
			player.getInventory().clear();
			player.setLocation(loc.add(0, 1, 0));

			player.offer(player.getOrCreate(FighterData.class).get());
			player.offer(Keys.GAME_MODE, GameModes.SURVIVAL);
			player.offer(Keys.HEALTH, (double) 20);
			player.offer(Keys.FOOD_LEVEL, 20);
			player.offer(FighterKeys.KILLS, 0);
			
			Location<World> underBlockLoc = player.getLocation().add(0, -0.5, 0);
			underBlockLoc.setBlockType(BlockTypes.STONE_SLAB);
		
			j++;
		}
	}

}
