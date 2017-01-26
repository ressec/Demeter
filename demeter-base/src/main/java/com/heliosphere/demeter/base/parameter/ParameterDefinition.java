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

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

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
	@XStreamAlias("priority")
	private int priority;

	/**
	 * Parameter description.
	 */
	@XStreamAlias("description")
	private String description;

	/**
	 * Is this parameter mandatory?
	 */
	@XStreamAlias("mandatory")
	private boolean isMandatory;

	/**
	 * Is this parameter reserved for internal usage?
	 */
	@XStreamAlias("reserved")
	private boolean isReserved;

	/**
	 * List of aliases.
	 */
	@XStreamImplicit(itemFieldName = "alias")
	private List<String> aliases;

	/**
	 * List of allowed values.
	 */
	@XStreamImplicit(itemFieldName = "allowed-value")
	private List<String> values;

	/**
	 * List of incompatible parameters.
	 */
	@XStreamImplicit(itemFieldName = "incompatible")
	private List<String> excludes;

	/**
	 * List of required parameters.
	 */
	@XStreamImplicit(itemFieldName = "required")
	private List<String> includes;

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
	public final void setMandatory(boolean isMandatory)
	{
		this.isMandatory = isMandatory;
	}

	@Override
	public final boolean isReserved()
	{
		return isReserved;
	}

	@Override
	public final void setReserved(boolean isReserved)
	{
		this.isReserved = isReserved;
	}

	@Override
	public final List<String> getAliases()
	{
		return aliases;
	}

	@Override
	public final void setAliases(List<String> values)
	{
		this.aliases = values;
	}

	@Override
	public final List<String> getAllowedValues()
	{
		return values;
	}

	@Override
	public final boolean isAllowed(@NonNull final String value)
	{
		for (String element : values)
		{
			if (element.equals(value))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public final List<String> getIncompatibleParameters()
	{
		return excludes;
	}

	@Override
	public final boolean isIncompatible(@NonNull final IParameter parameter)
	{
		for (String element : excludes)
		{
			if (element.equals(parameter.getName()))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public final List<String> getRequiredParameters()
	{
		return includes;
	}

	@Override
	public final boolean isRequired(@NonNull final IParameter parameter)
	{
		for (String element : includes)
		{
			if (element.equals(parameter.getName()))
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
	public final void setAllowedValues(@NonNull final List<String> values)
	{
		this.values = values;
	}

	@Override
	public final void setIncompatibleParameters(@NonNull final List<String> parameters)
	{
		this.excludes = parameters;
	}

	@Override
	public final void setRequiredParameters(@NonNull final List<String> parameters)
	{
		this.includes = parameters;
	}

	@Override
	public final void addAlias(@NonNull final String alias)
	{
		if (aliases == null)
		{
			aliases = new ArrayList<>();
		}

		if (!aliases.contains(alias))
		{
			aliases.add(alias);
		}
	}

	@Override
	public final void addExclude(@NonNull final String name)
	{
		if (excludes == null)
		{
			excludes = new ArrayList<>();
		}

		if (!excludes.contains(name))
		{
			excludes.add(name);
		}
	}

	@Override
	public final void addInclude(@NonNull final String name)
	{
		if (includes == null)
		{
			includes = new ArrayList<>();
		}

		if (!includes.contains(name))
		{
			includes.add(name);
		}
	}

	@Override
	public final void addAllowed(@NonNull final String value)
	{
		if (values == null)
		{
			values = new ArrayList<>();
		}

		if (!values.contains(value))
		{
			values.add(value);
		}
	}
}
