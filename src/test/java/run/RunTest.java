package run;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import setup.Setup;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {
                "pretty",
                "html:src/main/report",
                "json:src/main/report/cucumber.json",
                "junit:src/main/report/cucumber.xml"
        },
        features = {
                "src/main/resources/feature"
        },
        glue = {
                "step", "hooks"
        },
        tags = "@Teste01"

)
public class RunTest {


}
