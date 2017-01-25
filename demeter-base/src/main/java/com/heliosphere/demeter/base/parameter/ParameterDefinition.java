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
package com.heliosphere.demeter.base.parameter;

import java.util.List;

import lombok.NonNull;

/**
 * Provides a basic implementation of a parameter definition.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class ParameterDefinition extends AbstractParameter implements IParameterDefinition
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Parameter priority.
	 */
	private int priority;

	/**
	 * Parameter description.
	 */
	private String description;

	/**
	 * Is this parameter mandatory?
	 */
	private boolean isMandatory;

	/**
	 * List of allowed values.
	 */
	private List<String> allowedValues;

	/**
	 * List of incompatible parameters.
	 */
	private List<IParameterDefinition> incompatibleParameters;

	/**
	 * List of required parameters.
	 */
	private List<IParameterDefinition> requiredParameters;

	@Override
	public final int getPriority()
	{
		return priority;
	}

	@Override
	public final String getDescription()
	{
		return description;
	}

	@Override
	public final boolean isMandatory()
	{
		return isMandatory;
	}

	@Override
	public final List<String> getAllowedValues()
	{
		return allowedValues;
	}

	@Override
	public final boolean isAllowed(@NonNull final String value)
	{
		for (String element : allowedValues)
		{
			if (element.equals(value))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public final List<IParameterDefinition> getIncompatibleParameters()
	{
		return incompatibleParameters;
	}

	@Override
	public final boolean isIncompatible(@NonNull final IParameter parameter)
	{
		for (IParameter element : incompatibleParameters)
		{
			if (element.getName().equals(parameter.getName()))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public final List<IParameterDefinition> getRequiredParameters()
	{
		return requiredParameters;
	}

	@Override
	public final boolean isRequired(@NonNull final IParameter parameter)
	{
		for (IParameter element : requiredParameters)
		{
			if (element.getName().equals(parameter.getName()))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public final void setPriority(int priority)
	{
		this.priority = priority;
	}

	@Override
	public final void setDescription(@NonNull final String description)
	{
		this.description = description;
	}

	@Override
	public final void setMandatory(boolean isMandatory)
	{
		this.isMandatory = isMandatory;
	}

	@Override
	public final void setAllowedValues(@NonNull final List<String> values)
	{
		this.allowedValues = values;
	}

	@Override
	public final void setIncompatibleParameters(@NonNull final List<IParameterDefinition> parameters)
	{
		this.incompatibleParameters = parameters;
	}

	@Override
	public final void setRequiredParameters(@NonNull final List<IParameterDefinition> parameters)
	{
		this.requiredParameters = parameters;
	}
}
