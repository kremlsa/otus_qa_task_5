package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BaseClass {
    public WebDriver driver;
    public final Logger logger = LogManager.getLogger();
}