package Client.view;

import java.util.ArrayList;

import com.google.gson.Gson;

public class processData {
	public String processRegister(String username, String pass, String phone, String email) {
		String jsonString= "{\"method\": \"DAOsRegister\","
				+ "\"data\": {\"username\": \""+username+"\" , \"password\": \""+pass+"\", \"phone\": \""+phone+"\", \"email\": \""+email+"\" }}";
		return jsonString;
	}
	public String processError(String typeError, String message) {
		String jsonString = "{\"type\": \""+typeError+"\", \"message\": \""+message+"\"}";
		return jsonString;
	}
	public String processLogin(String phone, String password) {
		String jsonString = "{\"method\": \"DAOsLogin\","
				+ "\"data\": {\"phone\": \""+phone+"\" , \"password\": \""+password+"\"}}";
		return jsonString;
	}
	public String processIpPort (String ServerIp, int port) {
		String jsonString = "{\"serverIP\": \""+ServerIp+"\", \"port\": \""+port+"\"}";
		return jsonString;
	}
	
	public String processPhoneBook() {
		String jsonString=  "{\"method\": \"DAOsQuerryPhoneBook\"}";
		return jsonString;
	}
	
	public String processPhoneBooktoUser(ArrayList usernameList){
		// Khởi tạo đối tượng Gson
		Gson gson = new Gson();

		// Chuyển đổi usernameList thành chuỗi JSON
		String json = gson.toJson(usernameList);

		// In chuỗi JSON
		return json;
	}
}
