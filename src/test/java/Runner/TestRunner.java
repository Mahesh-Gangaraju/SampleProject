package Runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        dryRun = false,
        strict = true,
        monochrome = true,
        features = "src/test/java/features",
        plugin = {"pretty" , "json:C:\\Mahesh Gangaraju\\Work\\AutomationResources\\Run_Results/cucumber.json","rerun:target/failed_scenarios.txt"},
        glue = {"StepDefinitions" , "BaseClass" },
        tags = "@Demo_Suite"
)

public class TestRunner {
}



