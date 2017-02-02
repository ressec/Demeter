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
package com.heliosphere.demeter.base.runner.parameter.base;

import com.heliosphere.demeter.base.exception.EnumerationException;

/**
 * This interface defines the behavior of enumerations extending the notion of {@code parameter types}. 
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public interface IParameterType
{
	/**
	 * Creates an enumerated value from a given parameter name.
	 * <hr>
	 * @param name Parameter name.
	 * @return Enumerated value.
	 * @throws EnumerationException Thrown in case an error occurred while trying to create the enumerated value.
	 */
	Enum<? extends IParameterType> fromName(final String name) throws EnumerationException;

	/**
	 * Returns the parameter name.
	 * <hr>
	 * @return Parameter name.
	 */
	String getName();
}
