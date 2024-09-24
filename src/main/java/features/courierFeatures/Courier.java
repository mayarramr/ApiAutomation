package features.courierFeatures;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;

public class Courier {

    private SHAFT.API apiObject;

    public Courier(SHAFT.API apiObject) {
        this.apiObject = apiObject;
    }

    private String courierEndpoint = "/admin/khazna/courier/companies?lang=ar";
    private String courierUserInfoEndpoint = "/admin/khazna/courier/user/1604077/card/inquiry?lang=ar";


    public void getCourier() {
        apiObject
                .get(courierEndpoint)
                .setTargetStatusCode(200)
                .perform();
    }

    public void addCourierUser(String companyId , String fullName , String hrCode , String nationalId , String natIdBackImage , String natIdFrontImage) {
        JSONObject userCourierData = new JSONObject();
        userCourierData.put("company_id", companyId);
        userCourierData.put("full_name", fullName);
        userCourierData.put("hr_code", hrCode);
        userCourierData.put("nat_id", nationalId);
        userCourierData.put("natid_image_back", natIdBackImage);
        userCourierData.put("natid_image_front", natIdFrontImage);

    }

    public void userInfo(String firstName , String lastName , String houseNo , String streetName , String job , String natIdExpiryDate){
        JSONObject userCourierData = new JSONObject();
        userCourierData.put("first_name", firstName);
        userCourierData.put("last_name", lastName);
        userCourierData.put("house_no", houseNo);
        userCourierData.put("street_name", streetName);
        userCourierData.put("job", job);
        userCourierData.put("natid_expiry_date", natIdExpiryDate);
        apiObject
                .post(courierUserInfoEndpoint)
                .setContentType(ContentType.JSON)
                .setRequestBody(userCourierData)
                .setTargetStatusCode(200)
                .perform();
    }

}



