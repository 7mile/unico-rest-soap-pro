package au.com.unico.integration.endpoint;

import au.com.unico.Application;
import au.com.unico.dao.GcdRepository;
import au.com.unico.domain.Gcd;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;
import java.io.IOException;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.*;

/**
 * Created by xiaofei on 2017/12/11.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public class GcdEndpointTest {
    @Autowired
    private ApplicationContext applicationContext;
    private Resource xsdSchema = new ClassPathResource("gcd.xsd");

    private MockWebServiceClient mockClient;
    private GcdRepository gcdRepository;

    @Before
    public void createClient() throws Exception {
        mockClient = MockWebServiceClient.createClient(applicationContext);
        gcdRepository = applicationContext.getBean(GcdRepository.class);
    }

    @Test
    public void testGetGcdListRequestWithEmptyGcdList() throws IOException {
        gcdRepository.deleteAll();
        Source requestPayload = new StringSource(
                "<ns2:getGcdListRequest xmlns:ns2=\"http://www.unico.com.au/gcd-ws\">" +
                        "</ns2:getGcdListRequest>");

        Source responsePayload = new StringSource(
                "<ns2:getGcdListResponse xmlns:ns2=\"http://www.unico.com.au/gcd-ws\"><ns2:gcdList/></ns2:getGcdListResponse>");

        mockClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(noFault())
                .andExpect(payload(responsePayload))
                .andExpect(validPayload(xsdSchema));
    }

    @Test
    public void testGetGcdRequestWithEmptyGcd() throws IOException {
        gcdRepository.deleteAll();
        Source requestPayload = new StringSource(
                "<ns2:getGcdRequest xmlns:ns2=\"http://www.unico.com.au/gcd-ws\">" +
                        "</ns2:getGcdRequest>");

        Source responsePayload = new StringSource(
                "<ns2:getGcdResponse xmlns:ns2=\"http://www.unico.com.au/gcd-ws\"><ns2:gcd>0</ns2:gcd></ns2:getGcdResponse>");

        mockClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(noFault())
                .andExpect(payload(responsePayload))
                .andExpect(validPayload(xsdSchema));
    }

    @Test
    public void testGetGcdSumRequestWithEmptyGcdSum() throws IOException {
        gcdRepository.deleteAll();
        Source requestPayload = new StringSource(
                "<ns2:getGcdSumRequest xmlns:ns2=\"http://www.unico.com.au/gcd-ws\">" +
                        "</ns2:getGcdSumRequest>");

        Source responsePayload = new StringSource(
                "<ns2:getGcdSumResponse xmlns:ns2=\"http://www.unico.com.au/gcd-ws\"><ns2:gcdSum>0</ns2:gcdSum></ns2:getGcdSumResponse>");

        mockClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(noFault())
                .andExpect(payload(responsePayload))
                .andExpect(validPayload(xsdSchema));
    }

    @Test
    public void testGetGcdListRequestWithGcdList() throws IOException {
        gcdRepository.deleteAll();
        gcdRepository.save(new Gcd(10, 5, 5));
        gcdRepository.save(new Gcd(17, 34, 17));
        Source requestPayload = new StringSource(
                "<ns2:getGcdListRequest xmlns:ns2=\"http://www.unico.com.au/gcd-ws\">" +
                        "</ns2:getGcdListRequest>");

        Source responsePayload = new StringSource(
                "<ns2:getGcdListResponse xmlns:ns2=\"http://www.unico.com.au/gcd-ws\"><ns2:gcdList>5 17</ns2:gcdList></ns2:getGcdListResponse>");

        mockClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(noFault())
                .andExpect(payload(responsePayload))
                .andExpect(validPayload(xsdSchema));
    }


    @Test
    public void testGetGcdSumRequestWithGcdSum() throws IOException {
        gcdRepository.deleteAll();
        gcdRepository.save(new Gcd(10, 5, 5));
        gcdRepository.save(new Gcd(17, 34, 17));
        Source requestPayload = new StringSource(
                "<ns2:getGcdSumRequest xmlns:ns2=\"http://www.unico.com.au/gcd-ws\">" +
                        "</ns2:getGcdSumRequest>");

        Source responsePayload = new StringSource(
                "<ns2:getGcdSumResponse xmlns:ns2=\"http://www.unico.com.au/gcd-ws\"><ns2:gcdSum>22</ns2:gcdSum></ns2:getGcdSumResponse>");

        mockClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(noFault())
                .andExpect(payload(responsePayload))
                .andExpect(validPayload(xsdSchema));
    }
}
