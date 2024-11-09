import com.shaft.driver.SHAFT;
import features.courierFeatures.Courier;
import objectModel.KhaznaApi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CourierTests {

    private SHAFT.API apiObject;
    private Courier courierFeatures;
    private SHAFT.TestData.JSON authData;
    private SHAFT.TestData.JSON courierData;
    String frontImage;
    String backImage;
    String companyId;
    String fullName;
    String hrCode;
    String nationalID;
    String mobileNumber;
    String natBackImage;
    String natFrontImage;

    String firstName;
    String lastName;
    String houseNumber;
    String streetName;
    String job;
    String natIdExpiryDate;


    @BeforeClass
    public void prepare() {
        apiObject = new SHAFT.API(KhaznaApi.BaseURI);
        courierFeatures = new Courier(apiObject);
        authData = new SHAFT.TestData.JSON("auth.json");
        String username = authData.getTestData("username");
        String password = authData.getTestData("password");
        courierData = new SHAFT.TestData.JSON("courierData.json");
        fullName = courierData.getTestData("full_name");
        nationalID = courierData.getTestData("nat_id");
        companyId = courierData.getTestData("company_id");
        hrCode = courierData.getTestData("hr_code");
        mobileNumber = courierData.getTestData("mobile_num");
        frontImage = courierData.getTestData("natid_image_front");
        backImage = courierData.getTestData("natid_image_back");
        natBackImage = System.getProperty("user.dir") + courierData.getTestData("natid_image_back");
        natFrontImage = System.getProperty("user.dir") + courierData.getTestData("natid_image_front");
        firstName = courierData.getTestData("natid_image_back");
        lastName = courierData.getTestData("natid_image_back");
        job = courierData.getTestData("natid_image_back");
        streetName = courierData.getTestData("natid_image_back");
        houseNumber = courierData.getTestData("natid_image_back");
        natIdExpiryDate = courierData.getTestData("natid_image_back");
        KhaznaApi api = new KhaznaApi(apiObject);
        api.login(username, password);
    }

    @Test
    public void verifyCourier() {
        courierFeatures.getCourier();
    }

    @Test
    public void verifyCourierUser() {
        courierFeatures.addCourierUser(fullName, mobileNumber, companyId ,nationalID, natBackImage ,  natFrontImage);
    }

    @Test
    public void verifyUserInfo() {
        courierFeatures.userInfo(firstName , lastName , houseNumber , streetName , job , natIdExpiryDate);
    }
}
