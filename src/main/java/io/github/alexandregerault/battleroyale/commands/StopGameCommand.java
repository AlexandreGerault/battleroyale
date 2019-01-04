package io.github.alexandregerault.battleroyale.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;
import io.github.alexandregerault.battleroyale.main.GameStates;

public class StopGameCommand implements CommandExecutor {

	private BattleRoyale plugin;
	
	public StopGameCommand(BattleRoyale plugin_) {
		this.plugin = plugin_;
	}
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		plugin.setState(GameStates.LOBBY);
		plugin.game().getServer().getBroadcastChannel().send(Text.of(TextColors.GREEN, "The game has been stopped"));
		return CommandResult.success();
	}

}
