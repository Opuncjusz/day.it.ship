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
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

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
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JButton btnNewGame;
	private JButton btnAddPlayer;
	private JButton button;
	private JButton button_5;
	private JButton button_10;
	private JButton button_7;
	private JButton button_2;

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

		btnAddPlayer = new JButton("ADD PLAYER");
		btnAddPlayer.setEnabled(false);
		btnAddPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
				gameMessage.setContent(txtPlayer.getText());
				gameMessage.setId(txtId.getText());
				adminGUI.send(gameMessage);

				getButton_7().setEnabled(true);
				getBtnAddPlayer().setEnabled(false);
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

		JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (source.getValueIsAdjusting()) {
					getLabel().setText(source.getValue() + "");

					GameMessage gameMessage = new GameMessage();
					gameMessage.setMessageType(MessageType.SPEED);
					gameMessage.setContent(source.getValue() + "");
					gameMessage.setId(txtId.getText());
					adminGUI.send(gameMessage);
				}
			}
		});
		slider.setValue(0);
		slider.setMinimum(-200);
		slider.setMaximum(200);
		slider.setBounds(15, 102, 330, 25);
		panel_2.add(slider);

		label = new JLabel("0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(15, 124, 330, 20);
		panel_2.add(label);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(15, 176, 360, 160);
		panel_1.add(panel_3);

		button = new JButton("ADD PLAYER");
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
				gameMessage.setContent(txtPlayer_1.getText());
				gameMessage.setId(txtId_1.getText());
				adminGUI.send(gameMessage);

				getButton_7().setEnabled(true);
				getButton().setEnabled(false);
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

		label_1 = new JLabel("0");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(15, 124, 330, 20);
		panel_3.add(label_1);

		JSlider slider_1 = new JSlider();
		slider_1.setValue(0);
		slider_1.setMinimum(-200);
		slider_1.setMaximum(200);
		slider_1.setBounds(15, 102, 330, 25);
		panel_3.add(slider_1);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(15, 337, 360, 160);
		panel_1.add(panel_4);

		button_5 = new JButton("ADD PLAYER");
		button_5.setEnabled(false);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
				gameMessage.setContent(txtPlayer_2.getText());
				gameMessage.setId(txtId_2.getText());
				adminGUI.send(gameMessage);

				getButton_7().setEnabled(true);
				getButton_5().setEnabled(false);
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

		label_2 = new JLabel("0");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(15, 124, 330, 20);
		panel_4.add(label_2);

		JSlider slider_2 = new JSlider();
		slider_2.setValue(0);
		slider_2.setMinimum(-200);
		slider_2.setMaximum(200);
		slider_2.setBounds(15, 102, 330, 25);
		panel_4.add(slider_2);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(15, 497, 360, 160);
		panel_1.add(panel_5);

		button_10 = new JButton("ADD PLAYER");
		button_10.setEnabled(false);
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
				gameMessage.setContent(txtPlayer_3.getText());
				gameMessage.setId(txtId_3.getText());
				adminGUI.send(gameMessage);

				getButton_7().setEnabled(true);
				getButton_10().setEnabled(false);
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

		label_3 = new JLabel("0");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(15, 124, 330, 20);
		panel_5.add(label_3);

		JSlider slider_3 = new JSlider();
		slider_3.setValue(0);
		slider_3.setMinimum(-200);
		slider_3.setMaximum(200);
		slider_3.setBounds(15, 102, 330, 25);
		panel_5.add(slider_3);

		textArea = new JTextArea();
		// textArea.setBounds(420, 200, 850, 489);
		panel.add(textArea);

		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(420, 200, 850, 489);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		// Add Textarea in to middle panel
		panel.add(scroll);

		btnNewGame = new JButton("NEW GAME");
		btnNewGame.setEnabled(false);
		btnNewGame.setBounds(1136, 146, 134, 38);
		panel.add(btnNewGame);

		button_2 = new JButton("END GAME");
		button_2.setEnabled(false);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.END_GAME);
				gameMessage.setId("admin");
				adminGUI.send(gameMessage);

				getButton_2().setEnabled(false);
				getButton_7().setEnabled(true);
			}
		});
		button_2.setBounds(875, 146, 134, 38);
		panel.add(button_2);

		button_7 = new JButton("START GAME");
		button_7.setEnabled(false);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameMessage gameMessage = new GameMessage();
				gameMessage.setMessageType(MessageType.START_GAME);
				gameMessage.setId("admin");
				adminGUI.send(gameMessage);

				getButton_2().setEnabled(true);
				getButton_7().setEnabled(false);
			}
		});
		button_7.setBounds(700, 146, 134, 38);
		panel.add(button_7);

		btnConnect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					adminGUI.run();
					getTextArea().append("ready\n");
					txtNotConnected.setText("CONNECTED");

					btnConnect.setEnabled(false);
					getBtnNewGame().setEnabled(true);
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

				getBtnAddPlayer().setEnabled(true);
				getButton().setEnabled(true);
				getButton_10().setEnabled(true);
				getButton_5().setEnabled(true);
			}
		});

		slider_1.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (source.getValueIsAdjusting()) {
					getLabel_1().setText(source.getValue() + "");

					GameMessage gameMessage = new GameMessage();
					gameMessage.setMessageType(MessageType.SPEED);
					gameMessage.setContent(source.getValue() + "");
					gameMessage.setId(txtId.getText());
					adminGUI.send(gameMessage);
				}
			}
		});

		slider_2.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (source.getValueIsAdjusting()) {
					getLabel_2().setText(source.getValue() + "");

					GameMessage gameMessage = new GameMessage();
					gameMessage.setMessageType(MessageType.SPEED);
					gameMessage.setContent(source.getValue() + "");
					gameMessage.setId(txtId.getText());
					adminGUI.send(gameMessage);
				}
			}
		});

		slider_3.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (source.getValueIsAdjusting()) {
					getLabel_3().setText(source.getValue() + "");

					GameMessage gameMessage = new GameMessage();
					gameMessage.setMessageType(MessageType.SPEED);
					gameMessage.setContent(source.getValue() + "");
					gameMessage.setId(txtId.getText());
					adminGUI.send(gameMessage);
				}
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

	public JLabel getLabel() {
		return label;
	}

	public JLabel getLabel_1() {
		return label_1;
	}

	public JLabel getLabel_2() {
		return label_2;
	}

	public JLabel getLabel_3() {
		return label_3;
	}

	public JButton getBtnNewGame() {
		return btnNewGame;
	}

	public JButton getBtnAddPlayer() {
		return btnAddPlayer;
	}

	public JButton getButton() {
		return button;
	}

	public JButton getButton_10() {
		return button_10;
	}

	public JButton getButton_5() {
		return button_5;
	}

	public JButton getButton_2() {
		return button_2;
	}

	public JButton getButton_7() {
		return button_7;
	}
}
