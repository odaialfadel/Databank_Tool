package tool.excaption;

public class DatabaseException extends Exception {
	public DatabaseException(String msg) {
		super(msg);
	}
	
	public static void throwUserInvalid() throws DatabaseException {
		throw new DatabaseException("User name invalid!");
	}
}
