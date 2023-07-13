package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.HttpsUrlConnectionMessageSender;

import javax.net.ssl.*;
import java.security.cert.*;

@Configuration
public class NumberClientConfig {

    @Bean
    public NumberClient numberClient(Jaxb2Marshaller marshaller, @Value("${ws.client.number}") String defaultUri) {
        NumberClient client = new NumberClient();
        //client.setDefaultUri("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso");
        //client.setDefaultUri("https://www.dataaccess.com/webservicesserver/NumberConversion.wso");
        client.setDefaultUri(defaultUri);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        HttpsUrlConnectionMessageSender sender = new HttpsUrlConnectionMessageSender();
        sender.setTrustManagers(new TrustManager[]{new UnTrustworthyTrustManager()});
        client.setMessageSender(sender);
        //client.getWebServiceTemplate().setMessageSender(sender);
        return client;
    }

    static class UnTrustworthyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
        }

        public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
