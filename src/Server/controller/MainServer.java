package Server.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Client.model.Account;
import Client.model.AccountList;
import Client.view.ServerIP;
import Client.view.processData;
import Client.view.sendRequest;
import DAOs.AccountDB;
import DAOs.AccountListDB;
import DAOs.Condb;

public class MainServer {
//	public final static int port = ServerIP.port;
	
	public MainServer(int port) {
		// TODO Auto-generated constructor stub
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server running");
			
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Connected " + socket.getInetAddress());
				DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
				
				DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
				
				Thread thread = new Thread (new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
								String dataFromUser = dataInputStream.readUTF();
								System.out.println(dataFromUser);
								JsonObject jobj = new Gson().fromJson(dataFromUser, JsonObject.class);
								String method = jobj.get("method").toString();
//								System.out.println(method);
								String checkMethod = removeNgoac(method);
//								System.out.println(checkMethod);
							switch (checkMethod) {
								case "": 
									dataOutputStream.writeUTF("");
								break;
					
								case "DAOsRegister":
									String username = 	removeNgoac(jobj.getAsJsonObject("data").get("username").toString());
									String password = 	removeNgoac(jobj.getAsJsonObject("data").get("password").toString());
									String	phone = 	removeNgoac(jobj.getAsJsonObject("data").get("phone").toString());
									String email = 	removeNgoac(jobj.getAsJsonObject("data").get("email").toString());
									
									Account account = new Account(username, password,phone,email);
//									String string= account.toString();
//									System.out.println(string);
									Register(account, dataOutputStream);
								break;
								
								case "DAOsLogin":
									String phoneLogin = removeNgoac(jobj.getAsJsonObject("data").get("phone").toString());
									String passwordLogin = removeNgoac(jobj.getAsJsonObject("data").get("password").toString());
									Account accLogin = new Account(phoneLogin, passwordLogin);
									//Thuc hien dang nhap
									Login(accLogin, dataOutputStream, port);
									
								case "DAOsQuerryPhoneBook": 
									QuerryPhoneBook(port,dataOutputStream);
								break;
							
							}
							
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						} finally {
							try {
//								dataInputStream.close();
//								dataOutputStream.close();
								socket.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
				thread.start();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void Register(Account acc, DataOutputStream sendToUser) {
		Account account = new AccountDB().selectById(acc.getPhone());
//		String String = account.toString();
//		System.out.println(String);
		if (account!=null) {
			String dataToUser = new processData().processError("error", "ExistedPhone");
			try {
				sendToUser.writeUTF(dataToUser);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			String dataToUser = new processData().processError("success", "Register Successful");
			try {
				sendToUser.writeUTF(dataToUser);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Account coppyAccount = new Account(acc.getUsername(), mahoamotchieu(acc.getPassword()), acc.getPhone(), acc.getEmail());
			AccountDB.getInstance().insert(coppyAccount);
		}
	}
	
	public void Login (Account acc, DataOutputStream sentToUser, int port) {
		Account account = AccountDB.getInstance().selectById(acc.getPhone());
		if (account==null) {
			String datatoUser = new processData().processError("error", "WrongPass");
			try {
				sentToUser.writeUTF(datatoUser);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!account.getPassword().equals(mahoamotchieu(acc.getPassword()))) {
			String datatoUser = new processData().processError("error", "WrongPass");
			try {
				sentToUser.writeUTF(datatoUser);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String datatoUser = new processData().processError("success", "Login Successful");
			AccountList accList = new AccountList(account.getUsername(), account.getPhone(),port, randomID());
			AccountListDB.getInstance().insert(accList);
			try {
				sentToUser.writeUTF(datatoUser);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public void QuerryPhoneBook(int port,DataOutputStream senttoUser) {
		ArrayList<AccountList> accountLists = AccountListDB.getInstance().selectUserPhoneBook(port);
		ArrayList<String> usernameList = new ArrayList<String>();
		for (AccountList account : accountLists) {
		    String username = account.getUsername();
		    usernameList.add(username);
		}
		String datatoUser = new processData().processPhoneBooktoUser(usernameList);
		System.out.println(datatoUser);
		try {
			senttoUser.writeUTF(datatoUser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// Xu ly ngoac kep
	public static String removeNgoac(String input) {
		return input.replaceAll("^\"|\"$", "");
	}
	// Ham ma hoa du lieu mot chieu
	public String mahoamotchieu(String input) {
		 try {
		        MessageDigest md = MessageDigest.getInstance("MD5");
		        byte[] hashedInput = md.digest(input.getBytes());
		        StringBuilder sb = new StringBuilder();
		        for (byte b : hashedInput) {
		            sb.append(String.format("%02x", b));
		        }
		        return sb.toString();
		    } catch (NoSuchAlgorithmException e) {
		        e.printStackTrace();
		        return null;
		    }
	}
//	ham tao ID Random
	public static int randomID() {
		boolean check=false;
		double randomDouble;
		int random;
		do {
			check=false;
			randomDouble = Math.random();
			randomDouble = randomDouble*9999+1;
			random = (int)randomDouble;
			String sql1= "SELECT IDList FROM accountlist ";
			try {
				Connection connection = Condb.getConnection();
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql1);
				while (rs.next()) {
					if (rs.getInt(1)==random) {
						check=true;
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} while (check==true);
		return random;
	}
//	public void closeServer ()
//	public static void main(String[] args) {
//		new MainServer();
//	}
}
