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
package com.heliosphere.demeter.base.runner;

import com.heliosphere.demeter.base.runner.annotation.RunnerConfig;
import com.heliosphere.demeter.base.runner.annotation.RunnerFile;
import com.heliosphere.demeter.base.runner.file.xml.configuration.XmlConfigurationFile;
import com.heliosphere.demeter.base.runner.file.xml.execution.XmlExecutionFile;
import com.heliosphere.demeter.base.runner.parameter.TestParameterType;
import com.heliosphere.demeter.base.runner.processor.TestProcessor;

/**
 * Provides a concrete implementation of a test runner.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
@RunnerConfig(enumParameterClass = TestParameterType.class, processorClass = TestProcessor.class, threadCount = 4)
@RunnerFile(configurationFile = "config/runner/test.runner.configuration.xml", executionFile = "config/runner/test.runner.execution.xml")
public class TestRunner extends AbstractRunner
{
	/**
	 * Creates a new test runner.
	 * <hr>
	 * @throws RunnerException Thrown in case an error occurred while initializing the runner.
	 */
	public TestRunner() throws RunnerException
	{
		super();
	}

	/**
	 * Creates a new test runner using annotation for the configuration file only.
	 * <hr>
	 * @param execution XML execution file.
	 * @throws RunnerException Thrown in case an error occurred while initializing the runner.
	 */
	public TestRunner(final XmlExecutionFile execution) throws RunnerException
	{
		super(execution);
	}

	/**
	 * Creates a new test runner using annotation for the configuration and the execution files.
	 * <hr>
	 * @param configuration XML configuration file to use.
	 * @param execution XML execution file to use.
	 * @param properties Properties file name to use.
	 * @throws RunnerException Thrown in case an error occurred while initializing the runner.
	 */
	public TestRunner(final XmlConfigurationFile configuration, final XmlExecutionFile execution, final String properties) throws RunnerException
	{
		super(configuration, execution, properties);
	}
}
