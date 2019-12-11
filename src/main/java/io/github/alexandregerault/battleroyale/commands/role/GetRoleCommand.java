package io.github.alexandregerault.battleroyale.commands.role;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.alexandregerault.battleroyale.data.PlayerKeys;

public class GetRoleCommand implements CommandExecutor {

	
	public GetRoleCommand() {}
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) {
		if (!(src instanceof Player)) {
			src.sendMessage(Text.of(TextColors.RED, "Player command only"));
			return CommandResult.success();
		}
		
		Player pl = (Player) src;

		pl.get(PlayerKeys.ROLE).ifPresent(role -> pl.sendMessage(Text.of(TextColors.GREEN, "Your role is: ", role.name())));

		return CommandResult.success();
	}

}
