package com.hodvidar.text.modifier.interfaces;

import java.io.File;


public interface FileExplorer {

	final String level_unit = "-";

	/**
	 * Recursively goes through all files in the given directory
	 */
	void performAction();

	/**
	 * Recursively goes through all files in the given directory
	 *
	 * @param directory
	 * @param level
	 */
	default void performAction(File directory, String level) {
		File[] files = directory.listFiles();
		for(File f : files) {
			log(level+f.getName());
			if(f.isDirectory()) {
				performAction(f, level+level_unit);
			}
		}
	}

	/**
	 * Logs the given message.
	 * <p>
	 * Default action is <code>System.out.print(msg)</code>
	 * @param msg
	 */
	default void log(String msg) {
		System.out.print(msg);
	}

}
