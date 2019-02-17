package integration;

import java.net.URISyntaxException;

import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import client.Connector;
import gui.admin.AdminGUITemplate;
import gui.admin.AdminGameInfo;
import main.Main;
import model.GameStatus;
import websocket.GameEndpoint;
import websocket.to.GameMessage;
import websocket.type.MessageType;

@RunWith(JUnitPlatform.class)
public class GameIntegrationTest {

	private GameEndpoint server;
	private String ip;
	private AdminGameInfo info;

	@Test
	public void test1() throws URISyntaxException, InterruptedException {
		int port = 8886;

		server = new GameEndpoint(port);
		server.start();

		ip = "ws://localhost:" + port;

		System.out.println("TEST GameServer started on port: " + server.getPort());

		Main.GAME_ENDPOINT = server;

		Thread.sleep(300);

		Connector client = new Connector();
		client.run(ip);

		Thread.sleep(600);

		// nowa gra
		GameMessage gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.NEW_GAME);
		gameMessage.setId("admin");
		client.send(gameMessage);

		// dodaj gracza A1
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("GRACZ_A1");
		gameMessage.setId("ID_A1");
		client.send(gameMessage);

		// dodaj gracza A2
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("GRACZ_A2");
		gameMessage.setId("ID_A2");
		client.send(gameMessage);

		// dodaj gracza A3
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("GRACZ_A3");
		gameMessage.setId("ID_A3");
		client.send(gameMessage);

		// dodaj gracza A4
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("GRACZ_A4");
		gameMessage.setId("ID_A4");
		client.send(gameMessage);

