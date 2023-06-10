package Client.view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Client.model.Account;
import Server.controller.MainServer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class WindowLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtPhoneLogin;
	private JTextField txtPhoneRegister;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JPasswordField txtPassRegis;
	private JPasswordField txtConfirmPass;
	private JLabel confirmPassError;
	private JPanel registerPanel;
	private JLabel exitsPhoneError;
	private JPasswordField txtPassLogin;
	private JLabel wrongPassError;
	private JTextField txtServerIP;
	private JTextField txtPort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowLogin frame = new WindowLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 public static void showWindow() {
	    	WindowLogin frame = new WindowLogin();
	        frame.setVisible(true);
	    }
	/**
	 * Create the frame.
	 */
	public WindowLogin() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1019, 652);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 493, 615);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, 493, 615);
		ImageIcon icon = new ImageIcon(WindowLogin.class.getResource("/resoures/background.png"));
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImage);
		
		lblNewLabel.setIcon(newIcon);
		lblNewLabel.setOpaque(false);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		CardLayout cardLayout = new CardLayout();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(493, 0, 512, 615);
		contentPane.add(panel_1);
		panel_1.setLayout(cardLayout);
		
		JPanel ServerIPpanel = new JPanel();
		ServerIPpanel.setBackground(new Color(255, 255, 255));
		panel_1.add(ServerIPpanel, "ServerIPpanel");
		ServerIPpanel.setLayout(null);
		
		txtServerIP = new JTextField();
		txtServerIP.setText("192.168.1.143");
		txtServerIP.setColumns(10);
		txtServerIP.setBounds(83, 283, 169, 38);
		ServerIPpanel.add(txtServerIP);
		
		JLabel lblNewLabel_2_3 = new JLabel("ServerIP");
		lblNewLabel_2_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_2_3.setBounds(83, 260, 127, 22);
		ServerIPpanel.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Port");
		lblNewLabel_2_3_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_2_3_1.setBounds(262, 260, 73, 22);
		ServerIPpanel.add(lblNewLabel_2_3_1);
		
		txtPort = new JTextField();
		txtPort.setColumns(10);
		txtPort.setBounds(262, 283, 186, 38);
		ServerIPpanel.add(txtPort);
		
		JLabel portError = new JLabel("Can't find port");
		portError.setVisible(false);
		portError.setForeground(Color.RED);
		portError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		portError.setBounds(366, 260, 82, 22);
		ServerIPpanel.add(portError);
		
		JButton btnNext = new JButton("NEXT ->");
		btnNext.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNext.setForeground(new Color(255, 255, 255));
		btnNext.setBorder(null);
		btnNext.setBackground(new Color(0, 0, 0));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				System.out.println(port);
				boolean b=true;
				try {
					String test=new sendRequest().Fetch("{'method':\"\"}",txtServerIP.getText(),Integer.parseInt(txtPort.getText()));
				}
				catch(Exception exception) {
					b=false;
				}
				if(b) {
					cardLayout.show(panel_1, "login_panel");
//					Tạo file lưu port
					File file = new File("src/Client/view/ServerIP.txt");
					String serverIP = txtServerIP.getText();
					int port = Integer.parseInt(txtPort.getText());

					try {
					    FileWriter writer = new FileWriter(file);
					    writer.write("Server IP: " + serverIP + "\n");
					    writer.write("Port: " + port + "\n");
					    writer.close();
					    System.out.println("File đã được tạo và lưu thông tin thành công.");
					} catch (IOException e1) {
					    System.out.println("Lỗi khi tạo và lưu file: " + e1.getMessage());
					}
				}
				else {
					portError.setVisible(true);
				}
			}
		});
		btnNext.setBounds(335, 359, 113, 38);
		ServerIPpanel.add(btnNext);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setBackground(Color.WHITE);
		panel_1.add(loginPanel, "login_panel");
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 32));
		lblNewLabel_1.setBounds(221, 87, 98, 52);
		loginPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone Number");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_2.setBounds(82, 166, 127, 22);
		loginPanel.add(lblNewLabel_2);
		
		txtPhoneLogin = new JTextField();
		txtPhoneLogin.setColumns(10);
		txtPhoneLogin.setBounds(82, 189, 365, 38);
		loginPanel.add(txtPhoneLogin);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password");
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(82, 250, 127, 22);
		loginPanel.add(lblNewLabel_2_1);
		
		JButton btnLoginmain = new JButton("Login");
		btnLoginmain.setBorder(null);
		btnLoginmain.setForeground(new Color(255, 255, 255));
		btnLoginmain.setBackground(new Color(0, 0, 0));
		btnLoginmain.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnLoginmain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phone = txtPhoneLogin.getText();
				String password = new String(txtPassLogin.getPassword());
				//Tao doi tuong processData de xu ly du lieu
				processData processData = new processData();
				String request = processData.processLogin(phone, password);
				String resultString = null;
				try {
					resultString = new sendRequest().Fetch(request, txtServerIP.getText(), Integer.parseInt(txtPort.getText()));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				Xu ly du lieu nhan ve
				JsonObject jobj = new Gson().fromJson(resultString, JsonObject.class);
				String type = jobj.get("type").toString();
				if (MainServer.removeNgoac(type).equals("error")) {
					JOptionPane.showMessageDialog(null, "Đăng nhập không thành công do sai mật khẩu hoặc tài khoản chưa được đăng kí", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}else {
					wrongPassError.setVisible(false);
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					new MainChat().show();
					hide();
				}
				
			}
		});
		btnLoginmain.setBounds(82, 352, 365, 46);
		loginPanel.add(btnLoginmain);
		
		JLabel lblNewLabel_3 = new JLabel("Don't have account ?");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(82, 416, 127, 13);
		loginPanel.add(lblNewLabel_3);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegister.setBackground(new Color(255, 255, 255));
		btnRegister.setBorder(null);
		btnRegister.setBounds(202, 412, 62, 21);
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel_1, "register_panel");
			}
		});
		loginPanel.add(btnRegister);
		
		txtPassLogin = new JPasswordField();
		txtPassLogin.setBounds(82, 279, 365, 38);
		loginPanel.add(txtPassLogin);
		
		wrongPassError = new JLabel("Wrong password");
		wrongPassError.setVisible(false);
		wrongPassError.setForeground(new Color(255, 0, 0));
		wrongPassError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		wrongPassError.setBounds(349, 252, 98, 22);
		loginPanel.add(wrongPassError);
		
		
		registerPanel = new JPanel();
		registerPanel.setOpaque(false);
		registerPanel.setBackground(new Color(255, 255, 255));
		panel_1.add(registerPanel, "register_panel");
		registerPanel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sign up");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 32));
		lblNewLabel_1_1.setBounds(202, 33, 127, 52);
		registerPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Phone Number");
		lblNewLabel_2_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(80, 318, 127, 21);
		registerPanel.add(lblNewLabel_2_2);
		
		txtPhoneRegister = new JTextField();
		txtPhoneRegister.setColumns(10);
		txtPhoneRegister.setBounds(80, 341, 365, 38);
		registerPanel.add(txtPhoneRegister);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Password");
		lblNewLabel_2_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_2_1_1.setBounds(80, 176, 127, 21);
		registerPanel.add(lblNewLabel_2_1_1);
		
		JButton btnRegistermain = new JButton("Sign Up");
		btnRegistermain.setForeground(Color.WHITE);
		btnRegistermain.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnRegistermain.setBorder(null);
		btnRegistermain.setBackground(Color.BLACK);
		btnRegistermain.setBounds(80, 471, 365, 46);
		btnRegistermain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtUsername.getText();
				String pass = new String(txtPassRegis.getPassword());
				String confirmpass = new String(txtConfirmPass.getPassword());
				String phone = txtPhoneRegister.getText();
				String email = txtEmail.getText();
				if (!pass.equals(confirmpass)) {
					confirmPassError.setVisible(true);
				} else {
					processData processData = new processData();
					String request = processData.processRegister(user, pass, phone, email);
					String resultString = null;
					try {
						resultString = new sendRequest().Fetch(request, txtServerIP.getText(), Integer.parseInt(txtPort.getText()));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					Kiem tra xem co nhan duoc du lieu tu server khong
					System.out.println(resultString);
//					Xu ly du lieu nhan ve tu server
					JsonObject jobj = new Gson().fromJson(resultString, JsonObject.class);
					String type = jobj.get("type").toString();
					System.out.println(type);
					if(MainServer.removeNgoac(type).equals("error")) {
						exitsPhoneError.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Đăng kí thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		registerPanel.add(btnRegistermain);
		
		JLabel lblNewLabel_3_1 = new JLabel("Already have account?");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3_1.setBounds(80, 531, 127, 13);
		registerPanel.add(lblNewLabel_3_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(panel_1, "login_panel");
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBorder(null);
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(202, 527, 57, 21);
		registerPanel.add(btnLogin);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(80, 128, 365, 38);
		registerPanel.add(txtUsername);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Username");
		lblNewLabel_2_2_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_2_2_1.setBounds(80, 105, 127, 21);
		registerPanel.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Confirm Password");
		lblNewLabel_2_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_2_1_1_1.setBounds(80, 247, 149, 21);
		registerPanel.add(lblNewLabel_2_1_1_1);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(80, 412, 365, 38);
		registerPanel.add(txtEmail);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("Email");
		lblNewLabel_2_2_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_2_2_2.setBounds(80, 389, 127, 21);
		registerPanel.add(lblNewLabel_2_2_2);
		
		confirmPassError = new JLabel("Does not match");
		confirmPassError.setVisible(false);
		confirmPassError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		confirmPassError.setForeground(new Color(255, 0, 0));
		confirmPassError.setBounds(348, 253, 97, 13);
		registerPanel.add(confirmPassError);
		
		txtPassRegis = new JPasswordField();
		txtPassRegis.setBounds(80, 201, 365, 38);
		registerPanel.add(txtPassRegis);
		
		txtConfirmPass = new JPasswordField();
		txtConfirmPass.setBounds(80, 270, 365, 38);
		registerPanel.add(txtConfirmPass);
		
		exitsPhoneError = new JLabel("Already exits");
		exitsPhoneError.setVisible(false);
		exitsPhoneError.setForeground(new Color(255, 0, 0));
		exitsPhoneError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		exitsPhoneError.setBounds(367, 324, 78, 13);
		registerPanel.add(exitsPhoneError);
		
	}
}
