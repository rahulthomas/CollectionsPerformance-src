package org.aitek.collections.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class StatsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private String[] types = { "" };
	private long[] times = new long[10];
	private String operation = "";
	private JFreeChart chart;
	private DefaultCategoryDataset dataset;
	private ChartPanel chartPanel;

	{
		dataset = new DefaultCategoryDataset();
	}

	public StatsPanel(String collection, String... types) {

		this.types = types;
		dataset = new DefaultCategoryDataset();

		chart = ChartFactory.createBarChart("", collection + "  Type", "Time (ms)", dataset, PlotOrientation.HORIZONTAL, true, true, false);
		chart.setBackgroundPaint(new Color(240, 240, 240));
		chart.setTitle(operation);
		chart.setBorderVisible(true);
		chartPanel = new ChartPanel(chart);
		add(chartPanel);
		invalidate();
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		chartPanel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		chartPanel.setSize(new Dimension(this.getWidth(), this.getHeight()));

		for (int j = 0; j < types.length; j++) {
			dataset.addValue(times[j], types[j], types[j]);
		}
		chart.setTitle(operation);
	}

	public void setTimes(String operation, long... times) {

		this.operation = operation;
		this.times = times;
		repaint();
	}
}
