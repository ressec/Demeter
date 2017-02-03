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
package com.heliosphere.demeter.base.runner.parameter;

import com.heliosphere.demeter.base.exception.EnumerationException;
import com.heliosphere.demeter.base.runner.IRunner;
import com.heliosphere.demeter.base.runner.entity.EntityType;
import com.heliosphere.demeter.base.runner.entity.IEntityType;
import com.heliosphere.demeter.base.runner.parameter.base.IParameterType;

import lombok.NonNull;

/**
 * Provides a set of basic parameters to be used by a {@link IRunner} in its XML configuration/execution files.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
@SuppressWarnings("nls")
public enum TestParameterType implements IParameterType
{
	/**
	 * This parameter is <b>reserved</b> for internal use.
	 * <hr>
	 * NEVER DELETE THIS ENTRY!
	 */
	UNKNOWN("", EntityType.DISPLAY),

	/**
	 * Help parameter.
	 * <hr>
	 * This parameter is used to display the help of the options of the program handled by the runner.
	 */
	PARAMETER_HELP("help", EntityType.DISPLAY),

	/**
	 * Version parameter.
	 * <hr>
	 * This parameter is used to display the version number of the program handled by the runner.
	 */
	PARAMETER_VERSION("version", EntityType.DISPLAY),

	/**
	 * Hello parameter.
	 * <hr>
	 * This parameter is used to display the famous "Hello World" sentence.
	 */
	PARAMETER_HELLO("hello", EntityType.DISPLAY);

	/**
	 * Parameter name.
	 */
	private final String name;

	/**
	 * Entity type.
	 */
	private final Enum<? extends IEntityType> entityType;

	/**
	 * Creates a new enumerated value based on a parameter value.
	 * <hr>
	 * @param name Parameter name.
	 * @param type Entity type.
	 */
	private TestParameterType(@NonNull final String name, @NonNull final Enum<? extends IEntityType> type)
	{
		this.name = name;
		this.entityType = type;
	}

	@Override
	public final String getName()
	{
		return name;
	}

	@Override
	public final Enum<? extends IParameterType> fromName(@NonNull final String name) throws EnumerationException
	{
		for (TestParameterType element : TestParameterType.values())
		{
			if (element.getName().equals(name))
			{
				return element;
			}
		}

		throw new EnumerationException("Unable to create for enumeration: " + this.getClass().getSimpleName() + " an enumeated value for name: " + name);
	}

	@Override
	public final Enum<? extends IEntityType> getEntityType()
	{
		return entityType;
	}
}
