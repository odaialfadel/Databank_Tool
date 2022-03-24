package tool.model.formate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tool.model.UtilitiesTool;
import tool.model.formateTyp.ServerFormat;

public class ExcelFormat implements ServerFormat {

	private Workbook book;
	private Sheet sheet;
	private ResultSet resultSet;
	private int rowIndex = 0;


	@Override
	public void export(ResultSet rs, String filename, String outPut) {
		this.resultSet = rs;

		writeHeaderRows(filename);
		writeDataRows();
		exportFileXlsx(filename, outPut);
	}

	private List<String> columnsList() {
		try {
			ResultSetMetaData rsmd = resultSet.getMetaData();
			List<String> columns = new ArrayList<String>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columns.add(rsmd.getColumnLabel(i));
			}
			return columns;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void writeHeaderRows(String filename) {

		book = new XSSFWorkbook();
		sheet = book.createSheet(filename);
		Row header = sheet.createRow(0);

		/*
		 * alle Spalten durchlesen i = SpaltenIndex
		 */
		for (int i = 0; i < columnsList().size(); i++) {
			header.createCell(i).setCellValue(columnsList().get(i));
			header.getCell(i).setCellStyle(formatHeaderCell());
			sheet.setColumnWidth(i, 4000);

		}
	}

	private void writeDataRows() {

		// Zeilennummer
		rowIndex = 0;

		/*
		 * erstelle neue filder und fuelle sie aus
		 */
		try {
			while (resultSet.next()) {
				Row row = sheet.createRow(++rowIndex);
				for (int i = 0; i < columnsList().size(); i++) {
					Cell cell = row.createCell(i);
					String value = Objects.toString(resultSet.getObject(columnsList().get(i)), "");
					cell.setCellValue(value);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void exportFileXlsx(String filename, String path) {

		// String path = "output//";
		// wenn es Zeilen im Excel-Datei sind
		try {
			if (rowIndex != 0) {
				File file = new File(path + filename + "_" + UtilitiesTool.formatDate() + ".xlsx");
				if (!file.exists()) {
					file.getParentFile().mkdirs();
					file.createNewFile();
					FileOutputStream fos = new FileOutputStream(file);
					book.write(fos);
					fos.close();

				} else {
					// JOptionPane.showMessageDialog(null, "Dataei ist vorhanden!" + filename);
					System.err.println("Dataei ist vorhanden!");
				}

			} else {
				System.err.println("Es gibt keine Daten!!4");
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private CellStyle formatHeaderCell() {

		CellStyle style = book.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, columnsList().size() - 1));
		return style;
	}

	@Override
	public int getRowIndex() {
		return rowIndex;
	}

}
