package model;

import java.util.HashMap;
import java.util.Map;

public class Game {

	private GameStatus gameStatus;
	private Map<String, Player> players;

	public Game() {
		gameStatus = GameStatus.PREPAIR;
		players = new HashMap<String, Player>();
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Map<String, Player> getPlayers() {
		return players;
	}

	public void setPlayers(Map<String, Player> players) {
		this.players = players;
	}

}
