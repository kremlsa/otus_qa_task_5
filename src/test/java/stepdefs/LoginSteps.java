package stepdefs;

import common.BaseClass;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pages.MainPage;
import spring.TestApplication;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@SpringBootTest

public class LoginSteps {

    @Autowired
    private final MainPage mainPage;

    private final BaseClass base;
    String login;
    String pass;

    public LoginSteps(MainPage mainPage, BaseClass base) {
        this.mainPage = mainPage;
        this.base = base;
    }


    @Дано("Страница входа")
    public void loginPage() {
        //mainPage = new MainPage(base.driver);
        mainPage.initWebDriver(base.driver);
    }

    @Когда("пользователь вводит корректные данные")
    public void loginWithCorrectCreds() {
        setCreds();
        //Аутентифицируемся
        mainPage.open()
                .auth()
                .fillAuthForm(login, pass);
    }

    @То("пользователь осуществляет вход на сайт")
    public void checkLogin() {
        Assert.assertTrue(mainPage.checkLogin());
    }

    public void setCreds() {
        //Получаем имя пользователя из параметра -Dlogin командной строки
        login = Optional.ofNullable(System.getProperty("login")).orElse("null");

        //Получаем пароль из параметра -Dpassword командной строки
        pass = Optional.ofNullable(System.getProperty("password")).orElse("null");
    }

}