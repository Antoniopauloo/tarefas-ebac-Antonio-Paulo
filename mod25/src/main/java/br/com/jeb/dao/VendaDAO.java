package br.com.jeb.dao;

import br.com.jeb.dao.generic.GenericDAO;
import br.com.jeb.domain.Venda;
import br.com.jeb.domain.Venda.Status;
import br.com.jeb.exceptions.TipoChaveNaoEncontradaException;

public class VendaDAO extends GenericDAO<Venda, String> implements IVendaDAO {

	@Override
	public Class<Venda> getTipoClasse() {
		return Venda.class;
	}

	@Override
	public void atualiarDados(Venda entity, Venda entityCadastrado) {
		entityCadastrado.setCodigo(entity.getCodigo());
		entityCadastrado.setStatus(entity.getStatus());
	}

	@Override
	public void excluir(String valor) {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException {
		venda.setStatus(Status.CONCLUIDA);
		super.alterar(venda);
	}

	@Override
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException {
		venda.setStatus(Status.CANCELADA);
		super.alterar(venda);
	}

}
