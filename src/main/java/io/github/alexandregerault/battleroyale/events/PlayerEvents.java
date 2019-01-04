package io.github.alexandregerault.battleroyale.events;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;
import io.github.alexandregerault.battleroyale.main.GameStates;
import io.github.alexandregerault.battleroyale.main.PlayerData;
import io.github.alexandregerault.battleroyale.main.PlayerModes;
import io.github.alexandregerault.tasks.EndCountdownTask;

public class PlayerEvents {
	private final BattleRoyale plugin;

	public PlayerEvents(BattleRoyale plugin_) {
		this.plugin = plugin_;
	}

	@Listener
	public void onPlayerMove(MoveEntityEvent event) {
		if (event.getTargetEntity() instanceof Player && plugin.state().equals(GameStates.COUNTDOWN)) {
			event.setCancelled(true);
		}
	}

	@Listener
	public void onPlayerConnect(ClientConnectionEvent.Join event) {
		if (!plugin.state().equals(GameStates.LOBBY)) {
			Player pl = event.getTargetEntity();
			pl.kick(Text.of(TextColors.RED, "A game is already in progress, sorry"));
		} else {
			Player pl = (Player) event.getSource();
			PlayerData data = plugin.getPlayerData(pl);

			data.setRole(PlayerModes.FIGHTER);
		}
	}

	@Listener
	public void onPlayerKillPlayer(DestructEntityEvent.Death event) {
		if (plugin.state().equals(GameStates.BATTLE)) {
			Optional<EntityDamageSource> optDamageSource = event.getCause().first(EntityDamageSource.class);

			// Is the died entity a player?
			if (event.getTargetEntity() instanceof Player) {
				Player killed = (Player) event.getTargetEntity();
				PlayerData killedData = plugin.getPlayerData(killed);

				killedData.setRole(PlayerModes.SPECTATOR);

				if (optDamageSource.isPresent()) {
					EntityDamageSource damageSource = optDamageSource.get();
					Entity killerEntity = damageSource.getSource();

					if (killerEntity instanceof Player) {
						Player killer = (Player) killerEntity;

						killer.sendMessage(
								Text.of(TextColors.GOLD, "You have killed " + killed.getName() + "! Congratulations."));
						killed.kick(Text.of(TextColors.GOLD,
								"You have been killed by " + killer.getName() + ". You'll be better next time ;)"));

						PlayerData killerData = plugin.getPlayerData(killer);

						killerData.addKill();

						plugin.game().getServer().getBroadcastChannel()
								.send(Text.of(TextColors.GOLD, killed.getName() + " has been killed by " + killer.getName()
										+ ". Players remaining: " + plugin.fighters().size()));
					}
				}

				// if the last player just have been killed we end the game
				if (plugin.fighters().size() == 1) {
					Player winner = plugin.fighters().iterator().next();
					plugin.game().getServer().getBroadcastChannel().send(Text.of(TextColors.GOLD,
							"The game is over and the winner is " + winner.getName() + ". You can congratulate him!"));
					plugin.setState(GameStates.END);
					Task task = Task.builder().execute(new EndCountdownTask(plugin, 5)).interval(1, TimeUnit.SECONDS)
							.name("End Countdown Task").submit(plugin);
					plugin.plugin().getLogger().info(task.getName() + " task has been started");
				}
			}
		}
	}
}