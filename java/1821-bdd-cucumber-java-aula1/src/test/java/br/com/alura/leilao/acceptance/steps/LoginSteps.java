package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.e2e.pages.Browser;
import br.com.alura.leilao.e2e.pages.LeiloesPage;
import br.com.alura.leilao.e2e.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps {

    private Browser browser;
    private LoginPage loginPage;
    private LeiloesPage leiloesPage;

    @Given("the valid user")
    public void the_valid_user() { 
        browser = new Browser();
        browser.seed();
        loginPage = browser.getLoginPage();
    }

    @When("log in")
    public void log_in() {
        leiloesPage = loginPage.realizaLoginComo("fulano", "pass");
    }

    @Then("redirected to the auction page")
    public void redirected_to_the_auction_page() {
        Assert.assertTrue(leiloesPage.estaNaPaginaDeLeiloes());
        browser.clean();
    }

    @Given("the invalid user")
    public void the_invalid_user() {
        browser = new Browser();
        browser.seed();
        loginPage = browser.getLoginPage();
   }

    @When("try to login")
    public void try_to_login() {
        this.loginPage.realizaLoginComo("fulano", "xpto");
    }

    @Then("continue on the login page")
    public void continue_on_the_login_page() {
        Assert.assertTrue(this.loginPage.estaNaPaginaDeLoginComErro());
        browser.clean();
    }
}
