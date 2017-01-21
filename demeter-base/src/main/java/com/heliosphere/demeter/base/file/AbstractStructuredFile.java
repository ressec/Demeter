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
package com.heliosphere.demeter.base.file;

import java.util.List;

import com.heliosphere.demeter.base.file.model.FileContent;
import com.heliosphere.demeter.base.file.model.FileFooter;
import com.heliosphere.demeter.base.file.model.FileHeader;
import com.heliosphere.demeter.base.resource.AbstractFile;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.NonNull;

/**
 * Provides an abstract implementation of a structured file composed of a header, a content and a footer.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param 	<H> Record type of the header. 
 * @param 	<C> Record type of the content.
 * @param 	<F> Record type of the footer.
 */
public abstract class AbstractStructuredFile<H, C, F> extends AbstractFile implements IStructuredFile<H, C, F>
{
	/**
	 * Default serialization identifier.
	 */
	@XStreamOmitField
	private static final long serialVersionUID = 1L;

	/** 
	 * File header.
	 */
	@XStreamAlias("file-header")
	private FileHeader<H> header;

	/**
	 * File content.
	 */
	@XStreamAlias("file-content")
	private FileContent<C> content;

	/**
	 * File footer.
	 */
	@XStreamAlias("file-footer")
	private FileFooter<F> footer;

	/**
	 * Creates a new structured file.
	 * <hr>
	 * @param pathname File path name.
	 */
	public AbstractStructuredFile(final @NonNull String pathname)
	{
		super(pathname);
	}

	@Override
	public abstract void load() throws FileException;

	@Override
	public abstract void save() throws FileException;

	@Override
	public final H getHeader()
	{
		return header.get();
	}

	@Override
	public final void setHeader(H header)
	{
		if (this.header == null)
		{
			this.header = new FileHeader<>();
		}

		this.header.set(header);
	}

	@Override
	public final List<C> getContent()
	{
		return content.get();
	}

	@Override
	public final void setContent(List<C> content)
	{
		this.content.set(content);
	}

	@Override
	public final void addContent(C element)
	{
		if (this.content == null)
		{
			this.content = new FileContent<>();
		}

		this.content.add(element);
	}

	@Override
	public final F getFooter()
	{
		return footer.get();
	}

	@Override
	public final void setFooter(F footer)
	{
		if (this.footer == null)
		{
			this.footer = new FileFooter<>();
		}

		this.footer.set(footer);
	}
}
