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
import org.spongepowered.api.data.persistence.DataTranslators;
import org.spongepowered.api.data.persistence.InvalidDataException;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.data.value.mutable.OptionalValue;
import org.spongepowered.api.world.schematic.Schematic;

import javax.annotation.Nonnull;
import java.util.Optional;


public class ClipboardData extends AbstractData<ClipboardData, ClipboardData.Immutable> {

    private Vector3i cornerOne;
    private Vector3i cornerTwo;
    private Schematic clipboard;

    private ClipboardData() {}

    private ClipboardData(Vector3i cornerOne, Vector3i cornerTwo, Schematic clipboard) {
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

        registerFieldSetter(ClipboardKeys.CORNER_ONE, cornerOne -> cornerOne.ifPresent(vector3i -> this.cornerOne = vector3i));
        registerFieldSetter(ClipboardKeys.CORNER_TWO, cornerTwo -> cornerTwo.ifPresent(vector3i -> this.cornerTwo = vector3i));
        registerFieldSetter(ClipboardKeys.CLIPBOARD, clipboard -> clipboard.ifPresent(schematic -> this.clipboard = schematic));

        registerKeyValue(ClipboardKeys.CORNER_ONE, this::cornerOne);
        registerKeyValue(ClipboardKeys.CORNER_TWO, this::cornerTwo);
        registerKeyValue(ClipboardKeys.CLIPBOARD, this::clipboard);
    }

    private OptionalValue<Vector3i> cornerOne() {
        return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CORNER_ONE, cornerOne);
    }
    private OptionalValue<Vector3i> cornerTwo() {
        return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CORNER_TWO, cornerTwo);
    }
    private OptionalValue<Schematic> clipboard() {
        return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CLIPBOARD, clipboard);
    }

    @Override @Nonnull
    public Optional<ClipboardData> fill (DataHolder dataHolder,@Nonnull MergeFunction overlap) {
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

    @Override @Nonnull
    public Optional<ClipboardData> from (@Nonnull DataContainer container) {
        return from( (DataView) container);
    }

    private Optional<ClipboardData> from(DataView view) {
        if (view.contains(ClipboardKeys.CORNER_ONE.getQuery())
        && view.contains(ClipboardKeys.CORNER_TWO.getQuery())
        && view.contains(ClipboardKeys.CLIPBOARD.getQuery())) {
            view.getObject(ClipboardKeys.CORNER_ONE.getQuery(), Vector3i.class).ifPresent(object -> this.cornerOne = object);
            view.getObject(ClipboardKeys.CORNER_TWO.getQuery(), Vector3i.class).ifPresent(object -> this.cornerTwo = object);
            view.getObject(ClipboardKeys.CLIPBOARD.getQuery(), Schematic.class).ifPresent(object -> this.clipboard = object);

            return Optional.of(this);
        } else {
            return Optional.empty();
        }
    }

    @Override @Nonnull
    public ClipboardData copy () {
        return new ClipboardData(this.cornerOne, this.cornerTwo, this.clipboard);
    }

    @Override @Nonnull
    public Immutable asImmutable () {
        return new Immutable(this.cornerOne, this.cornerTwo, this.clipboard);
    }

    @Override
    public int getContentVersion () {
        return 1;
    }

    @Override @Nonnull
    public DataContainer toContainer () {
        DataContainer container = super.toContainer();
        container = container
                .set(ClipboardKeys.CORNER_ONE.getQuery(), this.cornerOne)
                .set(ClipboardKeys.CORNER_TWO.getQuery(), this.cornerTwo);

        if (this.clipboard != null) {
            container = container.set(ClipboardKeys.CLIPBOARD.getQuery(), DataTranslators.SCHEMATIC.translate(this.clipboard));
        }

        return container;
    }

    /**
     * Immutable class
     */
    public static class Immutable extends AbstractImmutableData<Immutable, ClipboardData> {

        private Vector3i cornerOne;
        private Vector3i cornerTwo;
        private Schematic clipboard;

        Immutable(Vector3i cornerOne, Vector3i cornerTwo, Schematic clipboard) {
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

        ImmutableValue<Optional<Vector3i>> cornerOne() {
            return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CORNER_ONE, cornerOne).asImmutable();
        }

        ImmutableValue<Optional<Vector3i>> cornerTwo() {
            return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CORNER_TWO, cornerTwo).asImmutable();
        }

        ImmutableValue<Optional<Schematic>> clipboard() {
            return Sponge.getRegistry().getValueFactory().createOptionalValue(ClipboardKeys.CLIPBOARD, clipboard).asImmutable();
        }

        @Override @Nonnull
        public ClipboardData asMutable () {
            return new ClipboardData(this.cornerOne, this.cornerTwo, this.clipboard);
        }

        @Override
        public int getContentVersion () {
            return 1;
        }

        @Override @Nonnull
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

        @Override @Nonnull
        public ClipboardData create() {
            return new ClipboardData();
        }

        @Override @Nonnull
        public Optional<ClipboardData> createFrom(@Nonnull DataHolder dataHolder) {
            return create().fill(dataHolder);
        }

        @Override @Nonnull
        protected Optional<ClipboardData> buildContent(@Nonnull DataView container) throws InvalidDataException {
            return create().from(container);
        }
    }
}
