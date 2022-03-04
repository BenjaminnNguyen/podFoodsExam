package tests;

import base.API.APISteps;
import base.API.BaseAPI;
import base.model.API.ErrorResponse;
import base.model.API.SuccessResponse;
import base.model.API.UserInfor;
import org.testng.annotations.Test;

import static base.Constant.Constants.*;

public class ApiTests extends BaseAPI {
    APISteps apiSteps = new APISteps();

    @Test
    public void TestLoginSuccsess(){
        UserInfor user = new UserInfor(EMAIL,PASSWORD);
        SuccessResponse successResponse = (SuccessResponse) apiSteps.when_Login(user)
                .then_VerifyLoginSuccess()
                .saveResponseObject(SuccessResponse.class);
        apiSteps.then_VerifyResponseUser(successResponse);
    }
    @Test
    public void TestLoginNonSuccsess(){
        UserInfor user = new UserInfor(EMAIL2,PASSWORD2);
        ErrorResponse errorResponse = (ErrorResponse) apiSteps.when_Login(user)
                .then_VerifyLoginNonSuccess(422)
                .saveResponseObject(ErrorResponse.class);
        apiSteps.then_VerifyErrorMessage(errorResponse);

    }
}
