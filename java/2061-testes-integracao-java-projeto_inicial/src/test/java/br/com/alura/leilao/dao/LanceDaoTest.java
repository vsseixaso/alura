package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.builder.LanceBuilder;
import br.com.alura.leilao.util.builder.LeilaoBuilder;
import br.com.alura.leilao.util.builder.UsuarioBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LanceDaoTest {

    private LanceDao dao;
    private LeilaoDao leilaoDao;
    private UsuarioDao usuarioDao;
    private EntityManager em;

    @BeforeEach
    void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new LanceDao(em);
        this.leilaoDao = new LeilaoDao(em);
        this.usuarioDao = new UsuarioDao(em);

        em.getTransaction().begin();
    }

    @AfterEach
    void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void shouldFindHighestBidInAuction() {
        Usuario usuario = createUser();
        em.persist(usuario);
        Leilao leilao = createAuction(usuario);
        leilaoDao.salvar(leilao);
        Lance lanceMenor = createBid("500", leilao);
        dao.salvar(lanceMenor);
        Lance lanceMaior = createBid("1000", leilao);
        dao.salvar(lanceMaior);
        Lance salvoMaior = dao.buscarMaiorLanceDoLeilao(leilao);

        assertEquals("1000", salvoMaior.getValor());
    }

    private Lance createBid(String bid, Leilao leilao) {
        Lance lance = new LanceBuilder()
                .comValor(new BigDecimal(bid))
                .comData(LocalDate.now())
                .comUsuario(createUser())
                .comLeilao(leilao)
                .build();
        return lance;
    }

    private Leilao createAuction(Usuario usuario) {
        Leilao leilao = new LeilaoBuilder()
                .comNome("Mochila")
                .comValorInicial("400")
                .comData(LocalDate.now())
                .comUsuario(usuario)
                .build();
        return leilao;
    }

    private Usuario createUser() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("fulano")
                .comEmail("fulano@gmail.com")
                .comSenha("1234")
                .build();
        em.persist(usuario);
        return usuario;
    }
}