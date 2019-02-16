package gui.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.cartest.CarTestGUI;
import gui.devadmin.AdminGUI;
import gui.devadmin.GUITemplate;
import gui.simpleclient.SimpleClient;

public class ChooseGUITemplate extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseGUITemplate frame = new ChooseGUITemplate();
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
	public ChooseGUITemplate() {
		setTitle("CAR MANAGEMENT");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("DEV SERVER GUI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminGUI adminGUI = new AdminGUI();
				GUITemplate.adminGUI = adminGUI;
				System.out.println("GUITemplate.adminGUI = " + GUITemplate.adminGUI);
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(15, 16, 459, 79);
		contentPane.add(btnNewButton);

		JButton btnSimpleClient = new JButton("SIMPLE CLIENT");
		btnSimpleClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SimpleClient();
			}
		});
		btnSimpleClient.setForeground(new Color(0, 128, 0));
		btnSimpleClient.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSimpleClient.setBounds(15, 301, 459, 79);
		contentPane.add(btnSimpleClient);

		JButton btnDevTestGui = new JButton("DEV CAR TEST");
		btnDevTestGui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CarTestGUI();
			}
		});
		btnDevTestGui.setForeground(Color.RED);
		btnDevTestGui.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDevTestGui.setBounds(15, 111, 459, 79);
		contentPane.add(btnDevTestGui);

		JButton btnAdminPanel = new JButton("ADMIN PANEL");
		btnAdminPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new gui.admin.AdminGUI();
			}
		});
		btnAdminPanel.setForeground(new Color(0, 128, 0));
		btnAdminPanel.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdminPanel.setBounds(15, 206, 459, 79);
		contentPane.add(btnAdminPanel);
	}

}
