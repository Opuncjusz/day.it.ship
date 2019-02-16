package client;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import gui.admin.AdminGUITemplate;
import gui.admin.AdminGameInfo;
import websocket.converter.GameMessageConverter;
import websocket.to.GameMessage;

public class AdminConnector {

	private static Logger LOGGER = LoggerFactory.getLogger(AdminConnector.class);

	private WebSocketClient websocket;

	private String ip;

	public void run(String uri) throws URISyntaxException {
		ip = uri;

		createNew();

		// websocket.connect();
		// LOGGER.info("websocket.isOpen()? {}", websocket.isOpen());
	}

	private void createNew() throws URISyntaxException {
		websocket = new WebSocketClient(new URI(ip)) {

			@Override
			public void onMessage(String message) {
				AdminGameInfo adminGameInfo = new Gson().fromJson(message, AdminGameInfo.class);
				AdminGUITemplate.ADMIN_GAME_INFO = adminGameInfo;
			}

			@Override
			public void onOpen(ServerHandshake handshake) {
				LOGGER.info("You are connected to server: " + getURI() + "\n");
			}

			@Override
			public void onClose(int code, String reason, boolean remote) {
				try {
					createNew();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onError(Exception ex) {
				LOGGER.error("Exception occured ...\n" + ex + "\n");
			}
		};

		websocket.connect();
		LOGGER.info("websocket.isOpen()? {}", websocket.isOpen());

	}

	public void send(GameMessage message) {
		send(GameMessageConverter.fromGameMessage(message));
	}

	public void send(String content) {
		websocket.send(content);
	}
}
