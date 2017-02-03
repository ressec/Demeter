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
package com.heliosphere.demeter.base.runner.parameter.execution;

import java.util.HashMap;
import java.util.Map;

import com.heliosphere.demeter.base.runner.parameter.base.AbstractParameter;
import com.heliosphere.demeter.base.runner.parameter.base.ParameterStatusType;
import com.heliosphere.demeter.base.runner.parameter.configuration.IParameterConfiguration;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.NonNull;

/**
 * Provides a basic implementation of a parameter execution.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class ParameterExecution extends AbstractParameter implements IParameterExecution
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Parameter value.
	 */
	private String value;

	/**
	 * Parameter status.
	 */
	@XStreamOmitField
	private ParameterStatusType status = ParameterStatusType.UNPROCESSED;

	/**
	 * Parameter properties.
	 */
	private Map<String, Object> properties;

	/**
	 * Parameter definition.
	 */
	private IParameterConfiguration definition;

	@Override
	public final String getValue()
	{
		return value;
	}

	@Override
	public final void setValue(final String value)
	{
		this.value = value;
	}

	@Override
	public final void addProperty(@NonNull final String name, Object value)
	{
		if (properties == null)
		{
			properties = new HashMap<>();
		}

		properties.put(name, value);
	}

	@Override
	public final void removeProperty(@NonNull final String name)
	{
		if (properties != null)
		{
			properties.remove(name);
		}
	}

	@Override
	public final Object getProperty(@NonNull final String name)
	{
		if (properties != null)
		{
			return properties.get(name);
		}

		return null;
	}

	@Override
	public final void deleteProperties()
	{
		if (properties != null)
		{
			properties.clear();
		}
	}

	@Override
	public final int countProperties()
	{
		if (properties != null)
		{
			return properties.size();
		}

		return 0;
	}

	@Override
	public final boolean existProperty(@NonNull final String name)
	{
		if (properties != null)
		{
			return properties.containsKey(name);
		}

		return false;
	}

	@Override
	public final IParameterConfiguration getDefinition()
	{
		return definition;
	}

	@Override
	public final ParameterStatusType getStatus()
	{
		return status;
	}

	@Override
	public final void setStatus(@NonNull final ParameterStatusType status)
	{
		this.status = status;
	}
}
