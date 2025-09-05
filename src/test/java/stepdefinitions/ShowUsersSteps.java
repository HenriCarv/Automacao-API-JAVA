package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageobjects.Endpoints;
import support.TestContext;

import java.util.Map;

public class ShowUsersSteps {

    private final Endpoints apiPage = new Endpoints();
    private final TestContext context;

    public ShowUsersSteps(TestContext context) {
        this.context = context;
    }

    @Given("I try authenticated with user non-existent")
    public void iTryAuthenticatedWithUserNonExistent() {
        Map<String,Object> response = apiPage.login("blabla@blabla.com", "xpto");
        context.setResponse(response);

        System.out.println("Login attempt with non-existent user: " + response);
    }

    @When("I request all users")
    public void iRequestAllUsers() {
        Map<String,Object> response = apiPage.getAllUsers();
        context.setResponse(response);

        System.out.println("Request URL: " + apiPage.getBaseUrl() + "/usuarios");
        System.out.println("Request Method: GET");
        System.out.println("Response: " + response);
    }

    @Then("the response should contain a list of users")
    public void theResponseShouldContainAListOfUsers() {
        Map<String,Object> body = (Map<String,Object>) context.getResponse().get("body");
        Assert.assertTrue("Response does not contain 'usuarios'", body.containsKey("usuarios"));
        Assert.assertTrue("Usuarios is not an array/list",
                body.get("usuarios") instanceof java.util.List);

        System.out.println("Response Body: " + body);
    }

    @When("I request with a id of users")
    public void iRequestWithIdOfUsers() {
        Map<String,Object> user = context.getCreatedUser();
        if (user == null || !user.containsKey("_id")) {
            throw new RuntimeException("No user has been created. Ensure the user creation step ran first.");
        }

        String userId = (String) user.get("_id");
        Map<String,Object> response = apiPage.getUserById(userId);
        context.setResponse(response);

        System.out.println("Request URL: " + apiPage.getBaseUrl() + "/usuarios/" + userId);
        System.out.println("Request Method: GET");
        System.out.println("Response: " + response);
    }

    @Then("the response should contain a user")
    public void theResponseShouldContainAUser() {
        Map<String,Object> body = (Map<String,Object>) context.getResponse().get("body");
        Assert.assertTrue("Response does not contain 'nome'", body.containsKey("nome"));
        Assert.assertTrue("Response does not contain 'email'", body.containsKey("email"));
        Assert.assertTrue("Response does not contain '_id'", body.containsKey("_id"));

        System.out.println("Response Body: " + body);
    }

    @When("I request a user with a non-existent ID")
    public void iRequestAUserWithNonExistentId() {
        String nonExistentId = "nonexistent123456789";
        Map<String,Object> response = apiPage.getUserById(nonExistentId);
        context.setResponse(response);

        System.out.println("Request URL: " + apiPage.getBaseUrl() + "/usuarios/" + nonExistentId);
        System.out.println("Request Method: GET");
        System.out.println("Response: " + response);
    }
}
