package com.example;

import com.gmfinancial.gmpcp.consumingwebservice.wsdl.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/***
 * SOAP Service connector class which uses internal implementation of WebServiceTemplate to invoke the Ally SOAP
 * service. Also provides the interface corresponding to access the operations that Ally web service had exposed.
 */
public class CountryClient extends WebServiceGatewaySupport {

    public AddResponse add(int num1, int num2) {
        Add request = new Add();
        request.setIntA(num1);
        request.setIntB(num2);
        //System.out.println(">>>>>>>>>>>>>> ====== " + request);
        //System.out.println(">>>>>>>>>>>>>> getWebServiceTemplate().getMarshaller() ====== " + getWebServiceTemplate
        // ().getMarshaller().supports(AddResponse.class));
        AddResponse response = (AddResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request, new SoapActionCallback("http://tempuri.org/Add"));
        return response;
    }
}
