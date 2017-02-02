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
package com.heliosphere.demeter.base.runner;

import java.util.List;

import com.heliosphere.demeter.base.file.xml.base.IXmlFile;
import com.heliosphere.demeter.base.runner.parameter.base.IParameterType;
import com.heliosphere.demeter.base.runner.parameter.base.ParameterException;
import com.heliosphere.demeter.base.runner.parameter.configuration.IParameterConfiguration;
import com.heliosphere.demeter.base.runner.parameter.execution.IParameterExecution;

/**
 * Defines the behavior of a {@code Runner} which is used as an advanced program launcher.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public interface IRunner
{
	/**
	 * Returns the XML configuration file used by this runner.
	 * <hr>
	 * @return XML configuration file.
	 */
	IXmlFile getConfiguration();

	/**
	 * Returns the XML execution file used by this runner execution.
	 * <hr>
	 * @return XML execution file.
	 */
	IXmlFile getExecution();

	/**
	 * Initializes the runner.
	 * <hr>
	 * @throws RunnerException Thrown in case an error occurred while trying to initialize the runner.
	 */
	void initialize() throws RunnerException;

	/**
	 * Starts the runner.
	 * <hr>
	 * @throws RunnerException Thrown in case an error occurred while trying to start the runner. 
	 */
	void start() throws RunnerException;

	/**
	 * Pauses the runner.
	 * <hr>
	 * @throws RunnerException Thrown in case an error occurred while trying to pause the runner. 
	 */
	void pause() throws RunnerException;

	/**
	 * Resumes the runner.
	 * <hr>
	 * @throws RunnerException Thrown in case an error occurred while trying to resume the runner. 
	 */
	void resume() throws RunnerException;

	/**
	 * Stops the runner.
	 * <hr>
	 * @throws RunnerException Thrown in case an error occurred while trying to stop the runner. 
	 */
	void stop() throws RunnerException;

	/**
	 * Resets the runner.
	 * <hr>
	 * @throws RunnerException Thrown in case an error occurred while trying to reset the runner. 
	 */
	void reset() throws RunnerException;

	/**
	 * Retrieves definition parameters based on a given parameter type.
	 * <hr>
	 * @param type Parameter type.
	 * @return List of definition parameters matching the parameter type.
	 */
	List<IParameterConfiguration> getDefinitionParameter(final Enum<? extends IParameterType> type);

	/**
	 * Retrieves a definition parameter based on a given parameter name or alias.
	 * @param nameOrAlias Parameter name or alias to search.
	 * @return Definition parameter or {@code null} if no parameter has been found with the given value.
	 */
	IParameterConfiguration getDefinitionParameter(final String nameOrAlias);

	/**
	 * Retrieves execution parameters based on a given parameter type.
	 * <hr>
	 * @param type Parameter type.
	 * @return List of execution parameters matching the given parameter type.
	 */
	IParameterExecution getExecutionParameter(final Enum<? extends IParameterType> type);

	/**
	 * Retrieves an execution parameter based on a given parameter name.
	 * @param name Parameter name to search.
	 * @return Execution parameter or {@code null} if no parameter has been found with the given value.
	 */
	IParameterExecution getExecutionParameter(final String name);

	/**
	 * Adds an execution parameter.
	 * <hr>
	 * @param execution Execution parameter to add.
	 * @throws ParameterException Thrown in case an error occurred while trying to add an execution parameter. 
	 */
	void addExecutionParameter(final IParameterExecution execution) throws ParameterException;
}
