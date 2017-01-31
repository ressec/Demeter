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
package com.heliosphere.demeter.base.runner.context;

import java.util.List;
import java.util.concurrent.Callable;

import com.heliosphere.demeter.base.runner.entity.IEntity;
import com.heliosphere.demeter.base.runner.parameter.IParameterExecution;
import com.heliosphere.demeter.base.runner.parameter.IParameterType;
import com.heliosphere.demeter.base.runner.processor.IProcessor;
import com.heliosphere.demeter.base.runner.result.IExecutionResult;

/**
 * This interface defines the behavior of a {@code context} which is used by a {@link IRunner} through a {@link IProcessor}
 * to process a {@link IEntity}.
 * <p>
 * The result can be retrieved getting the {@link IExecutionResult} through the {@link IProcessor}.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param 	<T> Entity type.
 */
public interface IContext<T>
{
	/**
	 * Returns the underlying case attached to this context.
	 * <hr>
	 * @return {@link IEntity}.
	 */
	IEntity<T> getEntitye();

	/**
	 * Sets the entity to be processed through this context.
	 * <hr>
	 * @param entity {@link IEntity} to be processed.
	 */
	void setEntity(final IEntity<T> entity);

	/**
	 * Returns the processor to be used for this context to process the entity.
	 * <hr>
	 * @return Processor.
	 */
	Callable<IProcessor> getProcessor();

	/**
	 * Sets the processor to be used by this context to process the entity.
	 * <hr>
	 * @param processor Processor to set.
	 */
	void setProcessor(final Callable<IProcessor> processor);

	/**
	 * Adds an execution parameter to the context.
	 * <hr>
	 * @param parameter Execution parameter to add.
	 */
	void addParameter(final IParameterExecution parameter);

	/**
	 * Retrieves an execution parameter from the context given its name.
	 * <hr>
	 * @param name Parameter name.
	 * @return Parameter if found, {@code null} otherwise.
	 */
	IParameterExecution getParameter(final String name);

	/**
	 * Retrieves an execution parameter of the execution context given its type.
	 * <hr>
	 * @param type Parameter type.
	 * @return Parameter if found, {@code null} otherwise.
	 */
	IParameterExecution getParameter(final Enum<? extends IParameterType> type);

	/**
	 * Removes an execution parameter from the context.
	 * <hr>
	 * @param parameter Parameter to remove.
	 */
	void removeParameter(final IParameterExecution parameter);

	/**
	 * Removes all execution parameters from the context.
	 */
	void removeParameters();

	/**
	 * Returns the list of the execution parameters for this context.
	 * <hr>
	 * @return List of execution parameters.
	 */
	List<IParameterExecution> getParameters();
}
