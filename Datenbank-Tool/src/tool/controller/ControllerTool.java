package tool.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import tool.model.ModelTool;
import tool.model.UtilitiesTool;
import tool.model.connection.ConnectionData;
import tool.model.connection.MySQLConnection;
import tool.model.connection.OracleSqlDeveloper;
import tool.view.UserSettingsView;
import tool.view.ViewTool;

public class ControllerTool {

	/**
	 * TODO scrolbar textArea
	 */

	private ViewTool viewTool;
	private ModelTool modelTool;
	private UserSettingsView userSettings;

	// Excel-Files output folder
	private final String OUTPUTPATH = "output//" + UtilitiesTool.datumA() + "\\";

	private Map<ConnectionData, List<File>> listOfConnAndFiles = new HashMap<ConnectionData, List<File>>();;

	public ControllerTool(ViewTool viewTool, ModelTool modelTool) {
		this.viewTool = viewTool;
		this.modelTool = modelTool;
	}

	public void initController() {
		// viewTool.getChangeConfigButton().addActionListener(e -> changeConfigValue());
		// View 1
		// get the config and put it in a combobox of the main View
		addElementsToCombo(viewTool);

		viewTool.getBackgroundLabel().setIcon(new ImageIcon("src\\tool\\img\\home3.jpg"));
		// to choose file and listen to it
		viewTool.getOpenFileButton().addActionListener(e -> chooseFile());

		viewTool.getAddFilesAndConnectionToCollection().addActionListener(e -> addConnectionsAndFilesToMapList());

		// viewTool.getConnectionComboBox().addActionListener(e ->
		// addElementsToCombo(viewTool));

		// to set new Config and save it
		viewTool.getSettingsMenuItem().addActionListener(e -> settingsDialog());

		// run Button
		viewTool.getRunButton().addActionListener(e -> excuteAllStatements());

		// Output
		viewTool.getOutputButton().addActionListener(e -> openFolder());

	}

//	public void setImg() {
//		
//		viewTool.getLogo().setVisible(true);
//	}

	/**
	 * Add connections and there Files to the Map liste
	 */
	private void addConnectionsAndFilesToMapList() {

		if (modelTool.getFileListe() != null && !modelTool.getFileListe().isEmpty()
				&& viewTool.getConnectionComboBox().getSelectedItem() != null) {
			// save connection as key and listOfFiles as Value
			listOfConnAndFiles.put(
					modelTool.getConfig().getValueByName(viewTool.getConnectionComboBox().getSelectedItem().toString()),
					modelTool.getFileListe());

			viewTool.getUebersichtTextArea().setText(viewTool.getUebersichtTextArea().getText()
					+ viewTool.getConnectionComboBox().getSelectedItem().toString() + "\n----\n\n");
			for (int i = 0; i < modelTool.getFileListe().size(); i++) {
				String name = modelTool.getFileListe().get(i).getName();
				viewTool.getUebersichtTextArea().setText(
						viewTool.getUebersichtTextArea().getText() + UtilitiesTool.removeFileExtension(name) + "\n");
			}

		}
	}

	/**
	 * Run all selected Statements
	 */
	private void excuteAllStatements() {
		// To reset the TextArea
		viewTool.getUebersichtTextArea().setText("");

		viewTool.getLoadingLabel().setIcon(new ImageIcon("src\\tool\\img\\loading2.gif"));
		viewTool.getLoadingLabel().setVisible(true);

		for (ConnectionData entryKey : listOfConnAndFiles.keySet()) {

			// create Connection
			try {
				if (MySQLConnection.connect(entryKey) != null) {
					MySQLConnection.connect(entryKey);
				} else if (OracleSqlDeveloper.connect(entryKey) != null) {
					OracleSqlDeveloper.connect(entryKey);
				}

			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Connection Faild!, please check your Configuration!");
				e1.printStackTrace();
			}

			// for (List<File> entryValueList : listOfConnAndFiles.values()) {
			for (int i = 0; i < listOfConnAndFiles.get(entryKey).size(); i++) {

				try {

					modelTool.getToExcel().export(
							MySQLConnection.execQuery(Files
									.readString(Paths.get(listOfConnAndFiles.get(entryKey).get(i).getAbsolutePath()))),
							UtilitiesTool.removeFileExtension(listOfConnAndFiles.get(entryKey).get(i).getName()),
							OUTPUTPATH + entryKey.getDatenbank() + "//");

					// Test rowIndex
					System.err.println("Done!  " + modelTool.getToExcel().rowIndex);

					// View all rows from the Files
					viewTool.getUebersichtTextArea()
							.setText(viewTool.getUebersichtTextArea().getText()
									+ UtilitiesTool
											.removeFileExtension(listOfConnAndFiles.get(entryKey).get(i).getName())
									+ ": " + modelTool.getToExcel().rowIndex + "\n");

				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);
				}

			}

			// }
			// Close Connection

			MySQLConnection.desconnect();
			// viewTool.getLoadingLabel().setVisible(false);

		}

