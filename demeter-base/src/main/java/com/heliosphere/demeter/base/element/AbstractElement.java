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
 * Provides an abstract implementation of an {@link IElement}.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param 	<T> Value type.
 */
public abstract class AbstractElement<T> implements IElement<T>
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Name.
	 */
	private String name;

	/**
	 * Value.
	 */
	private T value;

	/**
	 * Creates a new abstract element.
	 * <hr>
	 * @param name Name.
	 */
	public AbstractElement(@NonNull final String name)
	{
		this.name = name;
	}

	/**
	 * Creates a new abstract element.
	 * <hr>
	 * @param name Name.
	 * @param value Value.
	 */
	public AbstractElement(@NonNull final String name, final T value)
	{
		this.name = name;
		this.value = value;
	}

	@Override
	public final String getName()
	{
		return name;
	}

	@Override
	public final void setName(@NonNull final String name)
	{
		this.name = name;
	}

	@Override
	public final T getValue()
	{
		return value;
	}

	@Override
	public final void setValue(T value)
	{
		this.value = value;
	}

	@Override
	public int compareTo(IElement<T> o)
	{
		return name.compareTo(o.getName());
	}
}
