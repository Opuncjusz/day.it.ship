package gui.cartest;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import client.Connector;
import websocket.to.GameMessage;
import websocket.type.MessageType;

public class CarTestTemplate extends JFrame {

	private JPanel contentPane;

	private Connector connector;

	private JButton btnStartTest;

	private JCheckBox chckbxDoPrzodu;
	private JCheckBox chckbxDoTyu;
	private JTextArea textArea;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxZmianaPrdkoci;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxWartociBrzegowe;
	private JCheckBox chckbxKoniecGry;
	private JLabel lblZakonczono;
	private JCheckBox chckbxNewCheckBox_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarTestTemplate frame = new CarTestTemplate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CarTestTemplate() {
		setTitle("CAR TEST");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnStartTest = new JButton("START TEST");
		btnStartTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connector = new Connector();
				btnStartTest.setEnabled(false);
				textArea.setText("");

				btnStartTest.setText("START TEST");

				chckbxDoPrzodu.setSelected(false);
				chckbxDoTyu.setSelected(false);

				chckbxNewCheckBox.setSelected(false);
				chckbxZmianaPrdkoci.setSelected(false);
				chckbxNewCheckBox_1.setSelected(false);
				chckbxWartociBrzegowe.setSelected(false);
				chckbxKoniecGry.setSelected(false);
				lblZakonczono.setText("");
				chckbxNewCheckBox_2.setSelected(false);

				Thread thraed = new Thread(new Runnable() {

					public void run() {
						try {
							test();
						} catch (InterruptedException e) {
							textArea.append(e.getMessage() + "\n");
							lblZakonczono.setText("E R R O R");
							setAllWithSleep(0);
							btnStartTest.setEnabled(true);
						}
					}
				});

				thraed.start();
			}
		});
		btnStartTest.setBounds(15, 16, 213, 36);
		contentPane.add(btnStartTest);

		chckbxDoPrzodu = new JCheckBox("nowa gra");
		chckbxDoPrzodu.setEnabled(false);
		chckbxDoPrzodu.setBounds(15, 76, 135, 29);
		contentPane.add(chckbxDoPrzodu);

		chckbxDoTyu = new JCheckBox("do ty\u0142u");
		chckbxDoTyu.setEnabled(false);
		chckbxDoTyu.setBounds(15, 150, 203, 29);
		contentPane.add(chckbxDoTyu);

		textArea = new JTextArea();
		textArea.setBounds(243, 16, 430, 625);
		contentPane.add(textArea);

		chckbxNewCheckBox = new JCheckBox("do przodu");
		chckbxNewCheckBox.setEnabled(false);
		chckbxNewCheckBox.setBounds(15, 113, 135, 29);
		contentPane.add(chckbxNewCheckBox);

		chckbxZmianaPrdkoci = new JCheckBox("zmiana pr\u0119dko\u015Bci");
		chckbxZmianaPrdkoci.setEnabled(false);
		chckbxZmianaPrdkoci.setBounds(15, 187, 203, 29);
		contentPane.add(chckbxZmianaPrdkoci);

		chckbxNewCheckBox_1 = new JCheckBox("skr\u0119canie");
		chckbxNewCheckBox_1.setEnabled(false);
		chckbxNewCheckBox_1.setBounds(15, 224, 135, 29);
		contentPane.add(chckbxNewCheckBox_1);

		chckbxWartociBrzegowe = new JCheckBox("warto\u015Bci brzegowe");
		chckbxWartociBrzegowe.setEnabled(false);
		chckbxWartociBrzegowe.setBounds(15, 261, 203, 29);
		contentPane.add(chckbxWartociBrzegowe);

		chckbxKoniecGry = new JCheckBox("koniec gry");
		chckbxKoniecGry.setEnabled(false);
		chckbxKoniecGry.setBounds(15, 298, 135, 29);
		contentPane.add(chckbxKoniecGry);

		lblZakonczono = new JLabel("");
		lblZakonczono.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblZakonczono.setHorizontalAlignment(SwingConstants.CENTER);
		lblZakonczono.setBounds(15, 612, 213, 29);
		contentPane.add(lblZakonczono);

		chckbxNewCheckBox_2 = new JCheckBox("zmiana pr\u0119dko\u015Bci");
		chckbxNewCheckBox_2.setEnabled(false);
		chckbxNewCheckBox_2.setBounds(15, 335, 203, 29);
		contentPane.add(chckbxNewCheckBox_2);
	}

	private void log(String msg) {
		textArea.append(msg.replaceAll(", ", "\t") + "\n");
	}

	private void test() throws InterruptedException {
		log("start");

		String ip = "ws://localhost:8887";

		log("³¹cze z " + ip);

		try {
			connector.run(ip);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			log(e.getMessage());
			return;
		}

		chckbxDoPrzodu.setSelected(true);

		GameMessage gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.NEW_GAME);
		gameMessage.setId("admin");
		connector.send(gameMessage);

		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("Player_1");
		gameMessage.setId("ID_1");
		connector.send(gameMessage);

		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("Player_2");
		gameMessage.setId("ID_2");
		connector.send(gameMessage);

		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("Player_3");
		gameMessage.setId("ID_3");
		connector.send(gameMessage);

		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
		gameMessage.setContent("Player_4");
		gameMessage.setId("ID_4");
		connector.send(gameMessage);

		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.START_GAME);
		gameMessage.setId("admin");
		connector.send(gameMessage);

		chckbxNewCheckBox.setSelected(true);

		set1(200);
		set2(200);
		set3(200);
		set4(200);

		log("1: 200, 2: 200, 3: 200, 4: 200");

		Thread.sleep(2500);

		set1(0);
		set2(0);
		set3(0);
		set4(0);

		log("1: 0, 2: 0, 3: 0, 4: 0");

		Thread.sleep(1500);

		chckbxDoTyu.setSelected(true);

		set1(-200);
		set2(-200);
		set3(-200);
		set4(-200);

		log("1: -200, 2: -200, 3: -200, 4: -200");

		Thread.sleep(2500);

		set1(0);
		set2(0);
		set3(0);
		set4(0);

		log("1: 0, 2: 0, 3: 0, 4: 0");

		Thread.sleep(1500);

		chckbxZmianaPrdkoci.setSelected(true);

		set1(30);
		set2(30);
		set3(30);
		set4(30);

		log("1: 30, 2: 30, 3: 30, 4: 30");

		Thread.sleep(300);

		set1(60);
		set2(60);
		set3(60);
		set4(60);

		log("1: 60, 2: 60, 3: 60, 4: 60");

		Thread.sleep(300);

		set1(90);
		set2(90);
		set3(90);
		set4(90);

		log("1: 90, 2: 90, 3: 90, 4: 90");

		Thread.sleep(300);

		set1(120);
		set2(120);
		set3(120);
		set4(120);

		log("1: 120, 2: 120, 3: 120, 4: 120");

		Thread.sleep(300);

		set1(150);
		set2(150);
		set3(150);
		set4(150);

		log("1: 150, 2: 150, 3: 150, 4: 150");

		Thread.sleep(300);

		set1(180);
		set2(180);
		set3(180);
		set4(180);

		log("1: 180, 2: 180, 3: 180, 4: 180");

		Thread.sleep(300);

		set1(200);
		set2(200);
		set3(200);
		set4(200);

		log("1: 200, 2: 200, 3: 200, 4: 200");

		Thread.sleep(300);

		set1(0);
		set2(0);
		set3(0);
		set4(0);

		log("1: 0, 2: 0, 3: 0, 4: 0");

		Thread.sleep(1500);

		set1(-200);
		set2(-200);
		set3(-200);
		set4(-200);

		log("1: -200, 2: -200, 3: -200, 4: -200");

		Thread.sleep(300);

		set1(-180);
		set2(-180);
		set3(-180);
		set4(-180);

		log("1: -180, 2: -180, 3: -180, 4: -180");

		Thread.sleep(300);

		set1(-150);
		set2(-150);
		set3(-150);
		set4(-150);

		log("1: -150, 2: -150, 3: -150, 4: -150");

		Thread.sleep(300);

		set1(-120);
		set2(-120);
		set3(-120);
		set4(-120);

		log("1: -120, 2: -120, 3: -120, 4: -120");

		Thread.sleep(300);

		set1(-90);
		set2(-90);
		set3(-90);
		set4(-90);

		log("1: -90, 2: -90, 3: -90, 4: -90");

		Thread.sleep(300);

		set1(-60);
		set2(-60);
		set3(-60);
		set4(-60);

		log("1: -60, 2: -60, 3: -60, 4: -60");

		Thread.sleep(300);

		set1(-30);
		set2(-30);
		set3(-30);
		set4(-30);

		log("1: -30, 2: -30, 3: -30, 4: -30");

		Thread.sleep(300);

		set1(0);
		set2(0);
		set3(0);
		set4(0);

		log("1: 0, 2: 0, 3: 0, 4: 0");

		Thread.sleep(1500);

		chckbxNewCheckBox_1.setSelected(true);

		set1(200);
		set2(200);
		set3(200);
		set4(200);

		log("1: 200, 2: 200, 3: 200, 4: 200");

		Thread.sleep(1250);

		set1(0);
		set2(0);
		set3(0);
		set4(0);

		log("1: 0, 2: 0, 3: 0, 4: 0");

		Thread.sleep(1500);

		set1(105);
		set2(-105);
		set3(105);
		set4(-105);

		log("1: 105, 2: -105, 3: 105, 4: -105");

		Thread.sleep(3000);

		set1(0);
		set2(0);
		set3(0);
		set4(0);

		log("1: 0, 2: 0, 3: 0, 4: 0");

		Thread.sleep(1500);

		set1(-105);
		set2(105);
		set3(-105);
		set4(105);

		log("1: -105, 2: 105, 3: -105, 4: 105");

		Thread.sleep(3000);

		set1(0);
		set2(0);
		set3(0);
		set4(0);

		log("1: 0, 2: 0, 3: 0, 4: 0");

		Thread.sleep(1500);

		chckbxWartociBrzegowe.setSelected(true);

		set1(10);
		set2(0);
		set3(0);
		set4(0);

		log("1: 10, 2: 0, 3: 0, 4: 0");

		Thread.sleep(500);

		set1(0);
		set2(10);
		set3(0);
		set4(0);

		log("1: 0, 2: 10, 3: 0, 4: 0");

		Thread.sleep(500);

		set1(0);
		set2(0);
		set3(10);
		set4(0);

		log("1: 0, 2: 0, 3: 10, 4: 0");

		Thread.sleep(500);

		set1(0);
		set2(0);
		set3(0);
		set4(10);

		log("1: 0, 2: 0, 3: 0, 4: 10");

		Thread.sleep(500);

		set1(0);
		set2(0);
		set3(0);
		set4(-10);

		log("1: 0, 2: 0, 3: 0, 4: -10");

		Thread.sleep(500);

		set1(0);
		set2(0);
		set3(-10);
		set4(-10);

		log("1: 0, 2: 0, 3: -10, 4: -10");

		Thread.sleep(500);

		set1(0);
		set2(-10);
		set3(-10);
		set4(-10);

		log("1: 0, 2: -10, 3: -10, 4: -10");

		Thread.sleep(500);

		set1(-200);
		set2(-200);
		set3(200);
		set4(200);

		log("1: -200, 2: -200, 3: 200, 4: 200");

		Thread.sleep(500);

		chckbxKoniecGry.setSelected(true);

		gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.END_GAME);
		gameMessage.setId("admin");
		connector.send(gameMessage);

		Thread.sleep(750);

		chckbxNewCheckBox_2.setSelected(true);

		set1(-105);
		set2(105);
		set3(-105);
		set4(105);

		log("1: -105, 2: 105, 3: -105, 4: 105");

		Thread.sleep(1000);

		log("end");

		lblZakonczono.setText("ZAKOÑCZONO");

		btnStartTest.setText("POWTÓRZ");
		btnStartTest.setEnabled(true);
	}

	private void set1(long value) {
		GameMessage gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.SPEED);
		gameMessage.setContent(value + "");
		gameMessage.setId("ID_1");
		connector.send(gameMessage);
	}

	private void set2(long value) {
		GameMessage gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.SPEED);
		gameMessage.setContent(value + "");
		gameMessage.setId("ID_2");
		connector.send(gameMessage);
	}

	private void set3(long value) {
		GameMessage gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.SPEED);
		gameMessage.setContent(value + "");
		gameMessage.setId("ID_3");
		connector.send(gameMessage);
	}

	private void set4(long value) {
		GameMessage gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.SPEED);
		gameMessage.setContent(value + "");
		gameMessage.setId("ID_4");
		connector.send(gameMessage);
	}

	private void setAllWithSleep(long value) {
		set1(value);
		set2(value);
		set3(value);
		set4(value);

		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			log(e.getMessage());
			e.printStackTrace();
		}
	}

}
