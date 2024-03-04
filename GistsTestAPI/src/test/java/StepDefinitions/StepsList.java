package StepDefinitions;

import Data.TestData;
import Utils.ResponseData;
import Utils.RestAssured;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.Map;

public class StepsList extends StepsBase {

      public StepsList(TestData testData, RestAssured connectionProvider, ResponseData responseData)
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

    @Given("I do not have a valid GitHub access token")
    public void checkOwnGistId() {
        this.requestObject = this.connectionProvider.getRequestObjectWithoutToken();
        Assert.assertNotNull(this.requestObject);
    }

    @When("I send a GET request to the 'public' Gist API endpoint")
    public void deleteGist()  {
        this.response= this.connectionProvider.getPublicGists(this.requestObject);

    }

    @And("The response body will be a JSON object containing an array of public gists")
    public void checkPublicResponseData()  {
        this.response= this.connectionProvider.getPublicGists(this.requestObject);

        String data=this.response.jsonPath().getString("");
        Assert.assertTrue(data.length() > 1500);
    }

    @Then("the response status must be 200")
    public void checkResponseStatus200() {
        Assert.assertEquals(this.response.getStatusCode(), 200);
    }

    @When("I send a GET request to the Gist API for the authenticated user's gists")
    public void getAuthenticatedUserGists()  {
        this.response= this.connectionProvider.getPublicGists(this.requestObject);

    }

    @And("the response body will be a JSON object containing an array of the authenticated user's gists")
    public void checkAuthenticatedUserGists()  {
        String data = this.response.jsonPath().getString("");
        Assert.assertTrue(data.contains(this.testData.getGitHubUserName()));
    }

    @When("I make a GET request to the Starred Gists API endpoint")
    public void getStarredGists()  {
        this.response= this.connectionProvider.getStarredGists(this.requestObject);

    }
    @And("the response will list the authenticated user's starred Gists")
    public void getAuthenticatedUserStarredGists()  {
        Assert.assertTrue(this.response.jsonPath().getString("").toString().contains("url"));
    }

    @And("the response should include the creation date and update date")
    public void checkAuthenticatedUserStarredGists()  {
        String data = this.response.jsonPath().getString("");
        Assert.assertTrue(data.contains("created_at"));
        Assert.assertTrue(data.contains("updated_at"));

    }




}
