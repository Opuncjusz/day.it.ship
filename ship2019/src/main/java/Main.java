import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import websocket.GameEndpoint;

public class Main {

	private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String args[]) {
		LOGGER.info("works");

		try {
			runGameEndpoint();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		LOGGER.error("ending...");
	}

	private static void runGameEndpoint() throws IOException, InterruptedException {
		int port = 8887; 

		GameEndpoint s = new GameEndpoint(port);
		s.start();
		
		LOGGER.info("GameServer started on port: " + s.getPort());

		while (true) {
			Thread.sleep(30000);
			LOGGER.debug("i am still alive");
		}
	}

}
