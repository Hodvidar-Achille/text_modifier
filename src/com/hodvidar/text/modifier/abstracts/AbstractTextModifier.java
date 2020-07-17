package com.hodvidar.text.modifier.abstracts;

import com.hodvidar.text.modifier.interfaces.TextModifier;


public abstract class AbstractTextModifier implements TextModifier {

	protected final String toRemove;
	protected final String toReplace;

	public AbstractTextModifier(String toRemove, String toReplace) {
		this.toRemove = toRemove;
		this.toReplace = toReplace;
	}

	@Override
	public String transformLine(String oldString) {
		return oldString.replaceAll(toRemove, toReplace);
	}
}
