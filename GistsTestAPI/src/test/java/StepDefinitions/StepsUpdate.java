package StepDefinitions;

import Data.TestData;
import Utils.ResponseData;
import Utils.RestAssured;
import org.testng.Assert;
import org.json.JSONObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

public class StepsUpdate extends StepsBase{

    public StepsUpdate(TestData testData, RestAssured connectionProvider, ResponseData responseData)
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

    @Given("I have a Gist I can edit")
    public void checkOwnEditId() {
        Assert.assertNotNull(this.testData.getUpdateGistId());
        this.response = this.connectionProvider.getGistById(this.requestObject, this.testData.getUpdateGistId());
    }

    @When("I update the description of the Gist to <{string}>")
    public void updateGistDescription(String newDescription) {

        this.dataMap.clear();
        this.jsonParams.put("description", newDescription);
        this.response = this.connectionProvider.updateGistById(this.requestObject, this.testData.getOwnGistId(), this.jsonParams.toString());

        //Store for checking value later
        this.dataMap.put("updatedDescription",newDescription);
    }

    @Then("the response code should be 200")
    public void checkResponse200( ) {
        Assert.assertEquals(this.response.getStatusCode(), 200);
    }

    @And("the Gist should show the updated description")
    public void checkUpdatedDescription() {
        this.response = this.connectionProvider.getGistById(this.requestObject, this.testData.getOwnGistId() );
        Assert.assertEquals(this.response.jsonPath().getString("description"), this.dataMap.get("updatedDescription"));
    }



    @When("I update the filename of the Gist to <{string}>")
    public void updateGistFilename(String newFilename) {
        this.fileContent = new JSONObject();
        this.fileObject = new JSONObject();
        this.jsonParams = new JSONObject();

        String gistFilename = this.responseData.getFirstGistFilename(this.response.body().asString());

        this.dataMap.clear();
        this.dataMap.put("updateGistId", this.response.jsonPath().getString("id"));
        this.dataMap.put("updateFile", gistFilename);
        this.dataMap.put("updatedFilename", newFilename);

        this.fileContent.put("filename", newFilename);
        this.fileObject.put(gistFilename, this.fileContent);
        this.jsonParams.put("files", this.fileObject);

        this.response = this.connectionProvider.updateGistById(this.requestObject, this.testData.getUpdateGistId(), this.jsonParams.toString());
    }

    @And("the Gist should show the updated filename")
    public void checkUpdatedFilename() {
        this.response = this.connectionProvider.getGistById(this.requestObject,  this.testData.getUpdateGistId() );
        Assert.assertEquals(this.response.jsonPath().getString( this.responseData.getFilesFilenameJsonPath(this.dataMap.get("updatedFilename"))), this.dataMap.get("updatedFilename"));
    }

    @When("I update the content of the Gist to <{string}>")
    public void updateGistContentToNull(String newContent) {
        this.fileContent = new JSONObject();
        this.fileObject = new JSONObject();
        this.jsonParams = new JSONObject();

        String gistFilename = this.responseData.getFirstGistFilename(this.response.body().asString());

        this.fileContent.put("content",newContent);
        this.fileObject.put(gistFilename, this.fileContent);
        this.jsonParams.put("files", this.fileObject);

        this.response = this.connectionProvider.updateGistById(this.requestObject, this.testData.getOwnGistId(), this.jsonParams.toString());
    }

    @When("I update the content of the Gist to empty")
    public void updateGistContentToNull() {
        this.jsonParams = new JSONObject();
        this.jsonParams.put("public", "no");
        this.response = this.connectionProvider.updateGistById(this.requestObject, this.testData.getOwnGistId(), this.jsonParams.toString());
    }

    @And("the Gist should show the updated content")
    public void checkUpdatedContent() {
        this.response = this.connectionProvider.getGistById(this.requestObject, this.testData.getOwnGistId() );
        Assert.assertEquals(this.response.jsonPath().getString(this.responseData.getFilesContentJsonPath( this.dataMap.get("updateFile"))), this.dataMap.get("updatedContent"));
    }

    @Then("I should receive response code 422")
    public void checkResponseCode422() {
        Assert.assertEquals(this.response.getStatusCode(), 422);
    }

    @And("the error message should include <{string}>")
    public void theMessageShouldInclude(String msg) {
        Assert.assertTrue(this.response.jsonPath().get("message").toString().contains(msg));
    }
}