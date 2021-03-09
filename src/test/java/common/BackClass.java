package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

//Класс для тестирования бэкэнда
@Component
public class BackClass {
    HttpURLConnection connection;
    public final Logger logger = LogManager.getLogger();
    private Cert cert;

    //Инъекция класса для работы с сертификатами через конструктор
    @Autowired
    public BackClass(Cert cert) {
        this.cert = cert;
    }

    //Устанавливаем подключение к ресурсу
    public void setConnection(String address) {
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Получаем код http ответа
    public int getResponse() {
        try {
            return connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Создаём TLS соединение с указанным ресурсом
    public void createTLS(String subject) {
        cert.init(subject);
    }

    //Получаем цепочку сертификатов ресурса
    public void getCertificates() {
        cert.getCertificates();
    }

    //Ищем сертификат с указанным Common Name
    public boolean findCertificateWithCN(String subject) {
        return cert.findCert(subject);
    }


}
