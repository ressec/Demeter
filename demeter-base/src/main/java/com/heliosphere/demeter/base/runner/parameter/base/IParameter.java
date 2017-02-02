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

import java.io.Serializable;

/**
 * Defines the behavior of a parameter.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public interface IParameter extends Serializable, Cloneable, Comparable<IParameter>
{
	/**
	 * Returns the parameter name.
	 * <hr>
	 * @return Name.
	 */
	String getName();

	/**
	 * Sets the parameter name.
	 * <hr>
	 * @param name Name to set.
	 */
	void setName(String name);

	/**
	 * Returns the parameter type.
	 * <hr>
	 * @return Type.
	 */
	Enum<? extends IParameterType> getType();

	/**
	 * Sets the type of the parameter.
	 * <hr>
	 * @param type Type to set.
	 */
	void setType(final Enum<? extends IParameterType> type);

}
