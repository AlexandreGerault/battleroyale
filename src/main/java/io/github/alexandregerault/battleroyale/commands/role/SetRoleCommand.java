package io.github.alexandregerault.battleroyale.commands.role;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.alexandregerault.battleroyale.data.PlayerKeys;
import io.github.alexandregerault.battleroyale.main.PlayerRoles;

public class SetRoleCommand implements CommandExecutor {
	
	public SetRoleCommand () {}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		String roleName = args.<String>getOne("role").get();
		Player p = args.<Player>getOne("player name").get();
		
		switch (roleName.toLowerCase()) {
			case "fighter":
				p.offer(PlayerKeys.ROLE, PlayerRoles.FIGHTER);
				break;
			case "spectator":
				p.offer(PlayerKeys.ROLE, PlayerRoles.SPECTATOR);
				break;
			case "streamer":
				p.offer(PlayerKeys.ROLE, PlayerRoles.STREAMER);
				break;
			default:
				src.sendMessage(Text.of(TextColors.RED, "Error, specified role doesn't exist!"));
				return CommandResult.success();
		}
		
		p.sendMessage(Text.of(TextColors.GREEN, p.getName(), "'s role is now: ", p.get(PlayerKeys.ROLE)));
		
		
		return CommandResult.success();
	}

}
