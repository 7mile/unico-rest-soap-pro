//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.10 时间 04:17:28 PM EST 
//


package au.com.unico.gcd_ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the au.com.unico.gcd_ws package. 
 * <p>An ObjectFactory allows you to programatically 
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

    private final static QName _GetGcdRequest_QNAME = new QName("http://www.unico.com.au/gcd-ws", "getGcdRequest");
    private final static QName _GetGcdSumRequest_QNAME = new QName("http://www.unico.com.au/gcd-ws", "getGcdSumRequest");
    private final static QName _GetGcdListRequest_QNAME = new QName("http://www.unico.com.au/gcd-ws", "getGcdListRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: au.com.unico.gcd_ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetGcdSumResponse }
     * 
     */
    public GetGcdSumResponse createGetGcdSumResponse() {
        return new GetGcdSumResponse();
    }

    /**
     * Create an instance of {@link GetGcdListResponse }
     * 
     */
    public GetGcdListResponse createGetGcdListResponse() {
        return new GetGcdListResponse();
    }

    /**
     * Create an instance of {@link GetGcdResponse }
     * 
     */
    public GetGcdResponse createGetGcdResponse() {
        return new GetGcdResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.unico.com.au/gcd-ws", name = "getGcdRequest")
    public JAXBElement<Object> createGetGcdRequest(Object value) {
        return new JAXBElement<Object>(_GetGcdRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.unico.com.au/gcd-ws", name = "getGcdSumRequest")
    public JAXBElement<Object> createGetGcdSumRequest(Object value) {
        return new JAXBElement<Object>(_GetGcdSumRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.unico.com.au/gcd-ws", name = "getGcdListRequest")
    public JAXBElement<Object> createGetGcdListRequest(Object value) {
        return new JAXBElement<Object>(_GetGcdListRequest_QNAME, Object.class, null, value);
    }

}
