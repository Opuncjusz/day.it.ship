package websocket;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Endpoint extends WebSocketServer {

	public Endpoint(int port) {
		super(new InetSocketAddress(port));
	}

	private static Logger LOGGER = LoggerFactory.getLogger(Endpoint.class);

	@Override
	public void onClose(WebSocket webSocket, int arg1, String arg2, boolean arg3) {
		LOGGER.warn("onClose");
		LOGGER.warn("{}, {}, {}", arg1, arg2, arg3);
	}

	@Override
	public void onError(WebSocket webSocket, Exception exception) {
		LOGGER.error(exception.getMessage());
		exception.printStackTrace();
	}

	@Override
	public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
		LOGGER.info("Connected with {} ({})", webSocket.getLocalSocketAddress(), webSocket.getRemoteSocketAddress());
	}

	@Override
	public void onStart() {
		LOGGER.info("WEBSOCKET READY");
	}

}
