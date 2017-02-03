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
package com.heliosphere.demeter.base.runner.file.xml.execution;

import com.heliosphere.demeter.base.file.xml.base.AbstractXmlFile;
import com.heliosphere.demeter.base.file.xml.model.Footer;
import com.heliosphere.demeter.base.file.xml.model.Header;
import com.heliosphere.demeter.base.runner.parameter.base.IParameter;
import com.heliosphere.demeter.base.runner.parameter.base.IParameterType;
import com.heliosphere.demeter.base.runner.parameter.execution.IParameterExecution;
import com.heliosphere.demeter.base.runner.parameter.execution.ParameterExecution;
import com.heliosphere.demeter.base.runner.parameter.list.ParameterList;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.mapper.ClassAliasingMapper;

import lombok.NonNull;

/**
 * Provides a concrete implementation of a {@code Runner} XML execution file.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class XmlExecutionFile extends AbstractXmlFile<Header, ParameterList<IParameterExecution>, Footer>
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
	public XmlExecutionFile(final @NonNull String pathname)
	{
		super(pathname);
	}

	/**
	 * Returns an execution parameter given a parameter name.
	 * <hr>
	 * @param name Parameter name (case sensitive).
	 * @return {@link IParameterExecution} if found, {@code null} otherwise.
	 */
	public final IParameterExecution getParameter(final @NonNull String name)
	{
		for (IParameterExecution parameter : getContent().getElements())
		{
			// Does the parameter name is matching the given name?
			if (parameter.getName().equals(name))
			{
				return parameter;
			}
		}

		return null;
	}

	/**
	 * Returns an execution parameter given a parameter type.
	 * <hr>
	 * @param type Parameter type to retrieve.
	 * @return {@link IParameterExecution} if found, {@code null} otherwise.
	 */
	public final IParameterExecution getParameter(final @NonNull Enum<? extends IParameterType> type)
	{
		for (IParameterExecution parameter : getContent().getElements())
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
	 * Returns an execution parameter given a parameter.
	 * <hr>
	 * @param parameter Parameter to retrieve.
	 * @return {@link IParameterExecution} if found, {@code null} otherwise.
	 */
	public final IParameterExecution getParameter(final @NonNull IParameter parameter)
	{
		return getParameter(parameter.getType());
	}

	@SuppressWarnings("nls")
	@Override
	public void setAliases()
	{
		super.setAliases();

		// Converter for elements of the 'values' list in ParameterExecution class.
		ClassAliasingMapper mapper = new ClassAliasingMapper(getEngine().getMapper());
		mapper.addClassAlias("value", String.class);
		getEngine().registerLocalConverter(ParameterExecution.class, "values", new CollectionConverter(mapper));

		// Aliases the main file tag.
		getEngine().alias("xml-execution-file", this.getClass());

		// Aliases the interface of a parameter execution with its implementation.
		getEngine().alias("parameter", IParameterExecution.class, ParameterExecution.class);

		// Aliases the header tag with the Header class.
		getEngine().alias("header", Header.class);

		// Aliases the footer tag with the Footer class.
		getEngine().alias("footer", Footer.class);

		// Aliases the content tag with the List class.
		getEngine().alias("data", ParameterList.class);
	}
}
