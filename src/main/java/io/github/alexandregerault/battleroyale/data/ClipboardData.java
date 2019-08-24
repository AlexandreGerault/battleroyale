package io.github.alexandregerault.battleroyale.data;

import com.flowpowered.math.vector.Vector3i;
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
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.data.value.mutable.OptionalValue;
import org.spongepowered.api.world.extent.ArchetypeVolume;

import java.util.Optional;


public class ClipboardData extends AbstractData<ClipboardData, ClipboardData.Immutable> {

    private Vector3i cornerOne;
    private Vector3i cornerTwo;
    private ArchetypeVolume clipboard;

    ClipboardData () {}

    ClipboardData (Vector3i cornerOne, Vector3i cornerTwo, ArchetypeVolume clipboard) {
        this.cornerOne = cornerOne;
        this.cornerTwo = cornerTwo;
        this.clipboard = clipboard;

        registerGettersAndSetters();
    }

    @Override
    protected void registerGettersAndSetters () {
        registerFieldGetter(ClipboardKeys.CORNER_ONE, () -> Optional.ofNullable(this.cornerOne));
        registerFieldGetter(ClipboardKeys.CORNER_TWO, () -> Optional.ofNullable(this.cornerTwo));
        registerFieldGetter(ClipboardKeys.CLIPBOARD, () -> Optional.ofNullable(this.clipboard));

        registerFieldSetter(ClipboardKeys.CORNER_ONE, cornerOne -> this.cornerOne = cornerOne.get());
        registerFieldSetter(ClipboardKeys.CORNER_TWO, cornerTwo -> this.cornerTwo = cornerTwo.get());
        registerFieldSetter(ClipboardKeys.CLIPBOARD, clipboard -> this.clipboard = clipboard.get());

        registerKeyValue(ClipboardKeys.CORNER_ONE, this::cornerOne);
        registerKeyValue(ClipboardKeys.CORNER_TWO, this::cornerTwo);
        registerKeyValue(ClipboardKeys.CLIPBOARD, this::clipboard);
    }

    public OptionalValue<Vector3i> cornerOne () {
        return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CORNER_ONE, cornerOne);
    }
    public OptionalValue<Vector3i> cornerTwo () {
        return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CORNER_TWO, cornerTwo);
    }
    public OptionalValue<ArchetypeVolume> clipboard () {
        return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CLIPBOARD, clipboard);
    }

    @Override
    public Optional<ClipboardData> fill (DataHolder dataHolder, MergeFunction overlap) {
        Optional<ClipboardData> otherData_ = dataHolder.get(ClipboardData.class);

        if( otherData_.isPresent() ) {
            ClipboardData otherData = otherData_.get();
            ClipboardData finalData = overlap.merge(this, otherData);

            this.cornerOne = finalData.cornerOne;
            this.cornerTwo = finalData.cornerTwo;
            this.clipboard = finalData.clipboard;
        }

        return Optional.of(this);
    }

    @Override
    public Optional<ClipboardData> from (DataContainer container) {
        return from( (DataView) container);
    }

    public Optional<ClipboardData> from (DataView view) {
        if (view.contains(ClipboardKeys.CORNER_ONE.getQuery())
        && view.contains(ClipboardKeys.CORNER_TWO.getQuery())
        && view.contains(ClipboardKeys.CLIPBOARD.getQuery())) {
            this.cornerOne = view.getObject(ClipboardKeys.CORNER_ONE.getQuery(), Vector3i.class).get();
            this.cornerTwo = view.getObject(ClipboardKeys.CORNER_TWO.getQuery(), Vector3i.class).get();
            this.clipboard = view.getObject(ClipboardKeys.CLIPBOARD.getQuery(), ArchetypeVolume.class).get();

            return Optional.of(this);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public ClipboardData copy () {
        return new ClipboardData(this.cornerOne, this.cornerTwo, this.clipboard);
    }

    @Override
    public Immutable asImmutable () {
        return new Immutable(this.cornerOne, this.cornerTwo, this.clipboard);
    }

    @Override
    public int getContentVersion () {
        return 1;
    }

    @Override
    public DataContainer toContainer () {
        return super.toContainer()
                .set(ClipboardKeys.CORNER_ONE.getQuery(), this.cornerOne)
                .set(ClipboardKeys.CORNER_TWO.getQuery(), this.cornerTwo)
                .set(ClipboardKeys.CLIPBOARD.getQuery(), this.clipboard);
    }

    /**
     * Immutable class
     */
    public static class Immutable extends AbstractImmutableData<Immutable, ClipboardData> {

        private Vector3i cornerOne;
        private Vector3i cornerTwo;
        private ArchetypeVolume clipboard;

        public Immutable () {}

        public Immutable(Vector3i cornerOne, Vector3i cornerTwo, ArchetypeVolume clipboard) {
            this.cornerOne = cornerOne;
            this.cornerTwo = cornerTwo;
            this.clipboard = clipboard;

            registerGetters();
        }

        @Override
        public void registerGetters () {
            registerFieldGetter(ClipboardKeys.CORNER_ONE, () -> Optional.ofNullable(this.cornerOne));
            registerFieldGetter(ClipboardKeys.CORNER_TWO, () -> Optional.ofNullable(this.cornerTwo));
            registerFieldGetter(ClipboardKeys.CLIPBOARD, () -> Optional.ofNullable(this.clipboard));

            registerKeyValue(ClipboardKeys.CORNER_ONE, this::cornerOne);
            registerKeyValue(ClipboardKeys.CORNER_TWO, this::cornerTwo);
            registerKeyValue(ClipboardKeys.CLIPBOARD, this::clipboard);
        }

        public ImmutableValue<Optional<Vector3i>> cornerOne() {
            return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CORNER_ONE, cornerOne).asImmutable();
        }

        public ImmutableValue<Optional<Vector3i>> cornerTwo() {
            return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CORNER_TWO, cornerTwo).asImmutable();
        }

        public ImmutableValue<Optional<ArchetypeVolume>> clipboard() {
            return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CLIPBOARD, clipboard).asImmutable();
        }

        @Override
        public ClipboardData asMutable () {
            return new ClipboardData(this.cornerOne, this.cornerTwo, this.clipboard);
        }

        @Override
        public int getContentVersion () {
            return 1;
        }

        @Override
        public DataContainer toContainer () {
            return super.toContainer()
                    .set(ClipboardKeys.CORNER_ONE.getQuery(), this.cornerOne)
                    .set(ClipboardKeys.CORNER_TWO.getQuery(), this.cornerTwo)
                    .set(ClipboardKeys.CLIPBOARD.getQuery(), this.clipboard);
        }
    }

    public static class Builder extends AbstractDataBuilder<ClipboardData> implements DataManipulatorBuilder<ClipboardData, ClipboardData.Immutable> {

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
