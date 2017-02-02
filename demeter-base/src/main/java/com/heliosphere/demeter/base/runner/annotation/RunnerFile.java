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
package com.heliosphere.demeter.base.runner.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.heliosphere.demeter.base.runner.IRunner;

/**
 * This annotation is used exclusively to annotate a {@link IRunner} type. It defines the xml configuration file and the xml execution file to be used by the runner.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface RunnerFile
{
	/**
	 * XML configuration file.
	 * @return XML configuration file.
	 */
	String configurationFile();

	/**
	 * XML execution file.
	 * @return XML execution file.
	 */
	String executionFile() default "UNKNOWN";
}
