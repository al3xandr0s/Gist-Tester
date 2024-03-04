package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features={"src/test/resources/CreateGist.feature"
                ,"src/test/resources/GetGist.feature"
                , "src/test/resources/UpdateGist.feature"
                ,"src/test/resources/DeleteGist.feature"
                ,"src/test/resources/ListGist.feature"
                ,"src/test/resources/RateLimitQuotas.feature"
        },
        glue = {"StepDefinitions"},
        plugin = { "pretty", "html:target/cucumber-reports.html" },
        tags = "@group2 or @group1"
)

public class TestRunner extends AbstractTestNGCucumberTests {
}
