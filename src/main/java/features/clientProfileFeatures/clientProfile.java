package features.clientProfileFeatures;

import com.shaft.driver.SHAFT;

public class clientProfile {
private SHAFT.API apiObject;
public clientProfile(SHAFT.API apiObject) {this.apiObject = apiObject;}

    private String clearDeductibleRequestsEndPoint = "/admin-v2/client-profile/requests-view/";

    public void clearDeductibleRequests(String khaznaId, String deductible , String page , String limit){
        apiObject
                .get(clearDeductibleRequestsEndPoint + khaznaId)
                .setUrlArguments("deductible="+deductible+"&"+"page="+page+"&"+"limit="+limit)
                .setTargetStatusCode(200)
                .perform();

    }
}
