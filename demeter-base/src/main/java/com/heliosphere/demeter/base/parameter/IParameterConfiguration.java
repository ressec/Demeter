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

/**
 * Defines the behavior of the definition of a parameter.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public interface IParameterConfiguration extends IParameter
{
	/**
	 * Returns the parameter priority.
	 * <hr>
	 * The parameter priority is used when processing a context containing several execution parameters. Those
	 * parameters are processed according to their priority. The parameters with low priority are processed first and
	 * the parameters with high priority are processed after.
	 * <hr>
	 * @return Priority.
	 */
	int getPriority();

	/**
	 * Set the parameter priority.
	 * <hr>
	 * @param priority Priority to set.
	 */
	void setPriority(int priority);

	/**
	 * Returns the parameter description.
	 * <hr>
	 * @return Description.
	 */
	String getDescription();

	/**
	 * Set the parameter description.
	 * <hr>
	 * @param description Description to set.
	 */
	void setDescription(String description);

	/**
	 * Returns if this parameter is mandatory?
	 * <hr>
	 * @return {@code True} if this parameter is mandatory, {@code false} otherwise.
	 */
	boolean isMandatory();

	/**
	 * Sets if this parameter is mandatory.
	 * <hr>
	 * @param isMandatory {@code True} if this parameter is mandatory, {@code false} otherwise.
	 */
	void setMandatory(boolean isMandatory);

	/**
	 * Returns if this parameter is reserved for internal usage only?
	 * <hr>
	 * @return {@code True} if this parameter is reserved for internal usage only, {@code false} otherwise.
	 */
	boolean isReserved();

	/**
	 * Sets if this parameter is reserved for internal usage only.
	 * <hr>
	 * @param isReserved {@code True} if this parameter is reserved for internal usage only, {@code false} otherwise.
	 */
	void setReserved(boolean isReserved);

	/**
	 * Returns a list of allowed values. If this list is {@code null}, it means there is
	 * no restriction for the values this parameter can hold.
	 * <hr>
	 * @return List of allowed values.
	 */
	List<String> getAllowedValues();

	/**
	 * Sets a list of allowed values.
	 * <hr>
	 * @param values List of allowed values.
	 */
	void setAllowedValues(List<String> values);

	/**
	 * Returns a list of parameter aliases.
	 * <hr>
	 * @return List of aliases.
	 */
	List<String> getAliases();

	/**
	 * Sets a list of parameter aliases.
	 * <hr>
	 * @param values List of aliases.
	 */
	void setAliases(List<String> values);

	/**
	 * Returns if the given value is allowed?
	 * <hr>
	 * @param value Value to check.
	 * @return {@code True} if the given value is allowed, {@code false} otherwise. 
	 */
	boolean isAllowed(String value);

	/**
	 * Returns a list of incompatible parameters.
	 * <hr>
	 * @return List of incompatible parameters name.
	 */
	List<String> getIncompatibleParameters();

	/**
	 * Sets a list of incompatible parameters.
	 * <hr>
	 * @param parameters List of incompatible parameters name to set.
	 */
	void setIncompatibleParameters(List<String> parameters);

	/**
	 * Returns if the given parameter is incompatible?
	 * <hr>
	 * @param parameter Parameter to check for incompatibility.
	 * @return {@code True} if the given parameter is incompatible, {@code false} otherwise. 
	 */
	boolean isIncompatible(IParameter parameter);

	/**
	 * Returns a list of required parameters.
	 * <hr>
	 * @return List of required parameters name.
	 */
	List<String> getRequiredParameters();

	/**
	 * Sets a list of required parameters.
	 * <hr>
	 * @param parameters List of required parameters name to set.
	 */
	void setRequiredParameters(List<String> parameters);

	/**
	 * Returns if the given parameter is required?
	 * <hr>
	 * @param parameter Parameter to check for requirement.
	 * @return {@code True} if the given parameter is required, {@code false} otherwise. 
	 */
	boolean isRequired(IParameter parameter);

	/**
	 * Adds an alias to the parameter's alias list.
	 * <hr>
	 * @param alias Alias to add to the list. If the given alias already exist, it will be ignored.
	 */
	void addAlias(String alias);

	/**
	 * Adds a parameter name to the list of excluded - incompatible - parameters for this parameter.
	 * <hr>
	 * @param name Parameter name to be added to the list of the excluded parameters.
	 */
	void addExclude(String name);

	/**
	 * Adds a parameter name to the list of included - required - parameters for this parameter.
	 * <hr>
	 * @param name Parameter name to be added to the list of the included parameters.
	 */
	void addInclude(String name);

	/**
	 * Adds a value to the list of allowed values for this parameter.
	 * <hr>
	 * @param value Allowed value to add to the list.
	 */
	void addAllowed(String value);
}
