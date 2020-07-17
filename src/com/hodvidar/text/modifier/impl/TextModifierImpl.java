package com.hodvidar.text.modifier.impl;

import com.hodvidar.text.modifier.abstracts.AbstractTextModifier;


public class TextModifierImpl extends AbstractTextModifier {

	public TextModifierImpl(String toRemove, String toReplace) {
		super(toRemove, toReplace);
	}

	@Override
	public boolean actionNeeded(String aString) {
		return aString.contains(this.toRemove);
	}

	@Override
	public String transformLine(String oldString) {
		System.out.print(" Modification SIGN-2359... ");
		return oldString.replace(this.toRemove, this.toReplace);
	}
}
