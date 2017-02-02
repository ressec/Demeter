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
package com.heliosphere.demeter.base.runner.result;

import java.util.List;

import com.heliosphere.demeter.base.element.IElement;
import com.heliosphere.demeter.base.runner.IRunner;
import com.heliosphere.demeter.base.runner.context.IContext;
import com.heliosphere.demeter.base.runner.entity.IEntity;
import com.heliosphere.demeter.base.runner.parameter.base.IParameterType;
import com.heliosphere.demeter.base.runner.parameter.execution.IParameterExecution;
import com.heliosphere.demeter.base.runner.parameter.list.IParameterList;
import com.heliosphere.demeter.base.runner.processor.IProcessor;

/**
 * Defines the behavior of an execution result which is the result of the execution of an {@link IEntity} by a {@link IProcessor} launched by a
 * {@link IRunner} using a {@link IContext}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public interface IExecutionResult extends IElement<String>
{
	/**
	 * Returns the status of the execution result.
	 * <hr>
	 * @return Execution result status.
	 */
	ExecutionStatusType getStatus();

	/**
	 * Sets the execution result status.
	 * <hr>
	 * @param status Execution result status to set.
	 */
	void setStatus(final ExecutionStatusType status);

	/**
	 * Returns the list of exceptions raised during the execution.
	 * <hr>
	 * @return List of exceptions.
	 */
	List<Exception> getExceptions();

	/**
	 * Sets the list of exceptions raised during the execution.
	 * <hr>
	 * @param exceptions List of exceptions.
	 */
	void setExceptions(List<Exception> exceptions);

	/**
	 * Returns the elapsed time.
	 * @return Elapsed time.
	 */
	String getElapsed();

	/**
	 * Sets the elapsed time the processor took to process the entity.
	 * <hr>
	 * @param time Elapsed time.
	 */
	void setElapsed(final String time);

	/**
	 * Sets the list of parameters used for the execution.
	 * <hr>
	 * @param parameters List of parameters to set.
	 */
	void setParameters(IParameterList<IParameterExecution> parameters);

	/**
	 * Returns the list of parameters used for the execution.
	 * <hr>
	 * @return List of parameters.
	 */
	IParameterList<IParameterExecution> getParameters();

	/**
	 * Returns the list of the processed execution parameters.
	 * <hr>
	 * @return List of processed execution parameters.
	 */
	IParameterList<IParameterExecution> getProcessedParameters();

	/**
	 * Returns the list of the unprocessed execution parameters.
	 * <hr>
	 * @return List of unprocessed execution parameters.
	 */
	IParameterList<IParameterExecution> getUnprocessedParameters();

	/**
	 * Returns the thread name used to process the given parameter type.
	 * <hr>
	 * @param type Parameter type.
	 * @return Thread name.
	 */
	String getThreadName(Enum<? extends IParameterType> type);

	/**
	 * Sets the thread name for the given parameter type.
	 * <hr>
	 * @param type Parameter type.
	 * @param name Thread name.
	 */
	void setThreadName(Enum<? extends IParameterType> type, String name);

	/**
	 * Sets the status of the given parameter as processed.
	 * <hr>
	 * @param parameter Parameter to update.
	 */
	void setParameterProcessed(IParameterExecution parameter);
}
