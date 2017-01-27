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
package com.heliosphere.demeter.base.file.xml.test;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.heliosphere.demeter.base.file.xml.model.Footer;
import com.heliosphere.demeter.base.file.xml.model.Header;
import com.heliosphere.demeter.base.parameter.IParameterDefinition;
import com.heliosphere.demeter.base.parameter.ParameterDefinition;
import com.heliosphere.demeter.base.runner.file.xml.configuration.XmlConfigurationFile;

/**
 * Test an XML definition file.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class XmlDefinitionFileTest
{
	/**
	 * Initialization of the test cases.
	 * <p>
	 * @throws Exception In case an error occurs during the initialization.
	 */
	@BeforeClass
	public static final void setUpBeforeClass() throws Exception
	{
		// Empty
	}

	/**
	 * Finalization of the test cases.
	 * <p>
	 * @throws Exception In case an error occurs during the finalization.
	 */
	@AfterClass
	public static final void tearDownAfterClass() throws Exception
	{
		// Empty
	}

	/**
	 * Sets up the fixture.
	 * <p>
	 * @throws Exception In case an error occurs during the setup phase.
	 */
	@Before
	public final void setUp() throws Exception
	{
		// Empty
	}

	/**
	 * Tears down the fixture.
	 * <p>
	 * @throws Exception In case an error occurs during the tear down phase.
	 */
	@After
	public final void tearDown() throws Exception
	{
		// Empty
	}

	/**
	 * Creates an XML configuration file.
	 */
	@SuppressWarnings({ "static-method", "nls" })
	@Test
	public final void createXmlFile()
	{
		try
		{
			XmlConfigurationFile<Header, IParameterDefinition, Footer> file = new XmlConfigurationFile<>("/unit/path/test/test-runner-definition.xml");

			Header header = new Header();
			header.setCompany("Heliosphere Ltd.");
			header.setAuthor("Resse Christophe");
			header.setVersion("1.0");
			header.setDescription("A simple test xml configuration file typically used by a runner.");
			file.setHeader(header);

			Footer footer = new Footer();
			footer.setGenerated("on 2017/01/26 @ 10:59:58");
			file.setFooter(footer);

			IParameterDefinition p = new ParameterDefinition();
			p.setName("section");
			p.setDescription("Processes a specific section.");
			p.setPriority(150);
			p.setReserved(false);
			p.setMandatory(false);
			p.addAlias("s");
			p.addAlias("sec");
			p.addExclude("generate");
			p.addExclude("ingest");
			p.addInclude("analyze");
			p.addAllowed("CPA");
			p.addAllowed("TAK");
			p.addAllowed("CUY");
			p.addAllowed("WTRZ");
			file.addElement(p);

			file.save();

			Assert.assertTrue(file != null);
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}

	/**
	 * Loads the XML configuration file.
	 */
	@SuppressWarnings({ "static-method", "nls" })
	@Test
	public final void loadDefinitionXmlFile()
	{
		try
		{
			XmlConfigurationFile<Header, IParameterDefinition, Footer> file = new XmlConfigurationFile<>("/unit/path/test/test-runner-definition.xml");
			file.load();

			Assert.assertTrue(file != null);
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}
}
