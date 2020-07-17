package com.hodvidar.util.file;

import java.io.File;

import com.hodvidar.util.resource.GenericLogger;


public class FileUtil {

	public static final String java_extension = ".java";

	private FileUtil () {
		throw new IllegalStateException("Utility class");
	}

	public static boolean isJavaClass(File aFile) {
		return java_extension.equals(getFileExtension(aFile));
	}

	public static String getFileExtension(File aFile) {
		String extension = "";
		try {
			if (aFile != null && aFile.exists()) {
				String name = aFile.getName();
				extension = name.substring(name.lastIndexOf("."));
			}
		} catch (Exception e) {
			GenericLogger.logException(e);
			extension = "";
		}
		return extension;
	}
}
