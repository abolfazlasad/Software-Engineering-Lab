package calculator;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CalculatovV2Stepdefs {

    private Calculator calculator;
    private int value1;
    private int value2;
    private char operation;
    private int result;

    @Before
    public void before() {
        calculator = new Calculator();
    }

    @Given("^Two input values, (\\d+) and (\\d+) and operation (\\*|\\/|\\^)$")
    public void twoInputValuesWithOperation(int arg0, int arg1, char arg2) {
        value1 = arg0;
        value2 = arg1;
        operation = arg2;
    }

    @When("^I Press = button$")
    public void iPressTheEqualButton() {
        if (operation == '*')
            result = calculator.mul(value1, value2);
        else if (operation == '/')
            result = calculator.div(value1, value2);
        else if (operation == '^')
            result = calculator.pow(value1, value2);
        else result = 0;
        System.out.print(result);
    }

    @Then("^I expect the result (\\d+) on calculator$")
    public void iExpectTheResultOfCalculator(int arg0) {
        Assert.assertEquals(arg0, result);
    }
}
