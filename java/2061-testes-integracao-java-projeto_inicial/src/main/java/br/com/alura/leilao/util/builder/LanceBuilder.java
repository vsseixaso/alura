package br.com.alura.leilao.util.builder;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LanceBuilder {

    private BigDecimal valor;
    private LocalDate data;
    private Usuario usuario;
    private Leilao leilao;

    public LanceBuilder comValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public LanceBuilder comData(LocalDate data) {
        this.data = data;
        return this;
    }

    public LanceBuilder comUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public LanceBuilder comLeilao(Leilao leilao) {
        this.leilao = leilao;
        return this;
    }

    public Lance build() {
        return new Lance(this.valor, this.data, this.usuario, this.leilao);
    }
}
