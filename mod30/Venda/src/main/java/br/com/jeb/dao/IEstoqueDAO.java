package br.com.jeb.dao;

import br.com.jeb.dao.generic.IGenericDAO;
import br.com.jeb.domain.Estoque;
import br.com.jeb.exceptions.DAOException;
import br.com.jeb.exceptions.TipoChaveNaoEncontradaException;

public interface IEstoqueDAO extends IGenericDAO<Estoque, Long> {

    /**
     * Atualiza a quantidade em estoque para um produto
     */
    public void atualizarEstoque(Estoque estoque) throws TipoChaveNaoEncontradaException, DAOException;

    /**
     * Busca o estoque pelo ID do produto
     */
    public Estoque buscarPorProduto(Long idProduto) throws DAOException;
}
