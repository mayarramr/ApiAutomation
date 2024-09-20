import com.shaft.driver.SHAFT;
import features.portalFeatures.Users;
import objectModel.KhaznaApi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class getUsersTest {


    private SHAFT.API apiObject;
    private SHAFT.TestData.JSON authData;
    private Users usersFeatures;

    @BeforeClass
    public void prepare() {
        authData = new SHAFT.TestData.JSON("auth.json");
        String username = authData.getTestData("username");
        String password = authData.getTestData("password");
        apiObject = new SHAFT.API(KhaznaApi.BaseURI);
        KhaznaApi api = new KhaznaApi(apiObject);
        usersFeatures = new Users(apiObject);
        api.login(username, password);
    }

    @Test
    public void verifyE2EScenario() {
        usersFeatures.getAllUsers();
    }

//    @Test
//    public void getAllTransfers(){
//        apiObject = new SHAFT.API(KhaznaApi.BaseURI);
//        KhaznaApi api = new KhaznaApi(apiObject);
//        api.login("mayaramrsayedhassan@gmail.com" , "bWF5YXJhbXIxODEw");
//        apiObject
//                .get(getTransfersEndpoint)
//                .setTargetStatusCode(200)
//                .perform();
//    }
}
