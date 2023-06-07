package Client.view;

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
}
