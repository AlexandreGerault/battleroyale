package io.github.alexandregerault.battleroyale.tasks;

import java.util.function.Consumer;

import io.github.alexandregerault.battleroyale.main.GameState;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;

public class CountdownTask implements Consumer<Task> {

	private static BattleRoyale plugin;
	private int seconds;

	public CountdownTask(BattleRoyale plugin_, int seconds_){
		plugin = plugin_;
		seconds = seconds_;
	}
	
	public void accept(Task task){
		
		if(seconds > 0) {
			plugin.game().getServer().getBroadcastChannel().send(Text.of(TextColors.GREEN, "Game starts in " + seconds + " seconds!"));
			seconds--;
		} else if(seconds == 0) {
			plugin.setState(GameState.BATTLE);
			plugin.game().getServer().getBroadcastChannel().send(Text.of(TextColors.GREEN, "The game has started"));
			task.cancel();
		}
		
	}
}