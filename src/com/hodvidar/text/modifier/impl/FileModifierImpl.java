package com.hodvidar.text.modifier.impl;

import java.io.File;

import com.hodvidar.text.modifier.abstracts.AbstractFileModifier;
import com.hodvidar.text.modifier.interfaces.TextModifier;
import com.hodvidar.util.file.FileUtil;


public class FileModifierImpl extends AbstractFileModifier {

	public FileModifierImpl(TextModifier actionOnText) {
		super(actionOnText);
	}

	@Override
	public boolean isConcernedByAction(File aFile) {
		return FileUtil.isJavaClass(aFile);
	}


}
