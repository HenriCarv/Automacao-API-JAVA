package step;


import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Quando;
import page.AbraSuaContaPage;
import page.LoginPage;
import page.PrimeirosPassosPage;

public class AbrirContaStep {

    private LoginPage loginPage;
    private AbraSuaContaPage abraSuaContaPage;
    private PrimeirosPassosPage primeirosPassosPage;

    public AbrirContaStep(){

        this.loginPage = new LoginPage();
        this.abraSuaContaPage = new AbraSuaContaPage();
        this.primeirosPassosPage = new PrimeirosPassosPage();
    }

    @Dado("^que esteja na tela de login$")
    public void que_esteja_na_tela_de_login(){
        this.loginPage.validarLogin();
    }

    @Quando("^eu clicar em Abrir conta$")
    public void eu_clicar_em_Abrir_conta(){
        this.loginPage.clicarAbrirConta();
    }

    @E("^clicar em ABRIR CONTA$")
    public void clicar_em_ABRIR_CONTA(){
        this.abraSuaContaPage.clicarAbrirConta();
    }

    @E("^preencher \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
    public void preencher(String nome, String cpf, String dataNascimento, String email, String celular){
        this.primeirosPassosPage.inserirNomeCompleto(nome).inserirCPF(cpf).inserirDataNascimento(dataNascimento)
                .inserirQualSeuEmail(email).inserirConfirmeSeuEmail(email).inserirTelefone(celular);
    }

    @E("^clicar em Avancar$")
    public void clicar_em_Avancar(){

    }
}
