package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackClass {
    HttpURLConnection connection;
    public final Logger logger = LogManager.getLogger();
    Cert cert = new Cert();

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
