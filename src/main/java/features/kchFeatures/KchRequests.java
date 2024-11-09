package features.kchFeatures;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.*;

public class KchRequests {
    private SHAFT.API apiObject;

    public KchRequests(SHAFT.API apiObject) {
        this.apiObject = apiObject;
    }

    private String getKchRequestsEndPoint = "/admin/product/requests/kch";
    private String exportingRecordsEndPoint = "/admin/khazna/home/entities/kch/column-list";
    private String PayManualActionEndPoint = "/admin/product/requests/massUpdateRequestsStatus/kch/Paid";
    private String settleManualActionEndPoint = "/admin/product/requests/massUpdateRequestsStatus/kch/Settled";
    private String rejectManualActionEndPoint = "/admin/product/requests/massUpdateRequestsStatus/kch/Rejected";
    private String sortingEndPoint = "/admin/product/requests/kch";
    private String updateTransferRefNumEndPoint = "/admin/product/requests/kch/transfer-reference-number";
    String waitingTransferReqId;
    String runningReqId;
    String transferredReqId;

    public void exportingRecords() {
        apiObject
                .get(exportingRecordsEndPoint)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByMdpStatusWaitingTransfer(String page, String limit, String sortAttribute, String sortDirection, String mdp_state) {
        ArrayList<Object> mdpStatus = new ArrayList<>();
        mdpStatus.add(mdp_state);
        JSONObject filterMdpStatusBody = new JSONObject();
        filterMdpStatusBody.put("page", page);
        filterMdpStatusBody.put("limit", limit);
        filterMdpStatusBody.put("sortAttribute", sortAttribute);
        filterMdpStatusBody.put("sortDirection", sortDirection);
        filterMdpStatusBody.put("mdp_state", mdpStatus);

        apiObject
                .post(getKchRequestsEndPoint)
                .setContentType(ContentType.JSON)
                .setRequestBody(filterMdpStatusBody)
                .setTargetStatusCode(200)
                .perform();

        waitingTransferReqId = apiObject.getResponseJSONValue("body.data.data[0].id");

    }

    public void filterByRequestStatusRunning(String page, String limit, String sortAttribute, String sortDirection, String status) {
        ArrayList<Object> requestState = new ArrayList<>();
        requestState.add(status);
        JSONObject filterReqStatusBody = new JSONObject();
        filterReqStatusBody.put("page", page);
        filterReqStatusBody.put("limit", limit);
        filterReqStatusBody.put("sortAttribute", sortAttribute);
        filterReqStatusBody.put("sortDirection", sortDirection);
        filterReqStatusBody.put("status", requestState);

        apiObject
                .post(getKchRequestsEndPoint)
                .setContentType(ContentType.JSON)
                .setRequestBody(filterReqStatusBody)
                .setTargetStatusCode(200)
                .perform();

        runningReqId = apiObject.getResponseJSONValue("body.data.data[0].id");
    }

    public void kchPayManualAction(String status) {
        JSONObject innerObject = new JSONObject();
        innerObject.put("extra_id", this.runningReqId);
        innerObject.put("status", status);

        ArrayList<Object> requestStatusArray = new ArrayList<>();
        requestStatusArray.add(innerObject);

        JSONObject requestStatusObject = new JSONObject();
        requestStatusObject.put("request_status", requestStatusArray);

        apiObject
                .put(PayManualActionEndPoint)
                .setRequestBody(requestStatusObject)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();

    }

    public void kchRejectManualAction(String status) {
        JSONObject innerObject = new JSONObject();
        innerObject.put("extra_id", this.runningReqId);
        innerObject.put("status", status);

        ArrayList<Object> requestStatusArray = new ArrayList<>();
        requestStatusArray.add(innerObject);

        JSONObject requestStatusObject = new JSONObject();
        requestStatusObject.put("request_status", requestStatusArray);

        apiObject
                .put(rejectManualActionEndPoint)
                .setRequestBody(requestStatusObject)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByRequestStatusTransferred(String page, String limit, String sortAttribute, String sortDirection, String status) {
        ArrayList<Object> requestState = new ArrayList<>();
        requestState.add(status);
        JSONObject filterReqStatusBody = new JSONObject();
        filterReqStatusBody.put("page", page);
        filterReqStatusBody.put("limit", limit);
        filterReqStatusBody.put("sortAttribute", sortAttribute);
        filterReqStatusBody.put("sortDirection", sortDirection);
        filterReqStatusBody.put("status", requestState);

        apiObject
                .post(getKchRequestsEndPoint)
                .setContentType(ContentType.JSON)
                .setRequestBody(filterReqStatusBody)
                .setTargetStatusCode(200)
                .perform();

        transferredReqId = apiObject.getResponseJSONValue("body.data.data[0].id");
    }

    public void kchSettleManualAction(String status) {
        JSONObject innerObject = new JSONObject();
        innerObject.put("extra_id", this.transferredReqId);
        innerObject.put("status", status);

        ArrayList<Object> settleArray = new ArrayList<>();
        settleArray.add(innerObject);

        JSONObject settleRequestBody = new JSONObject();
        settleRequestBody.put("request_status", settleArray);

        apiObject
                .put(settleManualActionEndPoint)
                .setContentType(ContentType.JSON)
                .setRequestBody(settleRequestBody)
                .setTargetStatusCode(200)
                .perform();

    }

    public void sortingByRequestId(String page, String limit, String sortAttribute, String sortDirection) {
        JSONObject sortingBody = new JSONObject();
        sortingBody.put("page", page);
        sortingBody.put("limit", limit);
        sortingBody.put("sortAttribute", sortAttribute);
        sortingBody.put("sortDirection", sortDirection);

        apiObject
                .post(sortingEndPoint)
                .setRequestBody(sortingBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void updateTransferRefNumber(String refNumber) {
        JSONObject transferRefNumBody = new JSONObject();
        transferRefNumBody.put("reference_number", refNumber);
        transferRefNumBody.put("request_id", this.waitingTransferReqId);

        apiObject
                .put(updateTransferRefNumEndPoint)
                .setContentType(ContentType.JSON)
                .setRequestBody(transferRefNumBody)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByBatchId(String limit, String page, String sortAttribute, String sortDirection) {
        JSONObject filterBatchIdBody = new JSONObject();
        filterBatchIdBody.put("limit", limit);
        filterBatchIdBody.put("page", page);
        filterBatchIdBody.put("sortAttribute", sortAttribute);
        filterBatchIdBody.put("sortDirection", sortDirection);
        filterBatchIdBody.put("batch_id", 2105);

        apiObject
                .post(sortingEndPoint)
                .setRequestBody(filterBatchIdBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByRequestId(String limit, String page, String sortAttribute, String sortDirection) {
        JSONObject filterReqIdBody = new JSONObject();
        filterReqIdBody.put("limit", limit);
        filterReqIdBody.put("page", page);
        filterReqIdBody.put("sortAttribute", sortAttribute);
        filterReqIdBody.put("sortDirection", sortDirection);
        filterReqIdBody.put("id", 80169068);

        apiObject
                .post(getKchRequestsEndPoint)
                .setRequestBody(filterReqIdBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByAutoDeduction(String limit, String page, String sortAttribute, String sortDirection) {
        JSONObject filterBatchIdBody = new JSONObject();
        filterBatchIdBody.put("limit", limit);
        filterBatchIdBody.put("page", page);
        filterBatchIdBody.put("sortAttribute", sortAttribute);
        filterBatchIdBody.put("sortDirection", sortDirection);
        filterBatchIdBody.put("is_auto_deduction", "No");

        apiObject
                .post(sortingEndPoint)
                .setRequestBody(filterBatchIdBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByKhaznaId(String limit, String page, String sortAttribute, String sortDirection) {
        JSONObject filterKhaznaIdBody = new JSONObject();
        filterKhaznaIdBody.put("limit", limit);
        filterKhaznaIdBody.put("page", page);
        filterKhaznaIdBody.put("sortAttribute", sortAttribute);
        filterKhaznaIdBody.put("sortDirection", sortDirection);
        filterKhaznaIdBody.put("user_id", 1604536);

        apiObject
                .post(getKchRequestsEndPoint)
                .setRequestBody(filterKhaznaIdBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByHrCode(String limit, String page, String sortAttribute, String sortDirection) {
        JSONObject filterHrCodeBody = new JSONObject();
        filterHrCodeBody.put("limit", limit);
        filterHrCodeBody.put("page", page);
        filterHrCodeBody.put("sortAttribute", sortAttribute);
        filterHrCodeBody.put("sortDirection", sortDirection);
        filterHrCodeBody.put("sender_user_company_reference", 5828570);

        apiObject
                .post(getKchRequestsEndPoint)
                .setRequestBody(filterHrCodeBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByUserName(String limit, String page, String sortAttribute, String sortDirection) {
        JSONObject filterUserNameBody = new JSONObject();
        filterUserNameBody.put("limit", limit);
        filterUserNameBody.put("page", page);
        filterUserNameBody.put("sortAttribute", sortAttribute);
        filterUserNameBody.put("sortDirection", sortDirection);
        filterUserNameBody.put("sender_name", "mock user 3");

        apiObject
                .post(getKchRequestsEndPoint)
                .setRequestBody(filterUserNameBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByNatId(String limit, String page, String sortAttribute, String sortDirection) {
        JSONObject filterNatIdBody = new JSONObject();
        filterNatIdBody.put("limit", limit);
        filterNatIdBody.put("page", page);
        filterNatIdBody.put("sortAttribute", sortAttribute);
        filterNatIdBody.put("sortDirection", sortDirection);
        filterNatIdBody.put("sender_national_id_number", "30001070102731");

        apiObject
                .post(getKchRequestsEndPoint)
                .setRequestBody(filterNatIdBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByMobileNum(String limit, String page, String sortAttribute, String sortDirection) {
        JSONObject filterMobileNumBody = new JSONObject();
        filterMobileNumBody.put("limit", limit);
        filterMobileNumBody.put("page", page);
        filterMobileNumBody.put("sortAttribute", sortAttribute);
        filterMobileNumBody.put("sortDirection", sortDirection);
        filterMobileNumBody.put("sender_mobile", "01009161041");

        apiObject
                .post(getKchRequestsEndPoint)
                .setRequestBody(filterMobileNumBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByCompName(String limit, String page, String sortAttribute, String sortDirection) {
        String compName = "mdp mock";
        ArrayList<Object> compNameArray = new ArrayList<>();
        compNameArray.add(compName);
        JSONObject filterMobileNumBody = new JSONObject();
        filterMobileNumBody.put("limit", limit);
        filterMobileNumBody.put("page", page);
        filterMobileNumBody.put("sortAttribute", sortAttribute);
        filterMobileNumBody.put("sortDirection", sortDirection);
        filterMobileNumBody.put("sender_company_name", compNameArray);

        apiObject
                .post(getKchRequestsEndPoint)
                .setRequestBody(filterMobileNumBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByCardUUID(String limit, String page, String sortAttribute, String sortDirection) {
        JSONObject filterByCardUUIDBody = new JSONObject();
        filterByCardUUIDBody.put("limit", limit);
        filterByCardUUIDBody.put("page", page);
        filterByCardUUIDBody.put("sortAttribute", sortAttribute);
        filterByCardUUIDBody.put("sortDirection", sortDirection);
        filterByCardUUIDBody.put("card_uuid", "300000000007");

        apiObject
                .post(getKchRequestsEndPoint)
                .setRequestBody(filterByCardUUIDBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByBankName(String limit, String page, String sortAttribute, String sortDirection) {
        JSONObject filterByCardUUIDBody = new JSONObject();
        filterByCardUUIDBody.put("limit", limit);
        filterByCardUUIDBody.put("page", page);
        filterByCardUUIDBody.put("sortAttribute", sortAttribute);
        filterByCardUUIDBody.put("sortDirection", sortDirection);
        filterByCardUUIDBody.put("receiver_bank_name", "ADIB");

        apiObject
                .post(getKchRequestsEndPoint)
                .setRequestBody(filterByCardUUIDBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByAccountNumber(String limit, String page, String sortAttribute, String sortDirection) {
        JSONObject filterByAccountNumberBody = new JSONObject();
        filterByAccountNumberBody.put("limit", limit);
        filterByAccountNumberBody.put("page", page);
        filterByAccountNumberBody.put("sortAttribute", sortAttribute);
        filterByAccountNumberBody.put("sortDirection", sortDirection);
        filterByAccountNumberBody.put("receiver_account_number", "79940000007");

        apiObject
                .post(getKchRequestsEndPoint)
                .setRequestBody(filterByAccountNumberBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByAccountType(String limit, String page, String sortAttribute, String sortDirection) {
        String accType = "khazna_card";
        ArrayList<Object> accTypeArray = new ArrayList<>();
        accTypeArray.add(accType);
        JSONObject filterMobileNumBody = new JSONObject();
        filterMobileNumBody.put("limit", limit);
        filterMobileNumBody.put("page", page);
        filterMobileNumBody.put("sortAttribute", sortAttribute);
        filterMobileNumBody.put("sortDirection", sortDirection);
        filterMobileNumBody.put("receiver_account_type", accTypeArray);

        apiObject
                .post(getKchRequestsEndPoint)
                .setRequestBody(filterMobileNumBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByRefNumber(String limit, String page, String sortAttribute, String sortDirection) {
        JSONObject filterByRefNumberBody = new JSONObject();
        filterByRefNumberBody.put("limit", limit);
        filterByRefNumberBody.put("page", page);
        filterByRefNumberBody.put("sortAttribute", sortAttribute);
        filterByRefNumberBody.put("sortDirection", sortDirection);
        filterByRefNumberBody.put("transfer_ref_number", "695296455042");

        apiObject
                .post(getKchRequestsEndPoint)
                .setRequestBody(filterByRefNumberBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }


//    public void filterByReqDate(String limit, String page, String sortAttribute, String sortDirection) {
//        JSONObject filterByReqDateBody = new JSONObject();
//        filterByReqDateBody.put("limit", limit);
//        filterByReqDateBody.put("page", page);
//        filterByReqDateBody.put("sortAttribute", sortAttribute);
//        filterByReqDateBody.put("sortDirection", sortDirection);
//        filterByReqDateBody.put("submission_date", "1730461200000,1730584800000");
//
//        apiObject
//                .post(getKchRequestsEndPoint)
//                .setRequestBody(filterByReqDateBody)
//                .setContentType(ContentType.JSON)
//                .setTargetStatusCode(200)
//                .perform();
//    }

}


