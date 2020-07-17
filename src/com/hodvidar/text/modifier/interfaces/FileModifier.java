package com.hodvidar.text.modifier.interfaces;

import java.io.File;


public interface FileModifier {

	/**
	 * Checks if given file should be modified by this class
	 *
	 * @param aFile
	 * @return <code>true</code> if the file should be
	 */
	boolean isConcernedByAction(File aFile);

	/**
	 * Performs modifications on the given file.
	 * <p>
	 * This method expects that the method {@link #isConcernedByAction(java.io.File)}
	 * was previously ran and return true with the given file.
	 *
	 * @param aFile
	 */
	void modifyFile(File aFile);
}
