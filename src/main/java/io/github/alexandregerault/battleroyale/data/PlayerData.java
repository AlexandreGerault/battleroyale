package io.github.alexandregerault.battleroyale.data;

import io.github.alexandregerault.battleroyale.main.PlayerRole;
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

@Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2019-08-22T18:12:53.052Z")
public class PlayerData extends AbstractData<PlayerData, PlayerData.Immutable> {

    private PlayerRole role;

    {
        registerGettersAndSetters();
    }

    PlayerData() {
    }

    PlayerData(PlayerRole role) {
        this.role = role;
    }

    @Override
    protected void registerGettersAndSetters() {
        registerFieldGetter(PlayerKeys.ROLE, this::getRole);
        registerFieldSetter(PlayerKeys.ROLE, this::setRole);
        registerKeyValue(PlayerKeys.ROLE, this::role);
    }

    public PlayerRole getRole() {
        return role;
    }

    public void setRole(PlayerRole role) {
        this.role = role;
    }

    public Value<PlayerRole> role() {
        return Sponge.getRegistry().getValueFactory().createValue(PlayerKeys.ROLE, role);
    }

    @Override
    public Optional<PlayerData> fill(DataHolder dataHolder, MergeFunction overlap) {
        dataHolder.get(PlayerData.class).ifPresent(that -> {
                PlayerData data = overlap.merge(this, that);
                this.role = data.role;
        });
        return Optional.of(this);
    }

    @Override
    public Optional<PlayerData> from(DataContainer container) {
        return from((DataView) container);
    }

    public Optional<PlayerData> from(DataView container) {
        container.getObject(PlayerKeys.ROLE.getQuery(), PlayerRole.class).ifPresent(v -> role = v);
        return Optional.of(this);
    }

    @Override
    public PlayerData copy() {
        return new PlayerData(role);
    }

    @Override
    public Immutable asImmutable() {
        return new Immutable(role);
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    @Override
    public DataContainer toContainer() {
        return super.toContainer()
                .set(PlayerKeys.ROLE.getQuery(), role);
    }

    @Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2019-08-22T18:12:53.076Z")
    public static class Immutable extends AbstractImmutableData<Immutable, PlayerData> {

        private PlayerRole role;
        {
            registerGetters();
        }

        Immutable() {
        }

        Immutable(PlayerRole role) {
            this.role = role;
        }

        @Override
        protected void registerGetters() {
            registerFieldGetter(PlayerKeys.ROLE, this::getRole);
            registerKeyValue(PlayerKeys.ROLE, this::role);
        }

        public PlayerRole getRole() {
            return role;
        }

        public ImmutableValue<PlayerRole> role() {
            return Sponge.getRegistry().getValueFactory().createValue(PlayerKeys.ROLE, role).asImmutable();
        }

        @Override
        public PlayerData asMutable() {
            return new PlayerData(role);
        }

        @Override
        public int getContentVersion() {
            return 1;
        }

        @Override
        public DataContainer toContainer() {
            return super.toContainer()
                    .set(PlayerKeys.ROLE.getQuery(), role);
        }

    }

    @Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2019-08-22T18:12:53.081Z")
    public static class Builder extends AbstractDataBuilder<PlayerData> implements DataManipulatorBuilder<PlayerData, Immutable> {

        public Builder() {
            super(PlayerData.class, 1);
        }

        @Override
        public PlayerData create() {
            return new PlayerData();
        }

        @Override
        public Optional<PlayerData> createFrom(DataHolder dataHolder) {
            return create().fill(dataHolder);
        }

        @Override
        protected Optional<PlayerData> buildContent(DataView container) throws InvalidDataException {
            return create().from(container);
        }

    }
}
