
package com.gmfinancial.gmpcp.consumingwebservice.wsdl;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gmfinancial.gmpcp.consumingwebservice.wsdl package. 
 * <p>An ObjectFactory allows you to programmatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gmfinancial.gmpcp.consumingwebservice.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Add }
     * 
     * @return
     *     the new instance of {@link Add }
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     * @return
     *     the new instance of {@link AddResponse }
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link Subtract }
     * 
     * @return
     *     the new instance of {@link Subtract }
     */
    public Subtract createSubtract() {
        return new Subtract();
    }

    /**
     * Create an instance of {@link SubtractResponse }
     * 
     * @return
     *     the new instance of {@link SubtractResponse }
     */
    public SubtractResponse createSubtractResponse() {
        return new SubtractResponse();
    }

    /**
     * Create an instance of {@link Multiply }
     * 
     * @return
     *     the new instance of {@link Multiply }
     */
    public Multiply createMultiply() {
        return new Multiply();
    }

    /**
     * Create an instance of {@link MultiplyResponse }
     * 
     * @return
     *     the new instance of {@link MultiplyResponse }
     */
    public MultiplyResponse createMultiplyResponse() {
        return new MultiplyResponse();
    }

    /**
     * Create an instance of {@link Divide }
     * 
     * @return
     *     the new instance of {@link Divide }
     */
    public Divide createDivide() {
        return new Divide();
    }

    /**
     * Create an instance of {@link DivideResponse }
     * 
     * @return
     *     the new instance of {@link DivideResponse }
     */
    public DivideResponse createDivideResponse() {
        return new DivideResponse();
    }

    /**
     * Create an instance of {@link NumberToWords }
     * 
     * @return
     *     the new instance of {@link NumberToWords }
     */
    public NumberToWords createNumberToWords() {
        return new NumberToWords();
    }

    /**
     * Create an instance of {@link NumberToWordsResponse }
     * 
     * @return
     *     the new instance of {@link NumberToWordsResponse }
     */
    public NumberToWordsResponse createNumberToWordsResponse() {
        return new NumberToWordsResponse();
    }

    /**
     * Create an instance of {@link NumberToDollars }
     * 
     * @return
     *     the new instance of {@link NumberToDollars }
     */
    public NumberToDollars createNumberToDollars() {
        return new NumberToDollars();
    }

    /**
     * Create an instance of {@link NumberToDollarsResponse }
     * 
     * @return
     *     the new instance of {@link NumberToDollarsResponse }
     */
    public NumberToDollarsResponse createNumberToDollarsResponse() {
        return new NumberToDollarsResponse();
    }

}
