package DAOs;

import java.sql.*;
public class Condb {
	static String url = "jdbc:mysql://localhost:3306/chatbox";
	static String user = "root";
	static String password = "";

	public static Connection getConnection() {
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, user, password);
			if (c != null)
            {
         //    System.out.print("Connected to database successfully");    
             return c;
            }  
		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return c;
	}
	 public static void closeDBConnect(Connection c)
	    {
	        try{
	            if (c!= null){
	                c.close();
	            }
	        } catch (Exception e){
	            e.printStackTrace();
	        }   
	    }
	}