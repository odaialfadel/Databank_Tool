package tool;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import tool.controller.ControllerTool;
import tool.model.ModelTool;
import tool.view.ViewTool;

/**
 * @authur Odai Al Fadel
 * @sincen 22.02.2022
 * @Version 1.0
 */

public class Applikation {

	public static void main(String[] args) {
		runApp();
	}

	public static void runApp() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}

				ControllerTool controller = new ControllerTool(new ViewTool(), new ModelTool());
				controller.initController();
			}
		});

	}

}
