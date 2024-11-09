import com.shaft.driver.SHAFT;
import features.kchFeatures.KCHcompanies;
import objectModel.KhaznaApi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class KchCompaniesTests {
    private SHAFT.API apiObject;
    private SHAFT.TestData.JSON authData;
    private SHAFT.TestData.JSON KchData;
    private KCHcompanies KCHCompaiesFeatures;
    private String compId;
    String limit;
    String page;
    String sortAttribute;
    String sortDirection;
    String status;
    String batchId;
    boolean autoDeduction;
    String KchFile;
    String payrollTime;


    @BeforeClass
    public void prepare(){
        apiObject = new SHAFT.API(KhaznaApi.BaseURI);
        KCHCompaiesFeatures = new KCHcompanies(apiObject);
        authData = new SHAFT.TestData.JSON("auth.json");
        String username  = authData.getTestData("username");
        String password = authData.getTestData("password");
        KchData = new SHAFT.TestData.JSON("/KCH_Data/KchCompaniesData.json");
        compId = KchData.getTestData("compId");
        limit = KchData.getTestData("limit");
        page = KchData.getTestData("page");
        batchId = KchData.getTestData("batchId");
        sortAttribute = KchData.getTestData("sortAttribute");
        sortDirection = KchData.getTestData("sortDirection");
        payrollTime = KchData.getTestData("payrollTime");
        autoDeduction = Boolean.parseBoolean(KchData.getTestData("auto_deduction"));
        status = KchData.getTestData("status");
        KchFile = System.getProperty("user.dir") + KchData.getTestData("KchFile");
        KhaznaApi api = new KhaznaApi(apiObject);
        api.login(username,password);
    }

    @Test
    public void verifyKCHCompanies(){
        KCHCompaiesFeatures.getAllKCHCompanies();
    }

    @Test
    public void verifySpecificCompany(){
        KCHCompaiesFeatures.getSpecificCompany(compId);
    }

    @Test
    public void verifyFilterKchByStatus(){
        KCHCompaiesFeatures.filterByStatus(compId, limit, page, sortAttribute, sortDirection, status);
    }

    @Test
    public void updateAutoDeductionService(){
        KCHCompaiesFeatures.changeAutoDeductionService(compId , Boolean.parseBoolean(String.valueOf(autoDeduction)));
    }

    @Test
    public void compressKchBatch(){
        KCHCompaiesFeatures.compressKchBatch(KchFile , compId , payrollTime);
    }

    @Test
    public void getCreditLogs(){
        KCHCompaiesFeatures.getCreditLogs(compId, limit, page, sortAttribute, sortDirection, Integer.parseInt(batchId));

    }
}
