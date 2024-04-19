package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.Setup;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofSeconds;


public class Util {

    private static WebDriverWait wait;
    private final static int timeOut = 60;
    private static AppiumDriver<MobileElement> mobileDriver;

    public Util(){
        mobileDriver = Setup.getDriver();
        wait = new WebDriverWait(mobileDriver, timeOut);
    }


    protected void aguardarElementoHabilitado(MobileElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void clicarElemento(MobileElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void clicarXpath(String xpath){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        element.click();
    }

    protected void inserirTextoElemento(MobileElement element, String texto){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(texto);
    }

    protected void inserirTextoXpath(String xpath, String texto){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        element.sendKeys(texto);
    }

    protected String retornarTextoElemento(MobileElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public static void moverAteElemento(MobileElement elemento) {

        Dimension size = mobileDriver.manage().window().getSize();
        TouchAction swipe = new TouchAction(mobileDriver);

        int largura = size.width / 2;
        int alturaInicio = size.height / 2;
        int alturaFinal = 10;

        for (int i = 0; i < 5; i++) {
            try {
                if (elemento.isDisplayed()) {
                    break;
                }
            } catch (Exception e) {
                swipe.press(PointOption.point(largura, alturaInicio)).waitAction(waitOptions(ofSeconds(2)))
                        .moveTo(PointOption.point(largura, alturaFinal)).release();
                swipe.perform();
            }
        }
    }

    public static void swipeDown() {

        try {

            Dimension size = mobileDriver.manage().window().getSize();
            TouchAction swipe = new TouchAction(mobileDriver);

            int largura = size.width / 2;
            int alturaInicio = size.height - 10;
            int alturaFinal = 10;

            swipe.longPress(PointOption.point(largura, alturaInicio)).waitAction(waitOptions(ofSeconds(2)))
                    .moveTo(PointOption.point(largura, alturaFinal)).release();
            swipe.perform();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
