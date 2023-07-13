package com.example;

import com.gmfinancial.gmpcp.consumingwebservice.wsdl.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.math.BigInteger;

/***
 * SOAP Service connector class which uses internal implementation of WebServiceTemplate to invoke the Ally SOAP
 * service. Also provides the interface corresponding to access the operations that Ally web service had exposed.
 */
public class NumberClient extends WebServiceGatewaySupport {

    public NumberToWordsResponse numberToWords() {
        NumberToWords request = new NumberToWords();
        request.setUbiNum(BigInteger.ONE);
        NumberToWordsResponse response = (NumberToWordsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
        return response;
    }
}
