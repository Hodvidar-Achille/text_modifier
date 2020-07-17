package com.hodvidar.text.modifier.interfaces;

public interface TextModifier {

	default boolean actionNeeded(String aString) {
		return false;
	}

	/**
	 * Performs a modification on the given String
	 * @param aString
	 * @return a string
	 */
	String transformLine(String aString);
}
