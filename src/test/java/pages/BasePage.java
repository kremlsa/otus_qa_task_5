package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public class BasePage {

    //Объявление веб-драйвера
    protected WebDriver driver;

    public void initWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void openURL(String url) {
        driver.get(url);
    }

    public boolean elementIsPresent(By selector) {
        return !driver.findElements(selector).isEmpty();
    }

    public boolean elementIsDisplayed(By selector) {
        return driver.findElement(selector).isDisplayed();
    }

    public String getTitle() {
        return driver.getTitle();
    }

}
