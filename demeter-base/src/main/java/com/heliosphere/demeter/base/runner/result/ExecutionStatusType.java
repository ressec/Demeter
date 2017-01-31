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
package com.heliosphere.demeter.base.runner.result;

import com.heliosphere.demeter.base.runner.processor.IProcessor;

/**
 * This enumeration is used to give the status of an execution done by a {@link IProcessor}.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public enum ExecutionStatusType
{
	/**
	 * The execution status type is unknown.
	 */
	UNKNOWN,

	/**
	 * The execution status is running.
	 */
	RUNNING,

	/**
	 * The execution status is finished with error(s).
	 */
	FAILED,

	/**
	 * The execution status is finished without error.
	 */
	SUCESSS
}