		viewTool.getLoadingLabel().setIcon(new ImageIcon("src\\tool\\img\\dance.gif"));
		viewTool.getLoadingLabel().setVisible(true);

	}

	private void openFolder() {
		try {
			Desktop.getDesktop().open(new File(OUTPUTPATH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// damit die JDialog geöffnet werden kann
	private void settingsDialog() {

		userSettings = new UserSettingsView();
		addElementsToCombo(userSettings);

		// save the new Settings
		userSettings.getSaveButton().addActionListener(e -> saveConfig());

		// Comboox
		userSettings.getConnectionComboBox().addActionListener(e -> selectComboToShowData());

		// remove Config button
		userSettings.getDeleteButton().addActionListener(e -> deleteConfig());

		// Test the Connection
		userSettings.getTestConnection().addActionListener(e -> testSelectedConnection());

		// close JDialog with dispose methode
		userSettings.getCancelButton().addActionListener(e -> userSettings.dispose());

	}

	private void testSelectedConnection() {
		String selectedItem = userSettings.getConnectionComboBox().getSelectedItem().toString();
		if (selectedItem != null) {
			try {
				if (MySQLConnection.connect(modelTool.getConnectionDataList().get(selectedItem)) != null) {
					MySQLConnection.connect(modelTool.getConnectionDataList().get(selectedItem));
				} else if (OracleSqlDeveloper.connect(modelTool.getConnectionDataList().get(selectedItem)) != null) {
					OracleSqlDeveloper.connect(modelTool.getConnectionDataList().get(selectedItem));
				}

				modelTool.getConnectionDataList().get(selectedItem);
				// System.err.println("Connection sucess!");
				modelTool.setIcon(new ImageIcon("src\\tool\\img\\success.gif"));
				JOptionPane.showMessageDialog(null, "Connection sucess!", "Info", JOptionPane.INFORMATION_MESSAGE,
						modelTool.getIcon());
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Connection faild!", "Error", JOptionPane.ERROR_MESSAGE);
				// System.out.println("Connection faild");
			}
		}
	}

	private void deleteConfig() {
		// System.err.println(userSettings.getConnectionComboBox().getSelectedItem().toString());
		modelTool.getConfig().deleteConfig(userSettings.getConnectionComboBox().getSelectedItem().toString());
		userSettings.resetTextFelder();

		// Delete the deleted config from combobox
		userSettings.getConnectionComboBox()
				.removeItem(userSettings.getConnectionComboBox().getSelectedItem().toString());
		viewTool.getConnectionComboBox().removeItem(userSettings.getConnectionComboBox().getSelectedItem().toString());

	}

	private void selectComboToShowData() {
		/*
		 * show data as selected combobox
		 */
		for (Map.Entry<String, ConnectionData> entry : modelTool.getConnectionDataList().entrySet()) {
			if (entry.getKey().equals(userSettings.getConnectionComboBox().getSelectedItem().toString())) {
				userSettings.getDatenbankText().setText(entry.getValue().getDatenbank());
				userSettings.getUsernameText().setText(entry.getValue().getUsername());
				userSettings.getPasswortText().setText(entry.getValue().getPasswort());
				userSettings.getServiceNameText().setText(entry.getValue().getServiceName());
				userSettings.getPortText().setText(entry.getValue().getPort());
			}
		}

		// }

	}

	/**
	 * soll besser sein!!!! // ist viel besser geworden
	 */
	private void saveConfig() {

		// Add new config
		modelTool.setConnectionData(userSettings.getDatenbankText().getText(),
				new ConnectionData(userSettings.getDatenbankText().getText(), userSettings.getUsernameText().getText(),
						userSettings.getPasswortText().getText(), userSettings.getServiceNameText().getText(),
						userSettings.getPortText().getText()));

		// update combo
		userSettings.getConnectionComboBox().addItem(userSettings.getDatenbankText().getText().toString());
		viewTool.getConnectionComboBox().addItem(userSettings.getDatenbankText().getText().toString());

		// reset TextFelder
		userSettings.resetTextFelder();
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
				modelTool.setFileListe(selectedFiles);

				viewTool.getFilePathChooser().setText(selectedFiles.get(0).getAbsolutePath());
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
		}

	}

	/**
	 * @param object to update the combobox in the given object to read database
	 *               names and put it to combobox
	 */
	private void addElementsToCombo(Object obj) {
		if (modelTool.getConnectionDataList() != null && !modelTool.getConnectionDataList().isEmpty()) {

			if (obj instanceof ViewTool) {
				for (Map.Entry<String, ConnectionData> entry : modelTool.getConnectionDataList().entrySet()) {
					// viewTool.getConnectionComboBox().removeAll();
					viewTool.getConnectionComboBox().addItem(entry.getKey());
				}
			} else if (obj instanceof UserSettingsView) {
				for (Map.Entry<String, ConnectionData> entry : modelTool.getConnectionDataList().entrySet()) {
					// userSettings.getConnectionComboBox().removeAll();
					userSettings.getConnectionComboBox().addItem(entry.getKey());
				}
			}

		}
	}

}
