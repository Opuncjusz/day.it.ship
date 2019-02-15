package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import websocket.to.GameMessage;
import websocket.type.MessageType;

public class GUITemplate {

	private JButton btnConnect;
	private JFrame frmAdminDebug;
	private JTextField txtAddress;
	private JTextField txtNotConnected;
	private JTextField txtId;
	private JTextField txtPlayer;
	private JTextField txtId_1;
	private JTextField txtPlayer_1;
	private JTextField txtId_2;
	private JTextField txtPlayer_2;
	private JTextField txtId_3;
	private JTextField txtPlayer_3;
	private JTextArea textArea;

	public static GUITemplate window;
	public static AdminGUI adminGUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new GUITemplate();
					window.frmAdminDebug.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUITemplate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdminDebug = new JFrame();
		frmAdminDebug.setTitle("ADMIN DEBUG");
		frmAdminDebug.setResizable(false);
		frmAdminDebug.setBounds(100, 100, 1291, 743);
		frmAdminDebug.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frmAdminDebug.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblWebsocket = new JLabel("WebSocket");
		lblWebsocket.setBounds(476, 16, 92, 20);
		panel.add(lblWebsocket);

		txtAddress = new JTextField();
		txtAddress.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddress.setText("ws://localhost:8887");
		txtAddress.setBounds(583, 13, 261, 27);
		panel.add(txtAddress);
		txtAddress.setColumns(10);

		btnConnect = new JButton("CONNECT");
		btnConnect.setBounds(859, 12, 115, 29);
		panel.add(btnConnect);

