package features.merchantsFeatures;

import com.shaft.driver.SHAFT;

public class merchantOffers {

    SHAFT.API apiObject;

    public merchantOffers(SHAFT.API apiObject) {
        this.apiObject = apiObject;
    }

    private String exportMerchantsOffersEndPoint = "/admin/offer-management/merchant/export?";
    private String filterBySystemIdEndPoint = "/admin/offer-management/merchant?";

    public void exportMerchantsOffers() {
        apiObject
                .get(exportMerchantsOffersEndPoint)
                .setUrlArguments("sortDirection=" + "DESC&" + "sortAttribute=" + "id&")
                .setTargetStatusCode(200)
                .perform();
    }

    public void filterBySystemId() {
        apiObject
                .get(exportMerchantsOffersEndPoint)
                .setUrlArguments("page=" + "1" + "&" + "limit=" + "20" + "&" + "sortDirection=" + "DESC" + "&" + "sortAttribute=" + "id" + "&" + "id=" + "541")
                .setTargetStatusCode(200)
                .perform();
    }
}
