package io.github.alexandregerault.battleroyale.data;

import com.google.common.reflect.TypeToken;

import io.github.alexandregerault.battleroyale.main.PlayerRole;
import javax.annotation.Generated;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.Value;

@Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2019-08-22T18:12:53.092Z")
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