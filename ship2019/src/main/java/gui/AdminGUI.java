package gui;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JTextArea;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import websocket.converter.GameMessageConverter;
import websocket.to.GameMessage;

public class AdminGUI {

	private GUITemplate gui = GUITemplate.window;
	private WebSocketClient websocket;
	private JTextArea log;

	public AdminGUI() {
		GUITemplate.main(null);
	}

	public void run() throws URISyntaxException {
		websocket = new WebSocketClient(new URI(gui.window.getTxtAddress().getText())) {

			@Override
			public void onMessage(String message) {
				GUITemplate.window.getTextArea().append("got: " + message + "\n");
			}

			@Override
			public void onOpen(ServerHandshake handshake) {
				GUITemplate.window.getTextArea().append("You are connected to ChatServer: " + getURI() + "\n");
			}

			@Override
			public void onClose(int code, String reason, boolean remote) {
				GUITemplate.window.getTextArea().append(
						"You have been disconnected from: " + getURI() + "; Code: " + code + " " + reason + "\n");
			}

			@Override
			public void onError(Exception ex) {
				GUITemplate.window.getTextArea().append("Exception occured ...\n" + ex + "\n");
			}
		};

		websocket.connect();

	}

	public void send(GameMessage message) {
		send(GameMessageConverter.fromGameMessage(message));
	}

	public void send(String content) {
		GUITemplate.window.getTextArea().append("Sending...\n" + content + "\n");
		websocket.send(content);
	}

}
