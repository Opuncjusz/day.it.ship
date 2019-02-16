package websocket.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import websocket.to.GameMessage;

public class GameMessageConverter {

	private static Logger LOGGER = LoggerFactory.getLogger(GameMessageConverter.class);

	public static GameMessage getGameMessage(String content) {
		GameMessage fromJson = new Gson().fromJson(content, GameMessage.class);

		// if (fromJson.getMessageType() != MessageType.GET_INFO) {
		// LOGGER.debug(fromJson.toString());
		// }

		return fromJson;
	}

	public static String fromGameMessage(GameMessage content) {
		return new Gson().toJson(content);
	}

}
