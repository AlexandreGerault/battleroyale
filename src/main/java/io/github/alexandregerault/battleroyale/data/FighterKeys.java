package io.github.alexandregerault.battleroyale.data;

import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.util.TypeTokens;

public class FighterKeys {

    private FighterKeys() {}

    public final static Key<Value<Integer>> KILLS;
    static {
        
        KILLS = Key.builder()
        		.type(TypeTokens.INTEGER_VALUE_TOKEN)
        		.id("kills")
        		.name("Kills")
        		.query(DataQuery.of('.',"fighter.kills"))
        		.build();
        
    }
}
