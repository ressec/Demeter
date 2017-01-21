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
package com.heliosphere.demeter.base.element;

import java.io.Serializable;

/**
 * This interface defines the behavior of an {@code element}. An element is an object
 * grouping a name and a value. 
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere <a/>
 * @version 1.0.0
 * @param  <T> Value type.
 */
public interface IElement<T> extends Serializable, Comparable<IElement<T>>
{
	/**
	 * Returns the name of the element. 
	 * <hr>
	 * @return Element name.
	 */
	String getName();

	/**
	 * Sets the name of the element. 
	 * <hr>
	 * @param name Element name to set.
	 */
	void setName(String name);

	/**
	 * Returns the element value. 
	 * <hr>
	 * @return Element value.
	 */
	T getValue();

	/**
	 * Sets the element value. 
	 * <hr>
	 * @param value Element value to set.
	 */
	void setValue(T value);
}
