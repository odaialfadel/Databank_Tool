package tool.model.connection;

public class ConnectionData {

	private String datenbank;
	private String username;
	private String passwort;
	private String serviceName;
	private String port;

	public ConnectionData(String datenbank, String username, String passwort, String service, String port) {
		this.datenbank = datenbank;
		this.username = username;
		this.passwort = passwort;
		this.port = port;
		this.serviceName = service;
	}

	public String getDatenbank() {
		return datenbank;
	}

	public void setDatenbank(String datenbank) {
		this.datenbank = datenbank;
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
	
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}


}
