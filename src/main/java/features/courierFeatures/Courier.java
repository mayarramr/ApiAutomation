package features.courierFeatures;

import com.shaft.driver.SHAFT;

public class Courier {

    private SHAFT.API apiObject;

    public Courier (SHAFT.API apiObject){
        this.apiObject = apiObject;
    }

    public static final String Courier_BaseURI = System.getProperty("Courier_BaseURI");
    private String courierEndpoint = "admin/khazna/courier/user/nat-id/inquiry?lang=ar";

    public void getCourier(){
        apiObject
                .get(courierEndpoint)
                .setTargetStatusCode(200)
                .perform();
    }
}
