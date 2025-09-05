package support;

import io.qameta.allure.Allure;

import java.nio.charset.StandardCharsets;

public class AllureHelper {

    public static void attachJson(String name, String json) {
        Allure.addAttachment(name, "application/json", json, ".json");
    }

    public static void attachText(String name, String text) {
        Allure.addAttachment(name, "text/plain", text, ".txt");
    }
}