package io.github.alexandregerault.battleroyale.main;

import com.google.inject.Inject;
import io.github.alexandregerault.battleroyale.data.PlayerKeys;
import io.github.alexandregerault.battleroyale.tasks.CountdownTask;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.world.World;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Match {

    @Inject
    private PluginContainer plugin;
    private GameState state;
    private String name;
    private List<Player> players;
    private World world;

    /**
     * Full parametrised constructor
     *
     * @param state_ The GameState the match should start at
     * @param name_  The match name
     */
    public Match(GameState state_, String name_) {
        this.state = state_;
        this.name = name_;
    }

    /**
     * GameState setter
     *
     * @param state_ The GameState that should be set
     * @return Match
     */
    public Match setState(GameState state_) {
        this.state = state_;
        return this;
    }

    /**
     * Add a player to the match
     *
     * @param player_ The player to be added
     * @return Match
     */
    public Match add(Player player_) {
        this.players.add(player_);
        return this;
    }

    /**
     * Remove a user from the players list
     *
     * @param player_ The player to be removed
     * @return Match
     */
    public Match remove(Player player_) {
        this.players.remove(player_);
        return this;
    }

    /**
     * Start a match
     *
     * @param countdown The countdown in seconds
     */
    public void start(int countdown) {
        this.state = GameState.COUNTDOWN;
        Task countdownTask = Task.builder().execute(new CountdownTask(this, countdown))
                .interval(1, TimeUnit.SECONDS)
                .name("Countdown Task").submit(plugin);
    }

    /**
     * Get the players in the match
     *
     * @return List<Player>
     */
    public List<Player> players() {
        return this.players;
    }

    /**
     * Get all the fighters
     *
     * @return The fighters
     */
    public List<Player> fighters() {
        return this.players
                .stream()
                .filter(player -> Objects.equals(player.getOrNull(PlayerKeys.ROLE), PlayerRole.FIGHTER))
                .collect(Collectors.toList());
    }


    /**
     * Get the world of this match
     *
     * @return World
     */
    public World world() {
        return this.world;
    }

    /**
     * The state of the Match
     *
     * @return GameState
     */
    public GameState state() {
        return this.state;
    }
}
