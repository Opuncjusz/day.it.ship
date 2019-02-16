package CarAdapter;

import org.junit.Test;

public class CarDrivingTest {

	CarAdapter carAdapter = new CarAdapter();

//	@Test
	public void drive() {
		CarInfoDs carInfoDs = new CarInfoDs();
		carAdapter.setCarInformation(carInfoDs);
		carInfoDs.setSpeedOfMotorA(50);
		carInfoDs.setSpeedOfMotorB(50);
		carInfoDs.setSpeedOfMotorC(50);
		carInfoDs.setSpeedOfMotorD(50);
		carAdapter.sendInfoToCar();
	}

	@Test
	public void driveTest() {
		CarInfoDs carInfoDs = new CarInfoDs();
		carAdapter.setCarInformation(carInfoDs);
		carInfoDs.setSpeedOfMotorA(50);
		carInfoDs.setSpeedOfMotorB(50);
		carInfoDs.setSpeedOfMotorC(50);
		carInfoDs.setSpeedOfMotorD(50);
		COMPort.write("+050+050");
		COMPort.write("+050+050");
	}
}
