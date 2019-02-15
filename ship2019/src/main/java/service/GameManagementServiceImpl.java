package service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import model.Game;
import model.GameStatus;
import model.Player;

public class GameManagementServiceImpl implements GameManagementService {

	private Game game;

	public GameManagementServiceImpl() {
		createNewGame();
	}

	public void createNewGame() {
		game = new Game();
	}

	public Game getCurrentGame() {
		return this.game;
	}

	public void addPlayer(Player player) {
		game.getPlayers().put(player.getId(), player);
	}

	public void startGame() {
		setPlayersToMotors();

		game.setGameStatus(GameStatus.STARTED);
	}

	public void endGame() {
		game.setGameStatus(GameStatus.ENDED);
	}

	private void setPlayersToMotors() {
		Map<String, Player> players = game.getPlayers();

		Iterator<Player> iterator = players.values().iterator();

		if (players.size() == 4) {
			iterator.next().setMotorIds(Arrays.asList("A"));
			iterator.next().setMotorIds(Arrays.asList("B"));
			iterator.next().setMotorIds(Arrays.asList("C"));
			iterator.next().setMotorIds(Arrays.asList("D"));
		}

		if (players.size() == 3) {
			iterator.next().setMotorIds(Arrays.asList("A", "B"));
			iterator.next().setMotorIds(Arrays.asList("C"));
			iterator.next().setMotorIds(Arrays.asList("D"));
		}

		if (players.size() == 2) {
			iterator.next().setMotorIds(Arrays.asList("A", "B"));
			iterator.next().setMotorIds(Arrays.asList("C", "D"));
		}

		if (players.size() == 1) {
			iterator.next().setMotorIds(Arrays.asList("A", "B", "C", "D"));
		}

		if (players.size() == 0) {
			throw new IllegalArgumentException("players.size = 0");
		}
	}

}
