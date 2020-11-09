package APIRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/APIScenario/Demo.feature",
        //features = "@target/rerun.txt",
        glue = "apiStepDefs",
        tags = "@SmokeTest",
        dryRun = false,
        monochrome = true,
        plugin = {})

public class APIRunner extends AbstractTestNGCucumberTests {
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
