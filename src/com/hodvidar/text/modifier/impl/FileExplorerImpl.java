package com.hodvidar.text.modifier.impl;

import java.io.File;

import com.hodvidar.text.modifier.abstracts.AbstractFileExplorer;
import com.hodvidar.text.modifier.interfaces.FileModifier;


public class FileExplorerImpl extends AbstractFileExplorer {

	public FileExplorerImpl(FileModifier aFileModifier, File aRootDirectory) {
		super(aFileModifier, aRootDirectory);
	}
}
