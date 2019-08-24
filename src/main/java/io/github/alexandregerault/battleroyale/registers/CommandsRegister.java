package io.github.alexandregerault.battleroyale.registers;

import io.github.alexandregerault.battleroyale.commands.*;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;
import io.github.alexandregerault.battleroyale.commands.role.GetRoleCommand;
import io.github.alexandregerault.battleroyale.commands.role.SetRoleCommand;
import io.github.alexandregerault.battleroyale.commands.structure.BuildStructureCommand;
import io.github.alexandregerault.battleroyale.commands.structure.ListStructureCommand;
import io.github.alexandregerault.battleroyale.commands.structure.RemoveStructureCommand;
import io.github.alexandregerault.battleroyale.commands.structure.SaveStructureCommand;

public class CommandsRegister {
	
	public static void register(BattleRoyale plugin) {
		CommandSpec scoreCommand = CommandSpec.builder()
				.description(Text.of("Display the score of a player"))
				.permission("brp.command.score")
				.arguments(
						GenericArguments.optional(GenericArguments.player(Text.of("player name")))
				)
				.executor(new ScoreCommand())
				.build();

		CommandSpec getRoleCommand = CommandSpec.builder()
				.description(Text.of("Give your current role"))
				.permission("brp.command.role.get")
				.executor(new GetRoleCommand())
				.build();
		
		CommandSpec setRoleCommand = CommandSpec.builder()
				.description(Text.of("Set a specified role to a player"))
				.permission("brp.command.role.set")
				.arguments(
						GenericArguments.onlyOne(GenericArguments.player(Text.of("player name"))),
						GenericArguments.onlyOne(GenericArguments.string(Text.of("role"))))
				.executor(new SetRoleCommand())
				.build();
		
		CommandSpec makeSpawnCommand = CommandSpec.builder()
			    .description(Text.of("Command generating the spawn structure"))
			    .permission("brp.command.makespawn")
			    .executor(new BuildCornucopiaCommand(plugin))
			    .build();

		CommandSpec copySelectionCommand = CommandSpec.builder()
				.description(Text.of("Command copying the selected period to the clipboard"))
				.permission("brp.command.copy")
				.executor(new CopySelectionCommand())
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

		CommandSpec startCoundtownCommand = CommandSpec.builder()
				.permission("brp.command.start")
				.description(Text.of("Start the countdown and spawn the players around the cornucopia"))
				.arguments(GenericArguments.onlyOne(GenericArguments.integer(Text.of("coutdown duration"))))
				.executor(new StartCountdownCommand(plugin))
				.build();
		
		CommandSpec stopCoundtownCommand = CommandSpec.builder()
				.permission("brp.command.stop")
				.description(Text.of("Manually stop the current game"))
				.executor(new StopGameCommand(plugin))
				.build();

		CommandSpec structureCommands = CommandSpec.builder()
			    .permission("brp.command.structure")
			    .description(Text.of("Root of structure commands"))
			    .child(saveStructureCommand, "save", "sv")
			    .child(buildStructureCommand, "build", "bd")
			    .child(listStructureCommand, "list", "ls")
			    .child(removeStructureCommand, "remove", "rm")
			    .build();
		
		CommandSpec roleCommands = CommandSpec.builder()
				.permission("brp.command.role")
				.description(Text.of("Commands about your role"))
				.child(getRoleCommand, "get")
				.child(setRoleCommand, "set")
				.build();

		CommandSpec rootCommand = CommandSpec.builder()
				.permission("brp")
				.description(Text.of("Battle Royale commands"))
				.child(structureCommands, "structure", "str")
				.child(startCoundtownCommand, "start")
				.child(stopCoundtownCommand, "stop")
				.child(copySelectionCommand, "copy", "cp")
				.child(makeSpawnCommand, "make-spawn", "makespawn", "mks")
				.child(roleCommands, "role")
				.child(scoreCommand, "score")
				.build();

		Sponge.getCommandManager().register(plugin, rootCommand, "brp");
	}
}
