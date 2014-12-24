package example.bdd.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 * @author sergey.belonozhko@hp.com
 */
public class MathSteps {

    private int result;

    @Given("a value $val")
    public void putNumber(int val) {
        result = val;
    }

    @When("I add the value $val")
    public void add(int val) {
        result += val;
    }

    @Then("the result should be $expected")
    public void checkResult(int expected) {
        if (expected != result)
            throw new RuntimeException("Arithmetic error");
    }
}
