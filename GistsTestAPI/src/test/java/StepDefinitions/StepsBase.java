package StepDefinitions;

import java.util.HashMap;
import java.util.Map;

import Data.TestData;
import Utils.ResponseData;
import Utils.RestAssured;

import org.json.JSONObject;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

public class StepsBase {

    Response response;
    ResponseData responseData;
    TestData testData;

    RestAssured connectionProvider;

    RequestSpecification requestObject;

    JSONObject jsonParams = new JSONObject();
    JSONObject fileObject = new JSONObject();
    JSONObject fileContent = new JSONObject();
    HashMap<String, String> dataMap = new HashMap<String, String>();
    Map<String, Object> files = new HashMap<>();

    public StepsBase(TestData testData, RestAssured connectionProvider, ResponseData responseData)
    {
        this.testData = testData;
        this.connectionProvider = connectionProvider;
        this.responseData = responseData;
    }

}
