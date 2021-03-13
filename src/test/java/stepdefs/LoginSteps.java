package stepdefs;

import configSpring.Cfg;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pages.MainPage;
import common.BaseClass;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

//@SpringBootTest
public class LoginSteps extends BaseStep{
//public class LoginSteps extends CucumberSpringContextConfig{

    @Value("${correctLogin}")
    private String correctLogin;

    @Value("${correctPassword}")
    String correctPassword;

    @Autowired
    private MainPage mainPage;

    String login;
    String pass;
    WebDriver driver = BaseClass.getDriver();

    @Дано("Страница входа")
    public void loginPage() {
       mainPage.initWebDriver(driver);
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
        System.out.println(login + "-" + pass);
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
        mainPage.initWebDriver(driver);
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
        login = Optional.ofNullable(System.getProperty("login")).orElse(correctLogin);

        //Получаем пароль из параметра -Dpassword командной строки
        pass = Optional.ofNullable(System.getProperty("password")).orElse(correctPassword);
    }
}