package io.github.alexandregerault.battleroyale.commands;

import java.util.Optional;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import com.flowpowered.math.vector.Vector3i;

import io.github.alexandregerault.battleroyale.data.ClipboardKeys;
import org.spongepowered.api.world.schematic.Schematic;

public class CopySelectionCommand implements CommandExecutor {

	
	public CopySelectionCommand () {}
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (!(src instanceof Player)) {
			src.sendMessage(Text.of(TextColors.RED, "Player command only"));
			return CommandResult.success();
		}
		Player player = (Player) src;
		
		Optional<Vector3i> optPositionOne = player.get(ClipboardKeys.CORNER_ONE).get();
		Optional<Vector3i> optPositionTwo = player.get(ClipboardKeys.CORNER_TWO).get();
		
		if (!optPositionOne.isPresent() || !optPositionTwo.isPresent()) {
			player.sendMessage(Text.of(TextColors.RED, "Error, you must have selected two corners with golden hoe"));
			return CommandResult.success();
		}
		
		Vector3i positionOne = optPositionOne.get();
		Vector3i positionTwo = optPositionTwo.get();
		
		Vector3i origin = new Vector3i(
				( positionOne.getX() + positionTwo.getX() )/2,
				  positionOne.min(positionTwo).getY(),
				( positionOne.getZ() + positionTwo.getZ() )/2
		);

		Schematic volume = Schematic.builder().volume(player.getWorld().getExtentView(positionOne, positionTwo)).build();
		player.offer(ClipboardKeys.CLIPBOARD, Optional.of(volume));
		player.sendMessage(Text.of(TextColors.LIGHT_PURPLE, "Copying from " + positionOne.toString() + " to " + positionTwo.toString() +"! Clipboard updated."));
		
		return CommandResult.success();
	}

}
