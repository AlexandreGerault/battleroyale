package io.github.alexandregerault.battleroyale.commands;


import io.github.alexandregerault.battleroyale.utils.ClipboardHelper;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import io.github.alexandregerault.battleroyale.data.ClipboardKeys;

public class CopySelectionCommand implements CommandExecutor {

	
	public CopySelectionCommand () {}
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (!(src instanceof Player)) {
			throw new CommandException(Text.of("Only players can use this command"));
		}

		Player player = (Player) src;
		
		if (player.get(ClipboardKeys.CORNER_ONE).isPresent() && player.get(ClipboardKeys.CORNER_TWO).isPresent()) {
            ClipboardHelper.updateClipboard(player);
		}

		return CommandResult.success();
	}

}
