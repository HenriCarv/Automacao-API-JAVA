package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Setup {

    private static AndroidDriver<MobileElement> mobileDriver;

    public static void start(){

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", getSerialNumberDevice());
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability("platformVersion", getVersionAndroidDevice());
        capabilities.setCapability("appPackage", "com.salesforce.chatter");
        capabilities.setCapability("appActivity", "com.salesforce.auth.ChatterLoginActivity");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("newCommandTimeout", 200000);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        try{
            mobileDriver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            mobileDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }catch (MalformedURLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getSerialNumberDevice() {
        String s = null;
        try {
            Process p = Runtime.getRuntime().exec("adb shell getprop | grep ro.boot.serialno");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            s = stdInput.readLine();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return s.trim().replace("[ro.boot.serialno]: [", "").replace("]", "");
    }

    public static String getVersionAndroidDevice() {
        String s = null;
        try {
            Process p = Runtime.getRuntime().exec("adb shell getprop | grep ro.build.version.release");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            s = stdInput.readLine();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return s.trim().replace("[ro.build.version.release]: [", "").replace("]", "");
    }

    public static AppiumDriver<MobileElement> getDriver(){
        if(mobileDriver == null){
            start();
        }
        return mobileDriver;
    }

    public static void close(){
        mobileDriver.quit();
    }
}
