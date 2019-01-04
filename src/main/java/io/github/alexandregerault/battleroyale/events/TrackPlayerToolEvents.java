package io.github.alexandregerault.battleroyale.events;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.item.inventory.InteractItemEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;
import io.github.alexandregerault.battleroyale.main.PlayerModes;

public class TrackPlayerToolEvents {
	
	private final BattleRoyale plugin;
	
	public TrackPlayerToolEvents(BattleRoyale plugin_) {
		this.plugin = plugin_;
	}

	@Listener
	public void onPlayerTrack(InteractItemEvent.Secondary.MainHand event, @Root Player player) {
		ItemType item = event.getItemStack().getType();
		
		if (item.equals(ItemTypes.COMPASS)) {
			if (plugin.fighters().size() > 1) {
				Player nearestPlayer = getClosestPlayer(player);

				player.offer(Keys.TARGETED_LOCATION, nearestPlayer.getLocation().getPosition());

				player.sendMessage(Text.of(TextColors.GREEN, "The closest player is located at ", TextColors.GOLD,
						nearestPlayer.getLocation().getPosition(), TextColors.GREEN, ". It's ", TextColors.GOLD, nearestPlayer.getName(),
						TextColors.GREEN, "."));
			} else {
				player.sendMessage(Text.of(TextColors.RED, "You're the last fighter: there is nobody to track."));
			}
		}
		
		return;
	}

	private Player getClosestPlayer(final Player player) {
		Entity closest = null;
		double closestDistance = 0;

		for (Entity entity : player.getLocation().getExtent().getEntities()) {
			if (entity == player) {
				continue;
			}
			if (!(entity instanceof Player)) {
				continue;
			}
			
			Player iteratedPlayer = (Player) entity;
			
			if(!plugin.getPlayerData(iteratedPlayer).role().equals(PlayerModes.FIGHTER)) {
				continue;
			}

			double distance = entity.getLocation().getPosition().distance(player.getLocation().getPosition());
			if (closest == null || distance < closestDistance) {
				closest = entity;
				closestDistance = distance;
			}
		}

		Player nearestPlayer = (Player) closest;

		return nearestPlayer;
	}
}
