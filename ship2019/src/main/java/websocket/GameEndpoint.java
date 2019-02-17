package websocket;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.GameMessageService;
import service.GameMessageServiceImpl;
import websocket.converter.GameMessageConverter;

public class GameEndpoint extends Endpoint {

	private static Logger LOGGER = LoggerFactory.getLogger(GameEndpoint.class);

	private GameMessageService gameMessageService = new GameMessageServiceImpl();

	public GameEndpoint(int port) {
		super(port);
	}

	@Override
	public void onMessage(WebSocket websocket, String content) {
		// if (LOGGER.isDebugEnabled()) {
		// LOGGER.debug("FROM {}: {}", websocket.getRemoteSocketAddress(), content);
		// }

		gameMessageService.dispatch(GameMessageConverter.getGameMessage(content), websocket);
	}

	public void disconnect(InetSocketAddress remoteSocketAddress) {
		for (WebSocket each : getConnections()) {
			if (each.getRemoteSocketAddress().equals(remoteSocketAddress)) {
				LOGGER.warn("Rozlaczam " + each.getRemoteSocketAddress());
				each.close();
				break;
			}
		}
	}

}
