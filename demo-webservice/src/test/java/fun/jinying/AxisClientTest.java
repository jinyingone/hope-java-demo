package fun.jinying;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * @author jy
 * @date 2020/5/29
 */
public class AxisClientTest {

    public static void main(String[] args) throws ServiceException, RemoteException {
        //webService访问地址
        String url = "http://localhost:8080/ws/countries.wsdl";
        //创建服务
        Service service = new Service();
        //创建调用句柄
        Call call = (Call) service.createCall();
        call.setUseSOAPAction(true);
        //设置请求地址
        call.setTargetEndpointAddress(url);
        /**
         * 设置调用的方法和方法的命名空间；
         * 当然null也可以，因为本身它就没有设置命名空间，一般方法的命名空间是
         * 包名倒写组成，如com.hoo.service,ns=http://service.hoo.com
         */
        call.setOperationName(new QName("http://localhost:8080", "getCountry"));

        String result = (String) call.invoke(new Object[]{});
        System.out.println(result);
    }
}
