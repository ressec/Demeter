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

import com.heliosphere.demeter.base.file.xml.base.IXmlFile;
import com.heliosphere.demeter.base.runner.annotation.RunnerConfig;
import com.heliosphere.demeter.base.runner.annotation.RunnerFile;
import com.heliosphere.demeter.base.runner.file.xml.configuration.XmlConfigurationFile;
import com.heliosphere.demeter.base.runner.file.xml.execution.XmlExecutionFile;
import com.heliosphere.demeter.base.runner.parameter.BasicRunnerParameterType;

@RunnerConfig(enumParameterClass = BasicRunnerParameterType.class, processorClass = UnderwriterProcessor.class, threadCount = 4)
@RunnerFile(configurationFile = "config/runner/runner.config.swissre.underwriter.curation.xml")
public class Runner extends AbstractRunner
{

	public Runner() throws RunnerException
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a new generic runner using annotation for the configuration file only.
	 * <hr>
	 * @param execution XML execution file.
	 * @throws RunnerException Thrown in case an error occurred while initializing the runner.
	 */
	public Runner(final XmlExecutionFile execution) throws RunnerException
	{
		super(execution);
	}

	/**
	 * Creates a new generic runner using annotation for the configuration and the execution files.
	 * <hr>
	 * @param configuration XML configuration file to use.
	 * @param execution XML execution file to use.
	 * @throws RunnerException Thrown in case an error occurred while initializing the runner.
	 */
	public Runner(final XmlConfigurationFile configuration, final XmlExecutionFile execution, final String properties) throws RunnerException
	{
		super(configuration, execution, properties);
	}

	@Override
	public IXmlFile getExecution()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
