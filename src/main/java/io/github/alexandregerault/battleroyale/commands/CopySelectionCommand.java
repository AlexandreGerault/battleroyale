package io.github.alexandregerault.battleroyale.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import com.flowpowered.math.vector.Vector3i;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;
import io.github.alexandregerault.battleroyale.main.PlayerData;

public class CopySelectionCommand implements CommandExecutor {

	private BattleRoyale plugin;
	
	public CopySelectionCommand (BattleRoyale plugin_) {
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
		
		if (data.getPos1() == null || data.getPos2() == null) {
			pl.sendMessage(Text.of(TextColors.RED, "Error, you must have selected two corners with golden hoe"));
			return CommandResult.success();
		}
		
		Vector3i origin = new Vector3i(
				( data.getPos1().getX() + data.getPos2().getX() )/2,
				  data.getPos1().min(data.getPos2()).getY(),
				( data.getPos1().getZ() + data.getPos2().getZ() )/2
		);
		
		data.setClipboard(pl.getWorld().createArchetypeVolume(data.getPos1().min(data.getPos2()), data.getPos1().max(data.getPos2()), origin));
		pl.sendMessage(Text.of(TextColors.LIGHT_PURPLE, "Copying from " + data.getPos1().toString() + " to " + data.getPos2().toString() +"! Clipboard updated."));
		
		return CommandResult.success();
	}

}
