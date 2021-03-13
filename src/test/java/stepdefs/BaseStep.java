package stepdefs;

import configSpring.Cfg;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = Cfg.class)
@SpringBootTest
public class BaseStep {
}
