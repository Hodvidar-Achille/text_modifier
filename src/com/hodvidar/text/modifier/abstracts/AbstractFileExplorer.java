package com.hodvidar.text.modifier.abstracts;

import java.io.File;

import com.hodvidar.text.modifier.interfaces.FileExplorer;
import com.hodvidar.text.modifier.interfaces.FileModifier;


public abstract class AbstractFileExplorer implements FileExplorer {

	private FileModifier actionOnFile;
	private File rootDirectory;

	public AbstractFileExplorer(FileModifier aFileModifier, File aRootDirectory) {
		this.actionOnFile = aFileModifier;
		this.rootDirectory = aRootDirectory;
	}

	@Override
	public void performAction() {
		performAction(rootDirectory, "\n");
	}

	@Override
	public void performAction(File directory, String level) {
		File[] files = directory.listFiles();
		for(File f : files) {
			log(level+f.getName());
			if(f.isDirectory()) {
				performAction(f, level+level_unit);
			} else if (actionOnFile.isConcernedByAction(f)){
				actionOnFile.modifyFile(f);
			}
		}
	}


}
