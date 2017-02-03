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

import com.heliosphere.demeter.base.exception.EnumerationException;

import lombok.NonNull;

@SuppressWarnings("nls")
public enum EntityType implements IEntityType
{
	/**
	 * Reserved for internal use!
	 */
	RESERVED("reserved"),

	/**
	 * A message to be displayed on the console.
	 */
	DISPLAY("display"),

	/**
	 * A message to be sent.
	 */
	MESSAGE("message"),

	/**
	 * A daemon process.
	 */
	DAEMON("daemon"),

	/**
	 * A file to be processed.
	 */
	FILE("file"),

	/**
	 * A computation to be done.
	 */
	COMPUTATION("computation"),

	/**
	 * A batch to be run.
	 */
	BATCH("batch");

	/**
	 * Entity type name.
	 */
	private final String name;

	/**
	 * Creates a new enumerated value.
	 * <hr>
	 * @param name Entity type name.
	 */
	private EntityType(@NonNull final String name)
	{
		this.name = name;
	}

	@Override
	public Enum<? extends IEntityType> fromName(String name) throws EnumerationException
	{
		for (EntityType element : EntityType.values())
		{
			if (element.getName().equals(name))
			{
				return element;
			}
		}

		throw new EnumerationException("Unable to create for enumeration: " + this.getClass().getSimpleName() + " an enumeated value for name: " + name);
	}

	@Override
	public final String getName()
	{
		return name;
	}
}
