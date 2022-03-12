package tool.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class UserSettingsView extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField usernameText;
	private JTextField passwortText;
	private JTextField serviceText;
	private JTextField portText;
	private JTextField datenbankText;
	


	private JComboBox<String> connectionComboBox;
	private JButton saveButton;
	private JButton cancelButton;
	private JButton deleteButton;
	private JButton testConnection;


	/**
	 * Create the dialog.
	 */
	public UserSettingsView() {
		setBounds(100, 100, 346, 269);
		getContentPane().setLayout(new BorderLayout(0,0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setBounds(35, 47, 78, 22);
		contentPanel.add(userNameLabel);
		
		JLabel passwortLabel = new JLabel("Passwort");
		passwortLabel.setBounds(35, 79, 78, 21);
		contentPanel.add(passwortLabel);
		
		JLabel serviceLabel = new JLabel("Service");
		serviceLabel.setBounds(35, 111, 78, 22);
		contentPanel.add(serviceLabel);
		
		JLabel portLabel = new JLabel("Port");
		portLabel.setBounds(35, 143, 78, 21);
		contentPanel.add(portLabel);
		
		usernameText = new JTextField();
		usernameText.setBounds(152, 48, 125, 19);
		contentPanel.add(usernameText);
		usernameText.setColumns(10);
		
		passwortText = new JTextField();
		passwortText.setBounds(152, 80, 125, 19);
		contentPanel.add(passwortText);
		passwortText.setColumns(10);
		
		serviceText = new JTextField();
		serviceText.setBounds(152, 112, 125, 19);
		contentPanel.add(serviceText);
		serviceText.setColumns(10);
		
		portText = new JTextField();
		portText.setBounds(152, 144, 125, 19);
		contentPanel.add(portText);
		portText.setColumns(10);
		
		JLabel datenbankName = new JLabel("Datenbank");
		datenbankName.setBounds(35, 10, 78, 22);
		contentPanel.add(datenbankName);
		
		datenbankText = new JTextField();
		datenbankText.setBounds(152, 12, 125, 19);
		contentPanel.add(datenbankText);
		datenbankText.setColumns(10);
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
		setVisible(true);
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

	public JTextField getPasswortText() {
		return passwortText;
	}

	public void setPasswortText(JTextField passwortText) {
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
	
	public void resetTextFelder() {
		getDatenbankText().setText("");
		getUsernameText().setText("");
		getPasswortText().setText("");
		getServiceNameText().setText("");
		getPortText().setText("");
	}
}
