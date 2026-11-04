# HTTP Client Configuration

URL: https://www.selenium.dev/documentation/webdriver/drivers/http_client/
Site index: https://www.selenium.dev/llms.txt

---

These allow you to set various parameters for the HTTP library

**Java**

```java
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
        ChromeOptions options = getDefaultChromeOptions();
        options.setEnableDownloads(true);
        driver = RemoteWebDriver.builder()
                .oneOf(options)
                .address(gridUrl)
                .config(clientConfig)
                .build();
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
        ChromeOptions options = getDefaultChromeOptions();
        options.setEnableDownloads(true);
        driver = RemoteWebDriver.builder()
                .oneOf(options)
                .address(gridUrl)
                .config(clientConfig)
                .build();
    }

    @Test
    public void remoteWebDriverWithEmbedAuthUrl() throws Exception {
        ClientConfig clientConfig = ClientConfig.defaultConfig()
                .withRetries()
                .sslContext(createSSLContextWithCA(Path.of("src/test/resources/tls.crt").toAbsolutePath().toString()))
                .connectionTimeout(Duration.ofSeconds(300))
                .readTimeout(Duration.ofSeconds(3600))
                .version(HTTP_1_1.toString());
        ChromeOptions options = getDefaultChromeOptions();
        options.setEnableDownloads(true);
        driver = RemoteWebDriver.builder()
                .oneOf(options)
                .address(embedAuthToUrl(gridUrl, "admin", "myStrongPassword"))
                .config(clientConfig)
                .build();
    }

    private URL embedAuthToUrl(URL url, String username, String password) throws Exception {
        String userInfo = username + ":" + password;
        String urlWithAuth = url.getProtocol() + "://" + userInfo + "@" + url.getHost() + ":" + url.getPort() + url.getPath();
        return new URL(urlWithAuth);
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
```

**Python**

```python
import os
import pytest
import sys
from urllib3.util import Retry, Timeout
from selenium import webdriver
from selenium.webdriver.common.proxy import Proxy
from selenium.webdriver.common.proxy import ProxyType
from selenium.webdriver.remote.client_config import ClientConfig

@pytest.mark.skipif(sys.platform == "win32", reason="Gets stuck on Windows, passes locally")
def test_start_remote_with_client_config(grid_server):
    proxy = Proxy({"proxyType": ProxyType.AUTODETECT})
    retries = Retry(connect=2, read=2, redirect=2)
    timeout = Timeout(connect=300, read=3600)
    client_config = ClientConfig(remote_server_addr=grid_server,
                                 proxy=proxy,
                                 init_args_for_pool_manager={
                                     "init_args_for_pool_manager": {"retries": retries, "timeout": timeout}},
                                 ca_certs=_get_resource_path("tls.crt"),
                                 username="admin", password="myStrongPassword")
    options = get_default_chrome_options()
    driver = webdriver.Remote(command_executor=grid_server, options=options, client_config=client_config)
    driver.get("https://www.selenium.dev")
    driver.quit()

@pytest.mark.skipif(sys.platform == "win32", reason="Gets stuck on Windows, passes locally")
def test_start_remote_ignore_certs(grid_server):
    proxy = Proxy({"proxyType": ProxyType.AUTODETECT})
    client_config = ClientConfig(remote_server_addr=grid_server,
                                 proxy=proxy,
                                 timeout=3600,
                                 ignore_certificates=True,
                                 username="admin", password="myStrongPassword")
    options = get_default_chrome_options()
    driver = webdriver.Remote(command_executor=grid_server, options=options, client_config=client_config)
    driver.get("https://www.selenium.dev")
    driver.quit()

def _get_resource_path(file_name: str):
    if os.path.abspath("").endswith("tests"):
        path = os.path.abspath(f"resources/{file_name}")
    else:
        path = os.path.abspath(f"tests/resources/{file_name}")
    return path

def get_default_chrome_options():
    options = webdriver.ChromeOptions()
    options.add_argument("--no-sandbox")
    return options
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby

it 'sets client configuration' do
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._
