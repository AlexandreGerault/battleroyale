package io.github.alexandregerault.battleroyale.commands.role;

import io.github.alexandregerault.battleroyale.main.PlayerRole;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.alexandregerault.battleroyale.data.PlayerKeys;

public class SetRoleCommand implements CommandExecutor {
	
	public SetRoleCommand () {}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) {
		PlayerRole role = args.requireOne("role");
		Player player = args.requireOne("player name");

		player.offer(PlayerKeys.ROLE, role);

		player.sendMessage(Text.of(TextColors.GREEN, player.getName(), "'s role is now: ", role.name()));

		return CommandResult.success();
	}

}
