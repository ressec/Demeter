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
package com.heliosphere.demeter.base.runner.entity;

import com.heliosphere.demeter.base.element.AbstractElement;

import lombok.NonNull;

public class AbstractEntity<T> extends AbstractElement<String> implements IEntity<T>
{
	/**
	 * Entity type.
	 */
	private Enum<? extends IEntityType> type;

	/**
	 * Entity content (file, object, stream, etc...).
	 */
	private T content;

	/**
	 * Creates a new abstract entity.
	 * <hr>
	 * @param name Entity name.
	 * @param type Entity type.
	 * @param content Entity content.
	 */
	public AbstractEntity(String name, Enum<? extends IEntityType> type, T content)
	{
		super(name);

		this.type = type;
		this.content = content;
	}

	@Override
	public final Enum<? extends IEntityType> getType()
	{
		return type;
	}

	@Override
	public final void setType(@NonNull final Enum<? extends IEntityType> type)
	{
		this.type = type;
	}

	@Override
	public final T getContent()
	{
		return content;
	}

	@Override
	public final void setContent(@NonNull final T content)
	{
		this.content = content;
	}
}
