package stepdefs;

import common.BackClass;
import common.BaseClass;
import common.Cert;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import spring.TestApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@SpringBootTest

public class BackendSteps {

    @Autowired
    private BackClass back;

    private Integer responseCode;


    public BackendSteps(BackClass back) {
        this.back = back;
    }

    @Дано("Анонимное подключение")
    public void setConnection() {
        System.out.println("Connect");
    }

    @Когда("пользователь пытается получить доступ к закрытому разделу сайта {string}")
    public void getUrl(String url) {
        back.setConnection(url);
        responseCode = back.getResponse();
    }

    @То("получен ответ {int} для ссылки {string}")
    public void checkResponse(Integer expectedCode, String url) {
        Assert.assertEquals(responseCode,expectedCode);
    }

    @Дано("Открываем TLS соединение с ресурсом {string}")
    public void setConn(String subject) {
        back.createTLS(subject);
    }

    @Когда("получаем имя владельца сертификата")
    public void getCert() {
        back.getCertificates();
    }

    @То("сертификат выдан {string}")
    public void checkCert(String subject) {
        Assert.assertTrue(back.findCertificateWithCN(subject));
    }
}