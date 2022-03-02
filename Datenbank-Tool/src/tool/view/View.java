package tool.view;


import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import tool.model.Model;
import tool.model.connection.Config;
import tool.model.connection.UserData;
import tool.model.formate.BetterExcelFormate;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class View extends JFrame {


	private Model model;
	

	public View(Model model) throws HeadlessException {
		super("MVC Demo");
		this.model = model;
	}


	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Config config = new Config();
		config.saveConfig(new UserData("od","124j","serdc","124"));
		config.readConfig();
		
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton changeConfig = new JButton("username");
		changeConfig.setBounds(157, 115, 85, 21);
		getContentPane().add(changeConfig);
		
		changeConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				config.modifyConfigValue(changeConfig.getText(), "odai");
				System.err.println(config.readConfig().getUsername());
			}
		});
	}
}
