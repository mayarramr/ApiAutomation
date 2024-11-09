import com.shaft.driver.SHAFT;
import features.clientProfileFeatures.clientProfile;
import objectModel.KhaznaApi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class clientProfileTests {

    private SHAFT.API apiObject;
    private SHAFT.TestData.JSON clientProfileData;
    private SHAFT.TestData.JSON authData;
    private clientProfile clientProfileFeatures;
    String khaznaId;
    String deductible;
    String page;
    String limit;

    @BeforeClass
    public void prepare(){
        apiObject = new SHAFT.API(KhaznaApi.BaseURI);
        clientProfileFeatures = new clientProfile(apiObject);
        authData = new SHAFT.TestData.JSON("auth.json");
        String username  = authData.getTestData("username");
        String password = authData.getTestData("password");
        clientProfileData = new SHAFT.TestData.JSON("clientProfileData.json");
        khaznaId = clientProfileData.getTestData("khaznaId");
        deductible = clientProfileData.getTestData("deductible");
        page = clientProfileData.getTestData("page");
        limit = clientProfileData.getTestData("limit");
        KhaznaApi api = new KhaznaApi(apiObject);
        api.login(username,password);
    }

    @Test
    public void clearDeductibleRequests(){
        clientProfileFeatures.clearDeductibleRequests(khaznaId,deductible,page,limit);
    }

}
