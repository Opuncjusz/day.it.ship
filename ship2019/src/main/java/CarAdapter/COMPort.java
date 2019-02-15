package CarAdapter;

import jssc.SerialPort;
import jssc.SerialPortException;

public class COMPort {

	private static final String COM15 = "COM15";

	private static SerialPort SERIAL_PORT = new SerialPort(COM15);

	public static void write(String text) {
		if (!SERIAL_PORT.isOpened()) {
			openPort();
		}

		try {
			SERIAL_PORT.writeString(text);
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

	private static void openPort() {
		try {
			SERIAL_PORT.openPort();
			SERIAL_PORT.setParams(9600, 8, 1, 0);
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

}