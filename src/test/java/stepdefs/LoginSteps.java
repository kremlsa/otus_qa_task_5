package stepdefs;

import common.AppConfig;
import common.BaseClass;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import pages.MainPage;

import java.util.Optional;

public class LoginSteps {

    private final MainPage mainPage;
    private final BaseClass base;
    private final AppConfig cfg = ConfigFactory.create(AppConfig.class);
    String login;
    String pass;

    @Autowired
    public LoginSteps(MainPage mainPage, BaseClass base) {
        this.mainPage = mainPage;
        this.base = base;
    }

    @Дано("Страница входа")
    public void loginPage() {
        mainPage.initWebDriver(base.driver);
    }

    @То("пользователь осуществляет вход на сайт")
    public void checkLogin() {
        Assert.assertTrue(mainPage.checkLogin());
    }

    @То("пользователь получает предупреждение")
    public void isAlertPresent() {
        Assert.assertTrue(mainPage.
                elementIsDisplayed(By.xpath("//div[contains(text(),'не существует')]")));
    }

    @И("Корректные учётные данные")
    public void setUserDefinedCreds() {
        setCreds();
    }

    @Когда("пользователь вводит логин и пароль")
    public void fillAuthForm() {
        mainPage.open()
                .auth()
                .fillAuthForm(login, pass);
    }

    @И("логин {string}")
    public void setLogin(String login) {
        this.login = login;
    }

    @И("пароль {string}")
    public void setPass(String pass) {
        this.pass = pass;
    }

    @Дано("Анонимный пользователь на сайте {string}")
    public void init(String url) {
        mainPage.initWebDriver(base.driver);
        mainPage.openURL(url);
    }

    @Когда("пользователь переходит по прямой ссылке {string}")
    public void openUrl(String url) {
        mainPage.openURL(url);
    }

    @То("происходит переадресация на форму аутентификации")
    public void loginFormIsAppear() {
        Assert.assertTrue(mainPage.
                elementIsPresent(By.xpath("//*[contains(text(),'Войти')]")));
    }

    @То("происходит успешный переход в раздел {string}")
    public void checkTitle(String title) {
        Assert.assertTrue(mainPage.getTitle().contains(title));
    }

    public void setCreds() {
        //Получаем имя пользователя из параметра -Dlogin командной строки
        login = Optional.ofNullable(System.getProperty("login")).orElse(cfg.correctLogin());

        //Получаем пароль из параметра -Dpassword командной строки
        pass = Optional.ofNullable(System.getProperty("password")).orElse(cfg.correctPassword());
    }
}