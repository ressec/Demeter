/*
 * Copyright(c) 2016 - Heliosphere Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Heliosphere's project which is licensed under the 
 * Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package com.heliosphere.demeter.base.resource;

import java.io.File;
import java.net.URL;

/**
 * Provides an abstract implementation of a resource.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public abstract class AbstractResource implements IResource
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Underlying file object.
	 */
	private File file = null;

	/**
	 * Creates a new abstract resource given a pathname.
	 * <hr>
	 * @param pathname Resource pathname.
	 */
	@SuppressWarnings("nls")
	public AbstractResource(String pathname) throws ResourceException
	{
		if (!loadAbsolute(pathname))
		{
			if (!loadRelative(pathname))
			{
				if (!loadRelativeSeparator(pathname))
				{
					throw new ResourceException(String.format("The resource specified by the path name: %1s can not be found", pathname));
				}
			}
		}
	}

	@Override
	public File getFile()
	{
		return file;
	}

	/**
	 * Loads the file considering its pathname is absolute.
	 * <hr>
	 * @param pathname Resource path name.
	 * @return {@code True} if the resource can be loaded, {@code false} otherwise.
	 */
	private final boolean loadAbsolute(String pathname)
	{
		this.file = new File(pathname);

		return this.file.exists();
	}

	/**
	 * Loads the file considering its pathname is relative.
	 * <hr>
	 * @param pathname Resource path name.
	 * @return {@code True} if the resource can be loaded, {@code false} otherwise.
	 */
	private final boolean loadRelative(String pathname)
	{
		// Maybe the file pathname is relative.
		URL url = Thread.currentThread().getContextClassLoader().getResource(pathname);

		try
		{
			this.file = new File(url.toURI());
		}
		catch (Exception e)
		{
			return false;
		}

		return this.file.exists();
	}

	/**
	 * Loads the file considering its pathname is relative.
	 * <hr>
	 * @param pathname Resource path name.
	 * @return {@code True} if the resource can be loaded, {@code false} otherwise.
	 */
	@SuppressWarnings("nls")
	private final boolean loadRelativeSeparator(String pathname)
	{
		// Try to remove the leading file separator, if one exist.
		if (pathname.startsWith(File.separator))
		{
			try
			{
				// Maybe the file pathname is relative.
				URL url = Thread.currentThread().getContextClassLoader().getResource(pathname.replaceFirst(File.separator, ""));
				this.file = new File(url.toURI());
			}
			catch (Exception e)
			{
				return false;
			}
		}

		if (file == null)
		{
			return false;
		}

		return this.file.exists();
	}
}
