package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Player;
import websocket.converter.GameMessageConverter;
import websocket.to.GameMessage;

public class GameServiceImpl implements GameService {

	private static Logger LOGGER = LoggerFactory.getLogger(GameMessageConverter.class);

	private GameManagementService gameManagementService;

	public GameServiceImpl(GameManagementService gameManagementService) {
		this.gameManagementService = gameManagementService;
	}

	public void sendSpeed(GameMessage message) {
		Player player = gameManagementService.getCurrentGame().getPlayers().get(message.getId());

		LOGGER.info("Gracz {} wyslal speed {}", player.getName(), message.getContent());
	}

}
