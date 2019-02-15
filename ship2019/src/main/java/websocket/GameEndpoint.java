package websocket;

import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameEndpoint extends Endpoint {

	private static Logger LOGGER = LoggerFactory.getLogger(GameEndpoint.class);
	
    public GameEndpoint(int port) {
		super(port);
	}

	@Override
	public void onMessage(WebSocket websocket, String content) {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("FROM {}: {}", websocket.getRemoteSocketAddress(), content);
		}
		
		LOGGER.info("call service");
	}

}
