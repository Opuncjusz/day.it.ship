package CarAdapter;

import jssc.SerialPort;
import jssc.SerialPortException;

public class COMPort {

	private static final String COM = "COM15";
	private static SerialPort serialPort = new SerialPort(COM);

	public static void write(String text) {
		try {
			if (!serialPort.isOpened()) {
				serialPort.openPort();
				serialPort.setDTR(false);
				serialPort.setRTS(false);
				serialPort.setParams(9600, 8, 1, 0);
			}
			serialPort.writeString(text);
		} catch (SerialPortException ex) {
			System.out.println(ex);
		}
	}
}