package br.com.jeb.services;

import br.com.jeb.dao.IProdutoDAO;
import br.com.jeb.domain.Produto;
import br.com.jeb.services.generic.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

	public ProdutoService(IProdutoDAO dao) {
		super(dao);
	}

}
