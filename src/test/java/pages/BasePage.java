package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    //Объявление веб-драйвера
    protected WebDriver driver;

    public void initWebDriver(WebDriver driver) {
        this.driver = driver;
    }

}
