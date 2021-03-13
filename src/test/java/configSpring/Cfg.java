package configSpring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;

@Configuration
@ComponentScan("pages")
@ComponentScan("common")
@ComponentScan("stepdefs")
@PropertySource("classpath:config.properties")
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)

public class Cfg {

    /*@Bean(value = "chrome")
    public WebDriver getDriver() {
        return Driver.getChromeDriver();
    }

    @Bean(value = "firefox")
    public WebDriver getOtherDriver() {
        return Driver.getFirefoxDriver();
    }*/
}
