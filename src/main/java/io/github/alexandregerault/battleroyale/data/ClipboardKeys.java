package io.github.alexandregerault.battleroyale.data;

import com.flowpowered.math.vector.Vector3i;
import com.google.common.reflect.TypeToken;
import javax.annotation.Generated;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.OptionalValue;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.world.extent.ArchetypeVolume;
import org.spongepowered.api.world.schematic.Schematic;

@Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2019-08-22T18:21:11.356Z")
public class ClipboardKeys {

    private ClipboardKeys() {}
    
    static void dummy() {}

    public final static Key<OptionalValue<Vector3i>> CORNER_ONE;
    public final static Key<OptionalValue<Vector3i>> CORNER_TWO;
    public final static Key<OptionalValue<Schematic>> CLIPBOARD;
    static {        
        CORNER_ONE = Key.builder()
        		.type(new TypeToken<OptionalValue<Vector3i>>() {
					private static final long serialVersionUID = 1L;})
        		.id("cornerone")
        		.name("Corner One")
        		.query(DataQuery.of('.', "clipboard.cornerone"))
        		.build();

        CORNER_TWO = Key.builder()
        		.type(new TypeToken<OptionalValue<Vector3i>>() {
					private static final long serialVersionUID = 1L;})
        		.id("cornertwo")
        		.name("Corner Two")
        		.query(DataQuery.of('.', "clipboard.cornertwo"))
        		.build();

        CLIPBOARD = Key.builder()
        		.type(new TypeToken<OptionalValue<Schematic>> () {
					private static final long serialVersionUID = 1L;})
        		.id("Clipboard")
        		.name("Clipboard")
        		.query(DataQuery.of('.',"clipboard.clipboard"))
        		.build();
        }
}
