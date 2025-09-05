import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        plugin = {
                "pretty",                                    // saída no console
                "html:target/cucumber-reports/html",        // relatório HTML
                "json:target/cucumber-json/cucumber.json",  // necessário para Allure
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm" // plugin Allure
        }
)
public class RunCucumberTest {
}
