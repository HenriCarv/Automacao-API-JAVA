package hooks;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import setup.Setup;

public class Hooks {

    @Before(value = "@mobile")
    public static void tearUp(){
        Setup.start();
    }

    @After(value = "@mobile")
    public static void tearDown(){
        Setup.close();
    }
}
