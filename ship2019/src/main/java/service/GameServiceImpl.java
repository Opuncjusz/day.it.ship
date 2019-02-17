package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CarAdapter.CarAdapter;
import gui.admin.AdminGUITemplate;
import gui.admin.AdminGameInfo;
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

	public void sendAdminInfo() {
		AdminGameInfo info = new AdminGameInfo();

		// default
		info.setMotorOwnerA("");
		info.setMotorOwnerB("");
		info.setMotorOwnerC("");
		info.setMotorOwnerD("");

		// info
		int speedOfMotorA = carAdapter.getCarInformation().getSpeedOfMotorA();
		int speedOfMotorB = carAdapter.getCarInformation().getSpeedOfMotorB();
		int speedOfMotorC = carAdapter.getCarInformation().getSpeedOfMotorC();
		int speedOfMotorD = carAdapter.getCarInformation().getSpeedOfMotorD();

		info.setMotorPowerA(getMotorPowerAsText(speedOfMotorA));
		info.setMotorPowerB(getMotorPowerAsText(speedOfMotorB));
		info.setMotorPowerC(getMotorPowerAsText(speedOfMotorC));
		info.setMotorPowerD(getMotorPowerAsText(speedOfMotorD));

		info.setAllMotorsPower(
				Math.abs(speedOfMotorA) + Math.abs(speedOfMotorB) + Math.abs(speedOfMotorC) + Math.abs(speedOfMotorD));

		info.setMotorPowerAAsNumber(Math.abs(speedOfMotorA));
		info.setMotorPowerBAsNumber(Math.abs(speedOfMotorB));
		info.setMotorPowerCAsNumber(Math.abs(speedOfMotorC));
		info.setMotorPowerDAsNumber(Math.abs(speedOfMotorD));

		Map<String, Player> players = gameManagementService.getCurrentGame().getPlayers();

		for (String key : players.keySet()) {
			Player player = players.get(key);

			for (String motor : player.getMotorIds()) {
				if (motor.equals("A")) {
					info.setMotorOwnerA(player.getName());
				}

				if (motor.equals("B")) {
					info.setMotorOwnerB(player.getName());
				}

				if (motor.equals("C")) {
					info.setMotorOwnerC(player.getName());
				}

				if (motor.equals("D")) {
					info.setMotorOwnerD(player.getName());
				}
			}

			if (StringUtils.isEmpty(info.getPlayer1())) {
				info.setPlayer1(player.getName());
				continue;
			}

			if (StringUtils.isEmpty(info.getPlayer2())) {
				info.setPlayer2(player.getName());
				continue;
			}

			if (StringUtils.isEmpty(info.getPlayer3())) {
				info.setPlayer3(player.getName());
				continue;
			}

			if (StringUtils.isEmpty(info.getPlayer4())) {
				info.setPlayer4(player.getName());
				continue;
			}
		}

		if (StringUtils.isEmpty(info.getPlayer1())) {
			info.setPlayer1("NOT CONNECTED");
		}

		if (StringUtils.isEmpty(info.getPlayer2())) {
			info.setPlayer2("NOT CONNECTED");
		}

		if (StringUtils.isEmpty(info.getPlayer3())) {
			info.setPlayer3("NOT CONNECTED");
		}

		if (StringUtils.isEmpty(info.getPlayer4())) {
			info.setPlayer4("NOT CONNECTED");
		}

		info.setTime(getCurrentGameTime(gameManagementService.getCurrentGameTime()));

		AdminGUITemplate.ADMIN_GAME_INFO = info;
	}

	public String getCurrentGameTime(long time) {
		Date date = new Date(time);
		DateFormat formatter = new SimpleDateFormat("mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		return formatter.format(date);
	}

	public String getMotorPowerAsText(long power) {
		if (power > -1) {
			int value = (int) (((double) power / (double) CarAdapter.SPEED_LIMIT_POSITIVE) * 100);

			if (value == 0 && power > 0) {
				return "1%";
			}

			return value + "%";
		}

		if (power < 0) {
			int value = (int) (((double) power / (double) CarAdapter.SPEED_LIMIT_NEGATIVE) * -100);

			if (value == 0 && power < 0) {
				return "-1%";
			}

			return value + "%";
		}

		return "0%";
	}

}
