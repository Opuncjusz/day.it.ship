package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CarAdapter.CarAdapter;
import model.GameStatus;
import model.Player;
import websocket.to.GameMessage;

public class GameServiceImpl implements GameService {

	private static final int TIMER_MS = 400;

	private static Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);

	private GameManagementService gameManagementService;

	private CarAdapter carAdapter;

	public GameServiceImpl(GameManagementService gameManagementService) {
		this.gameManagementService = gameManagementService;
		this.carAdapter = new CarAdapter();
	}

	public void setSpeed(GameMessage message) {
		if (gameManagementService.getCurrentGame().getGameStatus() != GameStatus.STARTED) {
			LOGGER.warn("Gra zakonczona. Aktualizacja predkosci zostala odrzucona");
			return;
		}

		Player player = gameManagementService.getCurrentGame().getPlayers().get(message.getId());

		LOGGER.info("Gracz {} wyslal speed {} dla motora {}", player.getName(), message.getContent(),
				player.getMotorIds());

		for (String motor : player.getMotorIds()) {
			if ("A".equals(motor)) {
				carAdapter.getCarInformation().setSpeedOfMotorA(Integer.parseInt(message.getContent()));
			}

			if ("B".equals(motor)) {
				carAdapter.getCarInformation().setSpeedOfMotorB(Integer.parseInt(message.getContent()));
			}

			if ("C".equals(motor)) {
				carAdapter.getCarInformation().setSpeedOfMotorC(Integer.parseInt(message.getContent()));
			}

			if ("D".equals(motor)) {
				carAdapter.getCarInformation().setSpeedOfMotorD(Integer.parseInt(message.getContent()));
			}
		}
	}

	public void startConnectionWithCar() {
		if (gameManagementService.getCurrentGame().getGameStatus() != GameStatus.STARTED) {
			LOGGER.error("startConnectionWithCar - cos poszlo nie tak... obecny status {}",
					gameManagementService.getCurrentGame().getGameStatus());
		}

		Thread thread = new Thread(new Runnable() {
			public void run() {
				LOGGER.info("startConnectionWithCar...");

				while (gameManagementService.getCurrentGame().getGameStatus() == GameStatus.STARTED) {
					carAdapter.sendInfoToCar();

					try {
						Thread.sleep(TIMER_MS);
					} catch (InterruptedException e) {
						LOGGER.error(e.getMessage(), e);
					}
				}

				carAdapter.getCarInformation().setSpeedOfMotorA(0);
				carAdapter.getCarInformation().setSpeedOfMotorB(0);
				carAdapter.getCarInformation().setSpeedOfMotorC(0);
				carAdapter.getCarInformation().setSpeedOfMotorD(0);

				carAdapter.sendInfoToCar();

				LOGGER.info("startConnectionWithCar... STOP");
			}
		});

		thread.start();
	}

}
