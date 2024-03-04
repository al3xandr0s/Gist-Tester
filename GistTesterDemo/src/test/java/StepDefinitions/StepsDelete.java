package StepDefinitions;

import Data.TestData;
import Utils.ResponseData;
import Utils.RestAssured;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

public class StepsDelete extends StepsBase {

      public StepsDelete(TestData testData, RestAssured connectionProvider, ResponseData responseData)
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

    @Given("I have a Gist to delete")
    public void checkOwnGistId() {
        Assert.assertNotNull(testData.getGistToDelete());
        this.response = this.connectionProvider.getGistById(this.requestObject, this.testData.getGistToDelete());
    }

    @When("I delete the Gist")
    public void deleteGist()  {

        this.response = this.connectionProvider.deleteGistById(this.requestObject, this.testData.getGistToDelete());
    }

    @Then("the response code should be 204")
    public void checkResponse204( ) {
        Assert.assertEquals(this.response.getStatusCode(), 204);
    }

    @And("the Gist should no longer exist")
    public void checkUpdatedDescription() {
        this.response = this.connectionProvider.getGistById(this.requestObject, this.testData.getGistToDelete() );
        Assert.assertEquals(this.response.getStatusCode(), 404);
    }

}
