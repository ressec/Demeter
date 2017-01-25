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

import java.util.ArrayList;
import java.util.List;

import com.heliosphere.demeter.base.file.base.IFileContent;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Represents the file content part of an xml file.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param <C> - Element type composing the file content list (of elements).
 */
public class FileContent<C> implements IFileContent<C>
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * File content.
	 */
	@XStreamAlias("objects")
	private List<C> content = new ArrayList<>();

	@Override
	public final List<C> get()
	{
		return content;
	}

	@Override
	public void set(List<C> content)
	{
		this.content = content;
		//		Collections.copy(this.content, content);
	}

	@Override
	public void add(C record)
	{
		if (content == null)
		{
			content = new ArrayList<>();
		}

		if (!content.contains(record))
		{
			content.add(record);
		}
	}

	@Override
	public void remove(C record)
	{
		if (content != null)
		{
			content.remove(record);
		}
	}

	@Override
	public int size()
	{
		return content != null ? content.size() : 0;
	}
}
