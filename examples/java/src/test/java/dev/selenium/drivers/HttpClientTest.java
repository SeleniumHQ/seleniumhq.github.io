package dev.selenium.drivers;

import dev.selenium.BaseTest;

import org.openqa.selenium.remote.http.ClientConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.time.Duration;

import org.openqa.selenium.UsernameAndPassword;

import static java.net.http.HttpClient.Version.HTTP_1_1;

public class HttpClientTest extends BaseTest {
    URL gridUrl;

    @BeforeEach
    public void startGrid() {
        gridUrl = startStandaloneGridAdvanced();
    }

    @Test
    public void remoteWebDriverWithClientConfig() throws Exception {
        ClientConfig clientConfig = ClientConfig.defaultConfig()
                .withRetries()
                .sslContext(createSSLContextWithCA(Path.of("src/test/resources/tls.crt").toAbsolutePath().toString()))
                .connectionTimeout(Duration.ofSeconds(300))
                .readTimeout(Duration.ofSeconds(3600))
                .authenticateAs(new UsernameAndPassword("admin", "myStrongPassword"))
                .version(HTTP_1_1.toString());
        ChromeOptions options = new ChromeOptions();
        options.setEnableDownloads(true);
        driver = RemoteWebDriver.builder()
                .oneOf(options)
                .address(gridUrl)
                .config(clientConfig)
                .build();
        driver.quit();
    }

    @Test
    public void remoteWebDriverIgnoreSSL() throws Exception {
        ClientConfig clientConfig = ClientConfig.defaultConfig()
                .withRetries()
                .sslContext(createIgnoreSSLContext())
                .connectionTimeout(Duration.ofSeconds(300))
                .readTimeout(Duration.ofSeconds(3600))
                .authenticateAs(new UsernameAndPassword("admin", "myStrongPassword"))
                .version(HTTP_1_1.toString());
        ChromeOptions options = new ChromeOptions();
        options.setEnableDownloads(true);
        driver = RemoteWebDriver.builder()
                .oneOf(options)
                .address(gridUrl)
                .config(clientConfig)
                .build();
        driver.quit();
    }

    public static SSLContext createSSLContextWithCA(String caCertPath) throws Exception {
        FileInputStream fis = new FileInputStream(caCertPath);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate caCert = (X509Certificate) cf.generateCertificate(fis);
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        keyStore.setCertificateEntry("caCert", caCert);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(keyStore);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);
        return sslContext;
    }

    public static SSLContext createIgnoreSSLContext() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        return sslContext;
    }
}
