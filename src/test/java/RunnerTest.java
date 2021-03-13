import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(features= "src/test/resources/features",
        glue= {"stepdefs"},
        monochrome = true,
        plugin = {"pretty",
                "html:target/cucumber-reports/task5.html"})
public class RunnerTest {

}