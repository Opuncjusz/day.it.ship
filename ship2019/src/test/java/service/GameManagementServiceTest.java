package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import model.Game;
import model.GameStatus;

@RunWith(JUnitPlatform.class)
public class GameManagementServiceTest {

	private GameManagementService gameManagementService;
	
	@BeforeEach
	public void setUp() {
		gameManagementService = new GameManagementServiceImpl();
	}
	
	@Test
	public void newInstanceCreatesNewGameAutomatically() {
		gameManagementService = new GameManagementServiceImpl();
		Assertions.assertNotNull(gameManagementService.getCurrentGame());
	}
	
	@Test
	public void createNewGameTest() {
		Game currentGame = gameManagementService.getCurrentGame();
		
		gameManagementService.createNewGame();
		Game newGame = gameManagementService.getCurrentGame();
		
		Assertions.assertNotEquals(currentGame, newGame);
		Assertions.assertEquals(0, newGame.getPlayers().size());
		Assertions.assertEquals(GameStatus.PREPAIR, newGame.getGameStatus());

	}
	
}
