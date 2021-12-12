package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.e2e.pages.Browser;
import br.com.alura.leilao.e2e.pages.LeiloesPage;
import br.com.alura.leilao.e2e.pages.LoginPage;
import br.com.alura.leilao.e2e.pages.NovoLeilaoPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AuctionSteps {

    private Browser browser;
    private LoginPage loginPage;
    private LeiloesPage leiloesPage;
    private NovoLeilaoPage novoLeilaoPage;

    @Given("a logged in user")
    public void a_logged_in_user() {
        browser = new Browser();
        browser.seed();
        loginPage = browser.getLoginPage();
        leiloesPage = loginPage.realizaLoginComoFulano();
    }

    @When("access the new auction page")
    public void access_the_new_auction_page() {
        novoLeilaoPage = leiloesPage.visitaPaginaParaCriarUmNovoLeilao();
    }

    @When("fill the form with valid data")
    public void fill_the_form_with_valid_data() {
        leiloesPage = novoLeilaoPage.preencheForm("PC Gamer", "1500", "12/12/2021");
    }

    @Then("go back to the auctions page")
    public void go_back_to_the_auctions_page() {
        Assert.assertTrue(leiloesPage.estaNaPaginaDeLeiloes());
    }

    @Then("the new auction appears in the table")
    public void the_new_auction_appears_in_the_table() {
        Assert.assertTrue(leiloesPage.existe("PC Gamer", "1500", "12/12/2021", "fulano"));
        browser.clean();
    }
}
