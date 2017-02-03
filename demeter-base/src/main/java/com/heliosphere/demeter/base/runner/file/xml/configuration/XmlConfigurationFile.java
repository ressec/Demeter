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

import com.heliosphere.demeter.base.file.xml.base.AbstractXmlFile;
import com.heliosphere.demeter.base.file.xml.model.Footer;
import com.heliosphere.demeter.base.file.xml.model.Header;
import com.heliosphere.demeter.base.runner.parameter.base.IParameter;
import com.heliosphere.demeter.base.runner.parameter.base.IParameterType;
import com.heliosphere.demeter.base.runner.parameter.configuration.IParameterConfiguration;
import com.heliosphere.demeter.base.runner.parameter.configuration.ParameterConfiguration;
import com.heliosphere.demeter.base.runner.parameter.list.ParameterList;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.mapper.ClassAliasingMapper;

import lombok.NonNull;

/**
 * Provides a concrete implementation of a {@code Runner} XML configuration file.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class XmlConfigurationFile extends AbstractXmlFile<Header, ParameterList<IParameterConfiguration>, Footer>
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

	/**
	 * Returns a configuration parameter given a parameter name or alias.
	 * <hr>
	 * @param nameOrAlias Parameter name or alias (case sensitive).
	 * @return {@link IParameterConfiguration} if found, {@code null} otherwise.
	 */
	public final IParameterConfiguration getParameter(final @NonNull String nameOrAlias)
	{
		for (IParameterConfiguration parameter : getContent().getElements())
		{
			// Does the parameter name is matching the given name?
			if (parameter.getName().equals(nameOrAlias))
			{
				return parameter;
			}

			// Does one of the parameter alias (if some are defined) is matching the given alias?
			if (parameter.getAliases() != null)
			{
				for (String alias : parameter.getAliases())
				{
					if (alias.equals(nameOrAlias))
					{
						return parameter;
					}
				}
			}
		}

		return null;
	}

	/**
	 * Returns a configuration parameter given a parameter type.
	 * <hr>
	 * @param type Parameter type to retrieve.
	 * @return {@link IParameterConfiguration} if found, {@code null} otherwise.
	 */
	public final IParameterConfiguration getParameter(final @NonNull Enum<? extends IParameterType> type)
	{
		for (IParameterConfiguration parameter : getContent().getElements())
		{
			// Does the parameter type is matching the given type?
			if (parameter.getType() == type)
			{
				return parameter;
			}
		}

		return null;
	}

	/**
	 * Returns a configuration parameter given a parameter.
	 * <hr>
	 * @param parameter Parameter to retrieve.
	 * @return {@link IParameterConfiguration} if found, {@code null} otherwise.
	 */
	public final IParameterConfiguration getParameter(final @NonNull IParameter parameter)
	{
		return getParameter(parameter.getType());
	}

	@SuppressWarnings("nls")
	@Override
	public void setAliases()
	{
		super.setAliases();

		// Aliases the main file tag.
		getEngine().alias("xml-configuration-file", this.getClass());

		// Aliases the interface of a parameter definition with its implementation.
		getEngine().alias("parameter", IParameterConfiguration.class, ParameterConfiguration.class);

		// Aliases the header tag with the Header class.
		getEngine().alias("header", Header.class);

		// Aliases the footer tag with the Footer class.
		getEngine().alias("footer", Footer.class);

		// Aliases the content tag with the List class.
		getEngine().alias("data", ParameterList.class);

		// Converter for elements of the 'aliases' list in ParameterDefinition class.
		ClassAliasingMapper aliasMapper = new ClassAliasingMapper(getEngine().getMapper());
		aliasMapper.addClassAlias("alias", String.class);
		getEngine().registerLocalConverter(ParameterConfiguration.class, "aliases", new CollectionConverter(aliasMapper));

		// Converter for elements of the 'values' list in ParameterDefinition class.
		ClassAliasingMapper valueMapper = new ClassAliasingMapper(getEngine().getMapper());
		valueMapper.addClassAlias("value", String.class);
		getEngine().registerLocalConverter(ParameterConfiguration.class, "values", new CollectionConverter(valueMapper));

		// Converter for elements of the 'excludes' list in ParameterDefinition class.
		ClassAliasingMapper excludeMapper = new ClassAliasingMapper(getEngine().getMapper());
		excludeMapper.addClassAlias("exclude", String.class);
		getEngine().registerLocalConverter(ParameterConfiguration.class, "excludes", new CollectionConverter(excludeMapper));

		// Converter for elements of the 'includes' list in ParameterDefinition class.
		ClassAliasingMapper includeMapper = new ClassAliasingMapper(getEngine().getMapper());
		includeMapper.addClassAlias("include", String.class);
		getEngine().registerLocalConverter(ParameterConfiguration.class, "includes", new CollectionConverter(includeMapper));
	}
}
