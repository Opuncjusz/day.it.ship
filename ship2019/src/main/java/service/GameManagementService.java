package service;

import java.util.List;

import model.Game;
import model.Player;

interface GameManagementService {

	void createNewGame();

	Game getCurrentGame();

	void addPlayer(Player player);

	void startGame();

	void endGame();

	long getCurrentGameTime();

	List<Game> getTopGames();

}
