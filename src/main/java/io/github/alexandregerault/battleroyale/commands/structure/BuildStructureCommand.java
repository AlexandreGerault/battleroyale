package io.github.alexandregerault.battleroyale.commands.structure;

import java.io.FileNotFoundException;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;
import io.github.alexandregerault.battleroyale.builders.Builder;

public class BuildStructureCommand implements CommandExecutor {
	
	private BattleRoyale plugin;

	public BuildStructureCommand(BattleRoyale plugin_) {
		this.plugin = plugin_;
	}
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if(src instanceof Player) {
			src.sendMessage(Text.of(TextColors.GREEN, "You want to build here"));
			
			try {
				Builder.build(((Player) src).getLocation(), args.<String>getOne("structure name").get(), this.plugin);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		} else {
			plugin.plugin().getLogger().info("Only players can use this command at the moment");
		}
		
		return CommandResult.success();
	}

}
