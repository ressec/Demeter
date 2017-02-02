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
package com.heliosphere.demeter.base.runner.parameter.base;

/**
 * Provides an abstract implementation of a parameter.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class AbstractParameter implements IParameter
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Parameter name.
	 */
	private String name;

	/**
	 * Parameter type.
	 */
	private Enum<? extends IParameterType> type;

	@Override
	public final String getName()
	{
		return name;
	}

	@Override
	public final void setName(String name)
	{
		this.name = name;
	}

	@Override
	public final Enum<? extends IParameterType> getType()
	{
		return type;
	}

	@Override
	public final void setType(Enum<? extends IParameterType> type)
	{
		this.type = type;
	}

	@Override
	public int compareTo(IParameter o)
	{
		return name.compareTo(o.getName());
	}
}
