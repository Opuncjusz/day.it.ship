package gui.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import client.AdminConnector;
import model.Game;
import model.Player;
import websocket.to.GameMessage;
import websocket.type.MessageType;

public class AdminGUITemplate extends JFrame {

	private static AdminConnector connector;
	public static AdminGameInfo ADMIN_GAME_INFO;
	public static List<Game> TOP_GAMES;

	private JPanel contentPane;
	private JTextField txtAleksanderWtf;
	private JTextField txtNotConnected;
	private JTextField txtNotConnected_1;
	private JTextField txtNotConnected_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JButton btnStartNewGame;
	private JButton button;
	private JButton btnNewGame;
	private JTextField textField;
	private JPanel panel_5;
	private JLabel label_4;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JLabel label_5;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JLabel label_6;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JLabel label_7;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_27;
	private JTextField textField_28;
	private JTextField textField_29;
	private JLabel label_8;
	private JTextField textField_30;
	private JTextField textField_31;
	private JTextField textField_32;
	private JTextField textField_33;
	private JTextField textField_34;
	private JLabel label_9;
	private JTextField textField_35;
	private JTextField textField_36;
	private JTextField textField_37;
	private JTextField textField_38;
	private JTextField textField_39;
	private JLabel label_10;
	private JLabel label_11;
	private JTextField textField_40;
	private JTextField textField_41;
	private JTextField textField_42;
	private JTextField textField_43;
	private JTextField textField_44;
	private JLabel label_12;
	private JTextField textField_45;
	private JTextField textField_46;
	private JTextField textField_47;
	private JTextField textField_48;
	private JTextField textField_49;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUITemplate frame = new AdminGUITemplate();
					frame.setVisible(true);
					connector = new AdminConnector();
					connector.run("ws://localhost:8887");

					// default game info
					ADMIN_GAME_INFO = new AdminGameInfo();

					ADMIN_GAME_INFO.setMotorOwnerA("");
					ADMIN_GAME_INFO.setMotorOwnerB("");
					ADMIN_GAME_INFO.setMotorOwnerC("");
					ADMIN_GAME_INFO.setMotorOwnerD("");

					ADMIN_GAME_INFO.setPlayer1("NOT CONNECTED");
					ADMIN_GAME_INFO.setPlayer2("NOT CONNECTED");
					ADMIN_GAME_INFO.setPlayer3("NOT CONNECTED");
					ADMIN_GAME_INFO.setPlayer4("NOT CONNECTED");

					ADMIN_GAME_INFO.setMotorPowerA("0%");
					ADMIN_GAME_INFO.setMotorPowerB("0%");
					ADMIN_GAME_INFO.setMotorPowerC("0%");
					ADMIN_GAME_INFO.setMotorPowerD("0%");

					TOP_GAMES = new ArrayList<Game>();

