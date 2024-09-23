package features.portalFeatures;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;

public class SalaryAdvanceRequests {

    private SHAFT.API apiObject;
    public SalaryAdvanceRequests(SHAFT.API apiObject){
        this.apiObject = apiObject;
    }

    private String salaryAdvanceEndpoint = "/admin/requests/salary-advance";

    public void getAllSalaryAdvanceRequests(String page,String limit,String sortDirection,String sortAttribute){
        JSONObject salaryAdvanceReq = new JSONObject();
        salaryAdvanceReq.put("page" , page);
        salaryAdvanceReq.put("limit" , limit);
        salaryAdvanceReq.put("sortDirection" , sortDirection);
        salaryAdvanceReq.put("sortAttribute" , sortAttribute);
        apiObject
                .post(salaryAdvanceEndpoint)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .setRequestBody(salaryAdvanceReq)
                .perform();
    }

}
