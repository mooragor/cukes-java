package mphillips.cukes.project.steps;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import mphillips.cukes.project.Belly;
import mphillips.cukes.project.support.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {
    @Given("I have {int} cukes in my belly")
    public void I_have_cukes_in_my_belly(int cukes) {
        Belly belly = new Belly();
        belly.eat(cukes);

        System.out.printf(Test.env.getProperty("username"));
    }

    @When("^I wait (\\d+) seconds?$")
    public void i_wait_seconds(int secs) {
        try {
            Thread.sleep(secs * 1000L);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    @When("my belly should {word}")
    public void my_belly_should(String word) {
        // fine
    }



}
