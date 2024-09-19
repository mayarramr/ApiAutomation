package objectModel;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;

public class KhaznaApi {

    private SHAFT.API apiObject;

    public KhaznaApi(SHAFT.API apiObject){
        this.apiObject = apiObject;

    }
    public static final String BaseURI = System.getProperty("BASE_URI");
    private String emailEndpoint = "/admin-auth/email";
    private String passwordEndpoint = "/admin-auth/login";
    public void login(String email , String password) {

        JSONObject emailBody = new JSONObject();
        emailBody.put("email", email);

        JSONObject passwordBody = new JSONObject();
        passwordBody.put("password", password);

        apiObject
                .post(emailEndpoint)
                .setContentType(ContentType.JSON)
                .setRequestBody(emailBody)
                .setTargetStatusCode(200)
                .perform();

        String getToken = apiObject.getResponseJSONValue("body.token");
        System.out.println(getToken);


        apiObject
                .post(passwordEndpoint)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", getToken)
                .setRequestBody(passwordBody)
                .setTargetStatusCode(200).perform();

        String finalToken = apiObject.getResponseJSONValue("body.token");

        apiObject.addHeader("Authorization", finalToken);
    }
}
