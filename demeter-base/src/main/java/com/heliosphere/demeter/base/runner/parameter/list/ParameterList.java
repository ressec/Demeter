/*
 * Copyright(c) 2017 - Heliosphere Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Heliosphere's project which is licensed under the 
 * Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package com.heliosphere.demeter.base.runner.parameter.list;

import java.util.ArrayList;
import java.util.List;

import com.heliosphere.demeter.base.runner.parameter.base.IParameter;
import com.heliosphere.demeter.base.runner.parameter.base.IParameterType;

import lombok.NonNull;

/**
 * Provides a concrete implementation of a list of parameters.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param <T> - Type of parameter.
 */
public final class ParameterList<T extends IParameter> implements IParameterList<T>
{
	/**
	 * List of parameters.
	 */
	private List<T> parameters;

	@Override
	public final List<T> getElements()
	{
		return parameters;
	}

	@Override
	public final void setElements(@NonNull final List<T> elements)
	{
		this.parameters = elements;
	}

	@Override
	public final T get(@NonNull final Enum<? extends IParameterType> type)
	{
		if (parameters != null)
		{
			for (T parameter : parameters)
			{
				if (parameter.getType() == type)
				{
					return parameter;
				}
			}
		}

		return null;
	}

	@Override
	public final T get(@NonNull final String name)
	{
		if (parameters != null)
		{
			for (T parameter : parameters)
			{
				if (parameter.getName().equals(name))
				{
					return parameter;
				}
			}
		}

		return null;
	}

	@Override
	public final void add(@NonNull final T parameter)
	{
		if (parameters == null)
		{
			parameters = new ArrayList<>();
		}

		for (T element : parameters)
		{
			if (element.getType() == parameter.getType())
			{
				return; // Parameter of this type already exist!
			}
		}

		parameters.add(parameter);
	}

	@Override
	public final void remove(@NonNull final T parameter)
	{
		if (parameters == null)
		{
			return;
		}

		parameters.remove(parameter);
	}

	@Override
	public final void clear()
	{
		parameters.clear();
		parameters = null;
	}

	@Override
	public final boolean contain(@NonNull final T parameter)
	{
		return get(parameter.getType()) != null ? true : false;
	}

	@Override
	public final boolean contain(@NonNull final Enum<? extends IParameterType> type)
	{
		return get(type) != null ? true : false;
	}

	@Override
	public final boolean contain(@NonNull final String name)
	{
		return get(name) != null ? true : false;
	}
}
