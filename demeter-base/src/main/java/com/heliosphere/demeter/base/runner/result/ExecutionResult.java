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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.heliosphere.demeter.base.element.AbstractElement;
import com.heliosphere.demeter.base.element.IElement;
import com.heliosphere.demeter.base.runner.parameter.base.IParameterType;
import com.heliosphere.demeter.base.runner.parameter.base.ParameterStatusType;
import com.heliosphere.demeter.base.runner.parameter.execution.IParameterExecution;
import com.heliosphere.demeter.base.runner.parameter.list.IParameterList;
import com.heliosphere.demeter.base.runner.parameter.list.ParameterList;
import com.rits.cloning.Cloner;

import lombok.NonNull;

/**
 * Provides an abstract implementation of an {@link IExecutionResult}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class ExecutionResult extends AbstractElement<String> implements IExecutionResult
{
	/**
	 * List of exceptions that have been raised during the processor execution.
	 */
	private List<Exception> exceptions;

	/**
	 * Status of the execution result object.
	 */
	private ExecutionStatusType status;

	/**
	 * List of execution parameters.
	 */
	private IParameterList<IParameterExecution> parameters;

	/**
	 * Thread names used to run the processor' parameters.
	 */
	private Map<Enum<? extends IParameterType>, String> threads;

	/**
	 * Creates a new execution result given a name.
	 * <hr>
	 * @param name Name of the execution result.
	 */
	public ExecutionResult(String name)
	{
		super(name);
	}

	@Override
	public final ExecutionStatusType getStatus()
	{
		return status;
	}

	@Override
	public final void setStatus(ExecutionStatusType status)
	{
		this.status = status;
	}

	@Override
	public final List<Exception> getExceptions()
	{
		return exceptions;
	}

	@Override
	public final void setExceptions(@NonNull final List<Exception> exceptions)
	{
		this.exceptions = exceptions;
	}

	@Override
	public final String getThreadName(@NonNull final Enum<? extends IParameterType> type)
	{
		if (threads != null)
		{
			return threads.get(type);
		}

		return null;
	}

	@Override
	public final void setThreadName(@NonNull final Enum<? extends IParameterType> type, @NonNull final String name)
	{
		if (threads == null)
		{
			threads = new HashMap<>();
		}

		threads.put(type, name);
	}

	@Override
	public String getElapsed()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setElapsed(String time)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public final int compareTo(IElement<String> o)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public final void setParameters(@NonNull final IParameterList<IParameterExecution> parameters)
	{
		this.parameters = parameters;
	}

	@Override
	public final IParameterList<IParameterExecution> getParameters()
	{
		return parameters;
	}

	@Override
	public final IParameterList<IParameterExecution> getProcessedParameters()
	{
		Cloner cloner = new Cloner();
		IParameterList<IParameterExecution> result = new ParameterList<>();

		for (IParameterExecution parameter : parameters.getParameters())
		{
			if (parameter.getStatus() == ParameterStatusType.PROCESSED)
			{
				IParameterExecution clone = cloner.deepClone(parameter);
				result.add(clone);
			}
		}

		return result;
	}

	@Override
	public final IParameterList<IParameterExecution> getUnprocessedParameters()
	{
		Cloner cloner = new Cloner();
		IParameterList<IParameterExecution> result = new ParameterList<>();

		for (IParameterExecution parameter : parameters.getParameters())
		{
			if (parameter.getStatus() == ParameterStatusType.UNPROCESSED)
			{
				IParameterExecution clone = cloner.deepClone(parameter);
				result.add(clone);
			}
		}

		return result;
	}
}
