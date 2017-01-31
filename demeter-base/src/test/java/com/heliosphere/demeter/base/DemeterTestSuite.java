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
package com.heliosphere.demeter.base;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.heliosphere.demeter.base.file.xml.test.XmlDefinitionFileTest;
import com.heliosphere.demeter.base.file.xml.test.XmlFileTest;
import com.heliosphere.demeter.base.log4j.category.UnitTest;
import com.heliosphere.demeter.base.resource.test.ResourceTest;

@RunWith(Categories.class)
@IncludeCategory(UnitTest.class)

@SuiteClasses({ ResourceTest.class, XmlFileTest.class, XmlDefinitionFileTest.class })
public class DemeterTestSuite
{
	// Empty.
}
