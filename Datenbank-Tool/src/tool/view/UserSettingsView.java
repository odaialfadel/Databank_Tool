package tool.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class UserSettingsView extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField usernameText, serviceText, portText, datenbankText;
	private JPasswordField passwortText;
	


	private JComboBox<String> connectionComboBox;

	private JButton saveButton, cancelButton, deleteButton, testConnection, showButton;

	private JLabel datenbankName, userNameLabel, passwortLabel, serviceLabel, portLabel;

	public UserSettingsView() {
		initialize();
		setVisible(true);
	}
	/**
	 * Create the dialog.
	 */
	private void initialize() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(UserSettingsView.class.getResource("/tool/img/settings.png")));
		setBounds(100, 100, 346, 269);
		getContentPane().setLayout(new BorderLayout(0,0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setTitle("User Settings");
		
		userNameLabel = new JLabel("Username");
		userNameLabel.setBounds(35, 47, 78, 22);
		contentPanel.add(userNameLabel);
		
		passwortLabel = new JLabel("Passwort");
		passwortLabel.setBounds(35, 79, 78, 21);
		contentPanel.add(passwortLabel);
		
		serviceLabel = new JLabel("Service");
		serviceLabel.setBounds(35, 111, 78, 22);
		contentPanel.add(serviceLabel);
		
		portLabel = new JLabel("Port");
		portLabel.setBounds(35, 143, 78, 21);
		contentPanel.add(portLabel);
		
		usernameText = new JTextField();
		usernameText.setBounds(123, 49, 125, 19);
		contentPanel.add(usernameText);
		usernameText.setColumns(10);
		
		passwortText = new JPasswordField(20);
		passwortText.setBounds(123, 80, 125, 19);
		contentPanel.add(passwortText);
		passwortText.setColumns(10);
		
		serviceText = new JTextField();
		serviceText.setBounds(123, 113, 125, 19);
		contentPanel.add(serviceText);
		serviceText.setColumns(10);
		
		portText = new JTextField();
		portText.setBounds(123, 144, 125, 19);
		contentPanel.add(portText);
		portText.setColumns(10);
		
		datenbankName = new JLabel("Datenbank");
		datenbankName.setBounds(35, 10, 78, 22);
		contentPanel.add(datenbankName);
		
		datenbankText = new JTextField();
		datenbankText.setBounds(123, 12, 125, 19);
		contentPanel.add(datenbankText);
		datenbankText.setColumns(10);

		showButton = new JButton("show");
		showButton.setBounds(258, 79, 64, 21);
		contentPanel.add(showButton);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			connectionComboBox = new JComboBox<String>();
			buttonPane.add(connectionComboBox);

			deleteButton = new JButton("remove");
			buttonPane.add(deleteButton);
			{
				saveButton = new JButton("Save");
				
				saveButton.setActionCommand("Save");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}

			testConnection = new JButton("Test");
			buttonPane.add(testConnection);
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		}

		public JButton getDeleteButton() {
			return deleteButton;
		}

		public void setDeleteButton(JButton deleteButton) {
			this.deleteButton = deleteButton;
		}

	public JTextField getDatenbankText() {
		return datenbankText;
	}

	public void setDatenbankText(JTextField datenbankText) {
		this.datenbankText = datenbankText;
	}

	public JTextField getUsernameText() {
		return usernameText;
	}

	public void setUsernameText(JTextField usernameText) {
		this.usernameText = usernameText;
	}

	public JPasswordField getPasswortText() {
		return passwortText;
	}

	public void setPasswortText(JPasswordField passwortText) {
		this.passwortText = passwortText;
	}

	public JTextField getServiceNameText() {
		return serviceText;
	}

	public void setServiceText(JTextField serviceText) {
		this.serviceText = serviceText;
	}

	public JTextField getPortText() {
		return portText;
	}

	public void setPortText(JTextField portText) {
		this.portText = portText;
	}

	public JComboBox<String> getConnectionComboBox() {
		return connectionComboBox;
	}

	public void setConnectionComboBox(JComboBox<String> comboBox) {
		this.connectionComboBox = comboBox;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setOkButton(JButton okButton) {
		this.saveButton = okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}



	public JButton getTestConnection() {
		return testConnection;
	}

	public void setTestConnection(JButton testConnection) {
		this.testConnection = testConnection;
	}
	
	public JButton getShowButton() {
		return showButton;
	}

	public void setShowButton(JButton showButton) {
		this.showButton = showButton;
	}

	public void resetTextFelder() {
		getDatenbankText().setText("");
		getUsernameText().setText("");
		getPasswortText().setText("");
		getServiceNameText().setText("");
		getPortText().setText("");
	}
}
