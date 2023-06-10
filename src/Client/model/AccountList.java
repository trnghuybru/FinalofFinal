package Client.model;

public class AccountList extends Account{
	private int port;
	private int idList;
	
	public AccountList(String username,String phone, int port,int idList)
	{	
		super(username,"",phone);
		this.port=port;
		this.idList =idList;
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	
	
}