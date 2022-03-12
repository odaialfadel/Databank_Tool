package tool.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	public static String datumA() {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}




}
