package gui.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class AdminGUITemplate extends JFrame {

	private JPanel contentPane;
	private JTextField txtAleksanderWtf;
	private JTextField txtNotConnected;
	private JTextField txtNotConnected_1;
	private JTextField txtNotConnected_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JPanel panel_4;
	private JCheckBox chckbxNewCheckBox;
	private JSlider slider;
	private JLabel lblGameSpeed;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JButton btnStartNewGame;
	private JButton button;
	private JButton btnNewGame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUITemplate frame = new AdminGUITemplate();
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
	public AdminGUITemplate() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1378, 733);
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
		panel_1.setBounds(15, 111, 1342, 568);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Car", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(840, 213, 487, 339);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(176, 37, 137, 289);
		panel_2.add(panel_3);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setBounds(15, 79, 146, 26);
		panel_2.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setBounds(15, 259, 146, 26);
		panel_2.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(328, 259, 146, 26);
		panel_2.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(328, 79, 146, 26);
		panel_2.add(textField_6);

		label = new JLabel("0%");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(15, 116, 146, 20);
		panel_2.add(label);

		label_1 = new JLabel("0%");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(15, 301, 146, 20);
		panel_2.add(label_1);

		label_2 = new JLabel("0%");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(328, 301, 146, 20);
		panel_2.add(label_2);

		label_3 = new JLabel("0%");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(328, 121, 146, 20);
		panel_2.add(label_3);

		panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Rules", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(15, 32, 498, 520);
		panel_1.add(panel_4);
		panel_4.setLayout(null);

		chckbxNewCheckBox = new JCheckBox("FUEL LIMIT");
		chckbxNewCheckBox.setBounds(15, 63, 135, 29);
		panel_4.add(chckbxNewCheckBox);

		slider = new JSlider();
		slider.setValue(100);
		slider.setMinimum(50);
		slider.setBounds(214, 28, 269, 23);
		panel_4.add(slider);

		slider.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				Point p = e.getPoint();
				double percent = p.x / ((double) slider.getWidth());
				int range = slider.getMaximum() - slider.getMinimum();
				double newVal = range * percent;
				int result = (int) (slider.getMinimum() + newVal);
				slider.setValue(result);
				lblGameSpeed.setText("Game speed " + result + "%");
			}

		});

		slider.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				Point p = e.getPoint();
				double percent = p.x / ((double) slider.getWidth());
				int range = slider.getMaximum() - slider.getMinimum();
				double newVal = range * percent;
				int result = (int) (slider.getMinimum() + newVal);
				slider.setValue(result);
				lblGameSpeed.setText("Game speed " + result + "%");
			}

		});

		lblGameSpeed = new JLabel("Game speed 100%");
		lblGameSpeed.setBounds(15, 31, 184, 20);
		panel_4.add(lblGameSpeed);

		btnStartNewGame = new JButton("START GAME");
		btnStartNewGame.setEnabled(false);
		btnStartNewGame.setBounds(1075, 75, 252, 29);
		panel_1.add(btnStartNewGame);

		button = new JButton("END GAME");
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(1075, 120, 252, 29);
		panel_1.add(button);

		btnNewGame = new JButton("NEW GAME");
		btnNewGame.setBounds(1075, 28, 252, 29);
		panel_1.add(btnNewGame);
	}

}
