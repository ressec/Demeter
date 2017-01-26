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
package com.heliosphere.demeter.base.runner.file.xml.configuration;

import com.heliosphere.demeter.base.file.model.FileContent;
import com.heliosphere.demeter.base.file.xml.base.AbstractXmlFile;
import com.heliosphere.demeter.base.parameter.IParameterDefinition;
import com.heliosphere.demeter.base.parameter.ParameterDefinition;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.NonNull;

/**
 * Provides a concrete implementation of a {@code Runner} XML definition file.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param <H> - Header type.
 * @param <C> - Content element type.
 * @param <F> - Footer element type.
 */
@XStreamAlias("xml-definition-file")
public class XmlConfigurationFile<H, C, F> extends AbstractXmlFile<H, C, F>
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
	public XmlConfigurationFile(final @NonNull String pathname)
	{
		super(pathname);
	}

	@SuppressWarnings("nls")
	@Override
	public void setAliases()
	{
		super.setAliases();

		getEngine().alias("xml-definition-file", this.getClass());
		getEngine().aliasField("parameters", FileContent.class, "content");
		getEngine().alias("parameter", IParameterDefinition.class, ParameterDefinition.class);
		//getEngine().alias("alias", String.class);
		//getEngine().alias("allowed-value", String.class);
		//getEngine().alias("exclude", String.class);
		//getEngine().alias("include", String.class);
		//getEngine().addImplicitCollection(ParameterDefinition.class, "values", "allowed-value", String.class);
	}
}
