package com.hodvidar.text.modifier.abstracts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.hodvidar.text.modifier.interfaces.FileModifier;
import com.hodvidar.text.modifier.interfaces.TextModifier;
import com.hodvidar.util.resource.GenericLogger;
import com.hodvidar.util.resource.ResourceCloser;


public abstract class AbstractFileModifier implements FileModifier {

    private final TextModifier action;

	protected AbstractFileModifier(TextModifier action) {
		this.action = action;
	}

	@Override
	public boolean isConcernedByAction(File aFile) {
		return false;
	}

	@Override
	public void modifyFile(File aFile) {
		BufferedReader reader = null;
		FileWriter writer = null;
		try {
			StringBuilder fullContentSb = new StringBuilder();
			reader = new BufferedReader(new FileReader(aFile));
			String line = reader.readLine();
			while (line != null) {
				fullContentSb.append(line).append(System.lineSeparator());
				line = reader.readLine();
			}
			String fullContent = fullContentSb.toString();
			if(this.action.actionNeeded(fullContent)) {
				fullContent = this.action.transformLine(fullContent);
				writer = new FileWriter(aFile);
				writer.write(fullContent);
			}
		} catch (Exception e) {
			GenericLogger.logException(e);
		} finally {
			ResourceCloser.closeResource(reader);
			ResourceCloser.closeResource(writer);
		}
	}

}
