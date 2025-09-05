package support;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private Map<String, Object> createdUser;
    private Map<String, Object> response;

    public Map<String, Object> getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Map<String, Object> createdUser) {
        this.createdUser = createdUser;
    }

    public Map<String, Object> getResponse() {
        return response;
    }

    public void setResponse(Map<String, Object> response) {
        this.response = response;
    }

    public void reset() {
        this.createdUser = new HashMap<>();
        this.response = new HashMap<>();
    }
}
