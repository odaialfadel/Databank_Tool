package tool.model;

import java.io.File;
import java.util.List;
import java.util.Map;

import tool.model.connection.Config;
import tool.model.connection.ConnectionData;
import tool.model.formate.ExcelFormat;
import tool.model.formateTyp.ServerFormat;

public class ModelTool {


	private Config config;
	private List<File> fileList;
	private ServerFormat fileOutputFormt;

	public ModelTool() {
		config = new Config();
		fileOutputFormt = new ExcelFormat();
	}

	public void setConnectionData(String datenbankName, ConnectionData connectionData) {
		config.createConfig(datenbankName, connectionData);
	}

	public Map<String, ConnectionData> getConnectionDataList() {
		return config.getAllConfig();
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public List<File> getFileListe() {
		return fileList;
	}

	public void setFileListe(List<File> fileListe) {
		this.fileList = fileListe;
	}

	public ServerFormat getFileOutputFormt() {
		return fileOutputFormt;
	}

	public void setFileOutputFormt(ServerFormat fileOutputFormt) {
		this.fileOutputFormt = fileOutputFormt;
	}



}
