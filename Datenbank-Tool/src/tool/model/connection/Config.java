package tool.model.connection;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class Config {

	private Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
	private final String configPath = "Plugins//DB_Tool//config.json";
	private final File file = new File(configPath);

	/**
	 * muss implemintiert werden
	 * 
	 * @param ConnectionData Datenbank zugaenge
	 */
	public void createConfig(ConnectionData connectionData) {

		List<ConnectionData> currentList = new ArrayList<ConnectionData>();
		if (readConfigList() != null) {
			if (readConfigList().size() == 0) {
				file.delete();
			}
			currentList = readConfigList();
			currentList.add(connectionData);
		} else {

			currentList.add(connectionData);
		}

		try (FileWriter fileWriter = new FileWriter(file)) {
			if (!file.exists()) {
				file.getParentFile().mkdir();
				file.createNewFile();

				fileWriter.write(gson.toJson(currentList));
				fileWriter.flush();
				fileWriter.close();
			}
			fileWriter.write(gson.toJson(currentList));
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createConfigList(List<ConnectionData> connectionDataList) {
		try (FileWriter fileWriter = new FileWriter(file)) {
			if (!file.exists()) {
				file.getParentFile().mkdir();
				file.createNewFile();

				fileWriter.write(gson.toJson(connectionDataList));
				fileWriter.flush();
				fileWriter.close();
			}
			fileWriter.write(gson.toJson(connectionDataList));
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ConnectionData readConfig() {
		try {
			if (!file.exists()) {
				/*
				 * exeption output!
				 */
				file.getParentFile().mkdir();
				file.createNewFile();
				ConnectionData config = gson.fromJson(new FileReader(file), ConnectionData.class);
				return config;
			}
			ConnectionData config = gson.fromJson(new FileReader(file), ConnectionData.class);
			return config;
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ConnectionData> readConfigList() {
		try {
			if (!file.exists()) {
				/*
				 * exeption output!
				 */
				file.getParentFile().mkdir();
				file.createNewFile();
				Type listType = new TypeToken<ArrayList<ConnectionData>>() {
				}.getType();
				List<ConnectionData> configList = new Gson().fromJson(new FileReader(file), listType);
				// List<ConnectionData> configList = gson.fromJson(new FileReader(file),
				// List.class);
				// System.err.println(configList);
				return configList;
			}
			Type listType = new TypeToken<ArrayList<ConnectionData>>() {
			}.getType();
			List<ConnectionData> configList = new Gson().fromJson(new FileReader(file), listType);
			// List<ConnectionData> configList = gson.fromJson(new FileReader(file),
			// List.class);
			// System.err.println(configList);
			return configList;
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void modifyConfigValue(ConnectionData connectionData) {

		// copy the the existing config to new object
		// ConnectionData newJsonConfig = readConfig();
		/*
		 * ConnectionData newJsonConfig = connectionData;
		 * 
		 * // modify the value depend on the user data
		 * newJsonConfig.setDatenbank(connectionData.getDatenbank());
		 * newJsonConfig.setUsername(connectionData.getUsername());
		 * newJsonConfig.setPasswort(connectionData.getPasswort());
		 * newJsonConfig.setServiceName(connectionData.getServiceName());
		 * newJsonConfig.setPort(connectionData.getPort());
		 * 
		 * // convert the object to json file createConfig(newJsonConfig);
		 */
		createConfig(connectionData);
	}

}
