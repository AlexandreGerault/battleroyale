package io.github.alexandregerault.battleroyale.commands.structure;

import java.io.File;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;
import io.github.alexandregerault.battleroyale.utils.SchematicFile;

public class RemoveStructureCommand implements CommandExecutor {
	
	private BattleRoyale plugin;
	
	public RemoveStructureCommand(BattleRoyale plugin_) {
		this.plugin = plugin_;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) {
		String schematicName = args.requireOne("structure name");
		
		if(SchematicFile.exists(schematicName, plugin)) {
			File file = SchematicFile.get(schematicName, plugin);
			if(file.delete()) {
				src.sendMessage(Text.of(TextColors.GREEN, "The structure file (" + schematicName + ".schematic) has been deleted."));
			} else {
				src.sendMessage(Text.of(TextColors.RED, "An error has occurred while deleting the file... Please check logs."));
			}
		} else {
			src.sendMessage(Text.of(TextColors.RED, "It seems the structure file doesn't exist."));
		}
		
		return CommandResult.success();
	}

}
