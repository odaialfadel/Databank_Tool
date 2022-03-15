package tool.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;

public class UtilitiesTool {
	
	/*
	 * remove extension from File name .txt or .xlsx etc
	 */
	public static String removeFileExtension(String data) {
		if (data.lastIndexOf('.') < 0) {
			return data;
		}
		return data.substring(0, data.lastIndexOf('.'));
	}
	
	/*
	 * Datum so formatieren: 20210727
	 */
	public static String formatDate() {
		DateFormat dateFo = new SimpleDateFormat("yyyyMMdd");
		Date dF = new Date();
		return dateFo.format(dF);
	}
	/*
	 * to read File
	 */
	public static String readFile(String filePath) {
		String result = "";
		try {
			result = Files.readString(Paths.get(filePath));
			cleanQuery(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// clean Statement if it has semicolum, becaause jdbc cannt read it
	public static String cleanQuery(String query) {
		String tempQuery = "";
		if (query.contains(";")) {
			tempQuery = query.replace(";", "");
		} else {
			return query;
		}
		return tempQuery;
	}

	/**
	 * @param iconName put the icon name that you saved in the img package with the
	 *                 extension create Icon
	 */
	public static ImageIcon setIcon(String iconName) {
		return new ImageIcon("src\\tool\\img\\" + iconName);
	}

}
