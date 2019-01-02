package io.github.AlexandreGerault.BattleRoyal.registrers;

import org.spongepowered.api.Sponge;

import io.github.AlexandreGerault.BattleRoyal.BRP.BattleRoyale;
import io.github.AlexandreGerault.BattleRoyal.events.ClipboardEvents;

public class EventsRegister {

	public static void register (BattleRoyale plugin) {
		Sponge.getEventManager().registerListeners(plugin, new ClipboardEvents(plugin));
	}
}
