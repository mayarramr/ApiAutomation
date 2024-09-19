import com.shaft.driver.SHAFT;
import objectModel.KhaznaApi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class getUsersTest {
    private String getUsersEndpoint = "/admin/khazna/home/entities/user_corporate/column-list/v2";

    private SHAFT.API apiObject;

    @Test
    public void getAllUsers(){
        apiObject = new SHAFT.API(KhaznaApi.BaseURI);
        KhaznaApi api = new KhaznaApi(apiObject);
        api.login("mayaramrsayedhassan@gmail.com" , "bWF5YXJhbXIxODEw");
        apiObject
                .get(getUsersEndpoint)
                .setTargetStatusCode(403)
                .perform();
    }
}
