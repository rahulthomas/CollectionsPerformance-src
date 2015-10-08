package org.aitek.collections.utils;

import java.awt.Cursor;
import java.awt.Font;

public class Constants {

	public final static int SLEEP_DELAY = 250;

	public final static Font TITLE_FONT = new Font("Arial", Font.BOLD, 16);
	public final static Font DATA_FONT = new Font("Arial", Font.BOLD, 12);
	public final static Font DATA_VALUE_FONT = new Font("Arial", Font.PLAIN, 12);

	public static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
	public static final Cursor WAIT_CURSOR = new Cursor(Cursor.WAIT_CURSOR);

	public static final int COLLECTION_MIN_SIZE = 50;
	public static final int COLLECTION_MAX_SIZE = 1000;
	public static final int ITERATIONS = 100;
}
