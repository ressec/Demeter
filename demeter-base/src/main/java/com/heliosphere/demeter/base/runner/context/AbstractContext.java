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
import com.heliosphere.demeter.base.runner.parameter.list.ParameterList;
import com.heliosphere.demeter.base.runner.processor.IProcessor;
import com.rits.cloning.Cloner;

import lombok.NonNull;

/**
 * Provides an abstract implementation of a {@link IContext}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public abstract class AbstractContext implements IContext
{
	/**
	 * Execution parameters list.
	 */
	private IParameterList<IParameterExecution> parameters;

	/**
	 * Processor.
	 */
	private IProcessor processor;

	/**
	 * Entity to process.
	 */
	private IEntity<?> entity;

	/**
	 * Creates a new abstract context.
	 * <hr>
	 * @param entity Entity.
	 * @param parameters List of execution parameters.
	 */
	public AbstractContext(@NonNull final IEntity<?> entity, @NonNull final IParameterList<IParameterExecution> parameters)
	{
		this.entity = entity;

		Cloner cloner = new Cloner();
		IParameterList<IParameterExecution> clone = cloner.deepClone(parameters);
		this.parameters = clone;
	}

	/**
	 * Creates a new abstract context.
	 */
	public AbstractContext()
	{
		parameters = new ParameterList<>();
	}

	@Override
	public final IEntity<?> getEntity()
	{
		return entity;
	}

	@Override
	public final void setEntity(@NonNull final IEntity<?> entity)
	{
		this.entity = entity;
	}

	@Override
	public final IProcessor getProcessor()
	{
		return processor;
	}

	@Override
	public final void setProcessor(@NonNull final IProcessor processor)
	{
		this.processor = processor;
	}

	@Override
	public final IParameterList<IParameterExecution> getParameters()
	{
		return parameters;
	}

	@Override
	public final void setParameters(@NonNull final IParameterList<IParameterExecution> parameters)
	{
		this.parameters = parameters;
	}
}
