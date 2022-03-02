package tool.model.connection;

public class UserData {
	
	private String username;
	private String passwort;
	private String serviceName;
	private String port;
	
	
	
	public UserData(String username, String passwort, String service, String port) {
		this.username = username;
		this.passwort = passwort;
		this.port = port;
		this.serviceName = service;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getService() {
		return serviceName;
	}
	public void setService(String service) {
		this.serviceName = service;
	}

}
