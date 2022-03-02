package tool.model.connection;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class Config {

	private Gson gson = new Gson();
	private final String configPath = "Plugins//DB_Tool//config.json";
	private final File file = new File(configPath);

	/*
	 * muss implemintiert werden
	 */
	public void saveConfig(UserData userData) {

		try (FileWriter fileWriter = new FileWriter(file)) {
			if (!file.exists()) {
				file.getParentFile().mkdir();
				file.createNewFile();

				fileWriter.write(gson.toJson(userData));
				fileWriter.flush();
				fileWriter.close();
			}
			fileWriter.write(gson.toJson(userData));
			fileWriter.flush();
			fileWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public UserData readConfig() {
		try {
			if (!file.exists()) {
				/*
				 * exeption output!
				 */
				file.getParentFile().mkdir();
				file.createNewFile();
				UserData config = gson.fromJson(new FileReader(file), UserData.class);
				return config;
			}
			UserData config = gson.fromJson(new FileReader(file), UserData.class);
			return config;
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void modifyConfigValue(String typUserData, String newValue) {

		//copy the the existing config to new object
		UserData newJsonConfig = readConfig();

		//modify the value depend on the user data
		switch (typUserData) {
		case "username":
			newJsonConfig.setUsername(newValue);
			break;
		case "passwort":
			newJsonConfig.setPasswort(newValue);
			break;
		case "serverName":
			newJsonConfig.setService(newValue);
			break;
		case "port":
			newJsonConfig.setPort(newValue);
			break;
		default:
			System.out.println("none of them");
		}
		// convert the object to json file
		saveConfig(newJsonConfig);

	}
}
