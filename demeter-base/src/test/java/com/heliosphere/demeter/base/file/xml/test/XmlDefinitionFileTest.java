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

import com.heliosphere.demeter.base.file.xml.model.Content;
import com.heliosphere.demeter.base.file.xml.model.Footer;
import com.heliosphere.demeter.base.file.xml.model.Header;
import com.heliosphere.demeter.base.runner.file.XmlDefinitionFile;

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

	//	/**
	//	 * Creates a resource based on a file with an absolute path name.
	//	 */
	//	@SuppressWarnings({ "static-method", "nls" })
	//	@Test
	//	public final void createXmlFile()
	//	{
	//		try
	//		{
	//			XmlFile<Header, Content, Footer> file = new XmlFile<>("/unit/path/test/basic-xml-file.xml");
	//
	//			Header header = new Header();
	//			header.setCompany("Heliosphere Ltd.");
	//			header.setAuthor("Resse Christophe");
	//			header.setVersion("1.0");
	//			header.setDescription("A simple XML file using the <FileHeader> class as header, content and footer.");
	//			file.setHeader(header);
	//
	//			Footer footer = new Footer();
	//			footer.setGenerated("on 2017/01/20 @ 16:59:05");
	//			file.setFooter(footer);
	//
	//			file.addContent(new Content("Washington"));
	//			file.addContent(new Content("Paris"));
	//			file.addContent(new Content("London"));
	//			file.addContent(new Content("Singapore"));
	//
	//			file.save();
	//
	//			Assert.assertTrue(file != null);
	//		}
	//		catch (Exception e)
	//		{
	//			fail(e.getMessage());
	//		}
	//	}

	/**
	 * Loads our test xml definition file.
	 */
	@SuppressWarnings({ "static-method", "nls" })
	@Test
	public final void loadDefinitionXmlFile()
	{
		try
		{
			XmlDefinitionFile<Header, Content, Footer> file = new XmlDefinitionFile<>("/unit/path/test/test-runner-definition.xml");
			file.load();

			Assert.assertTrue(file != null);
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}
}
