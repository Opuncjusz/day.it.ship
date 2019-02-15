package CarAdapter;

public class CarAdapter {

	private static final int SPEED_LIMIT_POSITIVE = 200;
	private static final int SPEED_LIMIT_NEGATIVE = -200;

	private CarInfoDs carInformation = new CarInfoDs();

	public void sendInfoToCar() {
		String outputText = createMessage();
		COMPort.write(outputText);
	}

	public String createMessage() {
		StringBuilder outputText = new StringBuilder();
		outputText.append(speedToInfoText(carInformation.getSpeedOfMotorA()));
		outputText.append(speedToInfoText(carInformation.getSpeedOfMotorB()));
		outputText.append(speedToInfoText(carInformation.getSpeedOfMotorC()));
		outputText.append(speedToInfoText(carInformation.getSpeedOfMotorD()));
		return outputText.toString();
	}

	public String speedToInfoText(int number) {
		int limitedSpeed = limitCarSpeed(number);
		String numberWithLeadingZeros = "00" + String.valueOf(limitedSpeed >= 0 ? limitedSpeed : limitedSpeed*-1);
		return (limitedSpeed >= 0 ? "+" : "-") + numberWithLeadingZeros.substring(numberWithLeadingZeros.length() - 3);
	}

	private int limitCarSpeed(int number) {
		if(number < SPEED_LIMIT_NEGATIVE) {
			return SPEED_LIMIT_NEGATIVE;
		} else if (number > SPEED_LIMIT_POSITIVE) {
			return SPEED_LIMIT_POSITIVE;
		}
		return number;
	}

	public CarInfoDs getCarInformation() {
		return carInformation;
	}

	public void setCarInformation(CarInfoDs carInformation) {
		this.carInformation = carInformation;
	}
}