package io.github.alexandregerault.battleroyale.commands.role;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;
import io.github.alexandregerault.battleroyale.main.PlayerData;
import io.github.alexandregerault.battleroyale.main.PlayerModes;

public class SetRoleCommand implements CommandExecutor {
	
	private BattleRoyale plugin;
	
	public SetRoleCommand (BattleRoyale plugin_) {
		this.plugin = plugin_;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		String roleName = args.<String>getOne("role").get();
		Player p = args.<Player>getOne("player name").get();
		PlayerData data = plugin.getPlayerData(p);
		
		switch (roleName.toLowerCase()) {
			case "fighter":
				data.setRole(PlayerModes.FIGHTER);
				break;
			case "spectator":
				data.setRole(PlayerModes.SPECTATOR);
				break;
			case "streamer":
				data.setRole(PlayerModes.STREAMER);
				break;
			default:
				src.sendMessage(Text.of(TextColors.RED, "Error, specified role doesn't exist!"));
				break;
		}
		
		
		return CommandResult.success();
	}

}
