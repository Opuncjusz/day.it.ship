package CarAdapter;

import jssc.SerialPort;
import jssc.SerialPortException;

public class COMPort {

	private static final String COM = "COM15";
	private static SerialPort serialPort = new SerialPort(COM);

	public static void write(String text) {
		if(!serialPort.isOpened()) {
			try {
				serialPort.openPort();
			} catch (SerialPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			if (!serialPort.isOpened()) {
				serialPort.openPort();
//				serialPort.setDTR(false);
//				serialPort.setRTS(false);
//				serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_OUT | SerialPort.FLOWCONTROL_RTSCTS_IN);

				serialPort.setParams(9600, 8, 0, 0);
			}
			serialPort.writeString(text);
//			serialPort.closePort();
		} catch (SerialPortException ex) {
			System.out.println(ex);
		}
	}
}