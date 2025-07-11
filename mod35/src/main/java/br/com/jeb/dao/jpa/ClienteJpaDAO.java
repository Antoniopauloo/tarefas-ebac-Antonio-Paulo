package br.com.jeb.dao.jpa;

import br.com.jeb.dao.generic.jpa.GenericJpaDAO;
import br.com.jeb.domain.jpa.ClienteJpa;

public class ClienteJpaDAO extends GenericJpaDAO<ClienteJpa, Long> implements IClienteJpaDAO {

	public ClienteJpaDAO() {
		super(ClienteJpa.class);
	}

}
