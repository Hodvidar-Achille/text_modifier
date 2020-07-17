package com.hodvidar.util.resource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Provides methods to ensure closing resources properly.
 *
 * @author a.genet
 */
public final class ResourceCloser {

	private static final Logger logger = Logger.getLogger(ResourceCloser.class.getName());

	private ResourceCloser() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Ensures the closing of the given resource with no exception. Otherwise logs the failure.
	 *
	 * @param resource If <code>null</code> nothing happen
	 */
	public static void closeResource(AutoCloseable resource) {
		if (resource == null) {
			return;
		}

		try {
			resource.close();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Failed to close a resource", e);
		}
	}
}
