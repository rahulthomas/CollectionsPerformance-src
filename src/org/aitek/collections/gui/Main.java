package org.aitek.collections.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.aitek.collections.core.CollectionSample;
import org.aitek.collections.core.CollectionSample.OperationType;
import org.aitek.collections.core.ListSample;
import org.aitek.collections.core.MapSample;
import org.aitek.collections.core.SetSample;
import org.aitek.collections.utils.Constants;
import org.aitek.collections.utils.SwingUtils;

public class Main extends JFrame implements ActionListener, ChangeListener {

	private static final long serialVersionUID = 0L;
	private JButton jbPopulate;
	private CollectionSample collectionSample;
	private StatusBar statusBar;
	private ListSample listSample;
	private JSlider sizeSlider;
	private SetSample setSample;
	private MapSample mapSample;
	private JButton jbInsert;
	private JButton jbRemove;
	private JButton jbSearch;
	private JButton jbIterate;
	private JButton jbSort;
	private JLabel sizeLabel;

	public Main() throws Exception {

		super("Java Collections Performance");

		setSize(750, 400);
		SpringLayout sl = new SpringLayout();
		setLayout(sl);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		statusBar = new StatusBar();
		getContentPane().add(getStatusBar());
		sl.putConstraint(SpringLayout.SOUTH, getStatusBar(), -2, SpringLayout.SOUTH, this.getContentPane());
		sl.putConstraint(SpringLayout.WEST, getStatusBar(), 2, SpringLayout.WEST, this.getContentPane());
		sl.putConstraint(SpringLayout.EAST, getStatusBar(), -2, SpringLayout.EAST, this.getContentPane());

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addChangeListener(this);
		add(tabbedPane);

		StatsPanel listPanel = new StatsPanel("List", new String[] { "CopyOnWriteArrayList", "Stack", "Vector" });
		SpringLayout slList = new SpringLayout();
		listPanel.setLayout(slList);
		tabbedPane.addTab("    List    ", null, listPanel, "List Performances");
		listSample = new ListSample(listPanel, this);

		StatsPanel setPanel = new StatsPanel("Set", new String[] { "ConcurrentSkipListSet", "LinkedHashSet", "CopyOnWriteArraySet" });
		SpringLayout slSet = new SpringLayout();
		setPanel.setLayout(slSet);
		tabbedPane.addTab("    Set    ", null, setPanel, "Set Performances");
		setSample = new SetSample(setPanel, this);

		StatsPanel mapPanel = new StatsPanel("Map", new String[] { "IdentityHashMap", "ConcurrentHashMap", "ConcurrentSkipListMap", "WeakHashMap" });
		SpringLayout slMap = new SpringLayout();
		mapPanel.setLayout(slMap);
		tabbedPane.addTab("    Map    ", null, mapPanel, "Map Performances");
		mapSample = new MapSample(mapPanel, this);

//		JPanel jpJRE = new JREInfoPanel();
//		SpringLayout slJRE = new SpringLayout();
//		jpJRE.setLayout(slJRE);
//		tabbedPane.addTab("  JRE Info  ", null, jpJRE, "Information about Java Virtual Machine");

		jbPopulate = new JButton("Populate");
		add(jbPopulate);
		jbPopulate.addActionListener(this);
		sl.putConstraint(SpringLayout.WEST, jbPopulate, 5, SpringLayout.WEST, this.getContentPane());
		sl.putConstraint(SpringLayout.SOUTH, jbPopulate, -5, SpringLayout.NORTH, getStatusBar());

		jbInsert = new JButton("Insert");
		add(jbInsert);
		jbInsert.addActionListener(this);
		sl.putConstraint(SpringLayout.WEST, jbInsert, 5, SpringLayout.EAST, jbPopulate);
		sl.putConstraint(SpringLayout.SOUTH, jbInsert, -5, SpringLayout.NORTH, getStatusBar());

		jbRemove = new JButton("Remove");
		add(jbRemove);
		jbRemove.addActionListener(this);
		sl.putConstraint(SpringLayout.WEST, jbRemove, 5, SpringLayout.EAST, jbInsert);
		sl.putConstraint(SpringLayout.SOUTH, jbRemove, -5, SpringLayout.NORTH, getStatusBar());

		jbSearch = new JButton("Search");
		add(jbSearch);
		jbSearch.addActionListener(this);
		sl.putConstraint(SpringLayout.WEST, jbSearch, 5, SpringLayout.EAST, jbRemove);
		sl.putConstraint(SpringLayout.SOUTH, jbSearch, -5, SpringLayout.NORTH, getStatusBar());

		jbIterate = new JButton("Iterate");
		add(jbIterate);
		jbIterate.addActionListener(this);
		sl.putConstraint(SpringLayout.WEST, jbIterate, 5, SpringLayout.EAST, jbSearch);
		sl.putConstraint(SpringLayout.SOUTH, jbIterate, -5, SpringLayout.NORTH, getStatusBar());

		jbSort = new JButton("Sort");
		add(jbSort);
		jbSort.addActionListener(this);
		sl.putConstraint(SpringLayout.WEST, jbSort, 5, SpringLayout.EAST, jbIterate);
		sl.putConstraint(SpringLayout.SOUTH, jbSort, -5, SpringLayout.NORTH, getStatusBar());

		JButton jbQuit = new JButton("Quit");
		add(jbQuit);
		jbQuit.addActionListener(this);
		sl.putConstraint(SpringLayout.EAST, jbQuit, -5, SpringLayout.EAST, this.getContentPane());
		sl.putConstraint(SpringLayout.SOUTH, jbQuit, -5, SpringLayout.NORTH, getStatusBar());

		sizeLabel = new JLabel("Collection Size: " + Constants.COLLECTION_MIN_SIZE + ",000");
		add(sizeLabel);
		sl.putConstraint(SpringLayout.WEST, sizeLabel, 5, SpringLayout.WEST, getContentPane());
		sl.putConstraint(SpringLayout.SOUTH, sizeLabel, -10, SpringLayout.NORTH, jbPopulate);

		sizeSlider = new JSlider(JSlider.HORIZONTAL, Constants.COLLECTION_MIN_SIZE, Constants.COLLECTION_MAX_SIZE, 50);
		sizeSlider.setName("size");
		add(sizeSlider);
		sizeSlider.addChangeListener(this);
		sl.putConstraint(SpringLayout.WEST, sizeSlider, 10, SpringLayout.EAST, sizeLabel);
		sl.putConstraint(SpringLayout.EAST, sizeSlider, -5, SpringLayout.EAST, getContentPane());
		sl.putConstraint(SpringLayout.SOUTH, sizeSlider, -10, SpringLayout.NORTH, jbPopulate);

		sl.putConstraint(SpringLayout.WEST, tabbedPane, 5, SpringLayout.WEST, this.getContentPane());
		sl.putConstraint(SpringLayout.NORTH, tabbedPane, 5, SpringLayout.NORTH, this.getContentPane());
		sl.putConstraint(SpringLayout.EAST, tabbedPane, -5, SpringLayout.EAST, this.getContentPane());
		sl.putConstraint(SpringLayout.SOUTH, tabbedPane, -5, SpringLayout.NORTH, sizeSlider);

		collectionSample = listSample;
		collectionSample.setListSize(sizeSlider.getValue() * 1000);
		collectionSample.setIterations(Constants.ITERATIONS);

		setButtonsState();

		invalidate();
		repaint();
	}

