package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import model.Game;
import model.GameStatus;
import model.Player;

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

	@Test
	public void gameTimeTest() throws InterruptedException {
		gameManagementService.addPlayer(new Player());
		gameManagementService.startGame();
		Thread.sleep(200);
		long currentGameTime = gameManagementService.getCurrentGameTime();
		System.out.println("time: " + currentGameTime);
		Assertions.assertTrue(currentGameTime > 180);
		Assertions.assertTrue(currentGameTime < 700);
	}

}
