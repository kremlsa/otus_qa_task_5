package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;


@Component
public class MainPage extends BasePage {

    private static final String URL = "https://otus.ru";
    private final By auth = By.cssSelector("button[data-modal-id='new-log-reg']");
    private final By login = By.cssSelector("input[type='text']");
    private final By pass = By.cssSelector("input[type='password']");
    private final By submit = By.xpath("//*[contains(text(),'Войти')]");
    private final By myCourse = By.cssSelector(".ic-my-course");

    public MainPage open() {
        super.driver.get(URL);
        return this;
    }

    public MainPage auth() {
        super.driver.findElement(auth).click();
        return this;
    }

    public void fillAuthForm(String userName, String password) {
        (new WebDriverWait(super.driver, 5))
                .until(ExpectedConditions.elementToBeClickable(submit));
        super.driver.findElement(login).sendKeys(userName);
        super.driver.findElement(pass).sendKeys(password);
        super.driver.findElement(submit).click();
    }

    public boolean checkLogin() {
        return !super.driver.findElements(myCourse).isEmpty();
    }

}

