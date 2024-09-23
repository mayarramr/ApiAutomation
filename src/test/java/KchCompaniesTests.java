import com.shaft.driver.SHAFT;
import features.kchFeatures.KCHcompanies;
import objectModel.KhaznaApi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KchCompaniesTests {
    private SHAFT.API apiObject;
    private SHAFT.TestData.JSON authData;
    private SHAFT.TestData.JSON KchData;
    private KCHcompanies KCHCompaiesFeatures;
    private String id;

    @BeforeClass
    public void prepare(){
        apiObject = new SHAFT.API(KhaznaApi.BaseURI);
        KCHCompaiesFeatures = new KCHcompanies(apiObject);
        authData = new SHAFT.TestData.JSON("auth.json");
        String username  = authData.getTestData("username");
        String password = authData.getTestData("password");
        KchData = new SHAFT.TestData.JSON("/KCH_Data/KchCompaniesData.json");
        id = KchData.getTestData("id");
        KhaznaApi api = new KhaznaApi(apiObject);
        api.login(username,password);
    }

//    @Test
//    public void verifyKCHCompanies(){
//        KCHCompaiesFeatures.getAllKCHCompanies();
//    }
//
//    @Test
//    public void verifySpecificCompany(){
//        KCHCompaiesFeatures.getSpecificCompany(id);
//    }
}
