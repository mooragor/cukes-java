package mphillips.cukes.project;

import org.junit.platform.suite.api.ConfigurationParametersResource;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("mphillips.cukes.project")
@ConfigurationParametersResource("cucumber.properties")
public class RunCucumberTest {
}
