package tool.model;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import tool.model.connection.Config;
import tool.model.connection.ConnectionData;
import tool.model.formate.BetterExcelFormate;

public class ModelTool {


	private Config config;
	private List<File> fileList;
	private BetterExcelFormate toExcel;
	private ImageIcon icon;

	public ModelTool() {
		config = new Config();
		toExcel = new BetterExcelFormate();
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

	public BetterExcelFormate getToExcel() {
		return toExcel;
	}

	public void setToExcel(BetterExcelFormate toExcel) {
		this.toExcel = toExcel;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

}
