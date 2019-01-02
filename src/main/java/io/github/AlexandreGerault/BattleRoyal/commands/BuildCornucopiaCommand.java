package io.github.AlexandreGerault.BattleRoyal.commands;

import java.io.FileNotFoundException;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.AlexandreGerault.BattleRoyal.BRP.BattleRoyale;
import io.github.AlexandreGerault.BattleRoyal.builders.Builder;

public class BuildCornucopiaCommand implements CommandExecutor {
	
	private BattleRoyale plugin;
	
	public BuildCornucopiaCommand(BattleRoyale br_) {
		this.plugin = br_;
	}

	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if(src instanceof Player) {
			src.sendMessage(Text.of(TextColors.GREEN, "You want to build the spawn here"));
			
			try {
				Builder.build(((Player) src).getLocation(), "cornucopia", this.plugin);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		} else {
			plugin.plugin().getLogger().info("Only players can use this command at the moment");
		}
		
		return CommandResult.success();
	}
	
	

}
