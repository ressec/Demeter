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
package com.heliosphere.demeter.base.file.xml.model;

import java.util.List;

import com.heliosphere.demeter.base.file.base.AbstractStructuredFile;
import com.heliosphere.demeter.base.file.xml.base.AbstractXmlFile;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.NonNull;

/**
 * Provides a concrete implementation of an XML file.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param <H> - Header type.
 * @param <C> - Content element type.
 * @param <F> - Footer element type.
 */
@XStreamAlias("file")
public class SimpleXmlFile<H, C, F> extends AbstractXmlFile<H, C, F>
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new XML file.
	 * <hr>
	 * @param pathname XML file path name.
	 */
	public SimpleXmlFile(final @NonNull String pathname)
	{
		super(pathname);
	}

	@SuppressWarnings("nls")
	@Override
	public void setAliases()
	{
		super.setAliases();

		getEngine().alias("file", this.getClass());

		// Aliases the header tag with the Header class.
		getEngine().alias("header", Header.class);

		// Aliases the footer tag with the Footer class.
		getEngine().alias("footer", Footer.class);

		// Aliases the content tag with the Content class.
		getEngine().alias("content", List.class);

		// Aliases the 'content' list as 'parameters'.
		getEngine().aliasAttribute(AbstractStructuredFile.class, "content", "data");
	}
}
