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

/**
 * This interface defines the behavior of a listener that want to be notified of events that occur
 * in a {@link IProcessor}. 
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public interface IProcessorListener
{
	/**
	 * Triggered when the processor has started its execution cycle.
	 */
	void onStart();

	/**
	 * Triggered when the processor has finished its execution cycle.
	 */
	void onFinish();

	/**
	 * Triggered when the processor execution has raised a new error.
	 */
	void onException(Exception e);
}
