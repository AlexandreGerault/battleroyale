package io.github.alexandregerault.battleroyale.main;

public enum PlayerRole {

	FIGHTER("fighter"),
	SPECTATOR("spectator");

	private String name;

	PlayerRole(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
