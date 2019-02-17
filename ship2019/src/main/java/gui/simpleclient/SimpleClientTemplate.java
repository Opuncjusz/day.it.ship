package gui.simpleclient;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import client.Connector;
import websocket.to.GameMessage;
import websocket.type.MessageType;

public class SimpleClientTemplate extends JFrame {

	public Connector connector;

	private String playerId;
	private int currentPower = 0;

	private JPanel contentPane;
	private JTextField txtWsslocalhost;
	private JTextField txtPlayer;
	private JTextField textField;

	JPanel panel_1;
	JPanel panel_2;
	JPanel panel_3;
	JPanel panel_4;
	JPanel panel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleClientTemplate frame = new SimpleClientTemplate();
					frame.connector = new Connector();
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
	public SimpleClientTemplate() {
		setResizable(false);
		setTitle("SimpleClient");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtWsslocalhost = new JTextField();
		txtWsslocalhost.setHorizontalAlignment(SwingConstants.CENTER);
		txtWsslocalhost.setText("ws://localhost:8887");
		txtWsslocalhost.setBounds(15, 16, 244, 26);
		contentPane.add(txtWsslocalhost);
		txtWsslocalhost.setColumns(10);

		JButton btnConnect = new JButton("CONNECT");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connector.run(txtWsslocalhost.getText());

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					playerId = System.getProperty("user.name") + "_" + System.currentTimeMillis();

					GameMessage gameMessage = new GameMessage();
					gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
					gameMessage.setContent(txtPlayer.getText());
					gameMessage.setId(playerId);
					connector.send(gameMessage);
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnConnect.setBounds(356, 15, 134, 29);
		contentPane.add(btnConnect);

		JPanel panel = new JPanel();
		panel.setBounds(15, 114, 244, 367);
		contentPane.add(panel);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(15, 16, 214, 67);
		panel.add(panel_1);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new MatteBorder(0, 1, 0, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(15, 83, 214, 67);
		panel.add(panel_2);

		panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(0, 1, 0, 1, (Color) new Color(0, 0, 0)));
		panel_3.setBackground(Color.GRAY);
		panel_3.setBounds(15, 150, 214, 67);
		panel.add(panel_3);

		panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(0, 1, 0, 1, (Color) new Color(0, 0, 0)));
		panel_4.setBackground(Color.GRAY);
		panel_4.setBounds(15, 216, 214, 72);
		panel.add(panel_4);

		panel_5 = new JPanel();
		panel_5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_5.setBackground(Color.GRAY);
		panel_5.setBounds(15, 284, 214, 67);
		panel.add(panel_5);

		txtPlayer = new JTextField();
		txtPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer.setText("Player");
		txtPlayer.setBounds(78, 72, 181, 26);
		contentPane.add(txtPlayer);
		txtPlayer.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(15, 78, 69, 20);
		contentPane.add(lblName);

		JButton btnNewButton = new JButton("FORWARD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lvlUp();
			}
		});
		btnNewButton.setBounds(356, 114, 134, 134);
		contentPane.add(btnNewButton);

		JButton button = new JButton("BACKWARDS");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lvlDown();
			}
		});
		button.setBounds(356, 321, 134, 134);
		contentPane.add(button);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("0%");
		textField.setEnabled(false);
		textField.setBounds(356, 264, 134, 41);
		contentPane.add(textField);
		textField.setColumns(10);
	}

	public void setPlus100() {
		panel_1.setBackground(Color.GREEN);
		panel_2.setBackground(Color.GREEN);
		panel_3.setBackground(Color.GREEN);
		panel_4.setBackground(Color.GREEN);
		panel_5.setBackground(Color.GREEN);
		textField.setText("100%");
		currentPower = 100;

		GameMessage gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.SPEED);
		gameMessage.setContent("200");
		gameMessage.setId(playerId);
		connector.send(gameMessage);
	}

	public void setPlus50() {
		panel_1.setBackground(Color.WHITE);
		panel_2.setBackground(Color.CYAN);
		panel_3.setBackground(Color.CYAN);
		panel_4.setBackground(Color.CYAN);
		panel_5.setBackground(Color.CYAN);
		textField.setText("50%");
		currentPower = 50;

		GameMessage gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.SPEED);
		gameMessage.setContent("100");
		gameMessage.setId(playerId);
		connector.send(gameMessage);
	}

	public void setPlus0() {
		panel_1.setBackground(Color.WHITE);
		panel_2.setBackground(Color.WHITE);
		panel_3.setBackground(Color.GRAY);
		panel_4.setBackground(Color.GRAY);
		panel_5.setBackground(Color.GRAY);
		textField.setText("0%");
		currentPower = 0;

		GameMessage gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.SPEED);
		gameMessage.setContent("0");
		gameMessage.setId(playerId);
		connector.send(gameMessage);
	}

	public void setMinus50() {
		panel_1.setBackground(Color.WHITE);
		panel_2.setBackground(Color.WHITE);
		panel_3.setBackground(Color.WHITE);
		panel_4.setBackground(Color.ORANGE);
		panel_5.setBackground(Color.ORANGE);
		textField.setText("-50%");
		currentPower = -50;

		GameMessage gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.SPEED);
		gameMessage.setContent("-100");
		gameMessage.setId(playerId);
		connector.send(gameMessage);
	}

	public void setMinus100() {
		panel_1.setBackground(Color.WHITE);
		panel_2.setBackground(Color.WHITE);
		panel_3.setBackground(Color.WHITE);
		panel_4.setBackground(Color.WHITE);
		panel_5.setBackground(Color.RED);
		textField.setText("-100%");
		currentPower = -100;

		GameMessage gameMessage = new GameMessage();
		gameMessage.setMessageType(MessageType.SPEED);
		gameMessage.setContent("-200");
		gameMessage.setId(playerId);
		connector.send(gameMessage);
	}

	private void lvlUp() {
		if (currentPower == 100) {
			return;
		}

		if (currentPower == 50) {
			setPlus100();
			return;
		}

		if (currentPower == 0) {
			setPlus50();
			return;
		}

		if (currentPower == -50) {
			setPlus0();
			return;
		}

		if (currentPower == -100) {
			setMinus50();
			return;
		}
	}

	private void lvlDown() {
		if (currentPower == 100) {
			setPlus50();
			return;
		}

		if (currentPower == 50) {
			setPlus0();
			return;
		}

		if (currentPower == 0) {
			setMinus50();
			return;
		}

		if (currentPower == -50) {
			setMinus100();
			return;
		}

		if (currentPower == -100) {
			return;
		}
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}

}
