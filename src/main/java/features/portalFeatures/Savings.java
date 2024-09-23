package features.portalFeatures;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import objectModel.KhaznaApi;
import org.json.simple.JSONObject;

public class Savings {
    private SHAFT.API apiObject;

    public Savings (SHAFT.API apiObject){
        this.apiObject = apiObject;
    }

    private String SavingsEndpoint = "/admin/khazna/home/entities/saving_requests/column-list/v2";
    private String filterSavingsEndpoint = "/admin/requests/saving";


    public void getSavings(){
        apiObject
                .get(SavingsEndpoint)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterSavings(String limit  , String page , String sortDirection , String sortAttribute){
        JSONObject savingsBody = new JSONObject();
        savingsBody.put("limit" , limit);
        savingsBody.put("page" , page);
        savingsBody.put("sortAttribute" , sortAttribute);
        savingsBody.put("sortDirection" , sortDirection);
        apiObject
                .post(filterSavingsEndpoint)
                .setRequestBody(savingsBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterSavingsById(String id , String limit  , String page , String sortDirection , String sortAttribute){
        JSONObject filterByIdBody = new JSONObject();
        filterByIdBody.put("id" , id);
        filterByIdBody.put("limit" , limit);
        filterByIdBody.put("page" , page);
        filterByIdBody.put("sortAttribute" , sortAttribute);
        filterByIdBody.put("sortDirection" , sortDirection);
        apiObject
                .post(filterSavingsEndpoint)
                .setRequestBody(filterByIdBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();

    }
}
