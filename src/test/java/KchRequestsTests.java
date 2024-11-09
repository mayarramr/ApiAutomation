import com.shaft.driver.SHAFT;
import features.kchFeatures.KCHcompanies;
import features.kchFeatures.KchRequests;
import objectModel.KhaznaApi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KchRequestsTests {
    private SHAFT.API apiObject;
    private SHAFT.TestData.JSON authData;
    private SHAFT.TestData.JSON KchRequestsTests;
    private KchRequests KCHRequestsFeatures;
    String limit;
    String page;
    String sortAttribute;
    String sortDirection;
    String runningStatus;
    String transferredStatus;
    String refNumber;
    String mdpState;
    String payAction;
    String settleAction;
    String rejectAction;

    @BeforeClass
    public void prepare(){
        apiObject = new SHAFT.API(KhaznaApi.BaseURI);
        KCHRequestsFeatures = new KchRequests(apiObject);
        authData = new SHAFT.TestData.JSON("auth.json");
        String username  = authData.getTestData("username");
        String password = authData.getTestData("password");
        KchRequestsTests = new SHAFT.TestData.JSON("/KCH_Data/KchRequestsData.json");
        limit = KchRequestsTests.getTestData("limit");
        sortAttribute = KchRequestsTests.getTestData("sortAttribute");
        sortDirection = KchRequestsTests.getTestData("sortDirection");
        page = KchRequestsTests.getTestData("page");
        refNumber = KchRequestsTests.getTestData("reference_number");
        mdpState = KchRequestsTests.getTestData("mdp_state");
        runningStatus = KchRequestsTests.getTestData("running_status");
        transferredStatus = KchRequestsTests.getTestData("transferred_status");
        payAction = KchRequestsTests.getTestData("pay_action");
        settleAction = KchRequestsTests.getTestData("settle_action");
        rejectAction = KchRequestsTests.getTestData("reject_action");
        KhaznaApi api = new KhaznaApi(apiObject);
        api.login(username,password);
    }

    @Test
    public void verifyExportingKchRequests(){
        KCHRequestsFeatures.exportingRecords();
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyFilterByReqStatusRunning(){
        KCHRequestsFeatures.filterByRequestStatusRunning(page , limit , sortAttribute , sortDirection , runningStatus);
    }

    @Test
    public void verifyPayManualAction(){
        KCHRequestsFeatures.kchPayManualAction(payAction);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyRejectManualAction(){
        KCHRequestsFeatures.kchRejectManualAction(rejectAction);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success");
    }

    @Test
    public void verifyFilterByReqStatusTransferred(){
        KCHRequestsFeatures.filterByRequestStatusTransferred(page , limit , sortAttribute , sortDirection , transferredStatus);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifySettleManualAction(){
        KCHRequestsFeatures.kchSettleManualAction(settleAction);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success");
    }

    @Test
    public void verifySorting(){
        KCHRequestsFeatures.sortingByRequestId(page , limit , sortAttribute , sortDirection);
    }

    @Test
    public void verifyFilterByMdpStatusWaitingTransfer(){
        KCHRequestsFeatures.filterByMdpStatusWaitingTransfer(page , limit , sortAttribute , sortDirection , mdpState);
    }

    @Test
    public void verifyUpdateTransferRefNumber(){
        KCHRequestsFeatures.updateTransferRefNumber(refNumber);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyFilteringByBatchId(){
        KCHRequestsFeatures.filterByBatchId(limit , page, sortAttribute , sortDirection);
        apiObject.assertThatResponse().extractedJsonValue("body.data.data[0].batch_id").isEqualTo("2105").perform();

    }

    @Test
    public void verifyFilteringByRequestId(){
        KCHRequestsFeatures.filterByRequestId(limit , page, sortAttribute , sortDirection);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyFilteringByAutoDeduction() {
        KCHRequestsFeatures.filterByAutoDeduction(limit, page, sortAttribute, sortDirection);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyFilteringByKhaznaId(){
        KCHRequestsFeatures.filterByKhaznaId(limit, page, sortAttribute, sortDirection);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyFilteringByHrCode() {
        KCHRequestsFeatures.filterByHrCode(limit, page, sortAttribute, sortDirection);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyFilteringByNatId() {
        KCHRequestsFeatures.filterByNatId(limit, page, sortAttribute, sortDirection);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyFilteringByUserName() {
        KCHRequestsFeatures.filterByUserName(limit, page, sortAttribute, sortDirection);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyFilteringByMobileNum() {
        KCHRequestsFeatures.filterByMobileNum(limit, page, sortAttribute, sortDirection);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyFilteringByCompName() {
        KCHRequestsFeatures.filterByCompName(limit, page, sortAttribute, sortDirection);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyFilteringByCardUUID() {
        KCHRequestsFeatures.filterByCardUUID(limit, page, sortAttribute, sortDirection);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyFilteringByBankName() {
        KCHRequestsFeatures.filterByBankName(limit, page, sortAttribute, sortDirection);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyFilteringByAccountNumber() {
        KCHRequestsFeatures.filterByAccountNumber(limit, page, sortAttribute, sortDirection);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyFilteringByAccountType() {
        KCHRequestsFeatures.filterByAccountType(limit, page, sortAttribute, sortDirection);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

    @Test
    public void verifyFilteringByRefNumber() {
        KCHRequestsFeatures.filterByRefNumber(limit, page, sortAttribute, sortDirection);
        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
    }

//    @Test
//    public void verifyFilteringByReqDate() {
//        KCHRequestsFeatures.filterByReqDate(limit, page, sortAttribute, sortDirection);
//        apiObject.assertThatResponse().extractedJsonValue("status").isEqualTo("success").perform();
//    }

}
