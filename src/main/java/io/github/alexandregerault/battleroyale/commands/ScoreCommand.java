package io.github.alexandregerault.battleroyale.commands;

import io.github.alexandregerault.battleroyale.data.FighterKeys;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class ScoreCommand implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if ( !(src instanceof Player) && !args.getOne(Text.of("player name")).isPresent() ) {
            throw new CommandException(Text.of("When the command is ran via the console, you must specify a player name"));
        }

        Player target = args.<Player>getOne("player name").isPresent() ? args.<Player>getOne("player name").get() : (Player) src;

        target.get(FighterKeys.KILLS).ifPresent(kills -> src.sendMessage(Text.of(target.getName(), " has killed ", kills, " other players.")));

        return CommandResult.success();
    }
}
