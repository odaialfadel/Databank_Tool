package tool.model.formate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tool.model.formateTyp.ServerFormate;

public class EcxelFormat implements ServerFormate {

	public static int rowIndex = 0;
	private DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	private Date date = new Date();

	@Override
	public void export(ResultSet rs, String filename, String outPut) throws SQLException, IOException {

		/*
		 * speichere Alle Daten aus der Datenbank in @ResultSetMetaData Variable
		 */
		ResultSetMetaData rsmd = rs.getMetaData();

		/*
		 * alle Daten aus der Datenbank in ArrayListe speichern
		 */
		List<String> columns = new ArrayList<String>();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			columns.add(rsmd.getColumnLabel(i));
		}

		String path = outPut + dateFormat.format(date) + "\\";

		/*
		 * Excel-Datei erstellen
		 */
		try (Workbook book = new XSSFWorkbook()) {

			Sheet sheet = book.createSheet();
			Row header = sheet.createRow(0);

			/*
			 * Zeilen Formatieren
			 */
			CellStyle style = book.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			/*
			 * alle Spalten durchlesen i = SpaltenIndex
			 */
			for (int i = 0; i < columns.size(); i++) {
				header.createCell(i).setCellValue(columns.get(i));
				header.getCell(i).setCellStyle(style);
				sheet.setColumnWidth(i, 4000);

			}
			/*
			 * um ein neu Style zu erzeugen
			 */
			boolean fhmTab = false;
			CellStyle style2 = book.createCellStyle();

			/*
			 * Filter alle Spalten ab zeile 0 bis 0 und von Spalte 0 bis @columns.size()-1
			 * wenn die erste Spalte mit "TYP" gefuellt ist, dann mach keinen Format
			 */

			if (!columns.get(0).equals("TYP")) {
				sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, columns.size() - 1));
			} else {
				// um die Inhalt in die mitte zu schreiben
				style2.setVerticalAlignment(VerticalAlignment.CENTER);
				style2.setAlignment(HorizontalAlignment.CENTER);
				fhmTab = true;

				sheet.setColumnWidth(0, 2000);
				sheet.setColumnWidth(1, 2600);

				style.setVerticalAlignment(VerticalAlignment.CENTER);
				style.setAlignment(HorizontalAlignment.CENTER);

				sheet.autoSizeColumn(2);
				sheet.autoSizeColumn(3);
				sheet.autoSizeColumn(4);
			}

			// Zeilennummer
			rowIndex = 0;

			/*
			 * erstelle neue filder und fuelle sie aus
			 */
			while (rs.next()) {
				Row row = sheet.createRow(++rowIndex);
				for (int i = 0; i < columns.size(); i++) {
					Cell cell = row.createCell(i);
					String val = Objects.toString(rs.getObject(columns.get(i)), "");
					cell.setCellValue(val);

					/*
					 * wenn die WA FehlerhafteMeldung Tabelle ist, dann so formattieren
					 */
					if (fhmTab) {
						row.getCell(i).setCellStyle(style2);
					}
				}
			}

			// wenn es Zeilen im Excel-Datei sind
			if (rowIndex != 0) {

				File file = new File(path + filename + "_" + datumA() + ".xlsx");
				File file2 = new File(path + filename + "2" + datumA() + ".xlsx");
				File file3 = new File(path + filename + "3" + datumA() + ".xlsx");

				if (file.exists() && file2.exists()) {
					file3.getParentFile().mkdirs();

					file3.createNewFile();
					FileOutputStream fos = new FileOutputStream(file3);
					book.write(fos);
					fos.close();
				} else if (file.exists()) {

					file2.getParentFile().mkdirs();
					file2.createNewFile();
					FileOutputStream fos = new FileOutputStream(file2);
					book.write(fos);
					fos.close();
				} else {

					file.getParentFile().mkdirs();
					file.createNewFile();
					FileOutputStream fos = new FileOutputStream(file);
					book.write(fos);
					fos.close();
				}
			} else {
				// Nichts als Excel schreiben, weil es keine Daten sind
			}
		}

	}

	/*
	 * Datum so formatieren: 20210727
	 */
	public static String datumA() {
		DateFormat dateFo = new SimpleDateFormat("yyyyMMdd");
		Date dF = new Date();
		return dateFo.format(dF);
	}

}
