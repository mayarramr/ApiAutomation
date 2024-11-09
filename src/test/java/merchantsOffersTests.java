import com.shaft.driver.SHAFT;
import features.merchantsFeatures.merchantOffers;
import objectModel.KhaznaApi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class merchantsOffersTests {

    private SHAFT.API apiObject;
    private SHAFT.TestData.JSON merchantsOffersData;
    private SHAFT.TestData.JSON authData;
    private merchantOffers merchantsOffersFeatures;
    String sortDirection;
    String sortAttribute;



    @BeforeClass
    public void prepare(){
        apiObject = new SHAFT.API(KhaznaApi.BaseURI);
        merchantsOffersFeatures = new merchantOffers(apiObject);
        authData = new SHAFT.TestData.JSON("auth.json");
        String email = authData.getTestData("username");
        String password = authData.getTestData("password");
        merchantsOffersData = new SHAFT.TestData.JSON("/merchantsData/merchantsOffersData.json");
        sortDirection = merchantsOffersData.getTestData("sortDirection");
        sortAttribute = merchantsOffersData.getTestData("sortAttribute");
        KhaznaApi api = new KhaznaApi(apiObject);
        api.login(email,password);
    }

    @Test
    public void verifyExportMerchantsOffers(){
        merchantsOffersFeatures.exportMerchantsOffers();
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success");
    }

    @Test
    public void verifyFilterBySystemId() {
        merchantsOffersFeatures.filterBySystemId();
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success");
    }
}
