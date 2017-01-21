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

import lombok.NonNull;

/**
 * Provides a concrete implementation of an {@link IElement}.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param   <T> Value object type.
 */
public class Element<T> extends AbstractElement<T>
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new element.
	 * <hr>
	 * @param name Name.
	 */
	public Element(@NonNull final String name)
	{
		super(name);
	}

	/**
	 * Creates a new element.
	 * <hr>
	 * @param name Name.
	 * @param value Value.
	 */
	public Element(@NonNull final String name, final T value)
	{
		super(name, value);
	}
}
