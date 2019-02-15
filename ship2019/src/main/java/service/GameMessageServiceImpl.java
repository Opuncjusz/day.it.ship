package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Player;
import websocket.to.GameMessage;
import websocket.type.MessageType;

public class GameMessageServiceImpl implements GameMessageService {

	private static Logger LOGGER = LoggerFactory.getLogger(GameMessageServiceImpl.class);

	private GameManagementService gameManagementService;
	private GameService gameService;

	public GameMessageServiceImpl() {
		gameManagementService = new GameManagementServiceImpl();
		gameService = new GameServiceImpl(gameManagementService);
	}

	public void dispatch(GameMessage message) {
		MessageType messageType = message.getMessageType();

		if (messageType == MessageType.SPEED) {
			gameService.setSpeed(message);
			return;
		}

		if (messageType == MessageType.NEW_GAME) {
			gameManagementService.createNewGame();
			return;
		}

		if (messageType == MessageType.JOIN_TO_GAME) {
			Player player = new Player();
			player.setId(message.getId());
			player.setName(message.getContent());
			gameManagementService.addPlayer(player);
			return;
		}

		if (messageType == MessageType.START_GAME) {
			gameManagementService.startGame();
			gameService.startConnectionWithCar();
			return;
		}

		if (messageType == MessageType.END_GAME) {
			gameManagementService.endGame();
			return;
		}

		LOGGER.error("UNKNOWN MESSAGE TYPE! {}", messageType);
		throw new IllegalArgumentException("Unknown message type");
	}

}
