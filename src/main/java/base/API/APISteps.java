package base.API;

import base.model.API.ErrorResponse;
import base.model.API.SuccessResponse;
import org.testng.Assert;

import static base.Constant.Constants.URL_API;

public class APISteps extends BaseAPI {

    public APISteps when_Login(Object user) {
        sendPost(URL_API, user);
        return this;
    }

    public APISteps then_VerifyLoginSuccess() {
        Assert.assertEquals(this.response.getStatusCode(), 200);
        return this;
    }
    public APISteps then_VerifyResponseUser(SuccessResponse successResponse) {
        Assert.assertEquals(successResponse.getBuyer().getId(), 2561);
        Assert.assertEquals(successResponse.getBuyer().getUid(), "thuy+exam5@podfoods.co");
        Assert.assertEquals(successResponse.getBuyer().getEmail(), "thuy+exam5@podfoods.co");
        Assert.assertEquals(successResponse.getBuyer().getProvider(), "email");
        Assert.assertEquals(successResponse.getBuyer().getFirst_name(), "exam");
        Assert.assertEquals(successResponse.getBuyer().getLast_name(), "chicago5");
        Assert.assertEquals(successResponse.getBuyer().getContact_number(), "2342342222");
        Assert.assertEquals(successResponse.getBuyer().getImage(), null);
        Assert.assertEquals(successResponse.getBuyer().isSub_buyer(), false);
        Assert.assertEquals(successResponse.getBuyer().isInvoice_only(), true);
        Assert.assertEquals(successResponse.getBuyer().getStripe_customer_id(), "cus_LEZatn4u2bO01T");
        Assert.assertEquals(successResponse.getBuyer().isDid_agree_to_terms(), true);
        Assert.assertEquals(successResponse.getBuyer().getStore_id(), 2405);
        Assert.assertEquals(successResponse.getBuyer().getStore_name(), "exam store");
        Assert.assertEquals(successResponse.getBuyer().getBuyer_company_id(), 2170);
        Assert.assertEquals(successResponse.getBuyer().getBuyer_company_name(), "exam buyer company");
        return this;
    }
    public APISteps then_VerifyLoginNonSuccess(int statusCode) {
        Assert.assertEquals(this.response.getStatusCode(), statusCode);
        return this;
    }

    public APISteps then_VerifyErrorMessage(ErrorResponse response) {
        Assert.assertEquals(response.getErrors(), "[Invalid login credentials. Please try again or toggle between Vendor or Buyer.]");
        return this;
    }
}
