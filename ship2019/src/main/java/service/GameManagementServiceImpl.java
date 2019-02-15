package service;

import model.Game;
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
		getCurrentGame().getPlayers().put(player.getId(), player);
	}

}
