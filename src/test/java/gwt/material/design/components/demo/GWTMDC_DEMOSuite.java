package gwt.material.design.components.demo;

import gwt.material.design.components.demo.client.GWTMDC_DEMOTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class GWTMDC_DEMOSuite extends GWTTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Tests for GWTMDC_DEMO");
		suite.addTestSuite(GWTMDC_DEMOTest.class);
		return suite;
	}
}
