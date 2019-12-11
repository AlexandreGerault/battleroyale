package io.github.alexandregerault.battleroyale.main;

public enum GameState {

	LOBBY("lobby"),
	COUNTDOWN("countdown"),
	BATTLE("battle"),
	END("end");

	private String name;

	GameState(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
