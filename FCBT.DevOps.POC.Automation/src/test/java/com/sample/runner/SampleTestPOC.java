package com.sample.runner;

import com.fcbt.taf.core.reporting.CucumberTestNGListener;
import com.fcbt.taf.core.reporting.Reporter;
import com.fcbt.taf.core.reporting.extent.ExtentHTMLReportObserver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners({CucumberTestNGListener.class})
@CucumberOptions(
        features = {"src/test/resources/features/CME_NewDealCreation.feature"},
        //tags = "",
		//dryRun=true,
        plugin = {"pretty", "html:target/cucumberReport/cucumberReport.html","json:target/cucumber.json"},
        glue = {"com.sample", "com.fcbt.taf.core"})
public class SampleTestPOC extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite
    public void beforeSuite(){
        Reporter.register(new ExtentHTMLReportObserver());
    }

}
