package stepdefs;

import common.BaseClass;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import org.junit.Assert;
import pages.MainPage;

import java.util.Optional;


public class LoginSteps extends BaseClass {

    private MainPage mainPage;

    private BaseClass base;
    String login;
    String pass;

    public LoginSteps(BaseClass base) {
        this.base = base;
    }


    @Дано("Страница входа")
    public void loginPage() {
        mainPage = new MainPage(base.driver);
    }

    @Когда("пользователь вводит корректные данные")
    public void loginWithCorrectCreds() {
        setCreds();
        System.out.println(login + " " + pass);
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