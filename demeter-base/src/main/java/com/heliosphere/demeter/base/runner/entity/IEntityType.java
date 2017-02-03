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

/**
 * This interface defines the behavior of enumerations extending the notion of {@code entity types}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public interface IEntityType
{
	/**
	 * Creates an enumerated value from a given name.
	 * <hr>
	 * @param name Name.
	 * @return Enumerated value.
	 * @throws EnumerationException Thrown in case an error occurred while trying to create the enumerated value.
	 */
	Enum<? extends IEntityType> fromName(final String name) throws EnumerationException;

	/**
	 * Returns the entity type name.
	 * <hr>
	 * @return Entity type name.
	 */
	String getName();
}
