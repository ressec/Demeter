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

import java.util.List;

import com.heliosphere.demeter.base.runner.parameter.base.IParameter;
import com.heliosphere.demeter.base.runner.parameter.base.IParameterType;

/**
 * Defines the behavior of a list containing parameters.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param <T> - Parameter type.
 */
public interface IParameterList<T extends IParameter>
{
	/**
	 * Clears the list of parameters.
	 */
	void clear();

	/**
	 * Returns the elements of the list.
	 * <hr>
	 * @return List of elements.
	 */
	List<T> getElements();

	/**
	 * Sets the list of elements.
	 * <hr>
	 * @param elements List of elements to set.
	 */
	void setElements(List<T> elements);

	/**
	 * Returns a parameter given its type.
	 * <hr>
	 * @param type Parameter type to retrieve.
	 * @return Parameter if found, {@code null} otherwise.
	 */
	T get(Enum<? extends IParameterType> type);

	/**
	 * Returns a parameter given its name.
	 * <hr>
	 * @param name Parameter name.
	 * @return Parameter if found, {@code null} otherwise.
	 */
	T get(String name);

	/**
	 * Adds a parameter to the list.
	 * <hr>
	 * @param parameter Parameter to add.
	 */
	void add(T parameter);

	/**
	 * Removes a parameter from the list.
	 * <hr>
	 * @param parameter Parameter to remove.
	 */
	void remove(T parameter);

	/**
	 * Returns if the list contains the given parameter?
	 * <hr>
	 * @param parameter Parameter to check.
	 * @return {@code True} if the list contains the given parameter, {@code false} otherwise.
	 */
	boolean contain(T parameter);

	/**
	 * Returns if the list contains the given parameter type?
	 * <hr>
	 * @param type Parameter type to check.
	 * @return {@code True} if the list contains the given parameter type, {@code false} otherwise.
	 */
	boolean contain(Enum<? extends IParameterType> type);

	/**
	 * Returns if the list contains the given parameter name?
	 * <hr>
	 * @param name Parameter name to check.
	 * @return {@code True} if the list contains the given parameter name, {@code false} otherwise.
	 */
	boolean contain(String name);
}
