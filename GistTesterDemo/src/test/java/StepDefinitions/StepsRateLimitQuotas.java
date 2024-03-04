package StepDefinitions;

import Data.TestData;
import Utils.ResponseData;
import Utils.RestAssured;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class StepsRateLimitQuotas extends StepsBase {

    public StepsRateLimitQuotas(TestData testData, RestAssured connectionProvider, ResponseData responseData) {
        super(testData, connectionProvider, responseData);
        this.testData = testData;
        this.connectionProvider = connectionProvider;
        this.responseData = responseData;


        try {
            this.requestObject = this.connectionProvider.getRequestObjectVaildToken();
        } catch (Error e) {
            System.out.print(e.getMessage());
        }

    }

    @When("I send a GET request to the Github API rate limit endpoint")
    public void getRateLimit() {
        this.response = this.connectionProvider.getRateLimitAPI(this.requestObject);
    }

    @Then("the response should include the rate limit status")
    public void checkRateLimitResponse() {
        Assert.assertNotNull(this.response.jsonPath().getString("resources"));
        Assert.assertNotNull(this.response.jsonPath().getString("resources.core"));
    }

    @When("I send more than {int} authenticated requests to the Github API in {int} hour")
    public void sendMoreThanXRequestsIn1hourWithToken(int requestCount, int hour) {
        this.response = this.connectionProvider.sendMoreThanXRequestsIn1hourWithToken(requestCount,hour);
    }

    @When("I send more than {int} unauthenticated requests to the Github API in {int} hour")
    public void sendMoreThanXRequestsIn1hourNoToken(int requestCount, int hour) {
        this.response = this.connectionProvider.sendMoreThanXRequestsIn1hourWithoutToken(requestCount,hour);
    }

    @And("the response body should contain the error message <{string}>")
    public void verifyErrorMessage(String msg) {
        Assert.assertTrue(this.response.jsonPath().getString("message").contains(msg));
    }

    @Then("the response status code will be 200")
    public void checkResponse200() {
        Assert.assertEquals(this.response.getStatusCode(), 200);
    }

    @Then("the response code should be 403")
    public void checkResponse403() {
        Assert.assertEquals(this.response.getStatusCode(), 403);
    }

}

