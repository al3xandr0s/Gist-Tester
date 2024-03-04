# Github Gist Automation Test

This is a set of automated API tests built for testing the non-functional attributes and functional behavior of Gists according to Github Gists API.




## Prerequisite Setup
Before you proceed, ensure that you have the following installed on your machine and add any environment variables needed:
- Java Development Kit (JDK) version 8 or later
- IntelliJ IDEA Community or Ultimate edition
- Apache Maven 


## Tools and Libraries used in the framework

- Cucumber for writing BDD style feature files
- Gherkin language, used by the Cucumber testing tool
- RestAssured for making Rest API calls and validating responses
- TestNG for running tests
- Cucumber jvm used for reporting
- PicoContainer used for Dependency Injection
- Maven for building and managing dependencies

## What you need before running the tests:

1. Create an account and obtain an API token from Github. 
2. With your account create 3 gists and notedown their IDs
3. Configure the .env file as shown below:

.env 
```bash
    BASE_URL = https://api.github.com
    VALID_ACCESS_TOKEN = REPLACE_WITH_YOUR_GITHUB_TOKEN

    OWN_GIST_ID = REPLACE_WITH_YOUR_GIST_ID_1
    UPDATE_GIST_ID = REPLACE_WITH_YOUR_GIST_ID_2
    DELETE_GIST_ID = REPLACE_WITH_YOUR_GIST_ID_3
```

## Running the tests via CMD:

- use command prompt to navigate to the project folder
- use ``` mvn clean test``` to run the test suite
- use ``` mvn clean test -Dcucumber.filter.tags="@group1"``` to run the group1 of test suite.
- use ``` mvn clean test -Dcucumber.filter.tags="@group2"``` to run the group2 of test suite. 
- Once the test is completed you can find the report in the target folder of the project.  (cucumber-reports.html)

@group1 tests include the below scenarios :
Get - Create - Update - Delete - List Gists


@group2 tests include the below scenarios :
Rate Limiting Quotas for Authenticated/Unauthenticated user and take longer to complete. 

## Running the test via Eclipse/IntelliJ IDe:

- Open the project in your IDE
- From the build menu select "Build Project"
- Navigate to src/test/java/Runners 
- Right click on TestRunner class and select "Run TestRunner"

## Running the test in Dockerized environment 

- Open a terminal in the root directory of the project.
- Execute the following command  ``` docker build .```
- After the build process all tests will run automatically.

## Future improvements

- Create shared test context class and objects for reusability and code maintenability.
- Extract functions which test response code into seperate class.
- Use POJO classes to simplify working with data.
- Use service mocking where appropriate, eg when testing rate limits.
- Automate account and Gists creation as part of the prerequisite setup.
- Serve automated reports at the end of test, at the moment they are just generated at the target directory.
- Extend code and setup docker correctly to use environment variables.
