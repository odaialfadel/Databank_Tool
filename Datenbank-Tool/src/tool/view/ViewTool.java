package tool.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ViewTool extends JFrame {

	private JPanel contentPanel;
	private JButton changeConfigButton;
	private JButton openFileButton;
	private JMenuBar menuBar;
	private JMenu datei;
	private JMenuItem settingsMenuItem;
	private JPanel uebrsichtPanel;
	private JTextField filePathChooser;
	
	private JTextArea uebersichtTextArea;

	

	private JButton runButton;

	private JButton outputButton;

	private JLabel enterPathLabel;

	/**
	 * Create the application.
	 */
	public ViewTool() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// setBounds(100, 100, 536, 373);
		setTitle("Datenbank Tool");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 681, 549);

		contentPanel = new JPanel();
		contentPanel.setBackground(Color.GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		uebrsichtPanel = new JPanel();
		uebrsichtPanel.setForeground(Color.GRAY);
		uebrsichtPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		uebrsichtPanel.setBackground(Color.LIGHT_GRAY);
		uebrsichtPanel.setBounds(505, 10, 147, 470);
		contentPanel.add(uebrsichtPanel);
		uebrsichtPanel.setLayout(null);

		JLabel auswertungLabel = new JLabel("Auswertung:");
		auswertungLabel.setBounds(42, 10, 100, 50);
		uebrsichtPanel.add(auswertungLabel);
		
		uebersichtTextArea = new JTextArea();
		uebersichtTextArea.setBounds(10, 64, 127, 396);
		uebersichtTextArea.setOpaque(false);
		uebersichtTextArea.setLineWrap(true);
		uebersichtTextArea.setEditable(false);
		uebersichtTextArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		uebrsichtPanel.add(uebersichtTextArea);

		// Files Path
		filePathChooser = new JTextField("");
		filePathChooser.setBounds(10, 104, 275, 27);
		contentPanel.add(filePathChooser);
		filePathChooser.setColumns(10);

//		changeConfigButton = new JButton("username");
//		changeConfigButton.setBounds(163, 115, 85, 21);
//		getContentPane().add(changeConfigButton);

		// Button
		openFileButton = new JButton("Choose Folder");
		openFileButton.setOpaque(false);
		openFileButton.setBounds(306, 104, 116, 27);
		contentPanel.add(openFileButton);

		runButton = new JButton("Run");
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		runButton.setOpaque(false);
		runButton.setBounds(306, 349, 116, 27);
		contentPanel.add(runButton);

		outputButton = new JButton("Output");
		outputButton.setOpaque(false);
		outputButton.setBounds(169, 349, 116, 27);
		contentPanel.add(outputButton);

		enterPathLabel = new JLabel("Enter your Path here.");
		enterPathLabel.setBounds(10, 67, 206, 27);
		contentPanel.add(enterPathLabel);

		// Menu

		menuBar = new JMenuBar();
		datei = new JMenu("Datei");
		settingsMenuItem = new JMenuItem("Settings");
		// settingsMenuItem.addActionListener(null);

		datei.add(settingsMenuItem);
		menuBar.add(datei);
		setJMenuBar(menuBar);

	}

//	public JMenuBar getMenuBar() {
//		return menuBar;
//	}

//	public void setMenuBar(JMenuBar menuBar) {
//		this.menuBar = menuBar;
//	}

	public JButton getRunButton() {
		return runButton;
	}

	public void setRunButton(JButton runButton) {
		this.runButton = runButton;
	}

	public JLabel getEnterPathLabel() {
		return enterPathLabel;
	}

	public void setEnterPathLabel(JLabel enterPathLabel) {
		this.enterPathLabel = enterPathLabel;
	}

	public JMenu getDatei() {
		return datei;
	}

	public void setDatei(JMenu datei) {
		this.datei = datei;
	}

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
