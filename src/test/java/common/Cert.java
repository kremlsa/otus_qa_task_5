package common;

import org.springframework.stereotype.Component;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.security.cert.X509Certificate;

@Component
public class Cert {
    SSLSocket socket;
    X509Certificate[] chain;

    //Устанавливаем TLS
    public void init(String subject) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            socket = (SSLSocket) factory.createSocket("www." + subject, 443);
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    //Получаем цепочку сертификатов
    public void getCertificates() {
        try {
            chain = socket.getSession().getPeerCertificateChain();
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        }
    }

    //Поиск сертификата по Common Name
    public boolean findCert(String subject) {
        for (X509Certificate c : chain) {
            String subjectName = c.getSubjectDN().getName();
            System.out.println(subjectName);
            if (subjectName.equals("CN=" + subject)) {
                return true;
            }
        }
        return false;
    }

}
