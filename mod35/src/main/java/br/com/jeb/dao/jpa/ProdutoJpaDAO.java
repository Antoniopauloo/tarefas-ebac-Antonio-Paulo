package br.com.jeb.dao.jpa;

import br.com.jeb.dao.generic.jpa.GenericJpaDAO;
import br.com.jeb.domain.jpa.ProdutoJpa;

public class ProdutoJpaDAO extends GenericJpaDAO<ProdutoJpa, Long> implements IProdutoJpaDAO {

	public ProdutoJpaDAO() {
		super(ProdutoJpa.class);
	}

}
