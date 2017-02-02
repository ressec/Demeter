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

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.google.common.base.Stopwatch;
import com.heliosphere.demeter.base.file.FileException;
import com.heliosphere.demeter.base.file.xml.base.IXmlFile;
import com.heliosphere.demeter.base.runner.annotation.RunnerConfig;
import com.heliosphere.demeter.base.runner.annotation.RunnerFile;
import com.heliosphere.demeter.base.runner.context.IContext;
import com.heliosphere.demeter.base.runner.file.xml.configuration.XmlConfigurationFile;
import com.heliosphere.demeter.base.runner.file.xml.execution.XmlExecutionFile;
import com.heliosphere.demeter.base.runner.parameter.base.IParameter;
import com.heliosphere.demeter.base.runner.parameter.base.IParameterType;
import com.heliosphere.demeter.base.runner.parameter.base.ParameterException;
import com.heliosphere.demeter.base.runner.parameter.configuration.IParameterConfiguration;
import com.heliosphere.demeter.base.runner.parameter.execution.IParameterExecution;
import com.heliosphere.demeter.base.runner.processor.IProcessor;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;

/**
 * Provides an abstract implementation of a {@link IRunner}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
@Log4j
public abstract class AbstractRunner implements IRunner
{
	/**
	 * XML configuration file.
	 */
	private XmlConfigurationFile configuration = null;

	/**
	 * XML execution file.
	 */
	private XmlExecutionFile execution = null;

	/**
	 * Collection of contexts for this runner.
	 */
	private List<IContext> contexts = new ArrayList<>();

	/**
	 * Processor class to use.
	 */
	private Class<? extends IProcessor> processorClass;

	/**
	 * Maximum number of threads to use.
	 */
	private int threadCount;

	/**
	 * Executor service used for multi-threading the processors execution.
	 */
	@SuppressWarnings("unused")
	private ExecutorService executor = null;

	/**
	 * Collection of callables for the multi-threading.
	 */
	private List<Callable<IProcessor>> callables = new ArrayList<>();

	/**
	 * Collection of futures (for gathering processing results from callables) for multi-threading.
	 */
	private List<Future<IProcessor>> futures = null;

	/**
	 * Collection of delayed futures (for gathering processing results from callables) for multi-threading.
	 */
	private List<Future<IProcessor>> delayedFutures = null;

	/**
	 * Watch to measure elapsed time.
	 */
	private Stopwatch watch;

	/**
	 * Parameter type enumeration.
	 */
	@SuppressWarnings("rawtypes")
	private Class enumParameterClass = null;

	/**
	 * Properties file name.
	 */
	public String properties = null;

	/**
	 * Creates a new abstract runner given some values.
	 * <hr>
	 * @param configuration XML configuration file to be used by the runner.
	 * @param execution XML execution file to be used by the runner.
	 * @param properties Properties file name.
	 * @throws RunnerException Thrown in case an error occurred while trying to initialize the runner.
	 */
	@SuppressWarnings("rawtypes")
	public AbstractRunner(final IXmlFile configuration, final IXmlFile execution, final String properties) throws RunnerException
	{
		watch = Stopwatch.createStarted();

		this.properties = properties;

		initializeRunnerConfigAnnotation();

		this.configuration = (XmlConfigurationFile) configuration;
		this.execution = (XmlExecutionFile) execution;

		initialize();
	}

	/**
	 * Creates a new abstract runner.
	 * <hr>
	 * @throws RunnerException Thrown in case an error occurred while trying to initialize the runner.
	 */
	public AbstractRunner() throws RunnerException
	{
		// This constructor is intended to be used when configuration elements are provided using annotations.
		watch = Stopwatch.createStarted();
		initializeAnnotations();
		initialize();
	}

	/**
	 * Creates a new abstract runner given its XML execution file.
	 * <hr>
	 * @param execution XML execution file to be used by the runner.
	 * @throws RunnerException Thrown in case an error occurred while trying to initialize the runner.
	 */
	@SuppressWarnings({ "nls", "rawtypes" })
	public AbstractRunner(IXmlFile execution) throws RunnerException
	{
		watch = Stopwatch.createStarted();

		if (execution == null)
		{
			throw new NullPointerException("execution");
		}

		initializeAnnotations();

		this.execution = (XmlExecutionFile) execution;
		initialize();
	}

	/**
	 * Initializes the {@link IRunner} annotations.
	 * <hr>
	 * @throws RunnerException Thrown in case an error occurred while trying to initialize the runner.
	 */
	private final void initializeAnnotations() throws RunnerException
	{
		initializeRunnerFileAnnotation();
		initializeRunnerConfigAnnotation();
	}

	/**
	 * Initializes the runner configuration annotation.
	 * <hr>
	 * @throws RunnerException Thrown in case an error occurred while trying to initialize the runner.
	 * @see RunnerConfig
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "nls" })
	private final void initializeRunnerConfigAnnotation() throws RunnerException
	{
		for (Annotation annotation : this.getClass().getAnnotations())
		{
			Class<? extends Annotation> type = annotation.annotationType();
			if (type == RunnerConfig.class)
			{
				for (Method method : type.getDeclaredMethods())
				{
					Object value;
					try
					{
						value = method.invoke(annotation, (Object[]) null);
						if (method.getName().equals("processorClass"))
						{
							this.processorClass = (Class<? extends IProcessor>) value;
						}
						else
						{
							if (method.getName().equals("enumParameterClass"))
							{
								this.enumParameterClass = (Class) value;
							}
							else
							{
								if (method.getName().equals("threadCount"))
								{
									this.threadCount = ((Integer) value).intValue();
								}
							}
						}
					}
					catch (Exception e)
					{
						throw new RunnerException(String.format("Unable to initialize runner: %1s due to: %2s", this.getClass().getName(), e.getMessage()));
					}
				}
			}
		}
	}

	/**
	 * Initializes the runner file annotation.
	 * <hr>
	 * @throws RunnerException Thrown in case an error occurred while trying to initialize the runner.
	 * @see RunnerFile
	 */
	@SuppressWarnings("nls")
	private final void initializeRunnerFileAnnotation() throws RunnerException
	{
		for (Annotation annotation : this.getClass().getAnnotations())
		{
			Class<? extends Annotation> type = annotation.annotationType();
			if (type == RunnerFile.class)
			{
				for (Method method : type.getDeclaredMethods())
				{
					Object value = null;

					try
					{
						value = method.invoke(annotation, (Object[]) null);
						if (method.getName().equals("configurationFile"))
						{
							this.configuration = new XmlConfigurationFile((String) value);
						}
						else
						{
							if (method.getName().equals("executionFile"))
							{
								if (!((String) value).equals("UNKNOWN"))
								{
									this.execution = new XmlExecutionFile((String) value);
								}
							}
						}
					}
					catch (Exception e)
					{
						throw new RunnerException(String.format("Unable to initialize runner: %1s due to: %2s", this.getClass().getName(), e.getMessage()), e);
					}
				}
			}
		}
	}

	@SuppressWarnings("nls")
	@Override
	public final void initialize() throws RunnerException
	{
		log.info("*********************************************************************************************************");
		log.info("EXECUTION PREPARATION SUMMARY:");
		log.info(" ");

		try
		{
			loadConfiguration();
			loadExecution();
			validate();
		}
		catch (FileException | ParameterException e)
		{
			throw new RunnerException(e);
		}
	}

	@Override
	public final IXmlFile getConfiguration()
	{
		return configuration;
	}

	@Override
	public final IXmlFile getExecution()
	{
		return execution;
	}

	@Override
	public void start() throws RunnerException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() throws RunnerException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() throws RunnerException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() throws RunnerException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() throws RunnerException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public List<IParameterConfiguration> getDefinitionParameter(Enum<? extends IParameterType> type)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IParameterConfiguration getDefinitionParameter(String nameOrAlias)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IParameterExecution getExecutionParameter(Enum<? extends IParameterType> type)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IParameterExecution getExecutionParameter(String name)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addExecutionParameter(IParameterExecution execution) throws ParameterException
	{
		// TODO Auto-generated method stub

	}

	/**
	 * Initializes the XML configuration file.
	 * <hr>
	 * @throws FileException Thrown in case the XML configuration file has not been found.
	 */
	@SuppressWarnings("nls")
	private final void loadConfiguration() throws FileException
	{
		//setAliasesForConfiguration(this.configuration.getEngine(), this.configuration);
		configuration.load();
		log.info(String.format("Configuration file: [%1s] initialized.", configuration.getResource().getFile().getName()));
	}

	/**
	 * Initializes the XML execution file.
	 * <hr>
	 * @throws FileException Thrown in case the xml configuration file has not been found.
	 */
	@SuppressWarnings("nls")
	private final void loadExecution() throws FileException
	{
		//setAliasesForExecution(this.execution.getEngine(), this.execution);
		execution.load();
		log.info(String.format("Execution file...: [%1s] initialized.", execution.getResource().getFile().getName()));
	}

	/**
	 * Validates the execution file against the configuration one.
	 * <hr>
	 * @throws ParameterException Thrown in case an error occurred while validating the parameters.
	 */
	@SuppressWarnings("nls")
	protected final void validate() throws ParameterException
	{
		for (IParameterExecution p : execution.getContent())
		{
			validateParameter(p);
		}

		validateParameters();
		log.info(String.format("Execution file has been validated against configuration file."));
		log.info(" ");
	}

	/**
	 * Service for derived classes of {@link AbstractRunner} to do a kind of pre-process of the parameters before global validation.
	 * <hr>
	 * @throws ParameterException Thrown in case an error occurred while validating the parameters.
	 */
	@SuppressWarnings("nls")
	protected void validateParameters() throws ParameterException
	{
		IParameterConfiguration pConfig;
		IParameterConfiguration pConfigOther;
		IParameterExecution pExecOther;

		// Check incompatible parameters.
		for (IParameterExecution parameter : execution.getContent())
		{
			pConfig = configuration.getParameter(parameter.getType());
			if (pConfig.getIncompatibleParameters() != null)
			{
				for (String name : pConfig.getIncompatibleParameters())
				{
					pConfigOther = getDefinitionParameter(name);
					pExecOther = getExecutionParameter(pConfigOther.getType());

					if (pExecOther != null)
					{
						throw new ParameterException(String.format("Parameters %1s and %2s are incompatible!", pConfig.getName(), pConfigOther.getName()));
					}
				}
			}
		}

		// Check required parameters.
		for (IParameterExecution parameter : execution.getContent())
		{
			pConfig = configuration.getParameter(parameter.getType());
			if (pConfig.getRequiredParameters() != null)
			{
				for (String name : pConfig.getRequiredParameters())
				{
					pConfigOther = getDefinitionParameter(name);
					pExecOther = getExecutionParameter(pConfigOther.getType());

					if (pExecOther == null)
					{
						throw new ParameterException(String.format("Parameters %1s and %2s are required!", pConfig.getName(), pConfigOther.getName()));
					}
				}
			}
		}
	}

	/**
	 * Validates an execution parameter against its definition and its value.
	 * <hr>
	 * @param parameter Execution parameter to check.
	 * @throws ParameterException Thrown in case an error occurred while validating a parameter.
	 */
	@SuppressWarnings("nls")
	protected final void validateParameter(final IParameter parameter) throws ParameterException
	{
		determineParameterType(enumParameterClass);

		if (!existParameter(parameter, configuration.getParameter(parameter)))
		{
			throw new ParameterException(String.format("Invalid parameter: %1s for runner: %2s using configuration file: %3s and excution file: %4s", parameter.getName(), this.getClass().getName(), configuration.getResource().getFile().getName(), execution.getResource().getFile().getName()));
		}

		if (!isValueAllowed(parameter, configuration.getParameter(parameter)))
		{
			throw new ParameterException(String.format("Invalid parameter value: %1s for parameter name: %2s for runner: %3s using configuration file: %4s and excution file: %5s", parameter.getName(), parameter.getName(), this.getClass().getName(), configuration.getResource().getFile().getName(), execution.getResource().getFile().getName()));
		}
	}

	/**
	 * Determines the parameters' type.
	 * <hr>
	 * @param enumClass Parameter enumeration class to use to determine the parameters' type.
	 * @throws ParameterException Thrown in case an error occurred when determining a parameter type.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "nls" })
	private final void determineParameterType(final Class enumClass) throws ParameterException
	{
		Method method;
		String name = null;
		String value = null;

		this.enumParameterClass = enumClass;
		Object o = Enum.valueOf(enumClass, "UNKNOWN");

		try
		{
			method = enumClass.getDeclaredMethod("fromName", String.class);

			for (IParameterConfiguration parameter : configuration.getContent())
			{
				value = parameter.getName();
				parameter.setType((Enum<? extends IParameterType>) method.invoke(o, value));
				if (parameter.getType() == null)
				{
					throw new ParameterException("No parameter definition found for: " + value);
				}
			}

			for (IParameterExecution parameter : execution.getContent())
			{
				name = parameter.getName();
				parameter.setType((Enum<? extends IParameterType>) method.invoke(o, parameter.getName()));
			}
		}
		catch (Exception e)
		{
			throw new ParameterException(String.format("Unable to create enumerated value for enumeration: %1s, parameter: %2s", enumClass.getName(), name == null ? value : name));
		}

		checkParameterDefinitionAgainstEnumeration(o.getClass());
	}

	/**
	 * Checks the definition of the parameters against their enumeration class.
	 * <hr>
	 * @param clazz Parameter enumeration class.
	 * @throws ParameterException Thrown in case an error occurred when validating a parameter type against its enumeration class.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "nls" })
	private final void checkParameterDefinitionAgainstEnumeration(@NonNull final Class clazz) throws ParameterException
	{
		Class<? extends Enum<? extends IParameterType>> enumeration = clazz;
		List<Enum<? extends IParameterType>> list = (List<Enum<? extends IParameterType>>) Arrays.asList(enumeration.getEnumConstants());
		for (Enum<? extends IParameterType> e : list)
		{
			boolean found = false;
			for (IParameterConfiguration p : configuration.getContent())
			{
				if (p.getType() == e)
				{
					found = true;
					break;
				}
			}
			if (!found && !e.toString().equals("UNKNOWN"))
			{
				throw new ParameterException(String.format("No parameter definition found for type: %1s, value: %2s in configuration file: %2s", e.toString(), ((IParameterType) e).getName(), configuration.getResource().getFile().getName()));
			}
		}
	}

	/**
	 * Checks if the given execution parameter exist as a configuration parameter?
	 * <hr>
	 * @param execution Execution parameter.
	 * @param configuration Configuration parameter.
	 * @return {@code True} if able to find a configuration parameter matching the given execution parameter, {@code false} otherwise.
	 */
	@SuppressWarnings("static-method")
	protected boolean existParameter(@NonNull final IParameter execution, final IParameter configuration)
	{
		return configuration == null ? false : true;
	}

	/**
	 * Checks if an execution parameter value is allowed?
	 * <hr>
	 * @param execution Execution parameter.
	 * @param configuration Configuration parameter.
	 * @return {@code True} if the given execution parameter value is allowed, {@code false} otherwise.
	 */
	@SuppressWarnings("static-method")
	protected final boolean isValueAllowed(@NonNull final IParameter execution, @NonNull final IParameter configuration)
	{
		IParameterExecution e = (IParameterExecution) execution;
		IParameterConfiguration c = (IParameterConfiguration) configuration;

		if (e.getValue() == null)
		{
			if (c.getAllowedValues().isEmpty())
			{
				return true;
			}

			return false;
		}

		/*
		 * If no allowed value specified, then consider the execution parameter value as correct.
		 */
		if (c.getAllowedValues() == null || c.getAllowedValues().isEmpty())
		{
			return true;
		}

		for (String element : c.getAllowedValues())
		{
			if (element.equalsIgnoreCase(e.getValue()))
			{
				return true;
			}
		}

		return false;
	}
}
