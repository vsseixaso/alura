package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

//    @Given("a valid bid of (\\d+[.]\\d\\d?) BRL from the user (\\w+)$")
    @Given("a valid bid of {double} BRL from the user {string}")
    public void a_valid_bid_of_brl_from_the_user(Double value, String username) {
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

    @Given("a invalid bid of {double} BRL from the user {string}")
    public void a_invalid_bid_of_brl_from_the_user(Double value, String username) {
        bid = new Lance(new BigDecimal(value));
    }

    @Then("bid is denied")
    public void bid_is_denied() {
        Assert.assertEquals(0, auction.getLances().size());
    }

    @Given("two bids")
    public void two_bids(DataTable dataTable) {
        List<Map<String, String>> values = dataTable.asMaps();
        values.forEach(map -> {
            String value = map.get("value");
            String username = map.get("username");

            Lance bid = new Lance(new Usuario(username), new BigDecimal(value));
            bids.add(bid);
        });
    }

    @Then("the second bid is denied")
    public void the_second_bid_is_denied() {
        Assert.assertEquals(1, auction.getLances().size());
        Assert.assertEquals(bids.get(0).getValor(), auction.getLances().get(0).getValor());
    }
}
