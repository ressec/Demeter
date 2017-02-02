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

import java.util.concurrent.Callable;

import com.heliosphere.demeter.base.document.DocumentException;
import com.heliosphere.demeter.base.element.IElement;
import com.heliosphere.demeter.base.runner.IRunner;
import com.heliosphere.demeter.base.runner.context.IContext;
import com.heliosphere.demeter.base.runner.entity.IEntity;
import com.heliosphere.demeter.base.runner.result.IExecutionResult;

/**
 * This interface defines the behavior of an processor used to process an {@link IEntity} through {@link IContext} initialized by a {@link IRunner}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public interface IProcessor extends IElement<String>, Callable<IExecutionResult>, Cloneable
{
	/**
	 * Adds a listener that want to be notified of events that occur for this processor.
	 * <hr>
	 * @param listener Listener to add.
	 */
	void addProcessorListener(final IProcessorListener listener);

	/**
	 * Removes a listener from the list of registered listeners.
	 * <hr>
	 * @param listener Listener to remove.
	 */
	void removeProcessorListener(final IProcessorListener listener);

	/**
	 * Clears (remove) all the registered listeners.
	 */
	void clearListeners();

	/**
	 * Initializes the processor.
	 * <hr>
	 * @throws ProcessorException Thrown in case an error occurred while trying to initialize the processor.
	 */
	void initialize() throws ProcessorException;

	/**
	 * Runs the processor.
	 * <hr>
	 * In a derived class, a call to super {@code MUST} be done at the end of the overridden method implementation.
	 * @throws ProcessorException Thrown in case an error occurred with the processor while processing the document.
	 * @throws DocumentException Thrown in case an error occurred with the document to be processed.
	 */
	void process() throws ProcessorException, DocumentException;

	/**
	 * Returns the context used by the processor.
	 * <hr>
	 * @return Associated context.
	 */
	IContext getContext();

	/**
	 * Sets the context to be used by the processor.
	 * <hr>
	 * @param context Context to set.
	 */
	void setContext(IContext context);

	/**
	 * Returns the execution result.
	 * <hr>
	 * @return Execution result.
	 */
	IExecutionResult getExecutionResult();
}
