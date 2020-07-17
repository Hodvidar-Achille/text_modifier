package com.hodvidar.text.modifier.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.hodvidar.util.file.FileUtil;
import com.hodvidar.util.resource.GenericLogger;
import com.hodvidar.util.resource.ResourceCloser;


public class ModifierApp {

	private static final String root_directory_path = "C:\\w\\signbook\\signbook-back\\src\\main\\java";
	private static final String level_unit = "-";

	private static final String toRemove = ".printStackTrace();";

	private static final String codeToAdd01 = "GenericLogger.logException(";
	private static final String codeToAdd02 = ");";
	private static final String importToAdd = "import com.oodrive.certeurope.signbook.resource.GenericLogger; /* SIGN-2359 - automatic */ "+System.lineSeparator();

	private static int counterForModifycation = 0;
	private static int counterForDependency = 0;

	public static void main(String[] args) {

		// creates a file object
		File file = new File(root_directory_path);

		System.out.print(file.getAbsolutePath());
		listFilesInDirectory(file);
		System.out.print("\n\n File modified : "+ counterForModifycation);
		System.out.print("\n File where dependency has been added : "+ counterForDependency);
	}

	private static void listFilesInDirectory(File directory) {
		listFilesInDirectory(directory, "\n-");
	}

	private static void listFilesInDirectory(File directory, String level) {
		File[] files = directory.listFiles();
		for(File f : files) {
			System.out.print(level+f.getName());
			if(f.isDirectory()) {
				listFilesInDirectory(f, level+level_unit);
			} else if (FileUtil.isJavaClass(f)) {
				analyseJavaClass(f);
			}
		}
	}



	private static void analyseJavaClass(File javaClass) {
		BufferedReader reader = null;
		FileWriter writer = null;
		try {
			StringBuilder fullContentSb = new StringBuilder();
			reader = new BufferedReader(new FileReader(javaClass));
			String line = reader.readLine();
			while (line != null) {
				fullContentSb.append(line).append(System.lineSeparator());
				line = reader.readLine();
			}
			String fullContent = fullContentSb.toString();
			if(needToModifyCode(fullContent)) {
				modifyJavaClassCode(javaClass);
				// addDependency(javaClass); will need a second run to be added
			} else if(needToAddDependency(fullContent)) {
				addDependency(javaClass);
			}
		} catch (Exception e) {
			GenericLogger.logException(e);
		} finally {
			ResourceCloser.closeResource(reader);
		}
	}

	private static boolean needToModifyCode(String fullContent) {
		if(fullContent.contains(toRemove))
		{
			System.out.print("\t\t >>>>> to modify <<<<<");
			counterForModifycation++;
			return true;
		}
		return false;
	}

	private static boolean needToAddDependency(String fullContent) {
		if(!fullContent.contains(codeToAdd01)) {
			return false;
		}

		if(fullContent.contains(importToAdd)) {
			return false;
		}

		return true;
	}

	/**
	 * xxx.printStackTrace(); --> GenericLogger.logException(xxx);
	 * NO CHANGE IN THIS METHOD FOR NOW
	 *
	 *
	 * @param javaClass
	 */
	private static void modifyJavaClassCode(File javaClass) {
		BufferedReader reader = null;
		FileWriter writer = null;
		try {
			StringBuilder fullContentSb = new StringBuilder();
			reader = new BufferedReader(new FileReader(javaClass));
			String line = reader.readLine();
			while (line != null) {
				if(line.contains(toRemove)) {
					String newLine = transformPrintStackTrace(line);
					fullContentSb.append(newLine).append(System.lineSeparator());
				} else {
					fullContentSb.append(line).append(System.lineSeparator());
				}
				line = reader.readLine();
			}
			// NO CHANGE IN THIS METHOD FOR NOW
			String newContent = fullContentSb.toString();
			writer = new FileWriter(javaClass);
			writer.write(newContent);
		} catch (Exception e) {
			GenericLogger.logException(e);
		} finally {
			ResourceCloser.closeResource(reader);
			ResourceCloser.closeResource(writer);
		}
	}

	private static String transformPrintStackTrace(String aLine)
	{
		StringBuilder aNewLine = new StringBuilder();
		int min = Math.max(0, aLine.lastIndexOf("{")+1);
		String sub = aLine.substring(min, aLine.lastIndexOf(toRemove));
		sub = sub.replaceAll("/", "");
		sub = sub.trim();
		String newLine = "";
		if(min > 0) {
			aNewLine.append(aLine.substring(0, min));
		}
		aNewLine.append(codeToAdd01).append(sub).append(codeToAdd02);
		if(min > 0) {
			aNewLine.append("}");
		}
		// System.out.print(" --> "+newLine+ "   ");
		return aNewLine.toString();
	}

	/**
	 * ACTIVE
	 * Adds
	 * @param javaClass
	 */
	private static void addDependency(File javaClass) {
		BufferedReader reader = null;
		FileWriter writer = null;
		try {
			StringBuilder fullContentSb = new StringBuilder();
			reader = new BufferedReader(new FileReader(javaClass));
			String line = reader.readLine();
			boolean dependencyAdded = false;
			while (line != null) {
				if(!dependencyAdded && line.contains("import "))
				{
					fullContentSb.append(importToAdd).append(System.lineSeparator());
					dependencyAdded = true;
					System.out.print(" [Added dependency] ");
					counterForDependency++;
				}
				fullContentSb.append(line).append(System.lineSeparator());
				line = reader.readLine();
			}

			String newContent = fullContentSb.toString();
			writer = new FileWriter(javaClass);
			writer.write(newContent);
		} catch (Exception e) {
			GenericLogger.logException(e);
		} finally {
			ResourceCloser.closeResource(reader);
			ResourceCloser.closeResource(writer);
		}
	}

}
