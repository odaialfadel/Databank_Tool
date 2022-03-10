package tool.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import tool.model.connection.Config;
import tool.model.connection.ConnectionData;
import tool.model.formate.BetterExcelFormate;

public class ModelTool {

	private Config config;
	// private ConnectionData connectionData;
	private List<ConnectionData> connectionDataList;
	private List<File> fileListe;
	private BetterExcelFormate toExcel;

	public ModelTool() {
		config = new Config();
		toExcel = new BetterExcelFormate();
		// connectionData = config.readConfig();
		connectionDataList = config.readConfigList();
	}

	public List<File> listOfFiles(List<File> files) {
		fileListe = files;
		return fileListe;
	}

//	public List<String> sqlFilesToStrings() {
//		listOfStatement = new ArrayList<>();
//		BufferedReader br = null;
//		String line;
//		String temp="";
//		try {
//			for (int i = 0; i < fileListe.size(); i++) {
//				br = new BufferedReader(new FileReader(fileListe.get(i)));
//				while ((line = br.readLine()) != null) {
//					temp+=line;
//				}
//				listOfStatement.add(temp);
//			}
//			br.close();
//			return listOfStatement;
//		} catch (IOException e) {
//			return null;
//		}
//	}
	public String readFile(String filePath) {
		String result = "";
		try {
			result = Files.readString(Paths.get(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

//	public String sqlFileToString(String path) {
//		File file = new File(path);
//		BufferedReader br = null;
//		String line;
//		String stmt = "";
//		try {
//			br = new BufferedReader(new FileReader(file));
//			while ((line = br.readLine()) != null) {
//				stmt = line;
//			}
//			br.close();
//			return stmt;
//		} catch (IOException e) {
//			return null;
//		}
//	}

//	public String[] getListOfFiles(String path) {
//
//		List<String> results = new ArrayList<String>();
//
//		File[] files = new File(path).listFiles();
//		// If this pathname does not denote a directory, then listFiles() returns null.
//
//		for (File file : files) {
//			if (file.isFile()) {
//				results.add(file.getName());
//				System.out.println("File added");
//			}
//		}
//		String[] tempArray = results.toArray(new String[results.size()]);
//		return tempArray;
//	}

	public ConnectionData getConnectionData() {
		return config.readConfig();
	}

	public void setConnectionData(ConnectionData connectionData) {
		config.createConfig(connectionData);
	}

	public List<ConnectionData> getConnectionDataList() {
		return config.readConfigList();
	}

	public void setConnectionDataList(List<ConnectionData> connectionDataList) {
		config.createConfigList(connectionDataList);
	}

	public List<File> getFileListe() {
		return fileListe;
	}

	public void setFileListe(List<File> fileListe) {
		this.fileListe = fileListe;
	}

	public BetterExcelFormate getToExcel() {
		return toExcel;
	}

	public void setToExcel(BetterExcelFormate toExcel) {
		this.toExcel = toExcel;
	}

}
