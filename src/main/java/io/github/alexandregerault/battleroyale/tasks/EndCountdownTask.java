package io.github.alexandregerault.battleroyale.tasks;

import java.util.function.Consumer;

import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;

public class EndCountdownTask implements Consumer<Task> {

	private static BattleRoyale plugin;
	private int seconds;

	public EndCountdownTask(BattleRoyale plugin_, int seconds_){
		plugin = plugin_;
		seconds = seconds_;
	}
	
	public void accept(Task task){
		
		if(seconds > 0) {
			plugin.game().getServer().getBroadcastChannel().send(Text.of(TextColors.GREEN, seconds + " seconds left before the server stop!"));
			seconds--;
			return;
		} else if(seconds == 0) {
			plugin.game().getServer().getBroadcastChannel().send(Text.of(TextColors.GREEN, "The server is about to stop!"));
			task.cancel();
			plugin.game().getServer().shutdown(Text.of("The game is ended, wait for an admin to recreate a game"));
		}
		
	}
}