package mphillips.cukes.project.steps;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import mphillips.cukes.project.support.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {
    @Given("I visit {string}")
    public void i_visit_url(String url) {
        Test.driver.get(url);
    }

    @Given("I have {int} things")
    public void i_have_things(int cukes) {
        Test.log.debug("Count = " + cukes);
        Test.log.debug(Test.env.getProperty("username"));
    }

    @When("^I wait (\\d+) seconds?$")
    public void i_wait_seconds(int secs) {
        try {
            Thread.sleep(secs * 1000L);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    @When("I should have {int} things")
    public void i_should_have_things(int count) {
        // fine
    }


}
