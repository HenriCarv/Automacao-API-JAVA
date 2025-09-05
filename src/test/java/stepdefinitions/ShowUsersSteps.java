package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageobjects.Endpoints;
import support.TestContext;
import support.AllureHelper;

import java.util.Map;

public class ShowUsersSteps {

    private final Endpoints apiPage = new Endpoints();
    private final TestContext context;

    public ShowUsersSteps(TestContext context) {
        this.context = context;
    }

    // --- LOGIN COM USUÁRIO INEXISTENTE ---
    @Given("I try authenticated with user non-existent")
    public void iTryAuthenticatedWithUserNonExistent() {
        Map<String,Object> response = apiPage.login("blabla@blabla.com", "xpto");
        context.setResponse(response);

        logRequestAndResponse(apiPage.getBaseUrl() + "/login", "POST", null, response);
    }

    // --- OBTER TODOS OS USUÁRIOS ---
    @When("I request all users")
    public void iRequestAllUsers() {
        Map<String,Object> response = apiPage.getAllUsers();
        context.setResponse(response);

        logRequestAndResponse(apiPage.getBaseUrl() + "/usuarios", "GET", null, response);
    }

    @Then("the response should contain a list of users")
    public void theResponseShouldContainAListOfUsers() {
        Map<String,Object> body = (Map<String,Object>) context.getResponse().get("body");
        Assert.assertTrue("Response does not contain 'usuarios'", body.containsKey("usuarios"));
        Assert.assertTrue("Usuarios is not an array/list",
                body.get("usuarios") instanceof java.util.List);

        System.out.println("Validated response contains list of users.");
        AllureHelper.attachJson("Response Body - List Users", body.toString());
    }

    // --- OBTER USUÁRIO POR ID ---
    @When("I request with a id of users")
    public void iRequestWithIdOfUsers() {
        Map<String,Object> user = context.getCreatedUser();
        if (user == null || !user.containsKey("_id")) {
            throw new RuntimeException("No user has been created. Ensure the user creation step ran first.");
        }

        String userId = (String) user.get("_id");
        Map<String,Object> response = apiPage.getUserById(userId);
        context.setResponse(response);

        logRequestAndResponse(apiPage.getBaseUrl() + "/usuarios/" + userId, "GET", null, response);
    }

    @Then("the response should contain a user")
    public void theResponseShouldContainAUser() {
        Map<String,Object> body = (Map<String,Object>) context.getResponse().get("body");
        Assert.assertTrue("Response does not contain 'nome'", body.containsKey("nome"));
        Assert.assertTrue("Response does not contain 'email'", body.containsKey("email"));
        Assert.assertTrue("Response does not contain '_id'", body.containsKey("_id"));

        System.out.println("Validated response contains user details.");
        AllureHelper.attachJson("Response Body - User", body.toString());
    }

    // --- OBTER USUÁRIO POR ID NÃO EXISTENTE ---
    @When("I request a user with a non-existent ID")
    public void iRequestAUserWithNonExistentId() {
        String nonExistentId = "nonexistent123456789";
        Map<String,Object> response = apiPage.getUserById(nonExistentId);
        context.setResponse(response);

        logRequestAndResponse(apiPage.getBaseUrl() + "/usuarios/" + nonExistentId, "GET", null, response);
    }

    // --- HELPER PARA LOGAR REQUEST E RESPONSE ---
    private void logRequestAndResponse(String url, String method, Object payload, Map<String,Object> response) {
        System.out.println("=== API Request ===");
        System.out.println("URL: " + url);
        System.out.println("Method: " + method);
        if (payload != null) {
            System.out.println("Payload: " + payload);
            AllureHelper.attachJson("Request Payload", payload.toString());
        }
        System.out.println("--- Response ---");
        System.out.println("Status Code: " + response.get("statusCode"));
        System.out.println("Body: " + response.get("body"));
        System.out.println("Headers: " + response.get("headers"));
        System.out.println("==================");

        AllureHelper.attachText("Request - " + method + " " + url, method + " " + url);
        AllureHelper.attachJson("Response - " + url, response.toString());
    }
}
