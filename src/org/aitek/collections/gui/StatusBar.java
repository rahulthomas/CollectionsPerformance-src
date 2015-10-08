package org.aitek.collections.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class StatusBar extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel jlMessage;
	private JProgressBar progressBar;

	public StatusBar() {

		setBorder(new EtchedBorder());
		setLayout(new BorderLayout());

		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		rightPanel.add(progressBar);

		jlMessage = new JLabel("Ready");
		jlMessage.setAlignmentY(TOP_ALIGNMENT);
		JPanel leftPanel = new JPanel();
		leftPanel.add(jlMessage, BorderLayout.NORTH);

		add(rightPanel, BorderLayout.EAST);
		add(leftPanel, BorderLayout.WEST);
	}

	public void setMessage(String message) {

		jlMessage.setText(" " + message);
	}

	public void updateProgressBar(int n) {

		progressBar.setValue(n);
		updateUI();
		invalidate();
		repaint();
	}

	public void setMessage() {

		setMessage("Ready");
	}
}