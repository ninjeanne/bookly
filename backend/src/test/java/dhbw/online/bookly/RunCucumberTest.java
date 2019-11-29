package dhbw.online.bookly;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"cucumber.api.spring", "dhbw.online.bookly"},
        features = {"src/test/resources"}
)
public class RunCucumberTest {
}