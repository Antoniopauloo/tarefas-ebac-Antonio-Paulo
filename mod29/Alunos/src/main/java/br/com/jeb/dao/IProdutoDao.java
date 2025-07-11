package br.com.jeb.dao;

import java.util.List;

import br.com.jeb.domain.Produto;

public interface IProdutoDao {

    public Integer cadastrar(Produto produto) throws Exception;

    public Produto consultar(String codigo) throws Exception;

    public List<Produto> buscarTodos() throws Exception;

    public Integer excluir(Produto produtoBD) throws Exception;

    public Integer atualizar(Produto produto) throws Exception;
}
