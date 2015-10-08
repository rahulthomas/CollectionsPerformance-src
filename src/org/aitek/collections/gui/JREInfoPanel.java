package org.aitek.collections.gui;

import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.aitek.collections.utils.Constants;

public class JREInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {

		g.setFont(Constants.TITLE_FONT);
		g.drawString(System.getProperty("java.runtime.name") + " - Version " + System.getProperty("java.runtime.version"), 20, 20);
		FontMetrics fontMetrics = getFontMetrics(Constants.DATA_FONT);
		int size = (int) fontMetrics.getStringBounds("Available Processors:  ", g).getWidth();

		String[][] javaProps = new String[][] { { "java.vm.name", "Virtual Machine" }, { "os.arch", "Architecture" }, { "java.vendor", "Vendor" }, { "sun.desktop", "Desktop" } };
		for (int j = 0; j < javaProps.length; j++) {
			String key = javaProps[j][1] + ": ";
			g.setFont(Constants.DATA_FONT);
			g.drawString(key, 20, 50 + 20 * j);

			g.setFont(Constants.DATA_VALUE_FONT);
			g.drawString(System.getProperty(javaProps[j][0]), 20 + size, 50 + 20 * j);

		}

		g.setFont(Constants.DATA_FONT);
		g.drawString("Available Processors: ", 20, 50 + javaProps.length * 20);
		g.setFont(Constants.DATA_VALUE_FONT);
		g.drawString("" + Runtime.getRuntime().availableProcessors(), 20 + size, 50 + javaProps.length * 20);

		g.setFont(Constants.DATA_FONT);
		g.drawString("Free Memory: ", 20, 70 + javaProps.length * 20);
		g.setFont(Constants.DATA_VALUE_FONT);
		g.drawString("" + Runtime.getRuntime().freeMemory() / (1024 * 1024) + "Mb", 20 + size, 70 + javaProps.length * 20);

		g.setFont(Constants.DATA_FONT);
		g.drawString("Max Memory: ", 20, 90 + javaProps.length * 20);
		g.setFont(Constants.DATA_VALUE_FONT);
		g.drawString("" + Runtime.getRuntime().maxMemory() / (1024 * 1024) + "Mb", 20 + size, 90 + javaProps.length * 20);

		g.setFont(Constants.DATA_FONT);
		g.drawString("Total Memory: ", 20, 110 + javaProps.length * 20);
		g.setFont(Constants.DATA_VALUE_FONT);
		g.drawString("" + Runtime.getRuntime().totalMemory() / (1024 * 1024) + "Mb", 20 + size, 110 + javaProps.length * 20);

	}
}
