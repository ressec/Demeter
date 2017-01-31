/*
 * Copyright(c) 2016 - Heliosphere Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Heliosphere's project which is licensed under the
 * Apache license version 2 and use is subject to license terms. You should have
 * received a copy of the license with the project's artifact binaries and/or
 * sources.
 *
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package com.heliosphere.demeter.base.document;

import com.heliosphere.demeter.base.exception.AbstractCheckedException;
import com.heliosphere.demeter.base.exception.IExceptionType;
import com.heliosphere.demeter.base.resource.bundle.IBundle;

/**
 * Checked exception thrown to indicate an error occurred with a document.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse - Heliosphere</a>
 * @version 1.0.0
 */
public class DocumentException extends AbstractCheckedException
{
	/**
	 * Serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception thrown to indicate an error occurred with a document.
	 */
	public DocumentException()
	{
		super();
	}

	/**
	 * Exception thrown to indicate an error occurred with a document.
	 * <p>
	 * @param key Resource bundle key (enumerated value coming from an
	 * enumeration implementing the {@link IBundle} interface).
	 */
	public DocumentException(final Enum<? extends IBundle> key)
	{
		super(key);
	}

	/**
	 * Exception thrown to indicate an error occurred with a document.
	 * <p>
	 * @param key Exception key (enumerated value coming from an enumeration
	 * implementing the {@link IExceptionType} interface).
	 * @param parameters List of parameters used to populate the exception
	 * message.
	 */
	public DocumentException(final Enum<?> key, final Object... parameters)
	{
		super(key, parameters);
	}

	/**
	 * Exception thrown to indicate an error occurred with a document.
	 * <p>
	 * @param exception Parent exception.
	 */
	public DocumentException(final Exception exception)
	{
		super(exception);
	}

	/**
	 * Exception thrown to indicate an error occurred with a document.
	 * <p>
	 * @param message Message describing the error being the cause of the raised
	 * exception.
	 */
	public DocumentException(final String message)
	{
		super(message);
	}

	/**
	 * Exception thrown to indicate an error occurred with a document.
	 * <p>
	 * @param message Message describing the error being the cause of the raised
	 * exception.
	 * @param exception Parent exception.
	 */
	public DocumentException(final String message, final Exception exception)
	{
		super(message, exception);
	}
}