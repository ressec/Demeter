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
package com.heliosphere.demeter.base.file.base;

import java.util.ArrayList;
import java.util.List;

import com.heliosphere.demeter.base.file.FileException;
import com.heliosphere.demeter.base.resource.AbstractFile;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.NonNull;

/**
 * Provides an abstract implementation of a structured file composed of a header, a content and a footer.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param <H> Record type of the header.
 * @param <C> Record type of the content.
 * @param <F> Record type of the footer.
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
	@XStreamImplicit
	// TIP: Is defined as a list to avoid XStream to generate a 'class=...' attribute in the XML!
	private List<H> header;

	/**
	 * File content.
	 */
	//	@XStreamImplicit
	// TIP: Is defined as a list to avoid XStream to generate a 'class=...' attribute in the XML!
	private List<C> content;

	/**
	 * File footer.
	 */
	@XStreamImplicit
	// TIP: Is defined as a list to avoid XStream to generate a 'class=...' attribute in the XML!
	private List<F> footer;

	/**
	 * Default constructor.
	 */
	protected AbstractStructuredFile()
	{
		super();
	}

	/**
	 * Creates a new structured file.
	 * <hr>
	 * @param pathname File path name.
	 */
	public AbstractStructuredFile(final @NonNull String pathname)
	{
		super(pathname);

		header = new ArrayList<>();
		content = new ArrayList<>();
		footer = new ArrayList<>();
	}

	@Override
	public abstract void save() throws FileException;

	@Override
	public final H getHeader()
	{
		return header.size() > 0 ? header.get(0) : null;
	}

	@Override
	public final void setHeader(H header)
	{
		this.header.clear();
		this.header.add(header);
	}

	@Override
	public final C getContent()
	{
		return content.size() > 0 ? content.get(0) : null;
	}

	@Override
	public final void setContent(C content)
	{
		this.content.clear();
		this.content.add(content);
	}

	@Override
	public final F getFooter()
	{
		return footer.size() > 0 ? footer.get(0) : null;
	}

	@Override
	public final void setFooter(F footer)
	{
		this.footer.clear();
		this.footer.add(footer);
	}
}
