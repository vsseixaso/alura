package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProposingBidSteps {

    private Lance bid;
    private Leilao auction;
    private ArrayList<Lance> bids;

    @Before
    public void setup() {
        this.bids = new ArrayList<Lance>();
        auction = new Leilao("Tablet XPO");
    }

    @After
    public void tearDown() {
        System.out.println("@After");
    }

    @Given("a valid bid")
    public void a_valid_bid() {
        Usuario usuario = new Usuario("fulano");
        bid = new Lance(usuario, BigDecimal.TEN);
    }

    @When("propose to auction")
    public void propose_to_auction() {
        auction.propoe(bid);
    }

    @Then("bid is accepted")
    public void bid_is_accepted() {
        Assert.assertEquals(1, auction.getLances().size());
        Assert.assertEquals(BigDecimal.TEN, auction.getLances().get(0).getValor());
    }

//    @Given("a valid bid of (\\d+[.]\\d\\d?) reais from the user (\\w+)$")
    @Given("a valid bid of {double} reais from the user {string}")
    public void a_valid_bid_of_reais_from_the_user(Double value, String username) {
        Lance bid = new Lance(new Usuario(username), new BigDecimal(value));
        bids.add(bid);
    }

    @When("propose multiple bids to auction")
    public void propose_multiple_bids_to_auction() {
        bids.forEach(auction::propoe);
    }

    @Then("bids are accepted")
    public void bids_are_accepted() {
        Assert.assertEquals(bids.size(), auction.getLances().size());
        Assert.assertEquals(bids.get(0).getValor(), auction.getLances().get(0).getValor());
        Assert.assertEquals(bids.get(1).getValor(), auction.getLances().get(1).getValor());
    }
}