					frame.timer();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void timer() {

		Thread thread = new Thread(new Runnable() {

			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					getInfo();
					setInfo();
				}
			}
		});

		thread.start();

	}

	private void getInfo() {
		GameMessage gameMessage = new GameMessage();
		gameMessage.setId("admin");
		gameMessage.setMessageType(MessageType.GET_INFO);

		try {
			connector.send(gameMessage);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	private void setInfo() {
		txtAleksanderWtf.setText(ADMIN_GAME_INFO.getPlayer1());
		txtNotConnected.setText(ADMIN_GAME_INFO.getPlayer2());
		txtNotConnected_1.setText(ADMIN_GAME_INFO.getPlayer3());
		txtNotConnected_2.setText(ADMIN_GAME_INFO.getPlayer4());

		textField_3.setText(ADMIN_GAME_INFO.getMotorOwnerA());
		textField_6.setText(ADMIN_GAME_INFO.getMotorOwnerB());
		textField_4.setText(ADMIN_GAME_INFO.getMotorOwnerC());
		textField_5.setText(ADMIN_GAME_INFO.getMotorOwnerD());

		label.setText(ADMIN_GAME_INFO.getMotorPowerA());
		label_3.setText(ADMIN_GAME_INFO.getMotorPowerB());
		label_1.setText(ADMIN_GAME_INFO.getMotorPowerC());
		label_2.setText(ADMIN_GAME_INFO.getMotorPowerD());

		textField.setText(ADMIN_GAME_INFO.getTime());

		textField_3.setBackground(new Color(200 - ADMIN_GAME_INFO.getMotorPowerAAsNumber(), 255,
				200 - ADMIN_GAME_INFO.getMotorPowerAAsNumber()));

		textField_6.setBackground(new Color(200 - ADMIN_GAME_INFO.getMotorPowerBAsNumber(), 255,
				200 - ADMIN_GAME_INFO.getMotorPowerBAsNumber()));

		textField_4.setBackground(new Color(200 - ADMIN_GAME_INFO.getMotorPowerCAsNumber(), 255,
				200 - ADMIN_GAME_INFO.getMotorPowerCAsNumber()));

		textField_5.setBackground(new Color(200 - ADMIN_GAME_INFO.getMotorPowerDAsNumber(), 255,
				200 - ADMIN_GAME_INFO.getMotorPowerDAsNumber()));

		setTop();
	}

	private void setTop() {
		List<Game> top = new ArrayList<Game>();
		top.addAll(TOP_GAMES);

		try {
			if (top.get(0) != null) {
				setTop1(top.get(0));
			}

			if (top.get(1) != null) {
				setTop2(top.get(1));
			}

			if (top.get(2) != null) {
				setTop3(top.get(2));
			}

			if (top.get(3) != null) {
				setTop4(top.get(3));
			}

			if (top.get(4) != null) {
				setTop5(top.get(4));
			}

			if (top.get(5) != null) {
				setTop6(top.get(5));
			}

			if (top.get(6) != null) {
				setTop7(top.get(6));
			}

			if (top.get(7) != null) {
				setTop8(top.get(7));
			}

			if (top.get(8) != null) {
				setTop9(top.get(8));
			}
		} catch (IndexOutOfBoundsException e) {
			// ...
		}
	}

	/**
	 * Create the frame.
	 */
	public AdminGUITemplate() {
		setTitle("GAME PANEL");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1378, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Players", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(15, 16, 1342, 81);
		contentPane.add(panel);
		panel.setLayout(null);

		txtAleksanderWtf = new JTextField();
		txtAleksanderWtf.setHorizontalAlignment(SwingConstants.CENTER);
		txtAleksanderWtf.setEditable(false);
		txtAleksanderWtf.setText("NOT CONNECTED");
		txtAleksanderWtf.setBounds(100, 33, 219, 26);
		panel.add(txtAleksanderWtf);
		txtAleksanderWtf.setColumns(10);

		JLabel lblPlayer = new JLabel("PLAYER 1");
		lblPlayer.setBounds(15, 35, 107, 23);
		panel.add(lblPlayer);

		txtNotConnected = new JTextField();
		txtNotConnected.setText("NOT CONNECTED");
		txtNotConnected.setHorizontalAlignment(SwingConstants.CENTER);
		txtNotConnected.setEditable(false);
		txtNotConnected.setColumns(10);
		txtNotConnected.setBounds(430, 33, 219, 26);
		panel.add(txtNotConnected);

		JLabel lblPlayer_1 = new JLabel("PLAYER 2");
		lblPlayer_1.setBounds(345, 35, 107, 23);
		panel.add(lblPlayer_1);

		txtNotConnected_1 = new JTextField();
		txtNotConnected_1.setText("NOT CONNECTED");
		txtNotConnected_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtNotConnected_1.setEditable(false);
		txtNotConnected_1.setColumns(10);
		txtNotConnected_1.setBounds(771, 33, 219, 26);
		panel.add(txtNotConnected_1);

		JLabel lblPlayer_2 = new JLabel("PLAYER 3");
		lblPlayer_2.setBounds(686, 35, 107, 23);
		panel.add(lblPlayer_2);

		txtNotConnected_2 = new JTextField();
		txtNotConnected_2.setText("NOT CONNECTED");
		txtNotConnected_2.setHorizontalAlignment(SwingConstants.CENTER);
		txtNotConnected_2.setEditable(false);
		txtNotConnected_2.setColumns(10);
		txtNotConnected_2.setBounds(1108, 33, 219, 26);
		panel.add(txtNotConnected_2);

		JLabel lblPlayer_3 = new JLabel("PLAYER 4");
		lblPlayer_3.setBounds(1023, 35, 107, 23);
		panel.add(lblPlayer_3);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Game", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(15, 111, 1342, 480);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Car", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(840, 127, 487, 339);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(176, 37, 137, 289);
		panel_2.add(panel_3);

		textField_3 = new JTextField();
		textField_3.setBackground(UIManager.getColor("TextField.inactiveBackground"));
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setEditable(false);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setBounds(15, 79, 146, 26);
		panel_2.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setEditable(false);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setBounds(15, 259, 146, 26);
		panel_2.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setEditable(false);
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(328, 259, 146, 26);
		panel_2.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(328, 79, 146, 26);
		panel_2.add(textField_6);

		label = new JLabel("0%");
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(15, 116, 146, 20);
		panel_2.add(label);

		label_1 = new JLabel("0%");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(15, 301, 146, 20);
		panel_2.add(label_1);

		label_2 = new JLabel("0%");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(328, 301, 146, 20);
		panel_2.add(label_2);

		label_3 = new JLabel("0%");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(328, 121, 146, 20);
		panel_2.add(label_3);

		btnStartNewGame = new JButton("START GAME");
		btnStartNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.START_GAME);
				gameMessage.setId("admin");
				connector.send(gameMessage);

				btnStartNewGame.setEnabled(false);
				button.setEnabled(true);
			}
		});
		btnStartNewGame.setEnabled(false);
		btnStartNewGame.setBounds(1075, 37, 252, 29);
		panel_1.add(btnStartNewGame);

		button = new JButton("END GAME");
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.END_GAME);
				gameMessage.setId("admin");
				connector.send(gameMessage);

				button.setEnabled(false);
			}
		});
		button.setBounds(1075, 82, 252, 29);
		panel_1.add(button);

		btnNewGame = new JButton("NEW GAME");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.NEW_GAME);
				gameMessage.setId("admin");
				connector.send(gameMessage);
				btnStartNewGame.setEnabled(true);
			}
		});
		btnNewGame.setBounds(840, 40, 206, 29);
		panel_1.add(btnNewGame);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("00:00:00");
		textField.setEditable(false);
		textField.setBounds(840, 85, 206, 26);
		panel_1.add(textField);
		textField.setColumns(10);

		panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "TOP", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(15, 28, 810, 438);
		panel_1.add(panel_5);
		panel_5.setLayout(null);

		label_4 = new JLabel("1.");
		label_4.setBounds(15, 29, 69, 26);
		panel_5.add(label_4);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(40, 29, 127, 26);
		panel_5.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(182, 29, 146, 26);
		panel_5.add(textField_2);

		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_7.setText("");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(338, 29, 146, 26);
		panel_5.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_8.setText("");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(492, 29, 146, 26);
		panel_5.add(textField_8);

		textField_9 = new JTextField();
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_9.setText("");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBounds(649, 29, 146, 26);
		panel_5.add(textField_9);

		textField_10 = new JTextField();
		textField_10.setText("");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBounds(649, 71, 146, 26);
		panel_5.add(textField_10);

		textField_11 = new JTextField();
		textField_11.setText("");
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBounds(492, 71, 146, 26);
		panel_5.add(textField_11);

		textField_12 = new JTextField();
		textField_12.setText("");
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		textField_12.setBounds(338, 71, 146, 26);
		panel_5.add(textField_12);

		textField_13 = new JTextField();
		textField_13.setText("");
		textField_13.setHorizontalAlignment(SwingConstants.CENTER);
		textField_13.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_13.setEditable(false);
		textField_13.setColumns(10);
		textField_13.setBounds(182, 71, 146, 26);
		panel_5.add(textField_13);

		textField_14 = new JTextField();
		textField_14.setText("");
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		textField_14.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		textField_14.setBounds(40, 71, 127, 26);
		panel_5.add(textField_14);

		label_5 = new JLabel("2.");
		label_5.setBounds(15, 71, 69, 26);
		panel_5.add(label_5);

		textField_15 = new JTextField();
		textField_15.setText("");
		textField_15.setHorizontalAlignment(SwingConstants.CENTER);
		textField_15.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_15.setEditable(false);
		textField_15.setColumns(10);
		textField_15.setBounds(649, 113, 146, 26);
		panel_5.add(textField_15);

		textField_16 = new JTextField();
		textField_16.setText("");
		textField_16.setHorizontalAlignment(SwingConstants.CENTER);
		textField_16.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_16.setEditable(false);
		textField_16.setColumns(10);
		textField_16.setBounds(492, 113, 146, 26);
		panel_5.add(textField_16);

		textField_17 = new JTextField();
		textField_17.setText("");
		textField_17.setHorizontalAlignment(SwingConstants.CENTER);
		textField_17.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_17.setEditable(false);
		textField_17.setColumns(10);
		textField_17.setBounds(338, 113, 146, 26);
		panel_5.add(textField_17);

		textField_18 = new JTextField();
		textField_18.setText("");
		textField_18.setHorizontalAlignment(SwingConstants.CENTER);
		textField_18.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_18.setEditable(false);
		textField_18.setColumns(10);
		textField_18.setBounds(182, 113, 146, 26);
		panel_5.add(textField_18);

		textField_19 = new JTextField();
		textField_19.setText("");
		textField_19.setHorizontalAlignment(SwingConstants.CENTER);
		textField_19.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_19.setEditable(false);
		textField_19.setColumns(10);
		textField_19.setBounds(40, 113, 127, 26);
		panel_5.add(textField_19);

		label_6 = new JLabel("3.");
		label_6.setBounds(15, 113, 69, 26);
		panel_5.add(label_6);

		textField_20 = new JTextField();
		textField_20.setText("");
		textField_20.setHorizontalAlignment(SwingConstants.CENTER);
		textField_20.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_20.setEditable(false);
		textField_20.setColumns(10);
		textField_20.setBounds(649, 184, 146, 26);
		panel_5.add(textField_20);

		textField_21 = new JTextField();
		textField_21.setText("");
		textField_21.setHorizontalAlignment(SwingConstants.CENTER);
		textField_21.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_21.setEditable(false);
		textField_21.setColumns(10);
		textField_21.setBounds(492, 184, 146, 26);
		panel_5.add(textField_21);

		textField_22 = new JTextField();
		textField_22.setText("");
		textField_22.setHorizontalAlignment(SwingConstants.CENTER);
		textField_22.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_22.setEditable(false);
		textField_22.setColumns(10);
		textField_22.setBounds(338, 184, 146, 26);
		panel_5.add(textField_22);

		textField_23 = new JTextField();
		textField_23.setText("");
		textField_23.setHorizontalAlignment(SwingConstants.CENTER);
		textField_23.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_23.setEditable(false);
		textField_23.setColumns(10);
		textField_23.setBounds(182, 184, 146, 26);
		panel_5.add(textField_23);

		textField_24 = new JTextField();
		textField_24.setText("");
		textField_24.setHorizontalAlignment(SwingConstants.CENTER);
		textField_24.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_24.setEditable(false);
		textField_24.setColumns(10);
		textField_24.setBounds(40, 184, 127, 26);
		panel_5.add(textField_24);

		label_7 = new JLabel("4.");
		label_7.setBounds(15, 184, 69, 26);
		panel_5.add(label_7);

		textField_25 = new JTextField();
		textField_25.setText("");
		textField_25.setHorizontalAlignment(SwingConstants.CENTER);
		textField_25.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_25.setEditable(false);
		textField_25.setColumns(10);
		textField_25.setBounds(649, 226, 146, 26);
		panel_5.add(textField_25);

		textField_26 = new JTextField();
		textField_26.setText("");
		textField_26.setHorizontalAlignment(SwingConstants.CENTER);
		textField_26.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_26.setEditable(false);
		textField_26.setColumns(10);
		textField_26.setBounds(492, 226, 146, 26);
		panel_5.add(textField_26);

		textField_27 = new JTextField();
		textField_27.setText("");
		textField_27.setHorizontalAlignment(SwingConstants.CENTER);
		textField_27.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_27.setEditable(false);
		textField_27.setColumns(10);
		textField_27.setBounds(338, 226, 146, 26);
		panel_5.add(textField_27);

		textField_28 = new JTextField();
		textField_28.setText("");
		textField_28.setHorizontalAlignment(SwingConstants.CENTER);
		textField_28.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_28.setEditable(false);
		textField_28.setColumns(10);
		textField_28.setBounds(182, 226, 146, 26);
		panel_5.add(textField_28);

		textField_29 = new JTextField();
		textField_29.setText("");
		textField_29.setHorizontalAlignment(SwingConstants.CENTER);
		textField_29.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_29.setEditable(false);
		textField_29.setColumns(10);
		textField_29.setBounds(40, 226, 127, 26);
		panel_5.add(textField_29);

		label_8 = new JLabel("5.");
		label_8.setBounds(15, 226, 69, 26);
		panel_5.add(label_8);

		textField_30 = new JTextField();
		textField_30.setText("");
		textField_30.setHorizontalAlignment(SwingConstants.CENTER);
		textField_30.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_30.setEditable(false);
		textField_30.setColumns(10);
		textField_30.setBounds(649, 268, 146, 26);
		panel_5.add(textField_30);

		textField_31 = new JTextField();
		textField_31.setText("");
		textField_31.setHorizontalAlignment(SwingConstants.CENTER);
		textField_31.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_31.setEditable(false);
		textField_31.setColumns(10);
		textField_31.setBounds(492, 268, 146, 26);
		panel_5.add(textField_31);

		textField_32 = new JTextField();
		textField_32.setText("");
		textField_32.setHorizontalAlignment(SwingConstants.CENTER);
		textField_32.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_32.setEditable(false);
		textField_32.setColumns(10);
		textField_32.setBounds(338, 268, 146, 26);
		panel_5.add(textField_32);

		textField_33 = new JTextField();
		textField_33.setText("");
		textField_33.setHorizontalAlignment(SwingConstants.CENTER);
		textField_33.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_33.setEditable(false);
		textField_33.setColumns(10);
		textField_33.setBounds(182, 268, 146, 26);
		panel_5.add(textField_33);

		textField_34 = new JTextField();
		textField_34.setText("");
		textField_34.setHorizontalAlignment(SwingConstants.CENTER);
		textField_34.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_34.setEditable(false);
		textField_34.setColumns(10);
		textField_34.setBounds(40, 268, 127, 26);
		panel_5.add(textField_34);

		label_9 = new JLabel("6.");
		label_9.setBounds(15, 268, 69, 26);
		panel_5.add(label_9);

		textField_35 = new JTextField();
		textField_35.setText("");
		textField_35.setHorizontalAlignment(SwingConstants.CENTER);
		textField_35.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_35.setEditable(false);
		textField_35.setColumns(10);
		textField_35.setBounds(649, 310, 146, 26);
		panel_5.add(textField_35);

		textField_36 = new JTextField();
		textField_36.setText("");
		textField_36.setHorizontalAlignment(SwingConstants.CENTER);
		textField_36.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_36.setEditable(false);
		textField_36.setColumns(10);
		textField_36.setBounds(492, 310, 146, 26);
		panel_5.add(textField_36);

		textField_37 = new JTextField();
		textField_37.setText("");
		textField_37.setHorizontalAlignment(SwingConstants.CENTER);
		textField_37.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_37.setEditable(false);
		textField_37.setColumns(10);
		textField_37.setBounds(338, 310, 146, 26);
		panel_5.add(textField_37);

		textField_38 = new JTextField();
		textField_38.setText("");
		textField_38.setHorizontalAlignment(SwingConstants.CENTER);
		textField_38.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_38.setEditable(false);
		textField_38.setColumns(10);
		textField_38.setBounds(182, 310, 146, 26);
		panel_5.add(textField_38);

		textField_39 = new JTextField();
		textField_39.setText("");
		textField_39.setHorizontalAlignment(SwingConstants.CENTER);
		textField_39.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_39.setEditable(false);
		textField_39.setColumns(10);
		textField_39.setBounds(40, 310, 127, 26);
		panel_5.add(textField_39);

		label_10 = new JLabel("7.");
		label_10.setBounds(15, 310, 69, 26);
		panel_5.add(label_10);

		label_11 = new JLabel("8.");
		label_11.setBounds(15, 352, 69, 26);
		panel_5.add(label_11);

		textField_40 = new JTextField();
		textField_40.setText("");
		textField_40.setHorizontalAlignment(SwingConstants.CENTER);
		textField_40.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_40.setEditable(false);
		textField_40.setColumns(10);
		textField_40.setBounds(40, 352, 127, 26);
		panel_5.add(textField_40);

		textField_41 = new JTextField();
		textField_41.setText("");
		textField_41.setHorizontalAlignment(SwingConstants.CENTER);
		textField_41.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_41.setEditable(false);
		textField_41.setColumns(10);
		textField_41.setBounds(182, 352, 146, 26);
		panel_5.add(textField_41);

		textField_42 = new JTextField();
		textField_42.setText("");
		textField_42.setHorizontalAlignment(SwingConstants.CENTER);
		textField_42.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_42.setEditable(false);
		textField_42.setColumns(10);
		textField_42.setBounds(338, 352, 146, 26);
		panel_5.add(textField_42);

		textField_43 = new JTextField();
		textField_43.setText("");
		textField_43.setHorizontalAlignment(SwingConstants.CENTER);
		textField_43.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_43.setEditable(false);
		textField_43.setColumns(10);
		textField_43.setBounds(492, 352, 146, 26);
		panel_5.add(textField_43);

		textField_44 = new JTextField();
		textField_44.setText("");
		textField_44.setHorizontalAlignment(SwingConstants.CENTER);
		textField_44.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_44.setEditable(false);
		textField_44.setColumns(10);
		textField_44.setBounds(649, 352, 146, 26);
		panel_5.add(textField_44);

		label_12 = new JLabel("9.");
		label_12.setBounds(15, 394, 69, 26);
		panel_5.add(label_12);

		textField_45 = new JTextField();
		textField_45.setText("");
		textField_45.setHorizontalAlignment(SwingConstants.CENTER);
		textField_45.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_45.setEditable(false);
		textField_45.setColumns(10);
		textField_45.setBounds(40, 394, 127, 26);
		panel_5.add(textField_45);

		textField_46 = new JTextField();
		textField_46.setText("");
		textField_46.setHorizontalAlignment(SwingConstants.CENTER);
		textField_46.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_46.setEditable(false);
		textField_46.setColumns(10);
		textField_46.setBounds(182, 394, 146, 26);
		panel_5.add(textField_46);

		textField_47 = new JTextField();
		textField_47.setText("");
		textField_47.setHorizontalAlignment(SwingConstants.CENTER);
		textField_47.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_47.setEditable(false);
		textField_47.setColumns(10);
		textField_47.setBounds(338, 394, 146, 26);
		panel_5.add(textField_47);

		textField_48 = new JTextField();
		textField_48.setText("");
		textField_48.setHorizontalAlignment(SwingConstants.CENTER);
		textField_48.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_48.setEditable(false);
		textField_48.setColumns(10);
		textField_48.setBounds(492, 394, 146, 26);
		panel_5.add(textField_48);

		textField_49 = new JTextField();
		textField_49.setText("");
		textField_49.setHorizontalAlignment(SwingConstants.CENTER);
		textField_49.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_49.setEditable(false);
		textField_49.setColumns(10);
		textField_49.setBounds(649, 394, 146, 26);
		panel_5.add(textField_49);
	}

	private void setTop1(Game game) {
		textField_1.setText(game.getGameTimeAsString());
		textField_2.setText(getPlayer1(game.getPlayers()));
		textField_7.setText(getPlayer2(game.getPlayers()));
		textField_8.setText(getPlayer3(game.getPlayers()));
		textField_9.setText(getPlayer4(game.getPlayers()));
	}

	private void setTop2(Game game) {
		textField_14.setText(game.getGameTimeAsString());
		textField_13.setText(getPlayer1(game.getPlayers()));
		textField_12.setText(getPlayer2(game.getPlayers()));
		textField_11.setText(getPlayer3(game.getPlayers()));
		textField_10.setText(getPlayer4(game.getPlayers()));
	}

	private void setTop3(Game game) {
		textField_19.setText(game.getGameTimeAsString());
		textField_18.setText(getPlayer1(game.getPlayers()));
		textField_17.setText(getPlayer2(game.getPlayers()));
		textField_16.setText(getPlayer3(game.getPlayers()));
		textField_15.setText(getPlayer4(game.getPlayers()));
	}

	private void setTop4(Game game) {
		textField_24.setText(game.getGameTimeAsString());
		textField_23.setText(getPlayer1(game.getPlayers()));
		textField_22.setText(getPlayer2(game.getPlayers()));
		textField_21.setText(getPlayer3(game.getPlayers()));
		textField_20.setText(getPlayer4(game.getPlayers()));
	}

	private void setTop5(Game game) {
		textField_29.setText(game.getGameTimeAsString());
		textField_28.setText(getPlayer1(game.getPlayers()));
		textField_27.setText(getPlayer2(game.getPlayers()));
		textField_26.setText(getPlayer3(game.getPlayers()));
		textField_25.setText(getPlayer4(game.getPlayers()));
	}

	private void setTop6(Game game) {
		textField_34.setText(game.getGameTimeAsString());
		textField_33.setText(getPlayer1(game.getPlayers()));
		textField_32.setText(getPlayer2(game.getPlayers()));
		textField_31.setText(getPlayer3(game.getPlayers()));
		textField_30.setText(getPlayer4(game.getPlayers()));
	}

	private void setTop7(Game game) {
		textField_39.setText(game.getGameTimeAsString());
		textField_38.setText(getPlayer1(game.getPlayers()));
		textField_37.setText(getPlayer2(game.getPlayers()));
		textField_36.setText(getPlayer3(game.getPlayers()));
		textField_35.setText(getPlayer4(game.getPlayers()));
	}

	private void setTop8(Game game) {
		textField_40.setText(game.getGameTimeAsString());
		textField_41.setText(getPlayer1(game.getPlayers()));
		textField_42.setText(getPlayer2(game.getPlayers()));
		textField_43.setText(getPlayer3(game.getPlayers()));
		textField_44.setText(getPlayer4(game.getPlayers()));
	}

	private void setTop9(Game game) {
		textField_45.setText(game.getGameTimeAsString());
		textField_46.setText(getPlayer1(game.getPlayers()));
		textField_47.setText(getPlayer2(game.getPlayers()));
		textField_48.setText(getPlayer3(game.getPlayers()));
		textField_49.setText(getPlayer4(game.getPlayers()));
	}

	private String getPlayer1(Map<String, Player> map) {
		try {
			Iterator<Entry<String, Player>> iterator = map.entrySet().iterator();
			Player value = iterator.next().getValue();
			return value.getName();
		} catch (Exception e) {
			return "";
		}
	}

	private String getPlayer2(Map<String, Player> map) {
		try {
			Iterator<Entry<String, Player>> iterator = map.entrySet().iterator();
			iterator.next();
			Player value = iterator.next().getValue();
			return value.getName();
		} catch (Exception e) {
			return "";
		}
	}

	private String getPlayer3(Map<String, Player> map) {
		try {
			Iterator<Entry<String, Player>> iterator = map.entrySet().iterator();
			iterator.next();
			iterator.next();
			Player value = iterator.next().getValue();
			return value.getName();
		} catch (Exception e) {
			return "";
		}
	}

	private String getPlayer4(Map<String, Player> map) {
		try {
			Iterator<Entry<String, Player>> iterator = map.entrySet().iterator();
			iterator.next();
			iterator.next();
			iterator.next();
			Player value = iterator.next().getValue();
			return value.getName();
		} catch (Exception e) {
			return "";
		}
	}

	/*
	 * 
	 * private JTextField textField_10; private JTextField textField_11; private
	 * JTextField textField_12; private JTextField textField_13; private JTextField
	 * textField_14; private JLabel label_5; private JTextField textField_15;
	 * private JTextField textField_16; private JTextField textField_17; private
	 * JTextField textField_18; private JTextField textField_19; private JLabel
	 * label_6; private JTextField textField_20; private JTextField textField_21;
	 * private JTextField textField_22; private JTextField textField_23; private
	 * JTextField textField_24; private JLabel label_7; private JTextField
	 * textField_25; private JTextField textField_26; private JTextField
	 * textField_27; private JTextField textField_28; private JTextField
	 * textField_29; private JLabel label_8; private JTextField textField_30;
	 * private JTextField textField_31; private JTextField textField_32; private
	 * JTextField textField_33; private JTextField textField_34; private JLabel
	 * label_9; private JTextField textField_35; private JTextField textField_36;
	 * private JTextField textField_37; private JTextField textField_38; private
	 * JTextField textField_39;
	 */

}
