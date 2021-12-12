package br.com.alura.leilao.acceptance;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.math.BigDecimal;

public class ProposingBidSteps {

    private Lance lance;
    private Leilao leilao;

    @Given("a valid bid")
    public void a_valid_bid() {
        Usuario usuario = new Usuario("fulano");
        lance = new Lance(usuario, BigDecimal.TEN);
    }

    @When("propose the bid")
    public void propose_the_bid() {
        leilao = new Leilao("Tablet XPO");
        leilao.propoe(lance);
    }

    @Then("bid is accepted")
    public void bid_is_accepted() {
        Assert.assertEquals(1, leilao.getLances().size());
        Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
    }
}
