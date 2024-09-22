package features.kchFeatures;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
public class KCHcompanies {
    private SHAFT.API apiObject;

    public KCHcompanies(SHAFT.API apiObject) {
        this.apiObject = apiObject;
    }

    private String getAllKCHCompaniesEndPoint = "/admin-v2/kch/company/column-list";
    private String getSpecificCompanyEndpoint = "/admin-v2/kch/company";

    public void getAllKCHCompanies(){
        apiObject
                .get(getAllKCHCompaniesEndPoint)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

//    public void getSpecificCompany(){
//        apiObject
//                .get(getSpecificCompanyEndpoint)
//                .setParameters()
//                .setTargetStatusCode(200)
//                .perform();
//    }
    

}
