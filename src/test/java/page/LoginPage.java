package page;

import cucumber.api.java.bs.A;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import setup.Setup;
import sun.rmi.runtime.Log;
import util.Util;

public class LoginPage extends Util {

    public LoginPage(){
        PageFactory.initElements(new AppiumFieldDecorator(Setup.getDriver()), this);
    }

    @AndroidFindBy(id = "br.com.xp.carteira:id/imageViewXP")
    private MobileElement imagemXP;

    @AndroidFindBy(id = "br.com.xp.carteira:id/buttonLoginActivityNewAccount")
    private MobileElement botaoAbrirConta;

    @AndroidFindBy(id = "br.com.xp.carteira:id/editTextLoginActivityCpf")
    private MobileElement campoNumeroConta;

    @AndroidFindBy(id = "br.com.xp.carteira:id/buttonLoginAccount")
    private MobileElement botaoContinuar;

    @AndroidFindBy(id = "br.com.xp.carteira:id/snackbar_text")
    private MobileElement mensagem;

    public LoginPage validarLogin(){
        aguardarElementoHabilitado(imagemXP);
        return this;
    }

    public LoginPage clicarAbrirConta(){
        clicarElemento(botaoAbrirConta);
        return this;
    }

    public LoginPage inserirNumeroConta(String conta){
        inserirTextoElemento(campoNumeroConta, conta);
        return this;
    }

    public LoginPage clicarContinuar(){
        clicarElemento(botaoContinuar);
        return this;
    }

    public String retornarMensagem(){
        return retornarTextoElemento(mensagem);
    }


}
