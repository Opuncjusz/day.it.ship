package model;

import java.util.HashSet;
import java.util.Set;

public class Game {
	
	private GameStatus gameStatus;
	private Set<Player> players;
	
	public Game() {
		gameStatus = GameStatus.PREPAIR;
		players = new HashSet<Player>();
	}


	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

}
