
## How to add dependencies

1. Locate the pom.xml file in the project structure. If it's not already present, create it by right-clicking on the project directory, selecting New -> File, and naming the file pom.xml.
2. Open the pom.xml file and navigate to the <dependencies> section.
3. To add a new dependency, you can either type the code manually or use IntelliJ's built-in dependency management tools.
4. To add a dependency manually, you'll need to know the <groupId>, <artifactId>, and <version> of the dependency you want to add. Here's some of the dependencies used in this project and the structure of the file :

```bash
<dependencies>
        <!-- https://mvnrepository.com/artifact/org.json/json -->
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20230227</version>
        </dependency>
        <dependency>
            <groupId>io.github.cdimascio</groupId>
            <artifactId>dotenv-java</artifactId>
            <version>2.3.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>7.11.1</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-testng -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-testng</artifactId>
            <version>7.11.1</version>
        </dependency>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>5.3.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-path</artifactId>
            <version>5.3.0</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.14.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-picocontainer -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-picocontainer</artifactId>
            <version>7.11.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    
```
5. To use IntelliJ's built-in dependency management tools, right-click on the pom.xml file and select Maven -> Add Dependency.
6. In the search bar that appears, type in the name of the dependency you want to add and click Search.
7. Select the appropriate dependency from the search results and click OK.
The <groupId>, <artifactId>, and <version> elements for the dependency will automatically be added to the pom.xml file.