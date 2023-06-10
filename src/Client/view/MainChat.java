package Client.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.CardLayout;

import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.jgoodies.forms.layout.FormLayout;
import com.google.gson.Gson;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class MainChat extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTextField txtSendMess;
	private Box vbox;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainChat frame = new MainChat();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MainChat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 981, 656);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel navPanel = new JPanel();
		navPanel.setBorder(null);
		navPanel.setBackground(new Color(0, 144, 255));
		navPanel.setBounds(0, 0, 74, 619);
		contentPane.add(navPanel);
		navPanel.setLayout(null);
		
		JButton btnMess = new JButton("");
		btnMess.setBackground(new Color(0, 144, 255));
		btnMess.setBorder(null);
		btnMess.setIcon(new ImageIcon(MainChat.class.getResource("/resoures/chat-bubble.png")));
		btnMess.setBounds(0, 234, 74, 74);
		navPanel.add(btnMess);
		
		
		JPanel functionPanel = new JPanel();
		CardLayout cardLayout = new CardLayout();
		functionPanel.setBounds(75, 0, 892, 619);
		contentPane.add(functionPanel);
		functionPanel.setLayout(cardLayout);
		
		JPanel chatPanel = new JPanel();
		functionPanel.add(chatPanel, "chatPanel");
		chatPanel.setLayout(null);
		
		JPanel friendsPanel = new JPanel();
		friendsPanel.setBackground(new Color(255, 255, 255));
		friendsPanel.setBounds(0, 0, 332, 619);
		chatPanel.add(friendsPanel);
		friendsPanel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 10, 312, 41);
		friendsPanel.add(panel_2);
		panel_2.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setBackground(new Color(233, 234, 239));
		txtSearch.setBounds(0, 0, 265, 41);
		panel_2.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon(MainChat.class.getResource("/resoures/search.png")));
		btnSearch.setBorder(null);
		btnSearch.setBounds(265, 0, 47, 41);
		panel_2.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 61, 332, 558);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		friendsPanel.add(scrollPane);
		
		
		JPanel phoneBookPanel = new JPanel();
		phoneBookPanel.setBackground(new Color(255, 255, 255));
//		phoneBookPanel.setLayout(null);
		scrollPane.setViewportView(phoneBookPanel);
		
		vbox = Box.createVerticalBox();
		String usernameJson =getDataPhoneBook();
		int numofPanel =  getNumberOfPanels(usernameJson);
		System.out.println(numofPanel);
		// Thêm các panel con vào Box
        for (int i = 0; i < numofPanel; i++) {
            JPanel panel = createPanel(i,usernameJson); // Hàm tạo panel tương ứng
            vbox.add(panel);
            vbox.add(Box.createVerticalStrut(10)); // Khoảng cách 10px giữa các panel
        }
     // Đặt Box làm nội dung của content panel
        phoneBookPanel.add(vbox, BorderLayout.PAGE_START);
		

			
		JPanel contentChatPanel = new JPanel();
		CardLayout cardLayout2 = new CardLayout();
		contentChatPanel.setBounds(333, 0, 559, 619);
		chatPanel.add(contentChatPanel);
		contentChatPanel.setLayout(cardLayout2);
		
		
		
		
	}
	private JPanel createPanel(int index, String json) {
        // Hàm này tạo và trả về một JPanel tương ứng cho mỗi panel từ cơ sở dữ liệu
		JPanel objectChatPanel = new JPanel(); 
		objectChatPanel.setBackground(new Color(0, 141, 247)); 
		objectChatPanel.setPreferredSize(new Dimension(312, 65));
		
		objectChatPanel.setLayout(null); 
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(MainChat.class.getResource("/resoures/circle-user.png"))); 
		lblNewLabel_1.setBounds(10, 0, 32, 65); 
		objectChatPanel.add(lblNewLabel_1); 
		
		// Khởi tạo đối tượng Gson
        Gson gson = new Gson();

        // Chuyển đổi chuỗi JSON thành một mảng Java
        String[] array = gson.fromJson(json, String[].class);
        
		JLabel lblNewLabel_2 = new JLabel(array[index]);
		
		lblNewLabel_2.setForeground(new Color(255, 255, 255)); 	
		lblNewLabel_2.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
		lblNewLabel_2.setBounds(60, 25, 255, 19); 
		objectChatPanel.add(lblNewLabel_2);

        return objectChatPanel;
    }
	private int getNumberOfPanels(String json) {

        // Khởi tạo đối tượng Gson
        Gson gson = new Gson();

        // Chuyển đổi chuỗi JSON thành một mảng Java
        String[] array = gson.fromJson(json, String[].class);

        // Trả về số lượng phần tử trong mảng
        int count = array.length;

		return count;
	}
	
	private String getDataPhoneBook() {
		//Gui yeu cau toi server de tra ve mot so luong nguoi trong server va ten nguoi dung
				String request= new processData().processPhoneBook();
				//Doc file lấy ra port, IP server
				File file = new File("src/Client/view/ServerIP.txt");
				String serverIP = "";
				int port = 0;

				try (Scanner scanner = new Scanner(file)) {
				    while (scanner.hasNextLine()) {
				        String line = scanner.nextLine();
				        if (line.contains("Server IP:")) {
				            serverIP = line.substring(line.indexOf(":") + 1).trim();
				        } else if (line.contains("Port:")) {
				            port = Integer.parseInt(line.substring(line.indexOf(":") + 1).trim());
				        }
				    }
				} catch (FileNotFoundException e) {
				    System.out.println("Không tìm thấy file: " + e.getMessage());
				} catch (NumberFormatException e) {
				    System.out.println("Lỗi định dạng số: " + e.getMessage());
				}

				System.out.println("Server IP: " + serverIP);
				System.out.println("Port: " + port);
				String result = null;
				try {
					result = new sendRequest().Fetch(request, serverIP , port);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return result;
	}
}
