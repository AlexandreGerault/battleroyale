package io.github.alexandregerault.battleroyale.data;

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
import org.spongepowered.api.data.value.mutable.Value;

import java.util.Optional;

public class FighterData extends AbstractData<FighterData, FighterData.Immutable> {

    private Integer kills;

    FighterData () {
        this.kills = 0;

        registerGettersAndSetters();
    }

    FighterData (Integer kills) {
        this.kills = kills;

        registerGettersAndSetters();
    }

    @Override
    public void registerGettersAndSetters () {
        registerFieldGetter(FighterKeys.KILLS, () -> this.kills);

        registerFieldSetter(FighterKeys.KILLS, kills -> this.kills = kills);

        registerKeyValue(FighterKeys.KILLS, this::kills);
    }

    public Value<Integer> kills () {
        return Sponge.getRegistry().getValueFactory().createValue(FighterKeys.KILLS, kills);
    }

    @Override
    public Optional<FighterData> fill (DataHolder dataHolder, MergeFunction overlap) {
        Optional<FighterData> otherData_ = dataHolder.get(FighterData.class);

        if (otherData_.isPresent() ) {
            FighterData otherData = otherData_.get();
            FighterData finalData = overlap.merge(this, otherData);

            this.kills = finalData.kills;
        }

        return Optional.of(this);
    }

    @Override
    public Optional<FighterData> from (DataContainer container) {
        return from ( (DataView) container);
    }

    public Optional<FighterData> from (DataView view) {
        if ( view.contains(FighterKeys.KILLS.getQuery()) ) {
            this.kills = view.getObject(FighterKeys.KILLS.getQuery(), Integer.class).get();

            return Optional.of(this);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public FighterData copy () {
        return new FighterData(this.kills);
    }

    @Override
    public Immutable asImmutable () {
        return new Immutable(this.kills);
    }

    @Override
    public int getContentVersion () {
        return 1;
    }

    /**
     * Immutable class
     */
    public static class Immutable extends AbstractImmutableData<Immutable, FighterData> {
        private Integer kills;

        public Immutable () {
            registerGetters();
        }

        public Immutable (Integer kills) {
            this.kills = kills;

            registerGetters();
        }

        @Override
        public void registerGetters () {
            registerFieldGetter(FighterKeys.KILLS, () -> this.kills);

            registerKeyValue(FighterKeys.KILLS, this::kills);
        }

        public ImmutableValue<Integer> kills () {
            return Sponge.getRegistry().getValueFactory().createValue(FighterKeys.KILLS, kills).asImmutable();
        }

        @Override
        public FighterData asMutable () {
            return new FighterData(this.kills);
        }

        @Override
        public int getContentVersion () {
            return 1;
        }

        @Override
        public DataContainer toContainer() {
            return super.toContainer()
                    .set(FighterKeys.KILLS.getQuery(), this.kills);
        }

    }

    public static class Builder extends AbstractDataBuilder<FighterData> implements DataManipulatorBuilder<FighterData, Immutable> {
        public Builder () {
            super(FighterData.class, 1);
        }

        @Override
        public FighterData create () {
            return new FighterData();
        }

        @Override
        public Optional<FighterData> createFrom (DataHolder dataHolder) {
            return create().fill(dataHolder);
        }

        @Override
        protected Optional<FighterData> buildContent (DataView container) throws InvalidDataException {
            return create().from(container);
        }
    }
}
