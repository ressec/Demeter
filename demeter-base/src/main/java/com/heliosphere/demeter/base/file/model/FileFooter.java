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
package com.heliosphere.demeter.base.file.model;

import com.heliosphere.demeter.base.file.base.IFileFooter;

import lombok.NonNull;

/**
 * Represents the file footer part of an xml file.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param <F> - Element type embedded in the file footer.
 */
public class FileFooter<F> implements IFileFooter<F>
{
	/**
	 * Default serialization identifier. 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * File footer.
	 */
	private F footer;

	@Override
	public final F get()
	{
		return footer;
	}

	@Override
	public final void set(@NonNull final F footer)
	{
		this.footer = footer;
	}
}
