package br.com.jeb.dao.jpa;

import br.com.jeb.dao.generic.jpa.GenericJpaDB3DAO;
import br.com.jeb.domain.jpa.ClienteJpa2;

public class ClienteJpaDB3DAO extends GenericJpaDB3DAO<ClienteJpa2, Long> implements IClienteJpaDAO<ClienteJpa2> {

	public ClienteJpaDB3DAO() {
		super(ClienteJpa2.class);
	}

}
