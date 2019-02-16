package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class GameServiceTest {

	private GameServiceImpl gameService;

	@BeforeEach
	public void setUp() {
		gameService = new GameServiceImpl(null);
	}

	@Test
	public void setMotorSpeedAsTextTest() {
		Assertions.assertEquals("0%", gameService.getMotorPowerAsText(0));
		Assertions.assertEquals("100%", gameService.getMotorPowerAsText(200));
		Assertions.assertEquals("-100%", gameService.getMotorPowerAsText(-200));
		Assertions.assertEquals("50%", gameService.getMotorPowerAsText(100));
		Assertions.assertEquals("-50%", gameService.getMotorPowerAsText(-100));
		Assertions.assertEquals("25%", gameService.getMotorPowerAsText(50));
		Assertions.assertEquals("-25%", gameService.getMotorPowerAsText(-50));
		Assertions.assertEquals("75%", gameService.getMotorPowerAsText(150));
		Assertions.assertEquals("-75%", gameService.getMotorPowerAsText(-150));

		Assertions.assertEquals("1%", gameService.getMotorPowerAsText(1));
		Assertions.assertEquals("-1%", gameService.getMotorPowerAsText(-1));

		Assertions.assertEquals("1%", gameService.getMotorPowerAsText(2));
		Assertions.assertEquals("-1%", gameService.getMotorPowerAsText(-2));

		Assertions.assertEquals("1%", gameService.getMotorPowerAsText(3));
		Assertions.assertEquals("-1%", gameService.getMotorPowerAsText(-3));

		Assertions.assertEquals("2%", gameService.getMotorPowerAsText(4));
		Assertions.assertEquals("-2%", gameService.getMotorPowerAsText(-4));

		Assertions.assertEquals("99%", gameService.getMotorPowerAsText(199));
		Assertions.assertEquals("-99%", gameService.getMotorPowerAsText(-199));

	}

	@Test
	public void getGameTimeTest() {
		Assertions.assertEquals("00:00", gameService.getCurrentGameTime(0));
		Assertions.assertEquals("00:00", gameService.getCurrentGameTime(999));
		Assertions.assertEquals("00:01", gameService.getCurrentGameTime(1000));
		Assertions.assertEquals("00:01", gameService.getCurrentGameTime(1999));
		Assertions.assertEquals("00:02", gameService.getCurrentGameTime(2000));
		Assertions.assertEquals("00:58", gameService.getCurrentGameTime(58999));
		Assertions.assertEquals("00:59", gameService.getCurrentGameTime(59000));
		Assertions.assertEquals("00:59", gameService.getCurrentGameTime(59999));
		Assertions.assertEquals("01:00", gameService.getCurrentGameTime(60000));
		Assertions.assertEquals("01:00", gameService.getCurrentGameTime(60999));
		Assertions.assertEquals("01:01", gameService.getCurrentGameTime(61999));
		Assertions.assertEquals("10:01", gameService.getCurrentGameTime(601999));

	}

}
