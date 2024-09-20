import com.shaft.driver.SHAFT;
import features.portalFeatures.salaryAdvanceRequests;
import objectModel.KhaznaApi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class salaryAdvanceTests {

    private SHAFT.API apiObject;
    private salaryAdvanceRequests salaryAdvanceFeatures;
    SHAFT.TestData.JSON authData;
    SHAFT.TestData.JSON SAdata;

    String page;
    String limit;
    String sortDirection;
    String sortAttribute;

    @BeforeClass
    public void prepare() {
        apiObject = new SHAFT.API(KhaznaApi.BaseURI);
        authData = new SHAFT.TestData.JSON("auth.json");
        String username = authData.getTestData("username");
        String password = authData.getTestData("password");
        SAdata = new SHAFT.TestData.JSON("salaryAdvanceData.json");
        page = SAdata.getTestData("page");
        limit = SAdata.getTestData("limit");
        sortDirection = SAdata.getTestData("sortDirection");
        sortAttribute = SAdata.getTestData("sortAttribute");
        KhaznaApi api = new KhaznaApi(apiObject);
        api.login(username, password);
        salaryAdvanceFeatures = new salaryAdvanceRequests(apiObject);
    }

    @Test
    public void salaryAdvanceTest() {
        salaryAdvanceFeatures.getAllSalaryAdvanceRequests(page, limit, sortDirection, sortAttribute);
    }


}
