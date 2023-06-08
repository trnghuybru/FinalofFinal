package Client.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.PublicKey;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import com.mysql.cj.protocol.a.result.ResultsetRowsStatic;

import Server.controller.MainServer;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerIP extends JFrame {
	public static int port;
	public static String ip;
	private JPanel contentPane;
	private JTextField txtport;
	public JTextField txtidsv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerIP frame = new ServerIP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void hide()
	{
		ServerIP frame = new ServerIP();
		frame.setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public ServerIP() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 494, 529);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 492, 529);
		ImageIcon icon = new ImageIcon(WindowLogin.class.getResource("/resoures/hh.png"));
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImage);
		panel.setLayout(null);
		
		lblNewLabel.setIcon(newIcon);
		lblNewLabel.setOpaque(false);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(492, 0, 434, 529);
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("SERVER IP");
		lblNewLabel_1.setBounds(148, 0, 220, 96);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Port :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 224, 95, 40);
		panel_1.add(lblNewLabel_2);
		
		txtport = new JTextField();
		txtport.setBounds(139, 222, 256, 42);
		panel_1.add(txtport);
		txtport.setColumns(10);
		
		txtidsv = new JTextField();
		txtidsv.setColumns(10);
		txtidsv.setBounds(139, 139, 256, 42);
		panel_1.add(txtidsv);
		//------ lấy iP trực tiếp server 
		try {
			txtidsv.setText(Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel_2_1 = new JLabel("Server IP :");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(10, 139, 95, 40);
		panel_1.add(lblNewLabel_2_1);
		
		JButton btnstart = new JButton("Start Server");
		btnstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 	port = Integer.parseInt(txtport.getText());
				 	ip = txtidsv.getText();
				 	new MainServer(port);
//	                startServer(ip, port);
	                btnstart.setEnabled(false);
//	                WindowLogin.showWindow();
	              WindowLogin.showWindow();
//	               hide();
	                

			}

			
			
		});
		
		btnstart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnstart.setBounds(37, 336, 141, 54);
		panel_1.add(btnstart);
		
		JButton btnstop = new JButton("Stop Server");
		btnstop.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnstop.setBounds(264, 336, 141, 54);
		panel_1.add(btnstop);
		
		JLabel lblNewLabel_3 = new JLabel("Hệ thống server cực đỉnh");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(108, 430, 297, 54);
		panel_1.add(lblNewLabel_3);
		
	}
//	public void ServerIP(String ip, int port) {
//		
//		    try {
//		        ServerSocket serverSocket = new ServerSocket(port, 50, InetAddress.getByName(ip));
//		        System.out.println("Server started on " + ip + ":" + port);
//
//		        // Listen for client connections
//		        while (true) {
//		            System.out.println("Waiting for client connection...");
//		            Socket clientSocket = serverSocket.accept();
//		            System.out.println("Client connected from " + clientSocket.getInetAddress().getHostAddress());
//		            
//		            // TODO: Handle client requests
//		            // ...
//		            
//		            clientSocket.close();
//		        }
//		        
//		        // Close the server socket when done
////		        serverSocket.close();
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//		}
//	private boolean isServerRunning = false;
//
//	public void startServer(String ip, int port) {
//	    if (isServerRunning) {
//	        System.out.println("Server is already running.");
//	        return;
//	    }
//
//	    try {
//	        ServerSocket serverSocket = new ServerSocket(port, 50, InetAddress.getByName(ip));
//	        System.out.println("Server started on " + ip + ":" + port);
//
//	        // Start the server thread
//	        ServerThread serverThread = new ServerThread(serverSocket);
//	        serverThread.start();
//	        
//
//	        isServerRunning = true;
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}
//
//	private class ServerThread extends Thread {
//	    private final ServerSocket serverSocket;
//
//	    public ServerThread(ServerSocket serverSocket) {
//	        this.serverSocket = serverSocket;
//	    }
//
//	    @Override
//	    public void run() {
//	        while (true) {
//	            try {
//	                System.out.println("Waiting for client connection...");
//	                Socket clientSocket = serverSocket.accept();
//	                System.out.println("Client connected from " + clientSocket.getInetAddress().getHostAddress());
//
//	                // Show the login window
//	                WindowLogin.showWindow();
//
//	                // TODO: Handle client requests
//	                // ...
//	                					
//	                clientSocket.close();
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	                break;
//	            }
//	        }
//	    }
//	}
	
	public static String getLocalIPAddress() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return "127.0.0.1"; // Default to loopback address
        }
    }
//	
	public static int getPort() {
		return port;
		
	}
}