		public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					try {
						Main m = new Main();
						m.setVisible(true);
					} catch (Exception e) {
						SwingUtils.showFormError(e);
					}
				}
			});
}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {

			if (e.getActionCommand().equals("Quit")) {
				System.exit(0);
			}
			else if (e.getActionCommand().equals("Populate")) {
				collectionSample.execute(OperationType.POPULATE);
			}
			else if (e.getActionCommand().equals("Insert")) {
				collectionSample.execute(OperationType.INSERT);
			}
			else if (e.getActionCommand().equals("Remove")) {
				collectionSample.execute(OperationType.REMOVE);
			}
			else if (e.getActionCommand().equals("Search")) {
				collectionSample.execute(OperationType.SEARCH);
			}
			else if (e.getActionCommand().equals("Iterate")) {
				collectionSample.execute(OperationType.ITERATE);
			}
			else if (e.getActionCommand().equals("Sort")) {
				collectionSample.execute(OperationType.SORT);
			}

		}
		catch (Exception ex) {
			SwingUtils.showFormError(ex);
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {

		if (e.getSource() instanceof JSlider) {

			JSlider slider = (JSlider) e.getSource();
			if (slider.getName().equals("size")) {
				collectionSample.setListSize(slider.getValue() * 1000);
				String value = collectionSample.getNumberFormat().format(slider.getValue() * 1000);
				sizeLabel.setText("Collection Size: " + value);
			}
		}
		else {

			JTabbedPane pane = (JTabbedPane) e.getSource();

			int k = pane.getSelectedIndex();
			switch (k) {
				case 0:
					collectionSample = listSample;
				break;
				case 1:
					collectionSample = setSample;
				break;
				case 2:
					collectionSample = mapSample;
				break;

			}

			if (sizeSlider != null) {

				sizeSlider.setValue(Constants.COLLECTION_MIN_SIZE);
				collectionSample.setListSize(Constants.COLLECTION_MIN_SIZE * 1000);
				sizeSlider.invalidate();
				sizeSlider.repaint();

				jbPopulate.setEnabled(collectionSample.getSupportedOperations().contains(OperationType.POPULATE));
				setButtonsState();
				repaint();
			}

		}

	}

	public void setButtonsState() {

		jbInsert.setEnabled(collectionSample.getSupportedOperations().contains(OperationType.INSERT) && collectionSample.isPopulated());
		jbRemove.setEnabled(collectionSample.getSupportedOperations().contains(OperationType.REMOVE) && collectionSample.isPopulated());
		jbSearch.setEnabled(collectionSample.getSupportedOperations().contains(OperationType.SEARCH) && collectionSample.isPopulated());
		jbIterate.setEnabled(collectionSample.getSupportedOperations().contains(OperationType.ITERATE) && collectionSample.isPopulated());
		jbSort.setEnabled(collectionSample.getSupportedOperations().contains(OperationType.SORT) && collectionSample.isPopulated());
	}

	public StatusBar getStatusBar() {

		return statusBar;
	}

	public void setReady() {

		statusBar.setMessage();
		this.setCursor(Constants.DEFAULT_CURSOR);
	}

	public void setWorking(String message) {

		this.setCursor(Constants.WAIT_CURSOR);
		statusBar.setMessage(message);
	}
}
