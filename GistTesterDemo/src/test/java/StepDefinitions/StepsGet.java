package StepDefinitions;

import Data.TestData;
import Utils.ResponseData;
import Utils.RestAssured;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.testng.Assert;

public class StepsGet extends StepsBase {

      public StepsGet(TestData testData, RestAssured connectionProvider, ResponseData responseData)
    {
        super(testData, connectionProvider, responseData);
        this.testData = testData;
        this.connectionProvider = connectionProvider;
        this.responseData = responseData;
        try{
            this.requestObject = this.connectionProvider.getRequestObjectVaildToken();
        }
        catch(Error e){
            System.out.print(e.getMessage());
        }

    }

    @Given("I have the ID of a specific Gist")
    public void checkOwnGistId() {
        Assert.assertNotNull(this.testData.getOwnGistId());
    }
    @When("I make a GET request to the Gist API with the Gist ID")
    public void retrieveGistById() {
        this.response = this.connectionProvider.getGistById(this.requestObject, this.testData.getOwnGistId());

    }

    @Then("the response status code should be 200")
    public void checkResponse200( ) {
        Assert.assertEquals(this.response.getStatusCode(), 200);
    }


    @And("the response should include the Gist owner's login name")
    public void checkOwnerLoginName() {
        Assert.assertNotNull(this.response.jsonPath().getString("owner.login"));

    }


}
