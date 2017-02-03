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
package com.heliosphere.demeter.base.runner.processor;

import com.heliosphere.demeter.base.runner.context.IContext;
import com.heliosphere.demeter.base.runner.parameter.TestParameterType;
import com.heliosphere.demeter.base.runner.parameter.execution.IParameterExecution;

/**
 * Provides a concrete implementation of an test {@link IProcessor}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public final class TestProcessor extends AbstractProcessor
{
	/**
	 * Creates a new test processor.
	 * <hr>
	 * @param context Context to be used by the processor.
	 */
	public TestProcessor(IContext context)
	{
		super(context);
	}

	@Override
	public final void process() throws ProcessorException
	{
		super.process();

		// Process 'unprocessed' execution parameters only!
		for (IParameterExecution parameter : getExecutionResult().getUnprocessedParameters().getElements())
		{
			switch ((TestParameterType) parameter.getType())
			{
				case PARAMETER_HELP:
					executeHelp(parameter);
					break;

				case PARAMETER_VERSION:
					executeVersion(parameter);
					break;

				case PARAMETER_HELLO:
					executeHello(parameter);
					break;

				case UNKNOWN:
					// DO nothing ; it's a special enumerated value reserved for internal usage!
					break;

				default:
					break;
			}
		}
	}

	/**
	 * Executes the process of the {@link TestParameterType#PARAMETER_HELP} parameter.
	 * <hr>
	 * @param parameter Parameter.
	 */
	@SuppressWarnings("nls")
	private final void executeHelp(IParameterExecution parameter)
	{
		try
		{
			// Display the help message on the console.
			System.out.println("TestRunner");
			System.out.println("  List of available commands:");
			System.out.println("   * help    (h)  - Display the TestRunner usage.");
			System.out.println("   * version (v)  - Display the version information of the program.");
			System.out.println("   * hello   (he) - Display the famous 'hello world' message.");
		}
		catch (Exception e)
		{
			getExecutionResult().getExceptions().add(e);
		}
		finally
		{
			getExecutionResult().setParameterProcessed(parameter);
		}
	}

	/**
	 * Executes the process of the {@link TestParameterType#PARAMETER_VERSION} parameter.
	 * <hr>
	 * @param parameter Parameter.
	 */
	private final void executeVersion(IParameterExecution parameter)
	{
		try
		{
			// Do some work here!
		}
		catch (Exception e)
		{
			getExecutionResult().getExceptions().add(e);
		}
		finally
		{
			getExecutionResult().setParameterProcessed(parameter);
		}
	}

	/**
	 * Executes the process of the {@link TestParameterType#PARAMETER_HELLO} parameter.
	 * <hr>
	 * @param parameter Parameter.
	 */
	private final void executeHello(IParameterExecution parameter)
	{
		try
		{
			// Do some work here!
		}
		catch (Exception e)
		{
			getExecutionResult().getExceptions().add(e);
		}
		finally
		{
			getExecutionResult().setParameterProcessed(parameter);
		}
	}
}
