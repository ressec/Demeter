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

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Stopwatch;
import com.heliosphere.demeter.base.element.Element;
import com.heliosphere.demeter.base.element.IElement;
import com.heliosphere.demeter.base.runner.context.IContext;
import com.heliosphere.demeter.base.runner.result.ExecutionResult;
import com.heliosphere.demeter.base.runner.result.IExecutionResult;

import lombok.NonNull;

public class AbstractProcessor extends Element<String> implements IProcessor
{
	/**
	 * Processor execution result object.
	 */
	private IExecutionResult result;

	/**
	 * Processor context.
	 */
	private IContext context;

	/**
	 * Processor watch to measure elapsed time of processing.
	 */
	private Stopwatch watch = null;

	/**
	 * Collection of processor listeners.
	 */
	private List<IProcessorListener> listeners = new ArrayList<>();

	/**
	 * Creates a new abstract processor given its name.
	 * <hr>
	 * @param name Processor name.
	 */
	public AbstractProcessor(@NonNull final String name)
	{
		super(name);
	}

	/**
	 * Creates a new abstract processor given a context.
	 * <hr>
	 * @param context Context to process.
	 */
	public AbstractProcessor(@NonNull final IContext context)
	{
		super(context.getEntity().getName());

		this.context = context;
		setName(context.getEntity().getName());
		result = new ExecutionResult(getName());
		result.setParameters(context.getParameters());
		watch = Stopwatch.createStarted();
	}

	@Override
	public IExecutionResult call() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final void addProcessorListener(@NonNull final IProcessorListener listener)
	{
		if (listeners != null)
		{
			listeners = new ArrayList<>();
		}

		if (!listeners.contains(listener))
		{
			listeners.add(listener);
		}
	}

	@Override
	public final void removeProcessorListener(@NonNull final IProcessorListener listener)
	{
		if (listeners != null)
		{
			listeners.remove(listener);
		}
	}

	@Override
	public final void clearListeners()
	{
		listeners.clear();
		listeners = null;
	}

	@Override
	public void initialize() throws ProcessorException
	{
		// To be overridden by sub classes!
	}

	@Override
	public void process() throws ProcessorException
	{
		// To be overridden by sub classes!
	}

	@Override
	public final IContext getContext()
	{
		return context;
	}

	@Override
	public void setContext(@NonNull final IContext context)
	{
		this.context = context;
	}

	@Override
	public final IExecutionResult getExecutionResult()
	{
		return result;
	}

	@Override
	public int compareTo(IElement<String> other)
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
