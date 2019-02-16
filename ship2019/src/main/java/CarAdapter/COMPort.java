package CarAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jssc.SerialPort;
import jssc.SerialPortException;

public class COMPort {
	private static Logger LOGGER = LoggerFactory.getLogger(COMPort.class);

	private static final String COM = "COM15";
	private static SerialPort serialPort = new SerialPort(COM);

	public static void write(String text) {
		try {
			if(!serialPort.isOpened() && serialPort.openPort()) {
					LOGGER.info("Opened port");
					serialPort.setDTR(false);
					serialPort.setRTS(false);
					serialPort.setParams(9600, 8, 1, 0);
			}
			if(serialPort.isOpened()) {
				if(!serialPort.writeString(text)) {
					LOGGER.info("Sending text failed; closing SerialPort");
					serialPort.closePort();
					write(text);
				}
			}
		} catch (SerialPortException ex) {
			System.out.println(ex);
		}
	}
}