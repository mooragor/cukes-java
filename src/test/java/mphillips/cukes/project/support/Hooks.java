package mphillips.cukes.project.support;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class Hooks {

    private static final String OutputSeparator = "=======================================================";
    private static final int StepMaxTime = 2;
    private static long stepTimerStart;



    @BeforeAll()
    public static void beforeAllHook(){
        Test.SetupEnv();
    }

    @AfterAll()
    public static void afterAllHook(){
        Test.teardownWebDriver();
    }

    @Before()
    public void beforeScenarioHook(Scenario scenario){
        Test.setupWebDriver();
    }

    @After
    public void afterScenarioHook(Scenario scenario){
        if (!scenario.isFailed())
        {
            File scrFile = ((TakesScreenshot)Test.driver).getScreenshotAs(OutputType.FILE);
            Test.log.debug(scrFile.getAbsolutePath());

        }
        Test.teardownWebDriver();
    }

    @BeforeStep
    public void beforeStepHook(Scenario scenario){
        stepTimerStart = System.nanoTime();
    }

    @AfterStep
    public void afterStepHook(Scenario scenario){
        long elapse = (System.nanoTime() - stepTimerStart)/1_000_000_000L;
        if(elapse > StepMaxTime)
        {
            scenario.log(String.format("*** WARNING *** Step timer exceeded (%d/%d)", elapse, StepMaxTime));
        }
    }

}
