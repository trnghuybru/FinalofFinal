package DAOs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Client.model.Account;

/**
 *
 * @author dung
 */
public class AccountDB implements DAOinterface<Account> {
	public static AccountDB getInstance() {
		return new AccountDB();
	}

	public int insert(Account t) {
		int ketQua = 0;
		try {
			// Tao ket noi den CSDL
			Connection c = Condb.getConnection();

			// Tao doi tuong Statement
			Statement st = c.createStatement();

			// Thuc thi cau lenh SQL
			String sql = "INSERT INTO `Account`(`username`, `password`,  `phone`, `email`)" + "VALUES  ('"
					+ t.getUsername() + "','" + t.getPassword() + "','" + t.getPhone() + "','" + t.getEmail() 
				 + "'" + ")";

			ketQua = st.executeUpdate(sql);
			// ngat ket noi
			Condb.closeDBConnect(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public int update(Account t) {
		int ketQua = 0;
		try {
			Connection c = new Condb().getConnection();
			Statement st = c.createStatement();

			String sql = "UPDATE `Account`" + " SET" + " `username`='" + t.getUsername() + "' , `password`='"
					+ t.getPassword() + "' , `email`='" + t.getEmail() +  "'"
					+ " WHERE `Account`.`phone`=" + t.getPhone();
			ketQua = st.executeUpdate(sql);

			Condb.closeDBConnect(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ArrayList<Account> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Account> ketQua = new ArrayList<Account>();
		try {
			Connection c = new Condb().getConnection();

			Statement st = c.createStatement();

			String sql = "SELECT * FROM `Account` WHERE 1";

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				String username= rs.getString("username");
				String password= rs.getString("password");
				String phone= rs.getString("phone");
				String email= rs.getString("email");
				Account acc= new Account(username, password, phone, email);
				ketQua.add(acc);
			}
			Condb.closeDBConnect(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public Account selectById(String id) {
		Account ketQua = null;
		try {
			Connection c = Condb.getConnection();

			Statement st = c.createStatement();

			String sql = "SELECT * FROM `Account` WHERE `phone` = "+id+"";

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				String username= rs.getString("username");
				String password= rs.getString("password");
				String phone= rs.getString("phone");
				String email= rs.getString("email");
		
				ketQua =  new Account(username, password, phone, email);

			}
			Condb.closeDBConnect(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ArrayList<Account> selectByCondition(String condition) {

		ArrayList<Account> ketQua = new ArrayList<Account>();
		try {
			Connection c = new Condb().getConnection();

			Statement st = c.createStatement();

			String sql = "SELECT * FROM `Account` WHERE" + condition;

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				String username= rs.getString("username");
				String password= rs.getString("password");
				String phone= rs.getString("phone");
				String email= rs.getString("email");
				String role= rs.getString("role");
		
				Account acc =  new Account(username, password, phone, email);
				ketQua.add(acc);
			}
			Condb.closeDBConnect(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int delete(Account t) {

		int ketQua = 0;
		try {
			Connection c = new Condb().getConnection();
			Statement st = c.createStatement();

			String sql = "DELETE FROM `Account` " + " WHERE `phone`='" + t.getPhone() + "'";

			ketQua = st.executeUpdate(sql);

			Condb.closeDBConnect(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
}