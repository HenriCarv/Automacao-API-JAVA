package step;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Quando;
import page.LoginPage;

public class LoginStep {

    private LoginPage loginPage;

    public LoginStep(){
        this.loginPage = new LoginPage();
    }

    @Quando("^eu preencher o numero da conta \"([^\"]*)\"$")
    public void eu_preencher_o_numero_da_conta(String conta){
       loginPage.inserirNumeroConta(conta);
    }

    @E("^clicar em CONTINUAR$")
    public void clicar_em_CONTINUAR(){
        loginPage.clicarContinuar();
    }
}

