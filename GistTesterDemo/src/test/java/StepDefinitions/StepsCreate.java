package StepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Data.TestData;
import Utils.ResponseData;
import Utils.RestAssured;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;
import org.json.JSONObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.restassured.specification.RequestSpecification;


public class StepsCreate extends StepsBase {

    public StepsCreate(TestData testData, RestAssured connectionProvider, ResponseData responseData) {
        super(testData, connectionProvider, responseData);
        this.testData = testData;
        this.connectionProvider = connectionProvider;
        this.responseData = responseData;
    }
    @Given("I have a valid Github access token")
    public void setupValidTokenRequest() {
        this.requestObject = this.connectionProvider.getRequestObjectVaildToken();
        Assert.assertNotNull(this.requestObject);
    }

    @When("I create a new public Gist with the following details:")
    public void createPublicGistWithDetails(DataTable table) {
        List<List<String>> data = table.asLists(String.class);

        this.fileContent.put("content", data.get(1).get(1));
        this.fileObject.put(data.get(1).get(0), this.fileContent);

        this.jsonParams.put("description", data.get(1).get(2));
        this.jsonParams.put("public", true);
        this.jsonParams.put("files", this.fileObject);

        this.response = this.connectionProvider.createGistWithParams(this.requestObject, this.jsonParams.toString());

        this.dataMap.clear();
        this.dataMap.put("filename", data.get(1).get(0));
        this.dataMap.put("content", data.get(1).get(1));
        this.dataMap.put("description", data.get(1).get(2));
    }



    @When("I create a new private Gist with the following details:")
    public void createPrivateGistWithDetails(DataTable table) {
        List<List<String>> data = table.asLists(String.class);

        // Create files
        this.files.put(data.get(1).get(0), new HashMap<String, String>() {{
            put("content", data.get(1).get(1));
        }});
        this.files.put(data.get(2).get(0), new HashMap<String, String>() {{
            put("content", data.get(2).get(1));
        }});

        this.jsonParams.put("public", false);
        this.jsonParams.put("files", this.files);

        this.response = this.connectionProvider.createGistWithParams(this.requestObject, this.jsonParams.toString());
    }

    @Then("the response code should be 201")
    public void checkResponse201( ) {
        Assert.assertEquals(this.response.getStatusCode(), 201);
        this.dataMap.put("gistId", this.response.jsonPath().getString("id"));
    }

    @Then("the private Gist details should match the input details")
    public void checkInputMatchingPrivateGist() {
        Assert.assertEquals(this.response.jsonPath().getString("public"), "false");

        for (Map.Entry<String, Object> entry : files.entrySet()) {
            String filename = entry.getKey();
            String content = ((HashMap) entry.getValue()).get("content").toString();
            Assert.assertEquals(this.response.jsonPath().getString(this.responseData.getFilesFilenameJsonPath(filename)), filename);
            Assert.assertEquals(this.response.jsonPath().getString(this.responseData.getFilesContentJsonPath(filename)), content);
        }
    }

    @Then("the Gist details should match the input details")
    public void checkInputMatching() {
        Assert.assertEquals(this.response.jsonPath().getString("public"), "true");
        Assert.assertEquals(this.response.jsonPath().getString("description"), this.dataMap.get("description"));
        Assert.assertEquals(this.response.jsonPath().getString(responseData.getFilesFilenameJsonPath(this.dataMap.get("filename"))), this.dataMap.get("filename"));
    }

    @Then("the Gist should be retrievable by its id")
    public void retrieveGistById() {
        Assert.assertNotNull(this.dataMap.get("gistId"));
        this.response = this.connectionProvider.getGistById(this.requestObject, this.dataMap.get("gistId"));
        Assert.assertEquals(this.response.jsonPath().get("id"), this.dataMap.get("gistId"));
    }


    @When("I create a new Gist with an empty file")
    public void createGistWithNameAsContent() {
        this.fileContent = new JSONObject();
        this.jsonParams = new JSONObject();
        String nullValue = null;
        this.fileContent.put("content", nullValue);
        this.fileObject.put("TEST.md", this.fileContent);
        this.jsonParams.put("files", this.fileObject);
        this.response = this.connectionProvider.createGistWithParams(this.requestObject,  this.jsonParams.toString());
    }
    @Then("the response code should be 422")
    public void checkResponse422() {
      Assert.assertEquals(this.response.getStatusCode(), 422);
    }

    @And("the response message should include <{string}>")
    public void theMessageShouldInclude(String msg) {
        Assert.assertTrue(this.response.jsonPath().get("message").toString().contains(msg));
    }
}
