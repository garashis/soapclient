package com.example;

import com.gmfinancial.gmpcp.consumingwebservice.wsdl.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employees API", version = "2.0", description = "Employees Information"))
public class SoapclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapclientApplication.class, args);
	}

	//@Bean
	CommandLineRunner lookup(CountryClient quoteClient) {
		return args -> {
			String country = "IN";
			System.out.println(">>>>>>>>>>>>>> ====== " + quoteClient);
			//AddResponse response = quoteClient.add(country);
			//System.out.println(">>>>>>>>>>>>>> ====== " + response.getAddResult());
		};
	}
}
