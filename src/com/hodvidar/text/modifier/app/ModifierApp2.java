package com.hodvidar.text.modifier.app;

import java.io.File;

import com.hodvidar.text.modifier.interfaces.FileExplorer;
import com.hodvidar.text.modifier.impl.FileExplorerImpl;
import com.hodvidar.text.modifier.interfaces.FileModifier;
import com.hodvidar.text.modifier.impl.FileModifierImpl;
import com.hodvidar.text.modifier.interfaces.TextModifier;
import com.hodvidar.text.modifier.impl.TextModifierImpl;


public class ModifierApp2 {

	private static final String root_directory_path = "C:\\w\\signbook\\signbook-back\\src\\main\\java";

	private static final String toRemove = "/* SIGN-2359 */";
	private static final String toReplace = "/* DONE */";

	public static void main(String[] args) {

		// creates a file object
		File file = new File(root_directory_path);

		System.out.print(file.getAbsolutePath());
		TextModifier actionOnText = new TextModifierImpl(toRemove, toReplace);
		FileModifier actionOnFile = new FileModifierImpl(actionOnText);
		FileExplorer explorer = new FileExplorerImpl(actionOnFile, file);
		explorer.performAction();
	}
}
