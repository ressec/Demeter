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

import com.heliosphere.demeter.base.runner.parameter.base.IParameter;
import com.heliosphere.demeter.base.runner.parameter.base.ParameterStatusType;
import com.heliosphere.demeter.base.runner.parameter.configuration.IParameterConfiguration;

/**
 * Defines the behavior of an execution parameter.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public interface IParameterExecution extends IParameter
{
	/**
	 * Returns the parameter value.
	 * <hr>
	 * @return Value.
	 */
	String getValue();

	/**
	 * Sets the parameter value.
	 * <hr>
	 * @param value Value to set.
	 */
	void setValue(String value);

	/**
	 * Adds a property.
	 * <hr>
	 * An execution parameter can contain (at runtime) some additional properties.
	 * <hr>
	 * @param name Property name.
	 * @param value Property value.
	 */
	void addProperty(String name, Object value);

	/**
	 * Removes a property.
	 * <hr>
	 * @param name Property name.
	 */
	void removeProperty(String name);

	/**
	 * Returns the value of the given property.
	 * <hr>
	 * @param name Property name.
	 * @return Property value.
	 */
	Object getProperty(String name);

	/**
	 * Removes all properties.
	 */
	void deleteProperties();

	/**
	 * Returns the total number of properties.
	 * <hr>
	 * @return Number of properties.
	 */
	int countProperties();

	/**
	 * Returns if the given property exist given its name?
	 * <hr>
	 * @param name Property name.
	 * @return {@code True} if the given property exist, {@code false} otherwise.
	 */
	boolean existProperty(String name);

	/**
	 * Returns the associated configuration parameter.
	 * <hr>
	 * @return Parameter configuration.
	 */
	IParameterConfiguration getConfiguration();

	/**
	 * Sets the associated parameter configuration.
	 * <hr>
	 * @param parameter Configuration parameter to set.
	 */
	void setConfiguration(IParameterConfiguration parameter);

	/**
	 * Returns the status of the parameter.
	 * <hr>
	 * @return Parameter status.
	 */
	ParameterStatusType getStatus();

	/**
	 * Sets the status of the parameter.
	 * <hr>
	 * @param status Status to set.
	 */
	void setStatus(ParameterStatusType status);
}
