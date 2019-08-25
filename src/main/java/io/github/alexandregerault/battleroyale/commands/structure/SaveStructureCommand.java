package io.github.alexandregerault.battleroyale.commands.structure;

import java.util.Optional;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.extent.ArchetypeVolume;

import com.flowpowered.math.vector.Vector3i;

import io.github.alexandregerault.battleroyale.data.ClipboardKeys;
import io.github.alexandregerault.battleroyale.main.BattleRoyale;
import io.github.alexandregerault.battleroyale.utils.SchematicFile;

public class SaveStructureCommand implements CommandExecutor {
	
	private final BattleRoyale plugin;
	
	public SaveStructureCommand(BattleRoyale plugin_) {
		this.plugin = plugin_;
	}

	@Override
	public CommandResult execute(CommandSource source, CommandContext args) throws CommandException {
		
		if (!(source instanceof Player)) {
			source.sendMessage(Text.of(TextColors.RED, "The sender must be a player"));
			return CommandResult.success();
		}
		
		Player player = (Player) source;
		String name = args.requireOne("structure name");

		if (!player.get(ClipboardKeys.CORNER_ONE).isPresent() || !player.get(ClipboardKeys.CORNER_TWO).isPresent()) {
			throw new CommandException(Text.of("Error, you must have selected two corners with golden hoe"));
		}

		Optional<Vector3i> pos1 = player.get(ClipboardKeys.CORNER_ONE).get();
		Optional<Vector3i> pos2 = player.get(ClipboardKeys.CORNER_TWO).get();

		if (!pos1.isPresent() || !pos2.isPresent()) {
			throw new CommandException(Text.of("Error, you must have selected two corners with golden hoe"));
		}
		
		Vector3i min = pos1.get().min(pos2.get());
        Vector3i max = pos1.get().max(pos2.get());
        Vector3i origin = new Vector3i((min.getX() + max.getX())/2, Math.min(min.getY(), max.getY()), (min.getZ() + max.getZ())/2);

        ArchetypeVolume volume = player.getWorld().createArchetypeVolume(min, max, origin);
        
    	if(SchematicFile.save(volume, player.getName(), name, plugin.schematicDir().getPath() + "/" + name + ".schematic")) {
        	player.sendMessage(Text.of(TextColors.GREEN, "Saved structure to " + plugin.schematicDir().getPath() + "/" + name + ".schematic"));
        } else {
        	player.sendMessage(Text.of(TextColors.RED, "Error saving structure, check logs."));
        }

		return CommandResult.success();
	}

}
