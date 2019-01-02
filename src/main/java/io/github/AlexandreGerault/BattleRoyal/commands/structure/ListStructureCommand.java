package io.github.AlexandreGerault.BattleRoyal.commands.structure;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

import io.github.AlexandreGerault.BattleRoyal.BRP.BattleRoyale;

public class ListStructureCommand implements CommandExecutor {
	
	private BattleRoyale plugin;
	
	public ListStructureCommand(BattleRoyale plugin_) {
		this.plugin = plugin_;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		File directory = plugin.schematicDir();
		File[] schematicFiles = directory.listFiles();
		
		List<String> results = new ArrayList<String>();
		
		src.sendMessage(Text.of("List of saved schematics:"));
		for(File file : schematicFiles) {
			if(file.getName().endsWith(".schematic")) {
				String result = file.getName().subSequence(0, file.getName().length() - ".schematic".length()).toString();
				results.add(result);
				src.sendMessage(Text.of("[•] " + result));
			}
		}
		// TODO Auto-generated method stub
		return CommandResult.success();
	}

}
