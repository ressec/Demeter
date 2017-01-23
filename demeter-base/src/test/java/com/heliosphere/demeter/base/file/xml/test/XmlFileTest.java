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

import com.heliosphere.demeter.base.file.xml.XmlFile;
import com.heliosphere.demeter.base.file.xml.model.Content;
import com.heliosphere.demeter.base.file.xml.model.Footer;
import com.heliosphere.demeter.base.file.xml.model.Header;

/**
 * Test class for the {@link XmlFile} class.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class XmlFileTest
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
	 * Creates a resource based on a file with an absolute path name.
	 */
	@SuppressWarnings({ "static-method", "nls" })
	@Test
	public final void createXmlFile()
	{
		try
		{
			XmlFile<Header, Content, Footer> file = new XmlFile<>("/unit/path/test/basic-xml-file.xml");

			Header header = new Header();
			header.setCompany("Heliosphere Ltd.");
			header.setAuthor("Resse Christophe");
			header.setVersion("1.0");
			header.setDescription("A simple XML file using the <FileHeader> class as header, content and footer.");
			file.setHeader(header);

			Footer footer = new Footer();
			footer.setGenerated("on 2017/01/20 @ 16:57:05");
			file.setFooter(footer);

			file.addContent(new Content("toto"));
			file.addContent(new Content("tata"));
			file.addContent(new Content("titi"));
			file.addContent(new Content("tutu"));

			file.save();

			Assert.assertTrue(file != null);
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}

	/**
	 * Creates a resource based on a file with an absolute path name.
	 */
	@SuppressWarnings({ "static-method", "nls" })
	@Test
	public final void loadXmlFile()
	{
		try
		{
			XmlFile<Header, Content, Footer> file = new XmlFile<>("/unit/path/test/basic-xml-file.xml");
			file.load();

			Assert.assertTrue(file != null);

			Assert.assertTrue(file.getHeader().getAuthor().equals("Resse Christophe"));
			Assert.assertTrue(file.getContent().get(0).getValue().equals("toto"));
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}
}
