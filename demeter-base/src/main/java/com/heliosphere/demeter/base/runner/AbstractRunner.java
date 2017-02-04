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
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Stopwatch;
import com.heliosphere.demeter.base.file.FileException;
import com.heliosphere.demeter.base.file.xml.base.IXmlFile;
import com.heliosphere.demeter.base.runner.annotation.RunnerConfig;
import com.heliosphere.demeter.base.runner.annotation.RunnerFile;
import com.heliosphere.demeter.base.runner.context.Context;
import com.heliosphere.demeter.base.runner.context.IContext;
import com.heliosphere.demeter.base.runner.entity.Entity;
import com.heliosphere.demeter.base.runner.entity.EntityType;
import com.heliosphere.demeter.base.runner.entity.IEntity;
import com.heliosphere.demeter.base.runner.file.xml.configuration.XmlConfigurationFile;
import com.heliosphere.demeter.base.runner.file.xml.execution.XmlExecutionFile;
import com.heliosphere.demeter.base.runner.parameter.base.IParameter;
import com.heliosphere.demeter.base.runner.parameter.base.IParameterType;
import com.heliosphere.demeter.base.runner.parameter.base.ParameterException;
import com.heliosphere.demeter.base.runner.parameter.base.ParameterStatusType;
import com.heliosphere.demeter.base.runner.parameter.configuration.IParameterConfiguration;
import com.heliosphere.demeter.base.runner.parameter.execution.IParameterExecution;
import com.heliosphere.demeter.base.runner.processor.IProcessor;
import com.heliosphere.demeter.base.runner.processor.ProcessorException;
import com.heliosphere.demeter.base.runner.result.ExecutionStatusType;
import com.heliosphere.demeter.base.runner.result.IExecutionResult;

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
	private List<Callable<IExecutionResult>> callables = new ArrayList<>();

	/**
	 * Collection of futures (for gathering processing results from callables) for multi-threading.
	 */
	private List<Future<IExecutionResult>> futures = null;

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
			prepare();
		}
		catch (FileException | ParameterException | ProcessorException e)
		{
			throw new RunnerException(e);
		}
	}

	@Override
	public final XmlConfigurationFile getConfiguration()
	{
		return configuration;
	}

	@Override
	public final XmlExecutionFile getExecution()
	{
		return execution;
	}

	/**
	 * Prepares the internal runner structure.
	 * <hr>
	 * @throws ProcessorException Thrown in case an error occurred while trying to initialize the processor.
	 */
	@SuppressWarnings("nls")
	private void prepare() throws ProcessorException
	{
		initializeContexts();

		log.info(" ");
		log.info(String.format("%1d context(s) have been initialized:", contexts.size()));
		for (IContext context : contexts)
		{
			log.info(String.format("   context for entity name: [%1s], type: [%2s]", context.getEntity().getName(), context.getEntity().getType()));
		}
		log.info(" ");
	}

	/**
	 * Initializes the contexts.
	 * <hr>
	 * @throws ProcessorException Thrown in case an error occurred while trying to initialize the processor.
	 */
	public void initializeContexts() throws ProcessorException
	{
		IContext context = null;

		/*
		 * Create one context per entity to process.
		 */
		for (IEntity<?> entity : initializeEntities())
		{
			context = new Context(entity, execution.getContent());
			initializeProcessor(context);
			contexts.add(context);
		}
	}

	/**
	 * Initializes the processor.
	 * <hr>
	 * @param context Context to be processed by the processor.
	 * @return Initialized processor.
	 * @throws ProcessorException Thrown in case an error occurred while trying to initialize the processor.
	 */
	@SuppressWarnings({ "nls" })
	private IProcessor initializeProcessor(final IContext context) throws ProcessorException
	{
		IProcessor processor = null;
		Class<?> clazz;

		try
		{
			clazz = Class.forName(processorClass.getName());
			Constructor<?> ctor = clazz.getConstructor(IContext.class);
			processor = (IProcessor) ctor.newInstance(new Object[] { context });
			context.setProcessor(processor);
		}
		catch (Exception e)
		{
			throw new ProcessorException(String.format("Unable to instantiate processor of class: %1s due to: %2s", processorClass.getName(), e.getMessage()));
		}

		return processor;
	}

	/**
	 * Initializes the entities.
	 * <hr>
	 * @return List of entities to be processed.
	 */
	public List<IEntity<?>> initializeEntities()
	{
		List<IEntity<?>> entities = new ArrayList<>();

		// The entities can be initialized (for some of them) using the parameter type.
		for (IParameterExecution parameter : execution.getContent().getElements())
		{
			EntityType type = (EntityType) parameter.getEntityType();
			switch (type)
			{
				case DISPLAY:
					// Create a fake entity for the context initialization to display a message.
					IEntity<String> entity = new Entity<>(parameter.getName(), type, null);
					entities.add(entity);
					break;

				default:
					// Do nothing for these entity types!
				case BATCH:
				case DAEMON:
				case FILE:
				case COMPUTATION:
				case MESSAGE:
				case RESERVED:
					break;
			}

		}

		return entities;
	}

	@SuppressWarnings("nls")
	@Override
	public void start() throws RunnerException
	{
		log.info(String.format("Runner started: dispatching [%1d] context(s) across [%2d] thread(s).", contexts.size(), threadCount));
		log.info(" ");

		ExecutorService executor = Executors.newFixedThreadPool(this.threadCount);
		for (IContext context : contexts)
		{
			callables.add(context.getProcessor());
		}

		try
		{
			futures = executor.invokeAll(callables);
		}
		catch (InterruptedException e)
		{
			throw new RunnerException("An error occurred due to: " + e.getMessage(), e);
		}

		log.info("*********************************************************************************************************");
		log.info("EXECUTION SUMMARY:");
		log.info(" ");
		log.info(String.format(" Thread pool size..: [%1d]", threadCount));
		log.info(String.format(" Configuration file: [%1s]", configuration.getResource().getFile().getName()));
		log.info(String.format(" Execution file....: [%1s]", execution.getResource().getFile().getName()));
		log.info(String.format("        Description: %1s", execution.getHeader().getDescription()));
		log.info(String.format("       Parameter(s):"));
		IParameterConfiguration configuration = null;
		for (IParameterExecution p : execution.getContent().getElements())
		{
			configuration = p.getConfiguration();
			log.info(String.format("               type:[%1s], name:[%2s], value:[%3s], description:[%4s]", p.getType(), p.getName(), p.getValue(), configuration.getDescription()));
		}
		log.info(" ");

		for (Future<IExecutionResult> future : futures)
		{
			try
			{
				IExecutionResult result = future.get();

				// Dump the execution result of the execution of a processor.
				String message = String.format("Context name:[%1s], status:[%2s], execution:[%4s]", StringUtils.abbreviateMiddle(result.getName(), "...", 50), result.getStatus().toString(), result.getElapsed());
				log.error(message);

				// If process has failed, then dump the exceptions!
				if (result.getStatus() == ExecutionStatusType.FAILED)
				{
					for (Exception exception : result.getExceptions())
					{
						log.error(String.format("   Exception caught -> %1s", exception.getMessage()), exception);
					}
				}
			}
			catch (InterruptedException | ExecutionException e)
			{
				throw new RunnerException("An error occurred due to: " + e.getMessage(), e);
			}
		}

		executor.shutdown();
		watch.stop();

		log.info(" ");
		log.info(String.format("Runner finished processing: [%1d] context(s) in a total of: [%2s]", contexts.size(), watch.toString()));
		log.info("*********************************************************************************************************");
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
	public final IParameterConfiguration getConfigurationParameter(Enum<? extends IParameterType> type)
	{
		return configuration.getParameter(type);
	}

	@Override
	public final IParameterConfiguration getConfigurationParameter(@NonNull final String nameOrAlias)
	{
		return configuration.getParameter(nameOrAlias);
	}

	@Override
	public final IParameterConfiguration getConfigurationParameter(@NonNull final IParameterConfiguration parameter)
	{
		return configuration.getParameter(parameter);
	}

	@Override
	public final IParameterExecution getExecutionParameter(@NonNull final Enum<? extends IParameterType> type)
	{
		return execution.getParameter(type);
	}

	@Override
	public final IParameterExecution getExecutionParameter(String name)
	{
		return execution.getParameter(name);
	}

	@Override
	public final IParameterExecution getExecutionParameter(@NonNull final IParameterExecution parameter)
	{
		return execution.getParameter(parameter);
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
		for (IParameterExecution p : execution.getContent().getElements())
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
		for (IParameterExecution parameter : execution.getContent().getElements())
		{
			pConfig = configuration.getParameter(parameter.getType());
			if (pConfig.getIncompatibleParameters() != null)
			{
				for (String name : pConfig.getIncompatibleParameters())
				{
					pConfigOther = getConfigurationParameter(name);
					pExecOther = getExecutionParameter(pConfigOther.getType());

					if (pExecOther != null)
					{
						throw new ParameterException(String.format("Parameters %1s and %2s are incompatible!", pConfig.getName(), pConfigOther.getName()));
					}
				}
			}
		}

		// Check required parameters.
		for (IParameterExecution parameter : execution.getContent().getElements())
		{
			pConfig = configuration.getParameter(parameter.getType());
			if (pConfig.getRequiredParameters() != null)
			{
				for (String name : pConfig.getRequiredParameters())
				{
					pConfigOther = getConfigurationParameter(name);
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

			for (IParameterConfiguration parameter : configuration.getContent().getElements())
			{
				value = parameter.getName();
				parameter.setType((Enum<? extends IParameterType>) method.invoke(o, value));
				parameter.setEntityType(((IParameterType) parameter.getType()).getEntityType());
				if (parameter.getType() == null)
				{
					throw new ParameterException("No parameter definition found for: " + value);
				}
			}

			for (IParameterExecution parameter : execution.getContent().getElements())
			{
				name = parameter.getName();
				parameter.setType((Enum<? extends IParameterType>) method.invoke(o, name));
				parameter.setEntityType(((IParameterType) parameter.getType()).getEntityType());
				parameter.setStatus(ParameterStatusType.UNPROCESSED);
				parameter.setConfiguration(configuration.getParameter(parameter.getType()));
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
			for (IParameterConfiguration p : configuration.getContent().getElements())
			{
				if (p.getType() == e)
				{
					found = true;
					break;
				}
			}
			if (!found && !e.toString().equals("UNKNOWN"))
			{
				throw new ParameterException(String.format("Unable to find parameter name: '%1s' in file: '%2s' corresponding to enumeration class: %3s for enumerated value: %4s", ((IParameterType) e).getName(), configuration.getResource().getFile().getName(), clazz.getSimpleName(), e.toString()));
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
