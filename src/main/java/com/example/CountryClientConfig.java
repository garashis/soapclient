package com.example;

import com.gmfinancial.gmpcp.consumingwebservice.wsdl.AddResponse;
import org.springframework.context.annotation.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CountryClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        //marshaller.setClassesToBeBound(AddResponse.class);
        marshaller.setPackagesToScan("com.gmfinancial.gmpcp.consumingwebservice.wsdl");
        System.out.println(">>>>>>>>>>>>>> marshaller ====== " + marshaller);
        System.out.println(">>>>>>>>>>>>>> marshaller ====== " + marshaller.supports(AddResponse.class));

        return marshaller;
    }
    @Bean
    public CountryClient countryClient(Jaxb2Marshaller marshaller) {
        CountryClient client = new CountryClient();
        //client.setDefaultUri("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso");
        client.setDefaultUri("http://www.dneonline.com/calculator.asmx");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        //client.getWebServiceTemplate().setMessageSender(sender);
        return client;
    }
}
