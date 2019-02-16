package service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.Main;
import model.Game;
import model.GameStatus;
import model.Player;

public class GameManagementServiceImpl implements GameManagementService {

	private static Logger LOGGER = LoggerFactory.getLogger(GameManagementServiceImpl.class);

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
		disconnectWithCurrentClients();
	}

	private void disconnectWithCurrentClients() {
		Collection<WebSocket> connections = Main.GAME_ENDPOINT.getConnections();
		for (WebSocket each : connections) {
			LOGGER.warn("Rozlaczam {}", each.getRemoteSocketAddress());
			each.close();
		}
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

		LOGGER.info("#####################");

		if (players.size() == 4) {
			iterator = players.values().iterator();

			Player next = iterator.next();

			if (next.getId().equals("ID_1") || next.getId().equals("ID_2") || next.getId().equals("ID_3")
					|| next.getId().equals("ID_4")) {
				testMode();
			}

			setUptestMode(next);
			setUptestMode(iterator.next());
			setUptestMode(iterator.next());
			setUptestMode(iterator.next());

			LOGGER.info("TEST MODE");

			LOGGER.info("#####################");

		}

		for (Player each : players.values()) {
			LOGGER.info("Player {} ({}) has motors: {}", each.getName(), each.getId(), each.getMotorIds());
		}

		LOGGER.info("#####################");
	}

	private void testMode() {
		Map<String, Player> players = game.getPlayers();

		Iterator<Player> iterator = players.values().iterator();
	}

	private void setUptestMode(Player player) {
		if (player.getId().equals("ID_1")) {
			player.setMotorIds(Arrays.asList("A"));
		}
		if (player.getId().equals("ID_2")) {
			player.setMotorIds(Arrays.asList("B"));
		}
		if (player.getId().equals("ID_3")) {
			player.setMotorIds(Arrays.asList("C"));
		}
		if (player.getId().equals("ID_4")) {
			player.setMotorIds(Arrays.asList("D"));
		}
	}

}
