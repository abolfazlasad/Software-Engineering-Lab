package calculator;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features")
@RunWith(Cucumber.class)
public class RunnerTest {
}