		// przygotuj info
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.GET_INFO);
		gameMessage.setId("admin");
		client.send(gameMessage);

		Thread.sleep(600);

		info = AdminGUITemplate.ADMIN_GAME_INFO;
		Assertions.assertEquals(GameStatus.PREPAIR.toString(), info.getGameStatus());
		Assertions.assertTrue(info.getPlayer1().startsWith("GRACZ_A"));
		Assertions.assertTrue(info.getPlayer2().startsWith("GRACZ_A"));
		Assertions.assertTrue(info.getPlayer3().startsWith("GRACZ_A"));
		Assertions.assertTrue(info.getPlayer4().startsWith("GRACZ_A"));

		// start
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.START_GAME);
		gameMessage.setId("admin");
		client.send(gameMessage);

		// wyslij predkosci
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.SPEED);
		gameMessage.setContent("200");
		gameMessage.setId("ID_A1");
		client.send(gameMessage);

		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.SPEED);
		gameMessage.setContent("100");
		gameMessage.setId("ID_A2");
		client.send(gameMessage);

		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.SPEED);
		gameMessage.setContent("-50");
		gameMessage.setId("ID_A3");
		client.send(gameMessage);

		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.SPEED);
		gameMessage.setContent("-200");
		gameMessage.setId("ID_A4");
		client.send(gameMessage);

		Thread.sleep(300);

		// przygotuj info
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.GET_INFO);
		gameMessage.setId("admin");
		client.send(gameMessage);

		Thread.sleep(600);

		info = AdminGUITemplate.ADMIN_GAME_INFO;
		Assertions.assertEquals(GameStatus.STARTED.toString(), info.getGameStatus());

		Assertions.assertTrue(info.getMotorPowerAAsNumber() != 0);
		Assertions.assertTrue(info.getMotorPowerBAsNumber() != 0);
		Assertions.assertTrue(info.getMotorPowerCAsNumber() != 0);
		Assertions.assertTrue(info.getMotorPowerDAsNumber() != 0);

		Assertions.assertNotEquals("0%", info.getMotorPowerA());
		Assertions.assertNotEquals("0%", info.getMotorPowerB());
		Assertions.assertNotEquals("0%", info.getMotorPowerC());
		Assertions.assertNotEquals("0%", info.getMotorPowerD());

		Thread.sleep(1750);

		// stop
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.END_GAME);
		gameMessage.setId("admin");
		client.send(gameMessage);

		Thread.sleep(300);

		// dotychczasowi gracze (w tym ten client) zostal rozlaczony
		WebsocketNotConnectedException ex1 = null;
		try {
			gameMessage = new GameMessage();
			gameMessage.setMessageType(MessageType.GET_INFO);
			gameMessage.setId("admin");
			client.send(gameMessage);
		} catch (WebsocketNotConnectedException e) {
			ex1 = e;
		}

		Assertions.assertNotNull(ex1);

		client = new Connector();
		client.run(ip);

		Thread.sleep(600);

		// przygotuj info
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.GET_INFO);
		gameMessage.setId("admin");
		client.send(gameMessage);

		Thread.sleep(300);

		info = AdminGUITemplate.ADMIN_GAME_INFO;
		Assertions.assertEquals(GameStatus.ENDED.toString(), info.getGameStatus());
		Assertions.assertTrue(info.getMotorOwnerA().startsWith("GRACZ_A"));
		Assertions.assertTrue(info.getMotorOwnerB().startsWith("GRACZ_A"));
		Assertions.assertTrue(info.getMotorOwnerC().startsWith("GRACZ_A"));
		Assertions.assertTrue(info.getMotorOwnerD().startsWith("GRACZ_A"));

		Assertions.assertNotEquals(info.getMotorOwnerA(), info.getMotorOwnerB());
		Assertions.assertNotEquals(info.getMotorOwnerA(), info.getMotorOwnerC());
		Assertions.assertNotEquals(info.getMotorOwnerA(), info.getMotorOwnerD());
		Assertions.assertNotEquals(info.getMotorOwnerB(), info.getMotorOwnerC());
		Assertions.assertNotEquals(info.getMotorOwnerB(), info.getMotorOwnerD());
		Assertions.assertNotEquals(info.getMotorOwnerC(), info.getMotorOwnerD());

		Assertions.assertEquals("0%", info.getMotorPowerA());
		Assertions.assertEquals("0%", info.getMotorPowerB());
		Assertions.assertEquals("0%", info.getMotorPowerC());
		Assertions.assertEquals("0%", info.getMotorPowerD());

		Assertions.assertEquals(0, info.getMotorPowerAAsNumber());
		Assertions.assertEquals(0, info.getMotorPowerBAsNumber());
		Assertions.assertEquals(0, info.getMotorPowerCAsNumber());
		Assertions.assertEquals(0, info.getMotorPowerDAsNumber());
	}

	@Test
	public void test2() throws URISyntaxException, InterruptedException {
		int port = 8885;

		server = new GameEndpoint(port);
		server.start();

		ip = "ws://localhost:" + port;

		System.out.println("TEST GameServer started on port: " + server.getPort());

		Main.GAME_ENDPOINT = server;

		Thread.sleep(300);

		Connector client = new Connector();
		client.run(ip);

		Connector client2 = new Connector();
		client2.run(ip);

		Thread.sleep(600);

		// nowa gra
		GameMessage gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.NEW_GAME);
		gameMessage.setId("admin");
		client.send(gameMessage);

		// dodaj gracza B1
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("GRACZ_B1");
		gameMessage.setId("ID_B1");
		client.send(gameMessage);

		// dodaj gracza B2
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("GRACZ_B2");
		gameMessage.setId("ID_B2");
		client.send(gameMessage);

		// dodaj gracza B3
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("GRACZ_B3");
		gameMessage.setId("ID_B3");
		client.send(gameMessage);

		// dodaj gracza B4
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("GRACZ_B4");
		gameMessage.setId("ID_B4");
		client.send(gameMessage);

		Thread.sleep(500);

		// dodaj gracza F1
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("GRACZ_F1");
		gameMessage.setId("ID_F1");
		client2.send(gameMessage);

		Thread.sleep(500);

		// przygotuj info
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.GET_INFO);
		gameMessage.setId("admin");
		client.send(gameMessage);

		Thread.sleep(600);

		info = AdminGUITemplate.ADMIN_GAME_INFO;
		Assertions.assertEquals(GameStatus.PREPAIR.toString(), info.getGameStatus());
		Assertions.assertNotEquals("GRACZ_F1", info.getPlayer1());
		Assertions.assertNotEquals("GRACZ_F1", info.getPlayer2());
		Assertions.assertNotEquals("GRACZ_F1", info.getPlayer3());
		Assertions.assertNotEquals("GRACZ_F1", info.getPlayer4());

		// dodatkowy gracz powinien zostac rozlaczony

		WebsocketNotConnectedException ex1 = null;
		try {
			gameMessage = new GameMessage();
			gameMessage.setMessageType(MessageType.GET_INFO);
			gameMessage.setId("admin");
			client2.send(gameMessage);
		} catch (WebsocketNotConnectedException e) {
			ex1 = e;
		}

		Thread.sleep(600);

		Assertions.assertNotNull(ex1);

		// dotychczasowy gracz nie powinien zostac rozlaczony

		WebsocketNotConnectedException ex2 = null;
		try {
			gameMessage = new GameMessage();
			gameMessage.setMessageType(MessageType.GET_INFO);
			gameMessage.setId("admin");
			client.send(gameMessage);
		} catch (WebsocketNotConnectedException e) {
			ex2 = e;
		}

		Thread.sleep(600);

		Assertions.assertNull(ex2);

	}

	@Test
	public void test3() throws URISyntaxException, InterruptedException {
		int port = 8884;

		server = new GameEndpoint(port);
		server.start();

		ip = "ws://localhost:" + port;

		System.out.println("TEST GameServer started on port: " + server.getPort());

		Main.GAME_ENDPOINT = server;

		Thread.sleep(300);

		Connector client = new Connector();
		client.run(ip);

		Connector client2 = new Connector();
		client2.run(ip);

		Thread.sleep(600);

		// nowa gra
		GameMessage gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.NEW_GAME);
		gameMessage.setId("admin");
		client.send(gameMessage);

		// dodaj gracza C1
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("GRACZ_C1");
		gameMessage.setId("ID_C1");
		client.send(gameMessage);

		Thread.sleep(500);

		// przygotuj info
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.GET_INFO);
		gameMessage.setId("admin");
		client.send(gameMessage);

		Thread.sleep(600);

		info = AdminGUITemplate.ADMIN_GAME_INFO;
		Assertions.assertEquals(GameStatus.PREPAIR.toString(), info.getGameStatus());
		Assertions.assertEquals("GRACZ_C1", info.getPlayer1());
		Assertions.assertEquals("NOT CONNECTED", info.getPlayer2());
		Assertions.assertEquals("NOT CONNECTED", info.getPlayer3());
		Assertions.assertEquals("NOT CONNECTED", info.getPlayer4());

		// start
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.START_GAME);
		gameMessage.setId("admin");
		client.send(gameMessage);

		Thread.sleep(300);

		// dodaj gracza F2
		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("GRACZ_F2");
		gameMessage.setId("ID_F2");
		client2.send(gameMessage);

		Thread.sleep(300);

		// dodatkowy gracz powinien zostac rozlaczony, poniewaz gra sie juz zaczela

		WebsocketNotConnectedException ex1 = null;

		try {
			// wyslij cokolwiek jako inny gracz
			gameMessage = new GameMessage();
			gameMessage.setMessageType(MessageType.GET_INFO);
			client2.send(gameMessage);
		} catch (WebsocketNotConnectedException e) {
			ex1 = e;
		}

		Thread.sleep(600);

		Assertions.assertNotNull(ex1);

		// dotychczasowy gracz nie powinien zostac rozlaczony

		WebsocketNotConnectedException ex2 = null;
		try {
			gameMessage = new GameMessage();
			gameMessage.setMessageType(MessageType.GET_INFO);
			gameMessage.setId("admin");
			client.send(gameMessage);
		} catch (WebsocketNotConnectedException e) {
			ex2 = e;
		}

		Thread.sleep(600);

		Assertions.assertNull(ex2);

	}

}
