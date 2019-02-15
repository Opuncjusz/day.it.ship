package CarAdapter;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class COMPort {

	private static final String COM15 = "COM15";

	public static void write(String text) {
		SerialPort serialPort = new SerialPort(COM15);
		try {
			serialPort.openPort();
			serialPort.setParams(9600, 8, 1, 0);
			serialPort.writeString(text);
			serialPort.closePort();
		} catch (SerialPortException ex) {
			System.out.println(ex);
		}
	}
}