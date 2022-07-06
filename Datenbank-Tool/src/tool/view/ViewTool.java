package tool.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ViewTool extends JFrame {

	private JPanel contentPanel;
	private JButton changeConfigButton, runButton, openFileButton, addFilesAndConnectionToCollection, clearListsButtun,
			outputButton;
	

	private JMenuBar menuBar;
	// private JMenu datei;
	private JMenuItem settingsMenuItem;
	private JPanel uebrsichtPanel;
	private JTextField filePathChooser;
	
	private JTextArea uebersichtTextArea;
	private JComboBox<String> connectionComboBox;
	private JLabel loadingLabel, auswertungLabel, backgroundLabel;
	private JScrollPane scrollPane;





	/**
	 * Create the application.
	 */
	public ViewTool() {

		initialize();
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// setBounds(100, 100, 536, 373);
		setTitle("Datenbank Tool");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewTool.class.getResource("/tool/img/mainIcon.png")));

		setBounds(100, 100, 730, 550);
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		uebrsichtPanel = new JPanel();
		uebrsichtPanel.setForeground(Color.GRAY);
		uebrsichtPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		uebrsichtPanel.setBackground(Color.LIGHT_GRAY);
		uebrsichtPanel.setBounds(535, 10, 171, 471);
		uebrsichtPanel.setOpaque(false);
		contentPanel.add(uebrsichtPanel);
		uebrsichtPanel.setLayout(null);

		auswertungLabel = new JLabel("Auswertung:");
		auswertungLabel.setBounds(42, 0, 100, 50);
		auswertungLabel.setOpaque(false);
		uebrsichtPanel.add(auswertungLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(10, 34, 151, 427);

		// make tranperent scrollpan
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);
		uebrsichtPanel.add(scrollPane);

		uebersichtTextArea = new JTextArea();
		scrollPane.setViewportView(uebersichtTextArea);

		// scrollPane.setBackground(new Color(0, 0, 0, 0));
		uebersichtTextArea.setOpaque(false);
		uebersichtTextArea.setLineWrap(true);
		uebersichtTextArea.setEditable(false);
		uebersichtTextArea.setFont(new Font("Tahoma", Font.PLAIN, 11));

		// Files Path
		filePathChooser = new JTextField("");
		filePathChooser.setBounds(10, 104, 275, 27);
		contentPanel.add(filePathChooser);
		filePathChooser.setColumns(10);


		// Button
		openFileButton = new JButton("Choose Folder");
		openFileButton.setOpaque(false);
		openFileButton.setBounds(306, 104, 152, 27);
		contentPanel.add(openFileButton);

		runButton = new JButton("Run");
		runButton.setOpaque(false);
		runButton.setBounds(306, 349, 116, 27);
		contentPanel.add(runButton);

		outputButton = new JButton("Output");
		outputButton.setOpaque(false);
		outputButton.setBounds(169, 349, 116, 27);
		contentPanel.add(outputButton);
		
		connectionComboBox = new JComboBox<String>();
		connectionComboBox.setOpaque(false);
		connectionComboBox.setBounds(138, 141, 147, 27);
		contentPanel.add(connectionComboBox);

		addFilesAndConnectionToCollection = new JButton("Add");
		addFilesAndConnectionToCollection.setBounds(387, 142, 71, 27);
		addFilesAndConnectionToCollection.setOpaque(false);
		contentPanel.add(addFilesAndConnectionToCollection);


		clearListsButtun = new JButton("Clear");
		clearListsButtun.setOpaque(false);
		clearListsButtun.setBounds(306, 142, 71, 27);
		contentPanel.add(clearListsButtun);

		loadingLabel = new JLabel("");
		loadingLabel.setBounds(254, 178, 200, 161);
		// loadingLabel.setOpaque(false);
		contentPanel.add(loadingLabel);
		loadingLabel.setVisible(false);





		// Menu

		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		// datei = new JMenu("Datei");
		settingsMenuItem = new JMenuItem("Settings");
		settingsMenuItem.setBorderPainted(true);



		// datei.add(settingsMenuItem);
		menuBar.add(settingsMenuItem);
		setJMenuBar(menuBar);

		backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon(ViewTool.class.getResource("/tool/img/home3.jpg")));
		backgroundLabel.setBounds(0, 0, 716, 490);
		contentPanel.add(backgroundLabel);


	}

//	public JMenuBar getMenuBar() {
//		return menuBar;
//	}

//	public void setMenuBar(JMenuBar menuBar) {
//		this.menuBar = menuBar;
//	}


	public JLabel getBackgroundLabel() {
		return backgroundLabel;
	}

	public void setBackgroundLabel(JLabel backgroundLabel) {
		this.backgroundLabel = backgroundLabel;
	}
	public JLabel getLogo() {
		return loadingLabel;
	}

	public void setLogo(JLabel logo) {
		this.loadingLabel = logo;
	}

	public JComboBox<String> getConnectionComboBox() {
		return connectionComboBox;
	}

	public void setConnectionComboBox(JComboBox<String> connectionComboBox) {
		this.connectionComboBox = connectionComboBox;
	}

	public JButton getRunButton() {
		return runButton;
	}

	public void setRunButton(JButton runButton) {
		this.runButton = runButton;
	}

	public JButton getAddFilesAndConnectionToCollection() {
		return addFilesAndConnectionToCollection;
	}

	public void setAddFilesAndConnectionToCollection(JButton addFilesAndConnectionToCollection) {
		this.addFilesAndConnectionToCollection = addFilesAndConnectionToCollection;
	}

	public JButton getClearListsButtun() {
		return clearListsButtun;
	}

	public void setClearListsButtun(JButton clearListsButtun) {
		this.clearListsButtun = clearListsButtun;
	}

	public JLabel getLoadingLabel() {
		return loadingLabel;
	}

	public void setLoadingLabel(JLabel loadingLabel) {
		this.loadingLabel = loadingLabel;
	}



//	public JMenu getDatei() {
//		return datei;
//	}
//
//	public void setDatei(JMenu datei) {
//		this.datei = datei;
//	}

	public JMenuItem getSettingsMenuItem() {
		return settingsMenuItem;
	}

	public void setSettingsMenuItem(JMenuItem settingsMenuItem) {
		this.settingsMenuItem = settingsMenuItem;
	}

	public JTextField getFilePathChooser() {
		return filePathChooser;
	}

	public void setFilePathChooser(JTextField filePathChooser) {
		this.filePathChooser = filePathChooser;
	}

	public JButton getChangeConfigButton() {
		return changeConfigButton;
	}

	public void setChangeConfigButton(JButton changeConfigButton) {
		this.changeConfigButton = changeConfigButton;
	}

	public JButton getOpenFileButton() {
		return openFileButton;
	}

	public void setOpenFileButton(JButton openFileButton) {
		this.openFileButton = openFileButton;
	}

	public JButton getOutputButton() {
		return outputButton;
	}

	public void setOutputButton(JButton outputButton) {
		this.outputButton = outputButton;
	}
	
	public JTextArea getUebersichtTextArea() {
		return uebersichtTextArea;
	}

	public void setUebersichtTextArea(JTextArea uebersichtTextArea) {
		this.uebersichtTextArea = uebersichtTextArea;
	}
}
