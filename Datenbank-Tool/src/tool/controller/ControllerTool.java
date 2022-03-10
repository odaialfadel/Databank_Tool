package tool.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import tool.model.ModelTool;
import tool.model.connection.ConnectionData;
import tool.model.formate.BetterExcelFormate;
import tool.view.UserSettingsView;
import tool.view.ViewTool;

public class ControllerTool {

	private ViewTool viewTool;
	private ModelTool modelTool;
	private UserSettingsView userSettings;
	private Connection conn;

	public ControllerTool(ViewTool viewTool, ModelTool modelTool) {
		this.viewTool = viewTool;
		this.modelTool = modelTool;
	}

	public void initController() {
		// viewTool.getChangeConfigButton().addActionListener(e -> changeConfigValue());
		// View 1
		// to choose file and listen to it
		viewTool.getOpenFileButton().addActionListener(e -> chooseFile());
		// to set new Config and save it
		viewTool.getSettingsMenuItem().addActionListener(e -> settingsDialog());

		// run Button
		viewTool.getRunButton().addActionListener(e -> excuteAllStatements());

		// Output
		viewTool.getOutputButton().addActionListener(e -> openFolder());

	}

	private void excuteAllStatements() {

		// System.err.println(modelTool.sqlFilesToStrings());
		for (int i = 0; i < modelTool.getFileListe().size(); i++) {
			try {
				modelTool.getToExcel().export(
						execQuery(modelTool.readFile(modelTool.getFileListe().get(i).getAbsolutePath()), null),
						modelTool.getFileListe().get(i).getName(), "output//");
				desconnect();

				System.err.println("Done!  " + modelTool.getToExcel().rowIndex);
				viewTool.getUebersichtTextArea()
						.setText(viewTool.getUebersichtTextArea().getText()
								+ modelTool.getFileListe().get(i).getName().substring(0,
										modelTool.getFileListe().get(i).getName().length() - 4)
								+ ": " + modelTool.getToExcel().rowIndex + "\n");
				
			
				//TODO muss woanderes geschrieben beispiel "constructor"
				desconnect();
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void openFolder() {
		try {
			Desktop.getDesktop().open(new File("output//"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// damit die JDialog geöffnet werden kann
	private void settingsDialog() {

		userSettings = new UserSettingsView();
		addElementsToCombo();

		// save the new Settings
		userSettings.getSaveButton().addActionListener(e -> saveConfig());

		// Comboox
		userSettings.getComboBox().addActionListener(e -> updateCombo());

		// close JDialog with dispose methode
		userSettings.getCancelButton().addActionListener(e -> userSettings.dispose());

	}

	private void updateCombo() {
		/*
		 * show data as selected combobox
		 */
		// userSettings.getComboBox().addItem("select Database");
		// userSettings.getComboBox().add
		for (int i = 0; i < modelTool.getConnectionDataList().size(); i++) {
			if (modelTool.getConnectionDataList().get(i).getDatenbank()
					.equals(userSettings.getComboBox().getSelectedItem())) {
				userSettings.getDatenbankText().setText(modelTool.getConnectionDataList().get(i).getDatenbank());
				userSettings.getUsernameText().setText(modelTool.getConnectionDataList().get(i).getUsername());
				userSettings.getPasswortText().setText(modelTool.getConnectionDataList().get(i).getPasswort());
				userSettings.getServiceNameText().setText(modelTool.getConnectionDataList().get(i).getServiceName());
				userSettings.getPortText().setText(modelTool.getConnectionDataList().get(i).getPort());
			}
		}

	}

	/**
	 * soll besser sein!!!!
	 */
	@SuppressWarnings("unchecked")
	private void saveConfig() {

		List<ConnectionData> dataList = new ArrayList<ConnectionData>();
		if (modelTool.getConnectionDataList() != null && modelTool.getConnectionDataList().size() != 0) {
			for (int i = 0; i < modelTool.getConnectionDataList().size(); i++) {
				if (!userSettings.getDatenbankText().getText()
						.equals(modelTool.getConnectionDataList().get(i).getDatenbank())) {

					dataList.add(new ConnectionData(userSettings.getDatenbankText().getText(),
							userSettings.getUsernameText().getText(), userSettings.getPasswortText().getText(),
							userSettings.getServiceNameText().getText(), userSettings.getPortText().getText()));

				} else {
					// Diese Datenbak ist vorhanden Exception!!!
				}
			}
			if (dataList.size() != 0) {
				modelTool.setConnectionData(dataList.get(0));
				userSettings.getDatenbankText().setText("");
				userSettings.getUsernameText().setText("");
				userSettings.getPasswortText().setText("");
				userSettings.getServiceNameText().setText("");
				userSettings.getPortText().setText("");
				System.err.println("saved");
				userSettings.getComboBox().addItem(dataList.get(0).getDatenbank());
			}
		} else {
			modelTool.setConnectionData(new ConnectionData(userSettings.getDatenbankText().getText(),
					userSettings.getUsernameText().getText(), userSettings.getPasswortText().getText(),
					userSettings.getServiceNameText().getText(), userSettings.getPortText().getText()));
			userSettings.getDatenbankText().setText("");
			userSettings.getUsernameText().setText("");
			userSettings.getPasswortText().setText("");
			userSettings.getServiceNameText().setText("");
			userSettings.getPortText().setText("");
			System.err.println("saved");
			userSettings.getComboBox().addItem(dataList.get(0).getDatenbank());
		}

	}

	private void chooseFile() {

		JFileChooser chooser = new JFileChooser();
		// chooser.setAcceptAllFileFilterUsed(false);
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("SQL/TEXT", "txt", "sql"));
		chooser.setMultiSelectionEnabled(true);
		if (viewTool.getFilePathChooser().getText() != null || !viewTool.getFilePathChooser().getText().equals("")) {
			chooser.setCurrentDirectory(new File(viewTool.getFilePathChooser().getText()));
		}
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				List<File> selectedFiles = Arrays.asList(chooser.getSelectedFiles());
				modelTool.listOfFiles(selectedFiles);

				viewTool.getFilePathChooser().setText(selectedFiles.get(0).getAbsolutePath());
				// Desktop.getDesktop().open(myFile);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
		}

	}

	/*
	 * read database names and put it to combobox
	 */
	@SuppressWarnings("unchecked")
	private void addElementsToCombo() {
		for (int i = 0; i < modelTool.getConnectionDataList().size(); i++) {
			userSettings.getComboBox().addItem(modelTool.getConnectionDataList().get(i).getDatenbank());
		}
	}
	
	//TODO Singelton for teh database

	/**
	 * Verbindung aufbauen, statement ausfueren und speichern, Verbindung Abbrechen
	 * 
	 * @return ResultSet
	 */
	public ResultSet execQuery(String sql, ConnectionData connectionData) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/odai", "root", "");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// conn.close();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void desconnect() {
		try {
			conn.close();
			System.out.println("disconnected!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
