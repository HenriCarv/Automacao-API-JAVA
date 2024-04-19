package page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import setup.Setup;
import util.Util;

public class PrimeirosPassosPage extends Util {

    public PrimeirosPassosPage(){
        PageFactory.initElements(new AppiumFieldDecorator(Setup.getDriver()), this);
    }

    @AndroidFindBy(id = "br.com.xp.carteira:id/et_name")
    private MobileElement campoNomeCompleto;

    @AndroidFindBy(id = "br.com.xp.carteira:id/et_cpf")
    private MobileElement campoCPF;

    @AndroidFindBy(id = "br.com.xp.carteira:id/et_birthday")
    private MobileElement campoDataNascimento;

    @AndroidFindBy(id = "br.com.xp.carteira:id/et_email")
    private MobileElement campoQualSeuEmail;

    @AndroidFindBy(id = "br.com.xp.carteira:id/et_email_conf")
    private MobileElement campoConfirmeSeuEmail;

    @AndroidFindBy(id = "br.com.xp.carteira:id/et_phone")
    private MobileElement campoTelefone;

    public PrimeirosPassosPage inserirNomeCompleto(String nome){
        inserirTextoElemento(campoNomeCompleto, nome);
        return this;
    }

    public PrimeirosPassosPage inserirCPF(String cpf){
        inserirTextoElemento(campoCPF, cpf);
        return this;
    }

    public PrimeirosPassosPage inserirDataNascimento(String dataNascimento){
        inserirTextoElemento(campoDataNascimento, dataNascimento);
        return this;
    }

    public PrimeirosPassosPage inserirQualSeuEmail(String email){
        inserirTextoElemento(campoQualSeuEmail, email);
        return this;
    }

    public PrimeirosPassosPage inserirConfirmeSeuEmail(String email){
        inserirTextoElemento(campoConfirmeSeuEmail, email);
        return this;
    }

    public PrimeirosPassosPage inserirTelefone(String telefone){
        moverAteElemento(campoTelefone);
        inserirTextoElemento(campoTelefone, telefone);
        return this;
    }
}

