package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LeilaoDaoTest {

    private LeilaoDao dao;
    private EntityManager em;

    @BeforeEach
    void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new LeilaoDao(em);
        em.getTransaction().begin();
    }

    @Test
    void shouldRegisterAuction() {
        Leilao leilao = createAuction();
        leilao = dao.salvar(leilao);
        Leilao salvo = dao.buscarPorId(leilao.getId());

        assertNotNull(salvo);
    }

    @Test
    void shouldUpdateAuction() {
        Leilao leilao = createAuction();
        leilao = dao.salvar(leilao);
        leilao.setNome("Celular");
        leilao.setValorInicial(new BigDecimal("400"));
        leilao = dao.salvar(leilao);
        Leilao salvo = dao.buscarPorId(leilao.getId());

        assertEquals("Celular", salvo.getNome());
        assertEquals(new BigDecimal("400"), salvo.getValorInicial());
    }

    @AfterEach
    void afterEach() {
        em.getTransaction().rollback();
    }

    private Leilao createAuction() {
        Usuario usuario = createUser();
        Leilao leilao = new Leilao("Mochila", new BigDecimal("70"), LocalDate.now(), usuario);
        return leilao;
    }

    private Usuario createUser() {
        Usuario usuario = new Usuario("fulano", "fulano@gmail.com", "1234");
        em.persist(usuario);
        return usuario;
    }
}