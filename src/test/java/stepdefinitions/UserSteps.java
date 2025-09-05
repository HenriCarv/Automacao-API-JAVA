package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pageobjects.Endpoints;
import com.github.javafaker.Faker;
import org.junit.Assert;
import support.TestContext;
import support.AllureHelper;

import java.util.HashMap;
import java.util.Map;

public class UserSteps {

    private final Endpoints apiPage = new Endpoints();
    private final Faker faker = new Faker();
    private final TestContext context;

    public UserSteps(TestContext context) {
        this.context = context;
    }

    // --- CREATE USER ---
    @Given("I create new user")
    public void createNewUser() {
        Map<String,Object> user = new HashMap<>();
        user.put("nome", faker.name().fullName());
        user.put("email", faker.internet().emailAddress());
        user.put("password", faker.internet().password());
        user.put("administrador", "false");

        Map<String,Object> response = apiPage.createUser(user);
        user.put("_id", (String)((Map<String,Object>)response.get("body")).get("_id"));

        context.setCreatedUser(user);
        context.setResponse(response);

        System.out.println("Created User: " + user);
        System.out.println("Response: " + response);

        AllureHelper.attachJson("Created User", user.toString());
        AllureHelper.attachJson("Response", response.toString());
    }

    @Given("I create new user Administrator")
    public void createNewAdminUser() {
        Map<String,Object> user = new HashMap<>();
        user.put("nome", faker.name().fullName());
        user.put("email", faker.internet().emailAddress());
        user.put("password", faker.internet().password());
        user.put("administrador", "true");

        Map<String,Object> response = apiPage.createUser(user);
        user.put("_id", (String)((Map<String,Object>)response.get("body")).get("_id"));

        context.setCreatedUser(user);
        context.setResponse(response);

        System.out.println("Created Admin User: " + user);
        System.out.println("Response: " + response);

        AllureHelper.attachJson("Created Admin User", user.toString());
        AllureHelper.attachJson("Response", response.toString());
    }

    @When("the response status code should be {int}")
    public void checkStatusCode(int expected) {
        Assert.assertEquals(expected, context.getResponse().get("statusCode"));
        AllureHelper.attachText("Status Code", "Expected: " + expected + " | Actual: " + context.getResponse().get("statusCode"));
    }

    @Then("the response should contain a success message")
    public void checkSuccessMessage() {
        Map<String,Object> body = (Map<String,Object>) context.getResponse().get("body");
        Assert.assertTrue(body.containsKey("message"));
        Assert.assertTrue(((String)body.get("message")).toLowerCase().contains("sucesso"));

        AllureHelper.attachJson("Response Body - Success Message", body.toString());
    }

    @Then("the response should contain a error message")
    public void checkErrorMessage() {
        Map<String,Object> body = (Map<String,Object>) context.getResponse().get("body");
        Assert.assertTrue(body.size() > 0);

        AllureHelper.attachJson("Response Body - Error Message", body.toString());
    }

    @Given("I am authenticated with the new user credentials")
    public void iAmAuthenticatedWithTheNewUserCredentials() {
        Map<String,Object> user = context.getCreatedUser();
        if (user == null) {
            throw new RuntimeException("No user available in context. Ensure user creation step ran.");
        }
        context.setCreatedUser(user);

        System.out.println("Authenticated User: " + user);
        AllureHelper.attachJson("Authenticated User", user.toString());
    }

    @When("I request a delete with a id of user on-existent")
    public void iRequestADeleteWithAnIdOfUserOnExistent() {
        String userId = "Q0hurZQHmHxVlXJT";
        Map<String,Object> response = apiPage.deleteUserById(userId);
        context.setResponse(response);

        System.out.println("Request URL: " + apiPage.getBaseUrl() + "/usuarios/" + userId);
        System.out.println("Request Method: DELETE");
        System.out.println("Delete Response: " + response);

        AllureHelper.attachJson("Response - DELETE Non-existent User", response.toString());
    }

    @When("I request a delete with a id of user")
    public void deleteUserById() {
        Map<String,Object> user = context.getCreatedUser();
        if (user == null || !user.containsKey("_id")) {
            throw new RuntimeException("No user has been created. Ensure the user creation step ran first.");
        }

        String userId = (String) user.get("_id");
        Map<String,Object> response = apiPage.deleteUserById(userId);
        context.setResponse(response);

        System.out.println("Delete Response: " + response);
        AllureHelper.attachJson("Response - DELETE User", response.toString());
    }
}
