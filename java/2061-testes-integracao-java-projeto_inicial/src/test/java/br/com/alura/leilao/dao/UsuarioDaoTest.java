package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDaoTest {

    private UsuarioDao dao;

    private EntityManager em;

    @Test
    void buscaDeUsuarioPeloUsername() {
        EntityManager em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);

        Usuario usuario = new Usuario("fulano", "fulano@gmail.com", "1234");
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

        Usuario encontrado = this.dao.buscarPorUsername("fulano");

        assertNotNull(encontrado);
    }

}