package com.cme.runner;

import com.fcbt.taf.core.BaseRunner;
import com.fcbt.taf.core.SystemConstants;
import io.cucumber.java.Before;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        features = {"src/test/resources/features/Smoke Test/US57666.feature"}, //
        //dryRun=true
        glue = {"com.cme"}//,
)
public class CMETest extends BaseRunner {
    @BeforeSuite
    public void testEdge(){
        System.setProperty("webdriver.edge.driver", SystemConstants.EDGE_DRIVER_PATH);
    }


}

