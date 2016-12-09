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
	public AbstractResource(String pathname)
	{
		file = new File(pathname);
	}

	@Override
	public File getFile()
	{
		return file;
	}
}
