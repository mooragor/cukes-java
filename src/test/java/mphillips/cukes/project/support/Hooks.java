package mphillips.cukes.project.support;

import io.cucumber.java.*;

public class Hooks {

    private static final String OutputSeparator = "=======================================================";
    private static final int StepMaxTime = 2;
    private static long stepTimerStart;



    @BeforeAll()
    public static void beforeAllHook(){
        Test.SetupEnv();
    }

    @Before()
    public void beforeScenarioHook(Scenario scenario){
    }

    @After
    public void afterScenarioHook(Scenario scenario){
        // scenario.log("After Scenario hook");
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
