package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class BackClass {
    HttpURLConnection connection;
    public final Logger logger = LogManager.getLogger();

    @Autowired
    private Cert cert;

    public BackClass(Cert cert) {
        this.cert = cert;
    }

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

    public int getResponse() {
        try {
            return connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void createTLS(String subject) {
        cert.init(subject);
    }

    public void getCertificates() {
        cert.getCertificates();
    }

    public boolean findCertificateWithCN(String subject) {
        return cert.findCert(subject);
    }


}
