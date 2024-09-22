import com.shaft.driver.SHAFT;
import features.kchFeatures.KCHcompanies;
import objectModel.KhaznaApi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KchCompaniesTests {
    private SHAFT.API apiObject;
    private SHAFT.TestData.JSON authData;
    private KCHcompanies KCHCompaiesFeatures;



    @BeforeClass
    public void prepare(){
        apiObject = new SHAFT.API(KhaznaApi.BaseURI);
        KCHCompaiesFeatures = new KCHcompanies(apiObject);
        authData = new SHAFT.TestData.JSON("auth.json");
        String username  = authData.getTestData("username");
        String password = authData.getTestData("password");
        KhaznaApi api = new KhaznaApi(apiObject);
        api.login(username,password);
    }

    @Test
    public void verifyKCHCompanies(){
        KCHCompaiesFeatures.getAllKCHCompanies();
    }
}
