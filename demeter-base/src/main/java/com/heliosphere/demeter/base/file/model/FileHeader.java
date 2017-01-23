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

import com.heliosphere.demeter.base.file.IFileHeader;

import lombok.NonNull;

/**
 * Represents the file header part of an xml file.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param <H> - Element type embedded in the file header.
 */
public class FileHeader<H> implements IFileHeader<H>
{
	/**
	 * Default serialization identifier. 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * File header.
	 */
	private H header;

	@Override
	public final H get()
	{
		return header;
	}

	@Override
	public final void set(@NonNull H header)
	{
		this.header = header;
	}
}
