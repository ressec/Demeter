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
package com.heliosphere.demeter.base.file.xml.base;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.heliosphere.demeter.base.file.AbstractStructuredFile;
import com.heliosphere.demeter.base.file.FileException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.Getter;

/**
 * Provides an abstract implementation of a XML file.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param 	<H> Record type of the header. 
 * @param 	<C> Record type of the content.
 * @param 	<F> Record type of the footer.
 */
public abstract class AbstractXmlFile<H, C, F> extends AbstractStructuredFile<H, C, F> implements IXmlFile<H, C, F>
{
	/**
	 * Default serialization identifier.
	 */
	@XStreamOmitField
	private static final long serialVersionUID = 1L;

	/**
	 * XML engine to use.
	 */
	@XStreamOmitField
	@Getter
	private XStream engine = new XStream();

	/**
	 * Creates a new abstract XML file.
	 * <hr>
	 * @param pathname XML file path name.
	 */
	public AbstractXmlFile(String pathname)
	{
		super(pathname);
	}

	@Override
	public void setAliases()
	{
		engine.autodetectAnnotations(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void load() throws FileException
	{
		setAliases();

		try
		{
			IXmlFile<?, ?, ?> holder = (IXmlFile<H, C, F>) engine.fromXML(getResource().getFile());
			setHeader((H) holder.getHeader());
			setFooter((F) holder.getFooter());
			setContent((List<C>) holder.getContent());
		}
		catch (XStreamException e)
		{
			throw new FileException(e);
		}
	}

	@Override
	public void save() throws FileException
	{
		setAliases();

		if (getResource() != null)
		{
			try (FileOutputStream output = new FileOutputStream(getResource().getFile().getAbsolutePath()))
			{
				engine.toXML(this, output);
			}
			catch (XStreamException | IOException e)
			{
				throw new FileException(e);
			}
		}
	}
}
