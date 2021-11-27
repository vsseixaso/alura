package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDaoTest {

    private UsuarioDao dao;
    private EntityManager em;

    @BeforeEach
    void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void shouldFindRegisteredUser() {
        Usuario usuario = createUser();
        Usuario encontrado = this.dao.buscarPorUsername(usuario.getNome());

        assertNotNull(encontrado);
    }

    @Test
    void shouldNotFindUnregisteredUser() {
        assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername("fulano"));
    }

    @Test
    void shouldRemoveUser() {
        Usuario usuario = createUser();
        dao.deletar(usuario);

        assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername(usuario.getNome()));
    }

    private Usuario createUser() {
        Usuario usuario = new Usuario("fulano", "fulano@gmail.com", "1234");
        em.persist(usuario);
        return usuario;
    }

}