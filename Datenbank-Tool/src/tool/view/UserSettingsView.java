package tool.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tool.model.ModelTool;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class UserSettingsView extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField usernameText;
	private JTextField passwortText;
	private JTextField serviceText;
	private JTextField portText;
	private JTextField datenbankText;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JButton saveButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			UserSettings dialog = new UserSettings();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("rawtypes")
	public UserSettingsView() {
		setBounds(100, 100, 301, 300);
		getContentPane().setLayout(new BorderLayout(0,0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setBounds(10, 46, 78, 22);
		contentPanel.add(userNameLabel);
		
		JLabel passwortLabel = new JLabel("Passwort");
		passwortLabel.setBounds(10, 79, 78, 21);
		contentPanel.add(passwortLabel);
		
		JLabel serviceLabel = new JLabel("Service");
		serviceLabel.setBounds(10, 110, 78, 22);
		contentPanel.add(serviceLabel);
		
		JLabel portLabel = new JLabel("Port");
		portLabel.setBounds(10, 143, 78, 21);
		contentPanel.add(portLabel);
		
		usernameText = new JTextField();
		usernameText.setBounds(98, 48, 125, 19);
		contentPanel.add(usernameText);
		usernameText.setColumns(10);
		
		passwortText = new JTextField();
		passwortText.setBounds(98, 80, 125, 19);
		contentPanel.add(passwortText);
		passwortText.setColumns(10);
		
		serviceText = new JTextField();
		serviceText.setBounds(98, 112, 125, 19);
		contentPanel.add(serviceText);
		serviceText.setColumns(10);
		
		portText = new JTextField();
		portText.setBounds(98, 144, 125, 19);
		contentPanel.add(portText);
		portText.setColumns(10);
		
		JLabel datenbankName = new JLabel("Datenbank");
		datenbankName.setBounds(10, 10, 78, 22);
		contentPanel.add(datenbankName);
		
		datenbankText = new JTextField();
		datenbankText.setBounds(98, 12, 125, 19);
		contentPanel.add(datenbankText);
		datenbankText.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
//			//comboBox = new JComboBox(ModelTool.getListOfFiles("Plugins/DB_Tool/"));
//			if(ModelTool.getConnections()!=null) {
//				comboBox = new JComboBox(ModelTool.getConnections());
//			}else {
//				comboBox = new JComboBox();
//			}
			comboBox = new JComboBox();
			buttonPane.add(comboBox);
			{
				saveButton = new JButton("Save");
				
				saveButton.setActionCommand("Save");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
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

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
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
	
}
