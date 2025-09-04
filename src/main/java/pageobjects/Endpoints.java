package pageobjects;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.Headers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Endpoints {

    private static final String BASE_URL = "https://serverest.dev";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String token;

    // Rotas
    private static final String LOGIN_ROUTE = "/login";
    private static final String USERS_ROUTE = "/usuarios";

    public String getBaseUrl() {
        return BASE_URL;
    }

    // --- LOGIN ---
    public Map<String, Object> login(String email, String password) {
        try {
            Map<String, String> body = new HashMap<>();
            body.put("email", email);
            body.put("password", password);

            String jsonBody = objectMapper.writeValueAsString(body);

            Response response = RestAssured.given()
                    .baseUri(BASE_URL)
                    .contentType("application/json")
                    .body(jsonBody)
                    .post(LOGIN_ROUTE);

            Map<String, Object> responseMap = createResponseMap(response);

            // Salva token se existir
            Map<String, Object> bodyMap = (Map<String, Object>) responseMap.get("body");
            if (bodyMap != null && bodyMap.containsKey("authorization")) {
                this.token = (String) bodyMap.get("authorization");
            }

            return responseMap;

        } catch (Exception e) {
            return createErrorResponseMap(e, 401);
        }
    }

    // --- CREATE USER ---
    public Map<String, Object> createUser(Map<String, Object> userData) {
        try {
            String jsonBody = objectMapper.writeValueAsString(userData);
            Response response = RestAssured.given()
                    .baseUri(BASE_URL)
                    .contentType("application/json")
                    .body(jsonBody)
                    .post(USERS_ROUTE);

            return createResponseMap(response);
        } catch (Exception e) {
            return createErrorResponseMap(e, 500);
        }
    }

    // --- GET ALL USERS ---
    public Map<String, Object> getAllUsers() {
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + this.token)
                .get(USERS_ROUTE);

        return createResponseMap(response);
    }

    // --- GET USER BY ID ---
    public Map<String, Object> getUserById(String id) {
        try {
            Response response = RestAssured.given()
                    .baseUri(BASE_URL)
                    .header("Authorization", "Bearer " + this.token)
                    .get(USERS_ROUTE + "/" + id);

            return createResponseMap(response);
        } catch (Exception e) {
            return createErrorResponseMap(e, 500);
        }
    }

    // --- UPDATE USER BY ID ---
    public Map<String, Object> putChangeUserById(String id, Map<String, Object> userData) {
        try {
            String jsonBody = objectMapper.writeValueAsString(userData);

            Response response = RestAssured.given()
                    .baseUri(BASE_URL)
                    .header("Authorization", "Bearer " + this.token)
                    .contentType("application/json")
                    .body(jsonBody)
                    .put(USERS_ROUTE + "/" + id);

            return createResponseMap(response);
        } catch (Exception e) {
            return createErrorResponseMap(e, 500);
        }
    }

    // --- DELETE USER BY ID ---
    public Map<String, Object> deleteUserById(String id) {
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + this.token)
                .delete(USERS_ROUTE + "/" + id);

        return createResponseMap(response);
    }

    // --- Helpers ---
    private Map<String, Object> createResponseMap(Response response) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", response.getStatusCode());
        try {
            map.put("body", response.getBody().jsonPath().getMap(""));
        } catch (Exception e) {
            map.put("body", new HashMap<>());
        }
        map.put("headers", response.getHeaders().asList());
        return map;
    }

    private Map<String, Object> createErrorResponseMap(Exception e, int statusCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", statusCode);
        map.put("body", Map.of("error", e.getMessage()));
        map.put("headers", new HashMap<>());
        return map;
    }
}
