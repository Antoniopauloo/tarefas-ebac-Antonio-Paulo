package br.com.jeb.dao.jpa;

import br.com.jeb.dao.generic.jpa.GenericJpaDB1DAO;
import br.com.jeb.domain.jpa.ProdutoJpa;

public class ProdutoJpaDAO extends GenericJpaDB1DAO<ProdutoJpa, Long> implements IProdutoJpaDAO {

	public ProdutoJpaDAO() {
		super(ProdutoJpa.class);
	}

}
