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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.heliosphere.demeter.base.file.xml.XmlFileTest;
import com.heliosphere.demeter.base.file.xml.XmlRunnerParameterFileTest;
import com.heliosphere.demeter.base.resource.bundle.test.ResourceBundleTest;
import com.heliosphere.demeter.base.resource.test.ResourceTest;

/**
 * Defines the {@code demeter} unit test suite.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
@RunWith(Suite.class)
@SuiteClasses({ ResourceBundleTest.class, ResourceTest.class, XmlFileTest.class, XmlRunnerParameterFileTest.class })
public class DemeterTestSuite
{
	// Empty.
}
