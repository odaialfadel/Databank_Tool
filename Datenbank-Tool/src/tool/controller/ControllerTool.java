package tool.controller;

import java.awt.Desktop;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
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


	private ViewTool viewTool;
	private ModelTool modelTool;
	private UserSettingsView userSettingsView;

	// Excel-Files output folder
	private final String OUTPUTPATH = "output//" + UtilitiesTool.formatDate() + "\\";

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

		// to choose file and listen to it
		viewTool.getOpenFileButton().addActionListener(e -> chooseFile());

		viewTool.getAddFilesAndConnectionToCollection().addActionListener(e -> addConnectionsAndFilesToMapList());

		// viewTool.getConnectionComboBox().addActionListener(e ->
		// addElementsToCombo(viewTool));

		// to set new Config and save it
		viewTool.getSettingsMenuItem().addActionListener(e -> settingsDialog());
		// viewTool.getSettingsMenu().addActionListener(e -> settingsDialog());

		// run Button
		viewTool.getRunButton().addActionListener(e -> excuteAllStatements());

		// Output
		viewTool.getOutputButton().addActionListener(e -> openOutputFolder());

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
			checkConnection(entryKey);
			for (int i = 0; i < listOfConnAndFiles.get(entryKey).size(); i++) {
				try {
					modelTool.getToExcel().export(
							MySQLConnection.execQuery(Files
									.readString(Paths.get(listOfConnAndFiles.get(entryKey).get(i).getAbsolutePath()))),
							UtilitiesTool.removeFileExtension(listOfConnAndFiles.get(entryKey).get(i).getName()),
							OUTPUTPATH + entryKey.getDatenbank() + "//");

					// Test rowIndex
					System.err.println("Done!  " + modelTool.getToExcel().getRowIndex());

					// View all rows from the Files
					viewTool.getUebersichtTextArea()
							.setText(viewTool.getUebersichtTextArea().getText()
									+ UtilitiesTool
											.removeFileExtension(listOfConnAndFiles.get(entryKey).get(i).getName())
									+ ": " + modelTool.getToExcel().getRowIndex() + "\n");

				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);
				}
			}
			// Close Connection

			MySQLConnection.disconnect();
		}
		viewTool.getLoadingLabel().setIcon(new ImageIcon("src\\tool\\img\\dance.gif"));
		viewTool.getLoadingLabel().setVisible(true);
	}

	/**
	 * check to which connection belongs that Statement
	 * 
	 * @param entryKey
	 */
	private void checkConnection(ConnectionData entryKey) {
		if (MySQLConnection.connect(entryKey) != null) {
			MySQLConnection.connect(entryKey);
		} else if (OracleSqlDeveloper.connect(entryKey) != null) {
			OracleSqlDeveloper.connect(entryKey);
		}
	}

	private void openOutputFolder() {
		File file = new File(OUTPUTPATH);
		try {
			// check if the folder created if not then create new one
			if (!file.exists()) {
				file.mkdir();
				Desktop.getDesktop().open(file);
			}
			Desktop.getDesktop().open(file);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Path doesen't exist!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// damit die JDialog geöffnet werden kann
	private void settingsDialog() {

		userSettingsView = new UserSettingsView();

		addElementsToCombo(userSettingsView);

		// save the new Settings
		userSettingsView.getSaveButton().addActionListener(e -> saveConfig());

		// Comboox
		userSettingsView.getConnectionComboBox().addActionListener(e -> selectComboToShowData());

		// remove Config button
		userSettingsView.getDeleteButton().addActionListener(e -> deleteConfig());

		// Test the Connection
		userSettingsView.getTestConnection().addActionListener(e -> testSelectedConnection());

		// show passwort
		userSettingsView.getShowButton().addActionListener(e -> showPasswort());

		// close JDialog with dispose methode
		userSettingsView.getCancelButton().addActionListener(e -> userSettingsView.dispose());

	}

	private void showPasswort() {

		if (userSettingsView.getPasswortText().getPassword().length > 0) {
			if (userSettingsView.getShowButton().getText().equals("show")) {
				userSettingsView.getPasswortText().setEchoChar(((char) 0));
				userSettingsView.getShowButton().setText("hide");
			} else {
				userSettingsView.getShowButton().setText("show");
				userSettingsView.getPasswortText().setEchoChar('\u25cf');
			}
		}
	}

	private void testSelectedConnection() {
		String selectedItem = userSettingsView.getConnectionComboBox().getSelectedItem().toString();
		//modelTool.setIcon(new ImageIcon("src\\tool\\img\\success.gif"));
		if (selectedItem != null) {
			if (MySQLConnection.connect(modelTool.getConnectionDataList().get(selectedItem)) != null) {
				MySQLConnection.connect(modelTool.getConnectionDataList().get(selectedItem));
				JOptionPane.showMessageDialog(null, "Connection sucess!", "Info", JOptionPane.INFORMATION_MESSAGE,
						UtilitiesTool.setIcon("success.gif"));
			} else if (OracleSqlDeveloper.connect(modelTool.getConnectionDataList().get(selectedItem)) != null) {
				OracleSqlDeveloper.connect(modelTool.getConnectionDataList().get(selectedItem));
				JOptionPane.showMessageDialog(null, "Connection sucess!", "Info", JOptionPane.INFORMATION_MESSAGE,
						UtilitiesTool.setIcon("success.gif"));
			} else {
				JOptionPane.showMessageDialog(null, "Connection faild!", "Error", JOptionPane.ERROR_MESSAGE);
			}

			modelTool.getConnectionDataList().get(selectedItem);

		}
	}

	private void deleteConfig() {
		// System.err.println(userSettings.getConnectionComboBox().getSelectedItem().toString());
		modelTool.getConfig().deleteConfig(userSettingsView.getConnectionComboBox().getSelectedItem().toString());
		userSettingsView.resetTextFelder();

		// Delete the deleted config from combobox
		userSettingsView.getConnectionComboBox()
				.removeItem(userSettingsView.getConnectionComboBox().getSelectedItem().toString());
		viewTool.getConnectionComboBox()
				.removeItem(userSettingsView.getConnectionComboBox().getSelectedItem().toString());
	}

	private void selectComboToShowData() {
		/*
		 * show data as selected combobox
		 */
		for (Map.Entry<String, ConnectionData> entry : modelTool.getConnectionDataList().entrySet()) {
			if (entry.getKey().equals(userSettingsView.getConnectionComboBox().getSelectedItem().toString())) {
				userSettingsView.getDatenbankText().setText(entry.getValue().getDatenbank());
				userSettingsView.getUsernameText().setText(entry.getValue().getUsername());
				userSettingsView.getPasswortText().setText(entry.getValue().getPasswort());
				userSettingsView.getServiceNameText().setText(entry.getValue().getServiceName());
				userSettingsView.getPortText().setText(entry.getValue().getPort());
			}
		}
	}

	/**
	 * soll besser sein!!!! // ist viel besser geworden
	 */
	private void saveConfig() {

		// Add new config
		modelTool.setConnectionData(userSettingsView.getDatenbankText().getText(),
				new ConnectionData(userSettingsView.getDatenbankText().getText(),
						userSettingsView.getUsernameText().getText(),
						userSettingsView.getPasswortText().getPassword().toString(),
						userSettingsView.getServiceNameText().getText(), userSettingsView.getPortText().getText()));

		// update combo
		userSettingsView.getConnectionComboBox().addItem(userSettingsView.getDatenbankText().getText().toString());
		viewTool.getConnectionComboBox().addItem(userSettingsView.getDatenbankText().getText().toString());

		// reset TextFelder
		userSettingsView.resetTextFelder();
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
					viewTool.getConnectionComboBox().addItem(entry.getKey());
				}
			} else if (obj instanceof UserSettingsView) {
				for (Map.Entry<String, ConnectionData> entry : modelTool.getConnectionDataList().entrySet()) {
					userSettingsView.getConnectionComboBox().addItem(entry.getKey());
				}
			}

		}
	}

}
