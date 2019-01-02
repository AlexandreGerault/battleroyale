package io.github.AlexandreGerault.BattleRoyal.BRP;

import java.util.UUID;

import org.spongepowered.api.world.extent.ArchetypeVolume;

import com.flowpowered.math.vector.Vector3i;

public class PlayerData {
	private final UUID uid;
	private Vector3i pos1;
	private Vector3i pos2;
	private ArchetypeVolume clipboard;
	
	public PlayerData(UUID uid) {
        this.uid = uid;
    }

    public UUID getUid() {
        return this.uid;
    }

    public Vector3i getPos1() {
        return this.pos1;
    }

    public void setPos1(Vector3i pos) {
        this.pos1 = pos;
    }

    public Vector3i getPos2() {
        return this.pos2;
    }

    public void setPos2(Vector3i pos) {
        this.pos2 = pos;
    }

    public ArchetypeVolume getClipboard() {
        return this.clipboard;
    }

    public void setClipboard(ArchetypeVolume volume) {
        this.clipboard = volume;
    }
}
