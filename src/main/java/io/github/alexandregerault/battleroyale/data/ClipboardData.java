package io.github.alexandregerault.battleroyale.data;

import com.flowpowered.math.vector.Vector3i;
import java.util.Optional;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableData;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractData;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.persistence.AbstractDataBuilder;
import org.spongepowered.api.data.persistence.InvalidDataException;
import org.spongepowered.api.data.value.immutable.ImmutableOptionalValue;
import org.spongepowered.api.data.value.mutable.OptionalValue;
import org.spongepowered.api.world.extent.ArchetypeVolume;

@Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2019-08-22T18:21:11.322Z")
public class ClipboardData extends AbstractData<ClipboardData, ClipboardData.Immutable> {

    private Vector3i cornerOne;
    private Vector3i cornerTwo;
    private ArchetypeVolume clipboard;

    {
        registerGettersAndSetters();
    }

    ClipboardData() {
    }

    ClipboardData(Vector3i cornerOne, Vector3i cornerTwo, ArchetypeVolume clipboard) {
        this.cornerOne = cornerOne;
        this.cornerTwo = cornerTwo;
        this.clipboard = clipboard;
    }

    @Override
    protected void registerGettersAndSetters() {
        registerFieldGetter(ClipboardKeys.CORNER_ONE, this::getCornerOne);
        registerFieldSetter(ClipboardKeys.CORNER_ONE, v -> setCornerOne(v.get()));
        registerKeyValue(ClipboardKeys.CORNER_ONE, this::cornerOne);
        registerFieldGetter(ClipboardKeys.CORNER_TWO, this::getCornerTwo);
        registerFieldSetter(ClipboardKeys.CORNER_TWO, v -> setCornerTwo(v.get()));
        registerKeyValue(ClipboardKeys.CORNER_TWO, this::cornerTwo);
        registerFieldGetter(ClipboardKeys.CLIPBOARD, this::getClipboard);
        registerFieldSetter(ClipboardKeys.CLIPBOARD, v -> setClipboard(v.get()));
        registerKeyValue(ClipboardKeys.CLIPBOARD, this::clipboard);
    }

    public Optional<Vector3i> getCornerOne() {
        return Optional.ofNullable(cornerOne);
    }

    public void setCornerOne(@Nullable Vector3i cornerOne) {
        this.cornerOne = cornerOne;
    }

