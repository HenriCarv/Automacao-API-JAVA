package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageobjects.Endpoints;

import java.util.Map;

public class ShowUsersSteps {

    private final Endpoints apiPage = new Endpoints();
    private Map<String, Object> createdUser; // opcional, se precisar do usuário criado
    private Map<String, Object> response;

    // --- LOGIN COM USUÁRIO INEXISTENTE ---
    @Given("I try authenticated with user non-existent")
    public void iTryAuthenticatedWithUserNonExistent() {
        response = apiPage.login("blabla@blabla.com", "xpto");
        System.out.println("Login attempt with non-existent user: " + response);
    }

    // --- OBTER TODOS USUÁRIOS ---
    @When("I request all users")
    public void iRequestAllUsers() {
        response = apiPage.getAllUsers();

        System.out.println("Request URL: " + apiPage.getBaseUrl() + "/usuarios");
        System.out.println("Request Method: GET");
        System.out.println("Response: " + response);
    }

    // --- VALIDAR LISTA DE USUÁRIOS ---
    @Then("the response should contain a list of users")
    public void theResponseShouldContainAListOfUsers() {
        Map<String, Object> body = (Map<String, Object>) response.get("body");
        Assert.assertTrue("Response does not contain 'usuarios'", body.containsKey("usuarios"));
        Assert.assertTrue("Usuarios is not an array/list",
                body.get("usuarios") instanceof java.util.List);

        System.out.println("Response Body: " + body);
    }

    // --- OBTER USUÁRIO POR ID ---
    @When("I request with a id of users")
    public void iRequestWithIdOfUsers() {
        if (createdUser == null || !createdUser.containsKey("_id")) {
            throw new RuntimeException("No user has been created. Ensure the user creation step ran first.");
        }

        String userId = (String) createdUser.get("_id");
        response = apiPage.getUserById(userId);

        System.out.println("Request URL: " + apiPage.getBaseUrl() + "/usuarios/" + userId);
        System.out.println("Request Method: GET");
        System.out.println("Response: " + response);
    }

    @Then("the response should contain a user")
    public void theResponseShouldContainAUser() {
        Map<String, Object> body = (Map<String, Object>) response.get("body");
        Assert.assertTrue("Response does not contain 'nome'", body.containsKey("nome"));
        Assert.assertTrue("Response does not contain 'email'", body.containsKey("email"));
        Assert.assertTrue("Response does not contain '_id'", body.containsKey("_id"));

        System.out.println("Response Body: " + body);
    }

    // --- OBTER USUÁRIO COM ID INEXISTENTE ---
    @When("I request a user with a non-existent ID")
    public void iRequestAUserWithNonExistentId() {
        String nonExistentId = "nonexistent123456789";
        response = apiPage.getUserById(nonExistentId);

        System.out.println("Request URL: " + apiPage.getBaseUrl() + "/usuarios/" + nonExistentId);
        System.out.println("Request Method: GET");
        System.out.println("Response: " + response);
    }

    // --- Métodos auxiliares ---
    public void setCreatedUser(Map<String, Object> user) {
        this.createdUser = user;
    }

    public Map<String, Object> getResponse() {
        return response;
    }
}
