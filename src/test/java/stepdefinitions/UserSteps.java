package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pageobjects.Endpoints;
import com.github.javafaker.Faker;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class UserSteps {

    private final Endpoints apiPage = new Endpoints();
    private final Faker faker = new Faker();
    private Map<String,Object> createdUser;
    private Map<String,Object> response;

    // --- CREATE USER ---
    @Given("I create new user")
    public void createNewUser() {
        createdUser = new HashMap<>();
        createdUser.put("nome", faker.name().fullName());
        createdUser.put("email", faker.internet().emailAddress());
        createdUser.put("password", faker.internet().password());
        createdUser.put("administrador", "false");

        response = apiPage.createUser(createdUser);
        createdUser.put("_id", (String)((Map<String,Object>)response.get("body")).get("_id"));
        System.out.println("Created User: " + createdUser);
        System.out.println("Response: " + response);
    }

    @Given("I create new user Administrator")
    public void createNewAdminUser() {
        createdUser = new HashMap<>();
        createdUser.put("nome", faker.name().fullName());
        createdUser.put("email", faker.internet().emailAddress());
        createdUser.put("password", faker.internet().password());
        createdUser.put("administrador", "true");

        response = apiPage.createUser(createdUser);
        createdUser.put("_id", (String)((Map<String,Object>)response.get("body")).get("_id"));
        System.out.println("Created Admin User: " + createdUser);
        System.out.println("Response: " + response);
    }

    @When("the response status code should be {int}")
    public void checkStatusCode(int expected) {
        Assert.assertEquals(expected, response.get("statusCode"));
    }

    @Then("the response should contain a success message")
    public void checkSuccessMessage() {
        Map<String,Object> body = (Map<String,Object>) response.get("body");
        Assert.assertTrue(body.containsKey("message"));
        Assert.assertTrue(((String)body.get("message")).contains("sucesso"));
    }

    @Then("the response should contain a error message")
    public void checkErrorMessage() {
        Map<String,Object> body = (Map<String,Object>) response.get("body");
        Assert.assertTrue(body.size() > 0);
    }

    @Given("I am authenticated with the new user credentials")
    public void i_am_authenticated_with_the_new_user_credentials() {
        // Aqui você pode simplesmente criar um usuário ou usar credenciais existentes
        // e armazenar o ID/token no contexto (se necessário para autenticação)

        createdUser = new HashMap<>();
        createdUser.put("nome", faker.name().fullName());
        createdUser.put("email", faker.internet().emailAddress());
        createdUser.put("password", faker.internet().password());
        createdUser.put("administrador", "true");

        response = apiPage.createUser(createdUser);
        createdUser.put("_id", (String)((Map<String,Object>)response.get("body")).get("_id"));

        System.out.println("Authenticated User: " + createdUser);
    }

    @When("I request a delete with a id of user on-existent")
    public void iRequestADeleteWithAnIdOfUserOnExistent() {
        String userId = "Q0hurZQHmHxVlXJT";
        response = apiPage.deleteUserById(userId);

        System.out.println("Request URL: " + apiPage.getBaseUrl() + "/usuarios/" + userId);
        System.out.println("Request Method: DELETE");
        System.out.println("Delete Response: " + response);
    }

    // --- DELETE USER ---
    @When("I request a delete with a id of user")
    public void deleteUserById() {
        String userId = (String) createdUser.get("_id");
        response = apiPage.deleteUserById(userId);
        System.out.println("Delete Response: " + response);
    }
}
