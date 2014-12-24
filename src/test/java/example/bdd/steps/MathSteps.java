package example.bdd.steps;

import example.bdd.StateHolder;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 * @author sergey.belonozhko@hp.com
 */
public class MathSteps {

    @Given("a value $val")
    @Alias("a value <first_val>")
    public void putNumber(@Named("first_val") int val) {
        MathState state = new MathState();
        state.result = val;
        StateHolder.getHolder(MathState.class).setState(state);
    }

    @When("I add the value $val")
    @Alias("I add the value <second_val>")
    public void add(@Named("second_val") int val) {
        MathState state = StateHolder.getHolder(MathState.class).getState();
        state.result += val;
    }

    @Then("the result should be $expected")
    @Alias("the result should be <expected>")
    public void checkResult(@Named("expected") int expected) {
        MathState state = StateHolder.getHolder(MathState.class).getState();
        if (expected != state.result)
            throw new RuntimeException("Arithmetic error");
    }
}
