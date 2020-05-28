package fun.jinying;

import fun.jinying.ws.country.GetCountryRequest;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * @author jy
 * @date 2020/5/27
 */

public class CxfClientTest {
    public static void main(String[] args) throws Exception {
        JaxWsDynamicClientFactory dcflient = JaxWsDynamicClientFactory.newInstance();

        Client client = dcflient.createClient("http://localhost:8080/ws/countries.wsdl");

        GetCountryRequest request = new GetCountryRequest();
        request.setName("Spain");
        Object[] objects = client.invoke("getCountry", request);
        System.out.println("*******" + objects[0].toString());
    }
}
