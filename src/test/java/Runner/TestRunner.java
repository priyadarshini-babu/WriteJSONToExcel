package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features ="src/main/java/Features/",
		glue = {"StepDefinitions"},
		plugin = {"pretty","html:test-output"},
		dryRun = false,
		strict = true
		)

public class TestRunner {

}
