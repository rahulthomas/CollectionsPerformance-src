package org.aitek.collections.core;

import java.text.NumberFormat;
import java.util.HashSet;

import org.aitek.collections.gui.Main;
import org.aitek.collections.gui.StatsPanel;
import org.aitek.collections.gui.StatusBar;

public abstract class CollectionSample {

	public enum OperationType {

		NO_OPERATION, POPULATE, INSERT, REMOVE, SEARCH, ITERATE, SORT
	}

	protected int COLLECTION_TYPES;
	protected int listSize = 50000;
	protected int iterations = 50;
	protected OperationType currentOperation = OperationType.NO_OPERATION;
	protected long[] times;
	private NumberFormat nf;
	protected final Main main;
	protected final StatsPanel statsPanel;
	protected StatusBar statusBar;

	public CollectionSample(StatsPanel statsPanel, Main main) {

		this.statsPanel = statsPanel;
		this.main = main;
		statusBar = main.getStatusBar();
		nf = NumberFormat.getNumberInstance();
	}

	public abstract void execute(OperationType operation);

	public abstract HashSet<OperationType> getSupportedOperations();

	public abstract boolean isPopulated();

	public void setCurrentOperation(OperationType operations) {

		this.currentOperation = operations;
	}

	public void setListSize(int listSize) {

		this.listSize = listSize;
	}

	public int getListSize() {

		return listSize;
	}

	public String getListFormattedSize() {

		return nf.format(listSize);
	}

	public void setIterations(int iterations) {

		this.iterations = iterations;
	}

	public int getIterations() {

		return iterations;
	}

	public NumberFormat getNumberFormat() {

		return nf;
	}
}
