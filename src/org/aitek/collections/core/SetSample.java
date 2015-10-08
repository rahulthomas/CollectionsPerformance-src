//Aidan O'Brien Collections Performance Assignment. Loggers & Containers
//1  Modify the existing Java Collection Performance source code to include new containers  for each of the categories.
//2.  Fix any bugs that you may encounter.
//3.  Make the main() Thread safe.
//4.  Remove the JRE Info tab.
//The code can be found @ http://sourceforge.net/projects/javacollections/

package org.aitek.collections.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.swing.SwingWorker;

import org.aitek.collections.gui.Main;
import org.aitek.collections.gui.StatsPanel;

public class SetSample extends CollectionSample implements PropertyChangeListener {

	//private HashSet<Integer> concurrentSkipListSet;
	private ConcurrentSkipListSet<Integer> concurrentSkipListSet;
	private LinkedHashSet<Integer> linkedHashSet;
	//private TreeSet<Integer> copyOnWriteArraySet;
	private CopyOnWriteArraySet<Integer> copyOnWriteArraySet;
	private Task task;

	public SetSample(StatsPanel statsPanel, Main main) {

		super(statsPanel, main);
		COLLECTION_TYPES = 3;
		times = new long[COLLECTION_TYPES];
		concurrentSkipListSet = new ConcurrentSkipListSet<>();
		linkedHashSet = new LinkedHashSet<Integer>();
		copyOnWriteArraySet = new CopyOnWriteArraySet<>();
	}

	public HashSet<OperationType> getSupportedOperations() {

		HashSet<OperationType> set = new HashSet<OperationType>();

		set.add(OperationType.POPULATE);
		set.add(OperationType.INSERT);
		set.add(OperationType.REMOVE);
		set.add(OperationType.ITERATE);

		return set;
	}

	public void execute(OperationType operation) {

		this.currentOperation = operation;

		task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		statusBar.updateProgressBar(task.getProgress());
	}

	private class Task extends SwingWorker<Void, Void> {

		private double mult;

		@Override
		public Void doInBackground() {

			mult = 100d / iterations;
			switch (currentOperation) {
				case POPULATE:
					times = fillSets();
					statsPanel.setTimes("Populating", times);
					break;
				case INSERT:
					times = insertIntoSets();
					statsPanel.setTimes("Inserting new elements", times);
					break;
				case REMOVE:
					times = removeFromSets();
					statsPanel.setTimes("Removing existing elements", times);
					break;
				case ITERATE:
					times = iterateOnSets();
					statsPanel.setTimes("Iterating elements", times);
					break;
			}

			return null;
		}

		@Override
		public void done() {

			main.setButtonsState();
			main.setReady();
		}

		private long[] fillSets() {

			long[] times = new long[COLLECTION_TYPES];
			main.setWorking("Filling set with " + getListFormattedSize() + " elements...");
			setProgress(0);

			for (int z = 0; z <= iterations; z++) {

				concurrentSkipListSet.clear();
				linkedHashSet.clear();
				copyOnWriteArraySet.clear();

				int toBeInserted[] = new int[listSize];
				for (int j = 0; j < getListSize(); j++) {
					toBeInserted[j] = ((int) (Math.random() * listSize));
				}

				long startingTime = System.nanoTime();
				for (int j = 0; j < getListSize(); j++) {
					concurrentSkipListSet.add(toBeInserted[j]);
				}
				times[0] += System.nanoTime() - startingTime;

				startingTime = System.nanoTime();
				for (int j = 0; j < getListSize(); j++) {
					linkedHashSet.add(toBeInserted[j]);
				}
				times[1] += System.nanoTime() - startingTime;

				startingTime = System.nanoTime();
				for (int j = 0; j < getListSize(); j++) {
					copyOnWriteArraySet.add(toBeInserted[j]);
				}
				times[2] += System.nanoTime() - startingTime;

				setProgress((int) (z * mult));
			}
			for (int z = 0; z < COLLECTION_TYPES; z++) {
				times[z] = times[z] / iterations / 1000;
			}

			return times;
		}

		private long[] insertIntoSets() {

			long[] times = new long[COLLECTION_TYPES];
			main.setWorking("Inserting elements into set...");
			setProgress(0);
			for (int z = 0; z <= iterations; z++) {

				int toBeInserted = (int) (Math.random() * listSize);
				long startingTime = System.nanoTime();
				for (int j = 0; j < 50; j++)
					concurrentSkipListSet.add(toBeInserted);
				times[0] += System.nanoTime() - startingTime;

				startingTime = System.nanoTime();
				for (int j = 0; j < 50; j++)
					linkedHashSet.add(toBeInserted);
				times[1] += System.nanoTime() - startingTime;

				startingTime = System.nanoTime();
				for (int j = 0; j < 50; j++)
					copyOnWriteArraySet.add(toBeInserted);
				times[2] += System.nanoTime() - startingTime;

				setProgress((int) (z * mult));
			}
			for (int z = 0; z < COLLECTION_TYPES; z++) {
				times[z] = times[z] / iterations / 1000;
			}

			return times;
		}

		private long[] removeFromSets() {

			long[] times = new long[COLLECTION_TYPES];
			main.setWorking("Removing elements from set...");
			setProgress(0);

			for (int z = 0; z <= iterations; z++) {

				int toBeRemoved = (int) (Math.random() * listSize);

				long startingTime = System.nanoTime();
				for (int j = 0; j < 50; j++)
					concurrentSkipListSet.remove(toBeRemoved);
				times[0] += System.nanoTime() - startingTime;

				startingTime = System.nanoTime();
				for (int j = 0; j < 50; j++)
					linkedHashSet.remove(toBeRemoved);
				times[1] += System.nanoTime() - startingTime;

				startingTime = System.nanoTime();
				for (int j = 0; j < 50; j++)
					copyOnWriteArraySet.remove(toBeRemoved);
				times[2] += System.nanoTime() - startingTime;

				setProgress((int) (z * mult));
			}
			for (int z = 0; z < COLLECTION_TYPES; z++) {
				times[z] = times[z] / iterations / 1000;
			}

			return times;
		}

		private long[] iterateOnSets() {

			long[] times = new long[COLLECTION_TYPES];
			main.setWorking("Iterating on elements...");
			setProgress(0);

			for (int z = 0; z <= iterations; z++) {

				long startingTime = System.nanoTime();
				Iterator<Integer> iterator = concurrentSkipListSet.iterator();
				while (iterator.hasNext()) {
					iterator.next();
				}
				times[0] += System.nanoTime() - startingTime;

				startingTime = System.nanoTime();
				iterator = linkedHashSet.iterator();
				while (iterator.hasNext()) {
					iterator.next();
				}
				times[1] += System.nanoTime() - startingTime;

				startingTime = System.nanoTime();
				iterator = copyOnWriteArraySet.iterator();
				while (iterator.hasNext()) {
					iterator.next();
				}
				times[2] += System.nanoTime() - startingTime;
				setProgress((int) (z * mult));
			}

			for (int z = 0; z < COLLECTION_TYPES; z++) {
				times[z] = times[z] / iterations / 1000;
			}

			setProgress(100);

			return times;
		}

	}

	@Override
	public boolean isPopulated() {

		return copyOnWriteArraySet.size() > 0;
	}

}
