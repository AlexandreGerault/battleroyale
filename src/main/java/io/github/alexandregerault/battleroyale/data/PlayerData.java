package io.github.alexandregerault.battleroyale.data;


import io.github.alexandregerault.battleroyale.main.PlayerRole;
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

public class PlayerData extends AbstractData<PlayerData, PlayerData.Immutable> {

    private PlayerRole role;

    public PlayerData () {
        this.role = PlayerRole.SPECTATOR;

        registerGettersAndSetters();
    }

    public PlayerData (PlayerRole role) {
        this.role = role;

        registerGettersAndSetters();
    }

    @Override
    public void registerGettersAndSetters() {
        registerFieldGetter(PlayerKeys.ROLE, () -> this.role);

        registerFieldSetter(PlayerKeys.ROLE, role -> this.role = role);

        registerKeyValue(PlayerKeys.ROLE, this::role);
    }

    public Value<PlayerRole> role () {
        return Sponge.getRegistry().getValueFactory().createValue(PlayerKeys.ROLE, role);
    }

    @Override
    public Optional<PlayerData> fill (DataHolder dataHolder, MergeFunction overlap) {
        Optional<PlayerData> otherData_ = dataHolder.get(PlayerData.class);

        if (otherData_.isPresent()) {
            PlayerData otherData = otherData_.get();
            PlayerData finalData = overlap.merge(this, otherData);

            this.role = finalData.role;
        }

        return Optional.of(this);
    }

    @Override
    public Optional<PlayerData> from (DataContainer container) {
        return from ( (DataView) container);
    }

    public Optional<PlayerData> from (DataView view) {
        if (view.contains(PlayerKeys.ROLE.getQuery())) {
            view.getObject(PlayerKeys.ROLE.getQuery(), String.class).ifPresent(name -> this.role = PlayerRole.valueOf(name));

            return Optional.of(this);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public PlayerData copy () {
        return new PlayerData(this.role);
    }

    @Override
    public Immutable asImmutable () {
        return new Immutable(this.role);
    }

    @Override
    public int getContentVersion () {
        return 1;
    }

    @Override
    public DataContainer toContainer () {
        return super.toContainer()
                .set(PlayerKeys.ROLE.getQuery(), this.role.name());
    }

    /**
     * Immutable class
     */
    public static class Immutable extends AbstractImmutableData<Immutable, PlayerData> {
        private PlayerRole role;

        public Immutable () {
            this.role = PlayerRole.SPECTATOR;

            registerGetters();
        }

        public Immutable (PlayerRole role) {
            this.role = role;

            registerGetters();
        }

        @Override
        public void registerGetters () {
            registerFieldGetter(PlayerKeys.ROLE, () -> this.role);

            registerKeyValue(PlayerKeys.ROLE, this::role);
        }

        public ImmutableValue<PlayerRole> role () {
            return Sponge.getRegistry().getValueFactory().createValue(PlayerKeys.ROLE, role).asImmutable();
        }

        @Override
        public PlayerData asMutable () {
            return new PlayerData (this.role);
        }

        @Override
        public int getContentVersion () {
            return 1;
        }

        @Override
        public DataContainer toContainer () {
            return super.toContainer()
                    .set(PlayerKeys.ROLE.getQuery(), this.role);
        }
    }

    /**
     * Builder
     */
    public static class Builder extends AbstractDataBuilder<PlayerData> implements DataManipulatorBuilder<PlayerData, Immutable> {

        public Builder () {
            super(PlayerData.class, 1);
        }

        @Override
        public PlayerData create() {
            return new PlayerData();
        }

        @Override
        public Optional createFrom(DataHolder dataHolder) {
            return create().fill(dataHolder);
        }

        @Override
        protected Optional buildContent(DataView container) throws InvalidDataException {
            return create().from(container);
        }
    }
}
