package service;

import model.Game;
import model.Player;

interface GameManagementService {

	void createNewGame();

	Game getCurrentGame();

	void addPlayer(Player player);

	void startGame();

	void endGame();
}