    public OptionalValue<Vector3i> cornerOne() {
        return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CORNER_ONE, cornerOne);
    }

    public Optional<Vector3i> getCornerTwo() {
        return Optional.ofNullable(cornerTwo);
    }

    public void setCornerTwo(@Nullable Vector3i cornerTwo) {
        this.cornerTwo = cornerTwo;
    }

    public OptionalValue<Vector3i> cornerTwo() {
        return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CORNER_TWO, cornerTwo);
    }

    public Optional<ArchetypeVolume> getClipboard() {
        return Optional.ofNullable(clipboard);
    }

    public void setClipboard(@Nullable ArchetypeVolume clipboard) {
        this.clipboard = clipboard;
    }

    public OptionalValue<ArchetypeVolume> clipboard() {
        return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CLIPBOARD, clipboard);
    }

    @Override
    public Optional<ClipboardData> fill(DataHolder dataHolder, MergeFunction overlap) {
        dataHolder.get(ClipboardData.class).ifPresent(that -> {
                ClipboardData data = overlap.merge(this, that);
                this.cornerOne = data.cornerOne;
                this.cornerTwo = data.cornerTwo;
                this.clipboard = data.clipboard;
        });
        return Optional.of(this);
    }

    @Override
    public Optional<ClipboardData> from(DataContainer container) {
        return from((DataView) container);
    }

    public Optional<ClipboardData> from(DataView container) {
        container.getObject(ClipboardKeys.CORNER_ONE.getQuery(), Vector3i.class).ifPresent(v -> cornerOne = v);
        container.getObject(ClipboardKeys.CORNER_TWO.getQuery(), Vector3i.class).ifPresent(v -> cornerTwo = v);
        container.getObject(ClipboardKeys.CLIPBOARD.getQuery(), ArchetypeVolume.class).ifPresent(v -> clipboard = v);
        return Optional.of(this);
    }

    @Override
    public ClipboardData copy() {
        return new ClipboardData(cornerOne, cornerTwo, clipboard);
    }

    @Override
    public Immutable asImmutable() {
        return new Immutable(cornerOne, cornerTwo, clipboard);
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    @Override
    public DataContainer toContainer() {
        return super.toContainer()
                .set(ClipboardKeys.CORNER_ONE.getQuery(), cornerOne)
                .set(ClipboardKeys.CORNER_TWO.getQuery(), cornerTwo)
                .set(ClipboardKeys.CLIPBOARD.getQuery(), clipboard);
    }

    @Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2019-08-22T18:21:11.349Z")
    public static class Immutable extends AbstractImmutableData<Immutable, ClipboardData> {

        private Vector3i cornerOne;
        private Vector3i cornerTwo;
        private ArchetypeVolume clipboard;
        {
            registerGetters();
        }

        Immutable() {
        }

        Immutable(Vector3i cornerOne, Vector3i cornerTwo, ArchetypeVolume clipboard) {
            this.cornerOne = cornerOne;
            this.cornerTwo = cornerTwo;
            this.clipboard = clipboard;
        }

        @Override
        protected void registerGetters() {
            registerFieldGetter(ClipboardKeys.CORNER_ONE, this::getCornerOne);
            registerKeyValue(ClipboardKeys.CORNER_ONE, this::cornerOne);
            registerFieldGetter(ClipboardKeys.CORNER_TWO, this::getCornerTwo);
            registerKeyValue(ClipboardKeys.CORNER_TWO, this::cornerTwo);
            registerFieldGetter(ClipboardKeys.CLIPBOARD, this::getClipboard);
            registerKeyValue(ClipboardKeys.CLIPBOARD, this::clipboard);
        }

        public Optional<Vector3i> getCornerOne() {
            return Optional.ofNullable(cornerOne);
        }

        public ImmutableOptionalValue<Vector3i> cornerOne() {
            return (ImmutableOptionalValue<Vector3i>) Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CORNER_ONE, cornerOne).asImmutable();
        }

        public Optional<Vector3i> getCornerTwo() {
            return Optional.ofNullable(cornerTwo);
        }

        public ImmutableOptionalValue<Vector3i> cornerTwo() {
            return (ImmutableOptionalValue<Vector3i>) Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CORNER_TWO, cornerTwo).asImmutable();
        }

        public Optional<ArchetypeVolume> getClipboard() {
            return Optional.ofNullable(clipboard);
        }

        public ImmutableOptionalValue<ArchetypeVolume> clipboard() {
            return (ImmutableOptionalValue<ArchetypeVolume>) Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CLIPBOARD, clipboard).asImmutable();
        }

        @Override
        public ClipboardData asMutable() {
            return new ClipboardData(cornerOne, cornerTwo, clipboard);
        }

        @Override
        public int getContentVersion() {
            return 1;
        }

        @Override
        public DataContainer toContainer() {
            return super.toContainer()
                    .set(ClipboardKeys.CORNER_ONE.getQuery(), cornerOne)
                    .set(ClipboardKeys.CORNER_TWO.getQuery(), cornerTwo)
                    .set(ClipboardKeys.CLIPBOARD.getQuery(), clipboard);
        }

    }

    @Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2019-08-22T18:21:11.354Z")
    public static class Builder extends AbstractDataBuilder<ClipboardData> implements DataManipulatorBuilder<ClipboardData, Immutable> {

        public Builder() {
            super(ClipboardData.class, 1);
        }

        @Override
        public ClipboardData create() {
            return new ClipboardData();
        }

        @Override
        public Optional<ClipboardData> createFrom(DataHolder dataHolder) {
            return create().fill(dataHolder);
        }

        @Override
        protected Optional<ClipboardData> buildContent(DataView container) throws InvalidDataException {
            return create().from(container);
        }

    }
}
