package DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Client.model.Account;
import Client.model.AccountList;

public class AccountListDB implements DAOinterface<AccountList> {
	public static AccountListDB getInstance() {
		return new AccountListDB();
	}


	@Override
	public int insert (AccountList t) {
		
			int ketQua = 0;
			try {
				// Tao ket noi den CSDL
				Connection c = Condb.getConnection();

				// Tao doi tuong Statement
				Statement st = c.createStatement();

				// Thuc thi cau lenh SQL
				String sql = "INSERT INTO AccountList (phone, port)" + "VALUES  ('"
						+  t.getPhone() + "','" + t.getPort() 
					 + "'" + ")";

				ketQua = st.executeUpdate(sql);
				// ngat ket noi
				Condb.closeDBConnect(c);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ketQua;
		}


	@Override
	public int update(AccountList t) {
		int ketQua = 0;
		try {
			Connection c = new Condb().getConnection();
			Statement st = c.createStatement();

			String sql = "UPDATE AccountList" + " SET" + " `port`='" + t.getPort() +  "'"
					+ " WHERE AccountList.`phone`=" + t.getPhone();
			ketQua = st.executeUpdate(sql);

			Condb.closeDBConnect(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}


	@Override
	public int delete(AccountList t) {
		return 0;

	}


//	@Override
//	public ArrayList<AccountList> selectAll() {
//		ArrayList<AccountList> ketQua = new ArrayList<AccountList>();
//		try {
//			Connection c = new Condb().getConnection();
//
//			Statement st = c.createStatement();
//
//			String sql = "SELECT * FROM AccountList WHERE 1";
//
//			ResultSet rs = st.executeQuery(sql);
//
//			while (rs.next()) {
//				
//				String phone= rs.getString("phone");
//				int port= rs.getInt("port");
//				AccountList acc= new AccountList( phone, port);
//				ketQua.add(acc);
//			}
//			Condb.closeDBConnect(c);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return ketQua;
//	}


//	@Override
//	public AccountList selectById(String id) {
//		AccountList ketQua = null;
//		try {
//			Connection c = Condb.getConnection();
//
//			Statement st = c.createStatement();
//
//			String sql = "SELECT * FROM AccountList WHERE phone = "+id+"";
//
//			ResultSet rs = st.executeQuery(sql);
//
//			while (rs.next()) {
//
//				String phone= rs.getString("phone");
//				int port= rs.getInt("port");
//		
//				ketQua =  new AccountList( phone, port);
//
//			}
//			Condb.closeDBConnect(c);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return ketQua;
//		
//	}

	public ArrayList<AccountList> selectUserPhoneBook(int port) {

		ArrayList<AccountList> ketQua = new ArrayList<AccountList>();
		try {
			Connection c = new Condb().getConnection();

			Statement st = c.createStatement();

			String sql = "SELECT accountlist.phone, MIN(username) AS username, MIN(port) AS port, MIN(IDList) AS IDList FROM AccountList INNER JOIN Account ON AccountList.phone = Account.phone WHERE port = '"+port+"' GROUP BY accountlist.phone";

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				String username = rs.getString("username");
				String phone= rs.getString("phone");
				int port1= rs.getInt("port");
				int id = rs.getInt("IDList");

				AccountList acc =  new AccountList( username,phone, port1, id);
				ketQua.add(acc);
			}
			Condb.closeDBConnect(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}


	@Override
	public ArrayList<AccountList> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AccountList selectById(String st) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<AccountList> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ArrayList<AccountList> selectByCondition(String condition) {
//
//		ArrayList<AccountList> ketQua = new ArrayList<AccountList>();
//		try {
//			Connection c = new Condb().getConnection();
//
//			Statement st = c.createStatement();
//
//			String sql = "SELECT * FROM AccountList WHERE" + condition;
//
//			ResultSet rs = st.executeQuery(sql);
//
//			while (rs.next()) {
//				
//				String phone= rs.getString("phone");
//				int port= rs.getInt("port");
//
//				AccountList acc =  new AccountList( phone, port);
//				ketQua.add(acc);
//			}
//			Condb.closeDBConnect(c);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return ketQua;
//	}
//



}