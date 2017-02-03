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

import com.heliosphere.demeter.base.runner.file.xml.configuration.XmlConfigurationFile;
import com.heliosphere.demeter.base.runner.file.xml.execution.XmlExecutionFile;
import com.heliosphere.demeter.base.runner.parameter.base.IParameterType;
import com.heliosphere.demeter.base.runner.parameter.configuration.IParameterConfiguration;
import com.heliosphere.demeter.base.runner.parameter.execution.IParameterExecution;

/**
 * Defines the behavior of a {@code Runner} which is used as an advanced program launcher.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public interface IRunner
{
	/**
	 * Returns the XML configuration file used by this runner.
	 * <hr>
	 * @return XML configuration file.
	 */
	XmlConfigurationFile getConfiguration();

	/**
	 * Returns the XML execution file used by this runner execution.
	 * <hr>
	 * @return XML execution file.
	 */
	XmlExecutionFile getExecution();

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
	 * Retrieves the configuration parameter given a parameter type.
	 * <hr>
	 * @param type Parameter type.
	 * @return Configuration parameter or {@code null} if no configuration parameter has been found for the given type.
	 */
	IParameterConfiguration getConfigurationParameter(Enum<? extends IParameterType> type);

	/**
	 * Retrieves a configuration parameter given a parameter name or alias.
	 * <hr>
	 * @param nameOrAlias Parameter name or alias.
	 * @return Configuration parameter or {@code null} if no parameter has been found for the given name or alias.
	 */
	IParameterConfiguration getConfigurationParameter(String nameOrAlias);

	/**
	 * Retrieves a configuration parameter.
	 * <hr>
	 * @param parameter Parameter.
	 * @return Configuration parameter or {@code null} if no parameter has been found.
	 */
	IParameterConfiguration getConfigurationParameter(IParameterConfiguration parameter);

	/**
	 * Returns the execution parameter given a parameter type.
	 * <hr>
	 * @param type Parameter type.
	 * @return Execution parameter or {@code null} if no configuration parameter has been found for the given type.
	 */
	IParameterExecution getExecutionParameter(Enum<? extends IParameterType> type);

	/**
	 * Retrieves an execution parameter based on a given parameter name.
	 * <hr>
	 * @param name Parameter name.
	 * @return Execution parameter or {@code null} if no parameter has been found with the given value.
	 */
	IParameterExecution getExecutionParameter(String name);

	/**
	 * Retrieves an execution parameter.
	 * <hr>
	 * @param parameter Parameter.
	 * @return Execution parameter or {@code null} if no parameter has been found.
	 */
	IParameterExecution getExecutionParameter(IParameterExecution parameter);
}
