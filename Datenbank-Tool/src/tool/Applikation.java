package tool;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
		// ModelTool model = new ModelTool();
		// model.setConnectionData(new ConnectionData("odai","od","haha","125"));
		// config.modifyConfigValue(new ConnectionData("odai","od","haha","125"));
		// System.err.println(model.getListOfFiles("Plugins/DB_Tool/"));
//		Config c = new Config();
//		c.readConfigList();

		runApp();
	}

	public static void runApp() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}

				ModelTool model = new ModelTool();
				ViewTool view = new ViewTool();
				view.setVisible(true);
				ControllerTool controller = new ControllerTool(view, model);
				controller.initController();
			}
		});

	}

}
