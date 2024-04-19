package page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import setup.Setup;
import util.Util;

public class AbraSuaContaPage extends Util {

    public AbraSuaContaPage(){
        PageFactory.initElements(new AppiumFieldDecorator(Setup.getDriver()), this);
    }

    @AndroidFindBy(id = "br.com.xp.carteira:id/bt_start")
    private MobileElement botaoAbrirConta;

    public AbraSuaContaPage clicarAbrirConta(){
        clicarElemento(botaoAbrirConta);
        return this;
    }
}
