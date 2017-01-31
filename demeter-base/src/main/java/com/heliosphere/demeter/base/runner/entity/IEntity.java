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
package com.heliosphere.demeter.base.runner.entity;

import com.heliosphere.demeter.base.element.IElement;
import com.heliosphere.demeter.base.resource.IResource;
import com.heliosphere.demeter.base.runner.processor.IProcessor;

/**
 * This interface defines the behavior of an entity intended to be processed by a {@link IProcessor}
 * through a {@link IRunner}. 
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param   <T> Entity type. 
 */
public interface IEntity<T> extends IElement<String>
{
	/**
	 * Returns the case content.
	 * <hr>
	 * @return Case content.
	 */
	T getContent();

	/**
	 * Sets the case content.
	 * <hr>
	 * @param content Case content.
	 */
	void setContent(final T content);

	/**
	 * Returns the output path.
	 * <hr>
	 * @return Output path.
	 */
	IResource getOutputPath();

	/**
	 * Sets the output path.
	 * <hr>
	 * @param path Output path to set.
	 */
	void setOutputPath(IResource path);
}
