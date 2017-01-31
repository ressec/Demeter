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
import com.heliosphere.demeter.base.runner.context.IContext;
import com.heliosphere.demeter.base.runner.entity.IEntity;
import com.heliosphere.demeter.base.runner.parameter.IParameterExecution;
import com.heliosphere.demeter.base.runner.parameter.IParameterType;
import com.heliosphere.demeter.base.runner.processor.IProcessor;

/**
 * This interface defines the behavior of an execution result which is the result of the execution of an {@link IEntity} by
 * a {@link IProcessor} launched by a {@link IRunner} using a {@link IContext}.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
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
	 * Returns the thread name used for the execution.
	 * <hr>
	 * @return Thread name.
	 */
	String getThreadName();

	/**
	 * Sets the thread name to use for the execution.
	 * <hr>
	 * @param name Thread name.
	 */
	void setThreadName(final String name);

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
	void setParameters(List<IParameterExecution> parameters);

	/**
	 * Returns the list of parameters used for the execution.
	 * <hr>
	 * @return List of parameters.
	 */
	List<IParameterExecution> getParameters();

	/**
	 * Returns an execution parameter given its type.
	 * <hr>
	 * @param type Execution parameter type.
	 * @return Execution parameter if one is found, {@code null} otherwise.
	 */
	IParameterExecution getParameter(Enum<? extends IParameterType> type);

	/**
	 * Returns an execution parameter given its name.
	 * <hr>
	 * @param name Execution parameter name.
	 * @return Execution parameter if one is found, {@code null} otherwise.
	 */
	IParameterExecution getParameter(String name);

	/**
	 * Removes a parameter from the list of the execution parameters used for the execution.
	 * <hr>
	 * @param parameter Execution parameter to remove.
	 */
	void removeParameter(IParameterExecution parameter);

	/**
	 * Adds an execution parameter to the context.
	 * <hr>
	 * @param parameter Execution parameter to add.
	 */
	void addParameter(final IParameterExecution parameter);

	/**
	 * Returns the list of the processed execution parameters.
	 * <hr>
	 * @return List of processed execution parameters.
	 */
	List<IParameterExecution> getProcessedParameters();

	/**
	 * Returns the list of the unprocessed execution parameters.
	 * <hr>
	 * @return List of unprocessed execution parameters.
	 */
	List<IParameterExecution> getUnprocessedParameters();
}
