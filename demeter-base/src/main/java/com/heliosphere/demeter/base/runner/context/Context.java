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
package com.heliosphere.demeter.base.runner.context;

import com.heliosphere.demeter.base.runner.entity.IEntity;
import com.heliosphere.demeter.base.runner.parameter.execution.IParameterExecution;
import com.heliosphere.demeter.base.runner.parameter.list.IParameterList;

import lombok.NonNull;

public class Context extends AbstractContext
{
	/**
	 * Creates a new context.
	 * <hr>
	 * @param entity Entity.
	 * @param parameters List of execution parameters.
	 */
	public Context(@NonNull final IEntity<?> entity, @NonNull final IParameterList<IParameterExecution> parameters)
	{
		super(entity, parameters);
	}

}
