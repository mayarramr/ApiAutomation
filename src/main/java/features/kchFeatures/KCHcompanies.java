package features.kchFeatures;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class KCHcompanies {
    private SHAFT.API apiObject;

    public KCHcompanies(SHAFT.API apiObject) {
        this.apiObject = apiObject;
    }

    private String getAllKCHCompaniesEndPoint = "/admin-v2/kch/company/column-list";
    private String getSpecificCompanyEndpoint = "/admin-v2/kch/company/";
    private String filterKchCompaniesByStatusEndPoint = "/admin-v2/kch/company/batches/";
    private String changeAutoDeductionServiceEndPoint = "/admin/khazna/company/services/";
    private String compressKchBatchEndPoint = "/admin/khazna/upload/compressed?entity_name=";
    private String validateKchBatchEndPoint = "/admin-v2/kch/validate-upload/";
    private String confirmKchBatchEndPoint = "/admin-v2/kch/confirm-upload?";
    private String getCreditLogsEndPoint = "/admin-v2/kch/company/credit-log/";
    boolean batchId2087Found = false;


    public void getAllKCHCompanies() {
        apiObject
                .get(getAllKCHCompaniesEndPoint)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void getSpecificCompany(String companyId) {
        apiObject
                .get(getSpecificCompanyEndpoint + companyId)
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterByStatus(String companyId, String limit, String page, String sortAttribute, String sortDirection, String status) {
        apiObject
                .get(filterKchCompaniesByStatusEndPoint + companyId + "?limit=" + limit +
                        "&page=" + page +
                        "&sortAttribute=" + sortAttribute +
                        "&sortDirection=" + sortDirection +
                        "&status=" + status)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();

    }

    public void changeAutoDeductionService(String companyId, boolean autoDeduction) {
        JSONObject autoDeductionBody = new JSONObject();
        JSONObject kchBody = new JSONObject();
        kchBody.put("auto_deduction", autoDeduction);
        autoDeductionBody.put("kch", kchBody);
        apiObject
                .put(changeAutoDeductionServiceEndPoint + companyId)
                .setRequestBody(autoDeductionBody)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void compressKchBatch(String kchBatch, String companyId, String payrollTime) {
        List<List<Object>> parameters = Arrays.asList(Arrays.asList("file", new File(kchBatch)));
        apiObject
                .post(compressKchBatchEndPoint)
                .setParameters(parameters, RestActions.ParametersType.FORM)
                .setContentType(ContentType.MULTIPART)
                .setTargetStatusCode(201)
                .perform();

        String fileUri = apiObject.getResponseJSONValue("body.url");
        JSONObject kchInnerObject = new JSONObject();
        kchInnerObject.put("type", "text/csv");
        kchInnerObject.put("url", fileUri);
        ArrayList<Object> validateKchArray = new ArrayList<>();
        validateKchArray.add(kchInnerObject);
        JSONObject validateKchObject = new JSONObject();
        validateKchObject.put("files", validateKchArray);
        apiObject
                .post(validateKchBatchEndPoint + companyId + "/" + payrollTime)
                .setRequestBody(validateKchObject)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();

        String validatedBatchId = apiObject.getResponseJSONValue("body.summary.batchId");
        apiObject
                .post(confirmKchBatchEndPoint + "companyId=" + companyId + "&" + "batchId=" + validatedBatchId)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

    public void getCreditLogs(String companyId, String limit, String page, String sortAttribute, String sortDirection, int batchId) {

        Response response = apiObject
                .get(getCreditLogsEndPoint + companyId)
                .setUrlArguments("?limit=" + limit + "&page=" + page + "&sortAttribute=" + sortAttribute + "&sortDirection=" + sortDirection)
                .setTargetStatusCode(200)
                .perform();

        List<Map<String, Object>> dataList = response.jsonPath().getList("body.data");
        for (Map<String, Object> log : dataList) {
            if (log.get("batchId").equals(batchId)) {
                batchId2087Found = true;
                System.out.println(log);
            }
        }
    }
}


