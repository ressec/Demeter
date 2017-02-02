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

import lombok.NonNull;

/**
 * Provides a set of basic parameters to be used by a {@link IRunner} in its XML configuration/execution files.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public enum BasicRunnerParameterType implements IParameterType
{
	/**
	 * This parameter is <b>reserved</b> for internal use.
	 * <hr>
	 * NEVER DELETE THIS ENTRY! 
	 */
	@SuppressWarnings("nls")
	UNKNOWN(""),

	/**
	 * Help parameter.
	 * <hr>
	 * This parameter is used to display the help of the options of the program handled by the runner.
	 */
	@SuppressWarnings("nls")
	PARAMETER_HELP("h"),

	/**
	 * Version parameter.
	 * <hr>
	 * This parameter is used to display the version number of the program handled by the runner.
	 */
	@SuppressWarnings("nls")
	PARAMETER_VERSION("v"),

	/**
	 * Hello parameter.
	 * <hr>
	 * This parameter is used to display the famous "Hello World" sentence.
	 */
	@SuppressWarnings("nls")
	PARAMETER_HELLO("h");

	/**
	* Parameter name.
	*/
	private final String name;

	/**
	* Creates a new enumerated value based on a parameter value.
	* <hr>
	* @param name Parameter name.
	*/
	private BasicRunnerParameterType(@NonNull final String name)
	{
		this.name = name;
	}

	@Override
	public final String getName()
	{
		return name;
	}

	@SuppressWarnings("nls")
	@Override
	public final Enum<? extends IParameterType> fromName(@NonNull final String name) throws EnumerationException
	{
		for (BasicRunnerParameterType element : BasicRunnerParameterType.values())
		{
			if (element.getName().equals(name))
			{
				return element;
			}
		}

		throw new EnumerationException("Unable to create for enumeration " + this.getClass().getSimpleName() + ", enumeated value for name: " + name);
	}
}
