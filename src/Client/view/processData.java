package Client.view;

public class processData {
	public String processRegister(String username, String pass, String phone, String email) {
		String jsonString= "{\"method\": \"DAOsRegister\","
				+ "\"data\": {\"username\": \""+username+"\" , \"password\": \""+pass+"\", \"phone\": \""+phone+"\", \"email\": \""+email+"\" }}";
		return jsonString;
	}
	public String processRegisterError(String typeError, String message) {
		String jsonString = "{\"type\": \""+typeError+"\", \"message\": \""+message+"\"}";
		return jsonString;
	}
}
