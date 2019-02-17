package service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gui.admin.AdminGUITemplate;
import main.Main;
import model.Game;
import model.GameStatus;
import model.Player;
import websocket.GameException;

public class GameManagementServiceImpl implements GameManagementService {

	private static Logger LOGGER = LoggerFactory.getLogger(GameManagementServiceImpl.class);

	private Game game;

	private List<Game> allGames;

	public GameManagementServiceImpl() {
		allGames = new ArrayList<Game>();
		createNewGame();
	}

	public void createNewGame() {
		game = new Game();
	}

	public Game getCurrentGame() {
		return this.game;
	}

	public void addPlayer(Player player) {
		if (game.getGameStatus() != GameStatus.PREPAIR) {
			LOGGER.error("Zapisy do gry juz sie zakonczyly!");
			throw new GameException();
		}

		if (game.getPlayers().size() >= 4) {
			LOGGER.error("Zapisanych jest juz komplet graczy!");
			throw new GameException();
		}

		game.getPlayers().put(player.getId(), player);

	}

	public void startGame() {
		setPlayersToMotors();
		game.setStartDate(LocalDateTime.now());
		game.setEndDate(null);

		game.setGameStatus(GameStatus.STARTED);
	}

	public void endGame() {
		game.setGameStatus(GameStatus.ENDED);
		game.setEndDate(LocalDateTime.now());

		if (game.getStartDate() == null) {
			LOGGER.error("startDate is null!");
		}

		if (game.getEndDate() == null) {
			LOGGER.error("endDate is null!");
		}

		game.setGameTime(Duration.between(game.getStartDate(), game.getEndDate()).toMillis());

		if (game.getPlayers().size() > 0) {
			allGames.add(game);
		} else {
			LOGGER.error("Gra odbyla sie bez graczy!");
		}

		disconnectWithCurrentClients();

		List<Game> topGames = getTopGames();
		AdminGUITemplate.TOP_GAMES = topGames;
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
			iterator.next().setMotorIds(Arrays.asList("A", "C"));
			iterator.next().setMotorIds(Arrays.asList("B", "D"));
		}

		if (players.size() == 1) {
			iterator.next().setMotorIds(Arrays.asList("A", "B", "C", "D"));
		}

		if (players.size() == 0) {
			LOGGER.error("players.size = 0");
			return;
		}

		LOGGER.info("#####################");

		if (players.size() == 4) {
			iterator = players.values().iterator();

			Player next = iterator.next();

			if (next.getId().equals("ID_1") || next.getId().equals("ID_2") || next.getId().equals("ID_3")
					|| next.getId().equals("ID_4")) {
				testMode();
			}

		}

		for (Player each : players.values()) {
			LOGGER.info("Player {} ({}) has motors: {}", each.getName(), each.getId(), each.getMotorIds());
		}

		LOGGER.info("#####################");
	}

	private void testMode() {
		Map<String, Player> players = game.getPlayers();

		Iterator<Player> iterator = players.values().iterator();

		setUptestMode(iterator.next());
		setUptestMode(iterator.next());
		setUptestMode(iterator.next());
		setUptestMode(iterator.next());

		LOGGER.info("#####################");

		LOGGER.info("TEST MODE");

		LOGGER.info("#####################");

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

	public long getCurrentGameTime() {
		if (game.getStartDate() == null) {
			return 0;
		}

		LocalDateTime currentTime = game.getEndDate();

		if (currentTime == null) {
			currentTime = LocalDateTime.now();
		}

		return Duration.between(game.getStartDate(), currentTime).toMillis();
	}

	public List<Game> getTopGames() {
		allGames.sort(new Comparator<Game>() {

			public int compare(Game o1, Game o2) {
				return new Long(o1.getGameTime()).compareTo(o2.getGameTime());
			}
		});

		List<Game> top = new ArrayList<Game>();

		for (Game each : allGames) {
			if (top.size() >= 10) {
				break;
			}

			top.add(each);
		}

		return allGames;
	}

}
