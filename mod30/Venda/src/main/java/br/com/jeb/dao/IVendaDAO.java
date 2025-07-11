package br.com.jeb.dao;

import br.com.jeb.dao.generic.IGenericDAO;
import br.com.jeb.domain.Venda;
import br.com.jeb.exceptions.DAOException;
import br.com.jeb.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaDAO extends IGenericDAO<Venda, String> {
	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
}
