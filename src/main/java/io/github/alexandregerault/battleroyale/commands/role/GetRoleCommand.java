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

public class GetRoleCommand implements CommandExecutor {

	private BattleRoyale plugin;
	
	public GetRoleCommand(BattleRoyale plugin_) {
		this.plugin = plugin_;
	}
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (!(src instanceof Player)) {
			src.sendMessage(Text.of(TextColors.RED, "Player command only"));
			return CommandResult.success();
		}
		
		Player pl = (Player) src;
		PlayerData data = plugin.getPlayerData(pl);
		
		pl.sendMessage(Text.of(TextColors.GREEN, "Your role is: " + data.role().name()));
		return CommandResult.success();
	}

}
