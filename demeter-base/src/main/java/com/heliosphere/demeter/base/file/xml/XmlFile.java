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
package com.heliosphere.demeter.base.file.xml;

import java.util.ArrayList;

import com.heliosphere.demeter.base.file.model.FileContent;

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
public class XmlFile<H, C, F> extends AbstractXmlFile<H, C, F>
{
	/**
	 * Creates a new XML file.
	 * <hr>
	 * @param pathname XML file path name.
	 */
	public XmlFile(final @NonNull String pathname)
	{
		super(pathname);
	}

	@Override
	public void setAliases()
	{
		super.setAliases();

		getEngine().alias("header", getHeader().getClass());
		getEngine().alias("data", FileContent.class, ArrayList.class);
		getEngine().alias("footer", getFooter().getClass());
	}
}
