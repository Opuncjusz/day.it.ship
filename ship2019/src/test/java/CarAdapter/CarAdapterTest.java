package CarAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarAdapterTest {

	private CarAdapter carAdapter = new CarAdapter();

	@Test
	public void mapSpeed1() {
		simpleTest(0, 0, 0, 0, "+000+000+000+000");
	}

	@Test
	public void mapSpeed2() {
		simpleTest(100, 100, 100, 100, "+100+100+100+100");
	}

	@Test
	public void mapSpeed3() {
		simpleTest(1, 2, 3, 4, "+001+002+003+004");
	}

	@Test
	public void mapSpeed4() {
		simpleTest(-1, -1, -20, -20, "-001-001-020-020");
	}

	@Test
	public void mapSpeed5() {
		simpleTest(-123, +123, -123, +123, "-123+123-123+123");
	}

	@Test
	public void mapSpeed6() {
		simpleTest(99999, 204, -14243, 0, "+200+200-200+000");
	}

	private void simpleTest(int a, int b, int c, int d, String expected) {
		carAdapter.setCarInformation(createCarInfo(a, b, c, d));
		assertEquals(expected, carAdapter.createMessage());
	}

	private CarInfoDs createCarInfo(int a, int b, int c, int d) {
		CarInfoDs carInfoDs = new CarInfoDs();
		carInfoDs.setSpeedOfMotorA(a);
		carInfoDs.setSpeedOfMotorB(b);
		carInfoDs.setSpeedOfMotorC(c);
		carInfoDs.setSpeedOfMotorD(d);
		return carInfoDs;
	}

}
