import com.shaft.driver.SHAFT;
import features.courierFeatures.Courier;
import objectModel.KhaznaApi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CourierTests {

    private SHAFT.API apiObject;
    private SHAFT.API courierApiObject;
    private Courier courierFeatures;
    private SHAFT.TestData.JSON authData;


    @BeforeClass
    public void prepare(){
        apiObject = new SHAFT.API(KhaznaApi.BaseURI);
        courierFeatures = new Courier(apiObject);
        authData = new SHAFT.TestData.JSON("auth.json");
        String username = authData.getTestData("username");
        String password = authData.getTestData("password");
        KhaznaApi api = new KhaznaApi(apiObject);
        api.login(username,password);
    }

    @Test
    public void verifyCourier(){
        courierFeatures.getCourier();
    }
}
