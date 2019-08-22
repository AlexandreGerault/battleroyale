package io.github.alexandregerault.battleroyale.data;

import java.util.Optional;
import javax.annotation.Generated;
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

@Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2019-08-22T18:12:38.216Z")
public class FighterData extends AbstractData<FighterData, FighterData.Immutable> {

    private Integer kills;

    {
        registerGettersAndSetters();
    }

    FighterData() {
    }

    FighterData(Integer kills) {
        this.kills = kills;
    }

    @Override
    protected void registerGettersAndSetters() {
        registerFieldGetter(FighterKeys.KILLS, this::getKills);
        registerFieldSetter(FighterKeys.KILLS, this::setKills);
        registerKeyValue(FighterKeys.KILLS, this::kills);
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public Value<Integer> kills() {
        return Sponge.getRegistry().getValueFactory().createValue(FighterKeys.KILLS, kills);
    }

    @Override
    public Optional<FighterData> fill(DataHolder dataHolder, MergeFunction overlap) {
        dataHolder.get(FighterData.class).ifPresent(that -> {
                FighterData data = overlap.merge(this, that);
                this.kills = data.kills;
        });
        return Optional.of(this);
    }

    @Override
    public Optional<FighterData> from(DataContainer container) {
        return from((DataView) container);
    }

    public Optional<FighterData> from(DataView container) {
        container.getObject(FighterKeys.KILLS.getQuery(), Integer.class).ifPresent(v -> kills = v);
        return Optional.of(this);
    }

    @Override
    public FighterData copy() {
        return new FighterData(kills);
    }

    @Override
    public Immutable asImmutable() {
        return new Immutable(kills);
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    @Override
    public DataContainer toContainer() {
        return super.toContainer()
                .set(FighterKeys.KILLS.getQuery(), kills);
    }

    @Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2019-08-22T18:12:38.240Z")
    public static class Immutable extends AbstractImmutableData<Immutable, FighterData> {

        private int kills;
        {
            registerGetters();
        }

        Immutable() {
        }

        Immutable(Integer kills) {
            this.kills = kills;
        }

        @Override
        protected void registerGetters() {
            registerFieldGetter(FighterKeys.KILLS, this::getKills);
            registerKeyValue(FighterKeys.KILLS, this::kills);
        }

        public int getKills() {
            return kills;
        }

        public ImmutableValue<Integer> kills() {
            return Sponge.getRegistry().getValueFactory().createValue(FighterKeys.KILLS, kills).asImmutable();
        }

        @Override
        public FighterData asMutable() {
            return new FighterData(kills);
        }

        @Override
        public int getContentVersion() {
            return 1;
        }

        @Override
        public DataContainer toContainer() {
            return super.toContainer()
                    .set(FighterKeys.KILLS.getQuery(), kills);
        }

    }

    @Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2019-08-22T18:12:38.244Z")
    public static class Builder extends AbstractDataBuilder<FighterData> implements DataManipulatorBuilder<FighterData, Immutable> {

        public Builder() {
            super(FighterData.class, 1);
        }

        @Override
        public FighterData create() {
            return new FighterData();
        }

        @Override
        public Optional<FighterData> createFrom(DataHolder dataHolder) {
            return create().fill(dataHolder);
        }

        @Override
        protected Optional<FighterData> buildContent(DataView container) throws InvalidDataException {
            return create().from(container);
        }

    }
}
