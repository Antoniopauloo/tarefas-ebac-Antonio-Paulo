package br.com.jeb.dao.jpa;

import br.com.jeb.dao.generic.jpa.GenericJpaDB1DAO;
import br.com.jeb.domain.jpa.ClienteJpa;

public class ClienteJpaDAO extends GenericJpaDB1DAO<ClienteJpa, Long> implements IClienteJpaDAO<ClienteJpa> {

	public ClienteJpaDAO() {
		super(ClienteJpa.class);
	}

}
