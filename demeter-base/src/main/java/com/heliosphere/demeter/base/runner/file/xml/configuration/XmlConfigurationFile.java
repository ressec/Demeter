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

import com.heliosphere.demeter.base.file.base.AbstractStructuredFile;
import com.heliosphere.demeter.base.file.xml.base.AbstractXmlFile;
import com.heliosphere.demeter.base.file.xml.model.Footer;
import com.heliosphere.demeter.base.file.xml.model.Header;
import com.heliosphere.demeter.base.parameter.IParameterDefinition;
import com.heliosphere.demeter.base.parameter.ParameterDefinition;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.mapper.ClassAliasingMapper;

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

		ClassAliasingMapper mapper = new ClassAliasingMapper(getEngine().getMapper());

		// Converter for elements of the 'aliases' list in ParameterDefinition class.
		mapper.addClassAlias("alias", String.class);
		getEngine().registerLocalConverter(ParameterDefinition.class, "aliases", new CollectionConverter(mapper));

		// Converter for elements of the 'values' list in ParameterDefinition class.
		mapper.addClassAlias("value", String.class);
		getEngine().registerLocalConverter(ParameterDefinition.class, "values", new CollectionConverter(mapper));

		// Converter for elements of the 'excludes' list in ParameterDefinition class.
		mapper.addClassAlias("exclude", String.class);
		getEngine().registerLocalConverter(ParameterDefinition.class, "excludes", new CollectionConverter(mapper));

		// Converter for elements of the 'includes' list in ParameterDefinition class.
		mapper.addClassAlias("include", String.class);
		getEngine().registerLocalConverter(ParameterDefinition.class, "includes", new CollectionConverter(mapper));

		// Aliases the main file tag.
		getEngine().alias("xml-configuration-file", this.getClass());

		// Aliases the interface of a parameter definition with its implementation.
		getEngine().alias("parameter", IParameterDefinition.class, ParameterDefinition.class);

		// Aliases the header tag with the Header class.
		getEngine().alias("header", Header.class);

		// Aliases the footer tag with the Footer class.
		getEngine().alias("footer", Footer.class);

		// Aliases the 'content' list as 'parameters'.
		getEngine().aliasAttribute(AbstractStructuredFile.class, "content", "parameters");
	}
}
