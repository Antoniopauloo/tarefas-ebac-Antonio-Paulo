package br.com.jeb.dao.jpa;

import br.com.jeb.dao.generic.jpa.GenericJpaDB2DAO;
import br.com.jeb.domain.jpa.ClienteJpa;

public class ClienteJpaDB2DAO extends GenericJpaDB2DAO<ClienteJpa, Long> implements IClienteJpaDAO<ClienteJpa> {

	public ClienteJpaDB2DAO() {
		super(ClienteJpa.class);
	}

}
