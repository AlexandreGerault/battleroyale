package io.github.alexandregerault.battleroyale.commands.structure;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;

public class ListStructureCommand implements CommandExecutor {
	
	private BattleRoyale plugin;
	
	public ListStructureCommand(BattleRoyale plugin_) {
		this.plugin = plugin_;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		File directory = plugin.schematicDir();
		File[] schematicFiles = directory.listFiles();

		if (Objects.requireNonNull(schematicFiles).length > 0) {
			plugin.plugin().getLogger().info(Arrays.toString(schematicFiles));
			src.sendMessage(Text.of("List of saved schematics:"));

			for(File file: schematicFiles) {
				if(file.getName().endsWith(".schematic")) {
					String result = file.getName().subSequence(0, file.getName().length() - ".schematic".length()).toString();
					src.sendMessage(Text.of("[•] " + result));
				}
			}

			return CommandResult.success();
		} else {
			throw new CommandException(Text.of("No structures files"));
		}
	}

}
