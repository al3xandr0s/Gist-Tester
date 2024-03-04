package Utils;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import io.github.cdimascio.dotenv.Dotenv;

public class RestAssured {
    private Dotenv dotenv = Dotenv.configure().load();
    private Response response;
    private RequestSpecification request;

    // Reusable request object for authenticated connection
    public RequestSpecification getRequestObjectVaildToken() {
        request = given();
        request.contentType("application/json");
        request.baseUri(dotenv.get("BASE_URL"));
        request.header("Authorization", "Token " + dotenv.get("VALID_ACCESS_TOKEN"));
        return request;
    }

    // Reusable request object for unauthenticated connection
    public RequestSpecification getRequestObjectWithoutToken() {
        request = given();
        request.contentType("application/json");
        request.baseUri(dotenv.get("BASE_URL"));
        return request;
    }

    // GET Request to /gists API endpoint
    public Response getPublicGists(RequestSpecification requestObject) {
        return requestObject.body("").when().get("/gists");
    }

    // GET Request to /gists API endpoint with Gist ID parameter
    public Response getGistById(RequestSpecification requestObject, String id) {
        return requestObject.body(id).when().get("/gists/" + id);
    }

    // GET Request to /gists/starred API endpoint to list starred Gists
    public Response getStarredGists(RequestSpecification requestObject) {
        return requestObject.body("").when().get("/gists/starred");
    }

    // GET Request to /rate_limit API endpoint to get status
    public Response getRateLimitAPI(RequestSpecification requestObject) {
        return requestObject.body("").when().get("/rate_limit");
    }

    // POST Request to /gists API endpoint to create a Gist with parameters
    public Response createGistWithParams(RequestSpecification requestObject, String requestBody) {
        return requestObject.body(requestBody).when().post("/gists");
    }

    // PATCH Request to /gists API endpoint to update a Gist ID with parameters
    public Response updateGistById(RequestSpecification requestObject, String id, String requestBody) {
        return requestObject.body(requestBody).when().patch("/gists/" + id);
    }

    // DELETE Request to /gists API endpoint to delete a Gist by ID
    public Response deleteGistById(RequestSpecification requestObject, String id) {
        return requestObject
                .body("")
                .when()
                .delete("/gists/" + id);
    }


    // Function to reach rate limit for authenticated user
    public Response sendMoreThanXRequestsIn1hourWithToken(int requestCount, int hour) {
        int intervalMs = 50;
        for (int i = 0; i < requestCount; i++) {
            response = given()
                    .header("Authorization", "Token " + dotenv.get("VALID_ACCESS_TOKEN"))
                    .get(dotenv.get("BASE_URL") + "/gists");
            try {
                Thread.sleep(intervalMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    // Function to reach rate limit for unauthenticated user
    public Response sendMoreThanXRequestsIn1hourWithoutToken(int requestCount, int hour) {
        int intervalMs = 20;
        for (int i = 0; i < requestCount+1; i++) {
            response = given()
                    .get(dotenv.get("BASE_URL") + "/gists");
            try {
                Thread.sleep(intervalMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}
