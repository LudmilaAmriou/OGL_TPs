import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStepdefs {
   public int result;
    @io.cucumber.java.en.Given("I have a calculator")
    public void iHaveACalculator() {
    }

    @io.cucumber.java.en.When("I add {int} and {int}")
    public void iAddNumAndNum(int a, int b) {
        result = a+b;
    }

    @io.cucumber.java.en.Then("I should have {int}")
    public void iShouldHaveResult(int k) {
        assertEquals(k,result);
    }
}
