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

import com.heliosphere.demeter.base.file.IFileFooter;

import lombok.NonNull;

public class FileFooter<F> implements IFileFooter<F>
{
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
