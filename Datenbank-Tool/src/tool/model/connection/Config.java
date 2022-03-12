package tool.model.connection;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Config {

	private Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
	private static final String CONFIGPATH = "Plugins//DB_Tool//config.json";
	private static final File MYFILE = new File(CONFIGPATH);
	private Map<String, ConnectionData> allConfig;


	public Config() {
		allConfig = readAllConfig();
	}

	public Map<String, ConnectionData> readAllConfig() {
		try {
			if (!MYFILE.exists()) {
				MYFILE.getParentFile().mkdir();
				MYFILE.createNewFile();
				Type mapType = new TypeToken<Map<String, ConnectionData>>() {
				}.getType();
				Map<String, ConnectionData> configList = new Gson().fromJson(new FileReader(MYFILE), mapType);

				return configList;
			}
			Type mapType = new TypeToken<Map<String, ConnectionData>>() {
			}.getType();
			Map<String, ConnectionData> configList = new Gson().fromJson(new FileReader(MYFILE), mapType);

			return configList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void createConfig(String datenbankName, ConnectionData connectionData) {
		Map<String, ConnectionData> currentList = new HashMap<String, ConnectionData>();

		if (allConfig != null && !allConfig.isEmpty()) {

			if (datenbankName.equals(getKeyByConfig(connectionData))) {
				currentList = allConfig;
				System.err.println("vorhanden!");

				} else {

				currentList = allConfig;
				currentList.put(datenbankName, connectionData);
				System.err.println("saved: ");
			}
		} else {
			MYFILE.delete();
			currentList.put(datenbankName, connectionData);
			System.out.println("deleted and saved: ");
		}
		try (FileWriter fileWriter = new FileWriter(MYFILE)) {
			if (!MYFILE.exists()) {
				MYFILE.getParentFile().mkdir();
				MYFILE.createNewFile();

				fileWriter.write(gson.toJson(currentList));
				fileWriter.flush();
				fileWriter.close();
			} else {

				fileWriter.write(gson.toJson(currentList));
				fileWriter.flush();
				fileWriter.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteConfig(String datenbankName) {
		Map<String, ConnectionData> currentList = new HashMap<String, ConnectionData>();

		currentList = allConfig;
		currentList.remove(datenbankName);
		System.err.println("removed!");

		try (FileWriter fileWriter = new FileWriter(MYFILE)) {
			if (!MYFILE.exists()) {
				MYFILE.getParentFile().mkdir();
				MYFILE.createNewFile();

				fileWriter.write(gson.toJson(currentList));
				fileWriter.flush();
				fileWriter.close();
			} else {

				fileWriter.write(gson.toJson(currentList));
				fileWriter.flush();
				fileWriter.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ConnectionData getValueByName(String configName) {
		if (allConfig != null && !allConfig.isEmpty()) {
			for (Map.Entry<String, ConnectionData> entry : allConfig.entrySet()) {
				if (configName.equals(entry.getKey().toString())) {
					System.err.println("there!!!:   " + entry.getKey());
					return entry.getValue();
				}
				System.err.println("not in there!!!:   " + entry.getKey());
				// return null;
			}
		}
		return null;

	}

	public String getKeyByConfig(ConnectionData connectionData) {
		if (allConfig != null && !allConfig.isEmpty()) {
			for (Map.Entry<String, ConnectionData> entry : allConfig.entrySet()) {
				if (connectionData.equals(entry.getValue())) {
					return entry.getKey();
				}
				System.err.println("not in there!!!");
				return null;
			}
		}
		return null;
	}

	public Map<String, ConnectionData> getAllConfig() {
		return allConfig;
	}

	public void setAllConfig(Map<String, ConnectionData> allConfig) {
		this.allConfig = allConfig;
	}

}
