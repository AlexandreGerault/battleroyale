package io.github.alexandregerault.battleroyale.data;

import javax.annotation.Generated;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.util.TypeTokens;

@Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2019-08-22T18:12:38.247Z")
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
