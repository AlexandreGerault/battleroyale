package io.github.alexandregerault.battleroyale.data;

import com.google.common.reflect.TypeToken;

import io.github.alexandregerault.battleroyale.main.PlayerRole;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.Value;

public class PlayerKeys {

	private PlayerKeys() {}

	public final static Key<Value<PlayerRole>> ROLE;
	static {
		ROLE = Key.builder()
				.type(new TypeToken<Value<PlayerRole>>() {
					private static final long serialVersionUID = 1L;})
				.id("role")
				.name("Role")
				.query(DataQuery.of('.', "player.role"))
				.build();
	}
}