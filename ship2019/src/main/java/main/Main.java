package main;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gui.main.ChooseGUI;
import websocket.GameEndpoint;

public class Main {

	public static GameEndpoint GAME_ENDPOINT;
	private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String args[]) {
		LOGGER.info("works");

		try {
			runGUI();
			runGameEndpoint();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		LOGGER.error("ending...");
	}

	private static void runGUI() {
		new ChooseGUI();
	}

	private static void runGameEndpoint() throws IOException, InterruptedException {
		int port = 8887;

		Main.GAME_ENDPOINT = new GameEndpoint(port);
		GAME_ENDPOINT.start();

		LOGGER.info("GameServer started on port: " + GAME_ENDPOINT.getPort());

		while (true) {
			Thread.sleep(600000);
			LOGGER.debug("i am still alive");
		}
	}

}
