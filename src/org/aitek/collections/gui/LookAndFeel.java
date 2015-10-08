package org.aitek.collections.gui;

import javax.swing.UIManager;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class LookAndFeel {

	public enum LAF {
		System, Metal, Motif, GTK
	};

	public static String getLookAndFeelDefaultName() {

		String className = UIManager.getSystemLookAndFeelClassName();
		if (className.indexOf("GTK") > 0) return LAF.GTK.toString();
		else if (className.indexOf("Motif") > 0) return LAF.Motif.toString();
		else if (className.indexOf("Metal") > 0) return LAF.Metal.toString();
		return "undefined";
	}

	public static void initLookAndFeel(LAF lookAndFeelName) throws Exception {

		if (lookAndFeelName != null) {
			if (lookAndFeelName == LAF.Metal) {
				MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
				UIManager.setLookAndFeel(new MetalLookAndFeel());
			}

			else if (lookAndFeelName == LAF.System) {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}

			else if (lookAndFeelName == LAF.Motif) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			}

			else if (lookAndFeelName == LAF.GTK) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			}

			else {
				throw new Exception("Unexpected Look&Feel");
			}

			// sets antialiasing
			System.setProperty("swing.aatext", "true");
		}
	}
}
