package io.github.alexandregerault.battleroyale.registrers;

import org.spongepowered.api.Sponge;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;
import io.github.alexandregerault.battleroyale.events.ClipboardEvents;
import io.github.alexandregerault.battleroyale.events.PlayerEvents;
import io.github.alexandregerault.battleroyale.events.TrackPlayerToolEvents;

public class EventsRegister {

	public static void register (BattleRoyale plugin) {
		Sponge.getEventManager().registerListeners(plugin, new ClipboardEvents(plugin));
		Sponge.getEventManager().registerListeners(plugin, new PlayerEvents(plugin));
		Sponge.getEventManager().registerListeners(plugin, new TrackPlayerToolEvents(plugin));
	}
}