		txtNotConnected = new JTextField();
		txtNotConnected.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtNotConnected.setHorizontalAlignment(SwingConstants.CENTER);
		txtNotConnected.setEnabled(false);
		txtNotConnected.setEditable(false);
		txtNotConnected.setText("NOT CONNECTED");
		txtNotConnected.setBounds(989, 7, 281, 38);
		panel.add(txtNotConnected);
		txtNotConnected.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(15, 16, 390, 673);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(15, 16, 360, 160);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JButton btnAddPlayer = new JButton("ADD PLAYER");
		btnAddPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
				gameMessage.setContent(txtPlayer.getText());
				gameMessage.setId(txtId.getText());
				adminGUI.send(gameMessage);
			}
		});
		btnAddPlayer.setBounds(15, 57, 330, 29);
		panel_2.add(btnAddPlayer);

		txtId = new JTextField();
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setText("ID_001");
		txtId.setToolTipText("ID");
		txtId.setBounds(15, 15, 152, 26);
		panel_2.add(txtId);
		txtId.setColumns(10);

		txtPlayer = new JTextField();
		txtPlayer.setText("PLAYER_1");
		txtPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer.setToolTipText("NAME");
		txtPlayer.setBounds(199, 15, 146, 26);
		panel_2.add(txtPlayer);
		txtPlayer.setColumns(10);

		JButton btnUp = new JButton("-50");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("-50");
				gameMessage.setId(txtId.getText());
				adminGUI.send(gameMessage);
			}
		});
		btnUp.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnUp.setBounds(15, 102, 61, 29);
		panel_2.add(btnUp);

		JButton btnDown = new JButton("STOP");
		btnDown.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("0");
				gameMessage.setId(txtId.getText());
				adminGUI.send(gameMessage);
			}
		});
		btnDown.setBounds(101, 102, 61, 29);
		panel_2.add(btnDown);

		JButton btnLeft = new JButton("50");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("50");
				gameMessage.setId(txtId.getText());
				adminGUI.send(gameMessage);
			}
		});
		btnLeft.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnLeft.setBounds(199, 102, 61, 29);
		panel_2.add(btnLeft);

		JButton btnRight = new JButton("100");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("100");
				gameMessage.setId(txtId.getText());
				adminGUI.send(gameMessage);
			}
		});
		btnRight.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnRight.setBounds(284, 102, 61, 29);
		panel_2.add(btnRight);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(15, 176, 360, 160);
		panel_1.add(panel_3);

		JButton button = new JButton("ADD PLAYER");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
				gameMessage.setContent(txtPlayer_1.getText());
				gameMessage.setId(txtId_1.getText());
				adminGUI.send(gameMessage);
			}
		});
		button.setBounds(15, 57, 330, 29);
		panel_3.add(button);

		txtId_1 = new JTextField();
		txtId_1.setToolTipText("ID");
		txtId_1.setText("ID_002");
		txtId_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtId_1.setColumns(10);
		txtId_1.setBounds(15, 15, 152, 26);
		panel_3.add(txtId_1);

		txtPlayer_1 = new JTextField();
		txtPlayer_1.setToolTipText("NAME");
		txtPlayer_1.setText("PLAYER_2");
		txtPlayer_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer_1.setColumns(10);
		txtPlayer_1.setBounds(199, 15, 146, 26);
		panel_3.add(txtPlayer_1);

		JButton button_1 = new JButton("-50");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("-50");
				gameMessage.setId(txtId_1.getText());
				adminGUI.send(gameMessage);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_1.setBounds(15, 102, 61, 29);
		panel_3.add(button_1);

		JButton btnStop = new JButton("STOP");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("0");
				gameMessage.setId(txtId_1.getText());
				adminGUI.send(gameMessage);
			}
		});
		btnStop.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnStop.setBounds(101, 102, 61, 29);
		panel_3.add(btnStop);

		JButton button_3 = new JButton("50");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("50");
				gameMessage.setId(txtId_1.getText());
				adminGUI.send(gameMessage);
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_3.setBounds(199, 102, 61, 29);
		panel_3.add(button_3);

		JButton button_4 = new JButton("100");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("100");
				gameMessage.setId(txtId_1.getText());
				adminGUI.send(gameMessage);
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_4.setBounds(284, 102, 61, 29);
		panel_3.add(button_4);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(15, 337, 360, 160);
		panel_1.add(panel_4);

		JButton button_5 = new JButton("ADD PLAYER");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
				gameMessage.setContent(txtPlayer_2.getText());
				gameMessage.setId(txtId_2.getText());
				adminGUI.send(gameMessage);
			}
		});
		button_5.setBounds(15, 57, 330, 29);
		panel_4.add(button_5);

		txtId_2 = new JTextField();
		txtId_2.setToolTipText("ID");
		txtId_2.setText("ID_003");
		txtId_2.setHorizontalAlignment(SwingConstants.CENTER);
		txtId_2.setColumns(10);
		txtId_2.setBounds(15, 15, 152, 26);
		panel_4.add(txtId_2);

		txtPlayer_2 = new JTextField();
		txtPlayer_2.setToolTipText("NAME");
		txtPlayer_2.setText("PLAYER_3");
		txtPlayer_2.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer_2.setColumns(10);
		txtPlayer_2.setBounds(199, 15, 146, 26);
		panel_4.add(txtPlayer_2);

		JButton button_6 = new JButton("-50");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("-50");
				gameMessage.setId(txtId_2.getText());
				adminGUI.send(gameMessage);
			}
		});
		button_6.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_6.setBounds(15, 102, 61, 29);
		panel_4.add(button_6);

		JButton btnStop_1 = new JButton("STOP");
		btnStop_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("0");
				gameMessage.setId(txtId_2.getText());
				adminGUI.send(gameMessage);
			}
		});
		btnStop_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnStop_1.setBounds(101, 102, 61, 29);
		panel_4.add(btnStop_1);

		JButton button_8 = new JButton("50");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("50");
				gameMessage.setId(txtId_2.getText());
				adminGUI.send(gameMessage);
			}
		});
		button_8.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_8.setBounds(199, 102, 61, 29);
		panel_4.add(button_8);

		JButton button_9 = new JButton("100");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("100");
				gameMessage.setId(txtId_2.getText());
				adminGUI.send(gameMessage);
			}
		});
		button_9.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_9.setBounds(284, 102, 61, 29);
		panel_4.add(button_9);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(15, 497, 360, 160);
		panel_1.add(panel_5);

		JButton button_10 = new JButton("ADD PLAYER");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
				gameMessage.setContent(txtPlayer_3.getText());
				gameMessage.setId(txtId_3.getText());
				adminGUI.send(gameMessage);
			}
		});
		button_10.setBounds(15, 57, 330, 29);
		panel_5.add(button_10);

		txtId_3 = new JTextField();
		txtId_3.setToolTipText("ID");
		txtId_3.setText("ID_004");
		txtId_3.setHorizontalAlignment(SwingConstants.CENTER);
		txtId_3.setColumns(10);
		txtId_3.setBounds(15, 15, 152, 26);
		panel_5.add(txtId_3);

		txtPlayer_3 = new JTextField();
		txtPlayer_3.setToolTipText("NAME");
		txtPlayer_3.setText("PLAYER_4");
		txtPlayer_3.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer_3.setColumns(10);
		txtPlayer_3.setBounds(199, 15, 146, 26);
		panel_5.add(txtPlayer_3);

		JButton button_11 = new JButton("-50");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("-50");
				gameMessage.setId(txtId_3.getText());
				adminGUI.send(gameMessage);
			}
		});
		button_11.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_11.setBounds(15, 102, 61, 29);
		panel_5.add(button_11);

		JButton btnStop_2 = new JButton("STOP");
		btnStop_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("0");
				gameMessage.setId(txtId_3.getText());
				adminGUI.send(gameMessage);
			}
		});
		btnStop_2.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnStop_2.setBounds(101, 102, 61, 29);
		panel_5.add(btnStop_2);

		JButton button_13 = new JButton("50");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("50");
				gameMessage.setId(txtId_3.getText());
				adminGUI.send(gameMessage);
			}
		});
		button_13.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_13.setBounds(199, 102, 61, 29);
		panel_5.add(button_13);

		JButton button_14 = new JButton("100");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.SPEED);
				gameMessage.setContent("100");
				gameMessage.setId(txtId_3.getText());
				adminGUI.send(gameMessage);
			}
		});
		button_14.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_14.setBounds(284, 102, 61, 29);
		panel_5.add(button_14);

		textArea = new JTextArea();
		textArea.setBounds(420, 200, 850, 489);
		panel.add(textArea);

		JButton btnNewGame = new JButton("NEW GAME");
		btnNewGame.setBounds(1136, 146, 134, 38);
		panel.add(btnNewGame);

		btnConnect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					adminGUI.run();
					getTextArea().append("ready\n");
					txtNotConnected.setText("CONNECTED");
				} catch (Exception e1) {
					getTextArea().append(e1.getMessage() + "\n");
				}
			}
		});

		btnNewGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.NEW_GAME);
				gameMessage.setContent("blabla");
				gameMessage.setId("admin");
				adminGUI.send(gameMessage);
			}
		});

	}

	public JFrame getFrmAdminDebug() {
		return frmAdminDebug;
	}

	public void setFrmAdminDebug(JFrame frmAdminDebug) {
		this.frmAdminDebug = frmAdminDebug;
	}

	public JTextField getTxtAddress() {
		return txtAddress;
	}

	public void setTxtAddress(JTextField txtAddress) {
		this.txtAddress = txtAddress;
	}

	public JTextField getTxtNotConnected() {
		return txtNotConnected;
	}

	public void setTxtNotConnected(JTextField txtNotConnected) {
		this.txtNotConnected = txtNotConnected;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JTextField getTxtPlayer() {
		return txtPlayer;
	}

	public void setTxtPlayer(JTextField txtPlayer) {
		this.txtPlayer = txtPlayer;
	}

	public JTextField getTxtId_1() {
		return txtId_1;
	}

	public void setTxtId_1(JTextField txtId_1) {
		this.txtId_1 = txtId_1;
	}

	public JTextField getTxtPlayer_1() {
		return txtPlayer_1;
	}

	public void setTxtPlayer_1(JTextField txtPlayer_1) {
		this.txtPlayer_1 = txtPlayer_1;
	}

	public JTextField getTxtId_2() {
		return txtId_2;
	}

	public void setTxtId_2(JTextField txtId_2) {
		this.txtId_2 = txtId_2;
	}

	public JTextField getTxtPlayer_2() {
		return txtPlayer_2;
	}

	public void setTxtPlayer_2(JTextField txtPlayer_2) {
		this.txtPlayer_2 = txtPlayer_2;
	}

	public JTextField getTxtId_3() {
		return txtId_3;
	}

	public void setTxtId_3(JTextField txtId_3) {
		this.txtId_3 = txtId_3;
	}

	public JTextField getTxtPlayer_3() {
		return txtPlayer_3;
	}

	public void setTxtPlayer_3(JTextField txtPlayer_3) {
		this.txtPlayer_3 = txtPlayer_3;
	}

	public void setBtnConnect(JButton btnConnect) {
		this.btnConnect = btnConnect;
	}

	public JButton getBtnConnect() {
		return btnConnect;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

}
