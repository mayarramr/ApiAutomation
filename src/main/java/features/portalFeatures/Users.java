package features.portalFeatures;

import com.shaft.driver.SHAFT;

public class Users {

    private SHAFT.API apiObject;
    public Users(SHAFT.API apiObject){
        this.apiObject = apiObject;
    }
    private String getUsersEndpoint = "/admin/khazna/home/entities/user_corporate/column-list/v2";
    public void getAllUsers(){
        apiObject
                .get(getUsersEndpoint)
                .setTargetStatusCode(200)
                .perform();
    }

}
