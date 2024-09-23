import com.shaft.driver.SHAFT;
import features.portalFeatures.Savings;
import objectModel.KhaznaApi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SavingsTests {

    private SHAFT.API apiObject;
    SHAFT.TestData.JSON authData;
    SHAFT.TestData.JSON savingsData;
    private Savings savingsFeature;
    String id;
    String limit;
    String page;
    String sortAttribute;
    String sortDirection;

    @BeforeClass
    public void prepare() {
        apiObject = new SHAFT.API(KhaznaApi.BaseURI);
        authData = new SHAFT.TestData.JSON("auth.json");
        String username = authData.getTestData("username");
        String password = authData.getTestData("password");
        savingsFeature = new Savings(apiObject);
        savingsData = new SHAFT.TestData.JSON("savings.json");
        id = savingsData.getTestData("id");
        limit = savingsData.getTestData("limit");
        page = savingsData.getTestData("page");
        sortAttribute = savingsData.getTestData("sortAttribute");
        sortDirection = savingsData.getTestData("sortDirection");
        KhaznaApi api = new KhaznaApi(apiObject);
        api.login(username, password);
    }

    @Test
    public void verifySavings(){
        savingsFeature.getSavings();
    }

    @Test
    public void verifyFilteringSavings(){
        savingsFeature.filterSavings( limit , page , sortAttribute , sortDirection);
    }

    @Test
    public  void verifyFilteringById(){
        savingsFeature.filterSavingsById(id , limit , page , sortAttribute , sortDirection);
    }



}
