package io.github.AlexandreGerault.BattleRoyal.registrers;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import io.github.AlexandreGerault.BattleRoyal.BRP.BattleRoyale;
import io.github.AlexandreGerault.BattleRoyal.commands.BuildCornucopiaCommand;
import io.github.AlexandreGerault.BattleRoyal.commands.structure.BuildStructureCommand;
import io.github.AlexandreGerault.BattleRoyal.commands.structure.ListStructureCommand;
import io.github.AlexandreGerault.BattleRoyal.commands.structure.RemoveStructureCommand;
import io.github.AlexandreGerault.BattleRoyal.commands.structure.SaveStructureCommand;

public class CommandsRegister {
	
	public static void register(BattleRoyale plugin) {				
		CommandSpec makeSpawnCommandSpec = CommandSpec.builder()
			    .description(Text.of("Command generating the spawn structure"))
			    .permission("brp.command.makespawn")
			    .executor(new BuildCornucopiaCommand(plugin))
			    .build();
		
		CommandSpec saveStructureCommand = CommandSpec.builder()
			    .description(Text.of("Save a selected area to structure file of the given name"))
			    .permission("brp.command.structure.save")
			    .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("structure name"))))
			    .executor(new SaveStructureCommand(plugin))
			    .build();
		
		CommandSpec removeStructureCommand = CommandSpec.builder()
			    .description(Text.of("Remove a saved structure from storage device"))
			    .permission("brp.command.structure.remove")
			    .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("structure name"))))
			    .executor(new RemoveStructureCommand(plugin))
			    .build();
		
		CommandSpec buildStructureCommand = CommandSpec.builder()
				.description(Text.of("Build a saved structure from it's name"))
				.permission("brp.command.structure.build")
				.arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("structure name"))))
				.executor(new BuildStructureCommand(plugin))
				.build();
		
		CommandSpec listStructureCommand = CommandSpec.builder()
				.description(Text.of("List all saved structures"))
				.permission("brp.command.structure.list")
				.executor(new ListStructureCommand(plugin))
				.build();
		
		CommandSpec structureCommands = CommandSpec.builder()
			    .permission("brp.command.structure")
			    .description(Text.of("Root of structure commands"))
			    .child(saveStructureCommand, "save", "sv")
			    .child(buildStructureCommand, "build", "bd")
			    .child(listStructureCommand, "list", "ls")
			    .child(removeStructureCommand, "remove", "rm")
			    .build();

		Sponge.getCommandManager().register(plugin, structureCommands, "structure", "st");
		Sponge.getCommandManager().register(plugin, makeSpawnCommandSpec, "make-spawn", "makespawn", "mks");
	}
}
