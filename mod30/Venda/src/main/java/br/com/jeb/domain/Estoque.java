package br.com.jeb.domain;

import java.time.Instant;

import anotacao.ColunaTabela;
import anotacao.Tabela;
import br.com.jeb.dao.Persistente;

@Tabela("TB_ESTOQUE")
public class Estoque implements Persistente {

    @ColunaTabela(dbName = "id", setJavaName = "setId")
    private Long id;

    @ColunaTabela(dbName = "id_produto", setJavaName = "setIdProduto")
    private Long idProduto;

    @ColunaTabela(dbName = "quantidade", setJavaName = "setQuantidade")
    private Integer quantidade;

    @ColunaTabela(dbName = "quantidade_minima", setJavaName = "setQuantidadeMinima")
    private Integer quantidadeMinima;

    @ColunaTabela(dbName = "ultima_atualizacao", setJavaName = "setUltimaAtualizacao")
    private Instant ultimaAtualizacao;

    private Produto produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public Instant getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(Instant ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        if (produto != null) {
            this.idProduto = produto.getId();
        }
    }

    public boolean temEstoqueSuficiente(Integer quantidade) {
        return this.quantidade >= quantidade;
    }

    public void baixarEstoque(Integer quantidade) {
        if (!temEstoqueSuficiente(quantidade)) {
            throw new IllegalStateException("Quantidade em estoque insuficiente");
        }
        this.quantidade -= quantidade;
        this.ultimaAtualizacao = Instant.now();
    }

    public void adicionarEstoque(Integer quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        this.quantidade += quantidade;
        this.ultimaAtualizacao = Instant.now();
    }

    public boolean precisaReposicao() {
        return this.quantidade <= this.quantidadeMinima;
    }
}
