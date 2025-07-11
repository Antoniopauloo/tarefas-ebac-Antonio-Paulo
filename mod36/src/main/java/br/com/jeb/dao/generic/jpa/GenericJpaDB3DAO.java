package br.com.jeb.dao.generic.jpa;

import java.io.Serializable;

import br.com.jeb.domain.jpa.Persistente;

public abstract class GenericJpaDB3DAO <T extends Persistente, E extends Serializable>
	extends GenericJpaDAO<T,E> {

	public GenericJpaDB3DAO(Class<T> persistenteClass) {
		super(persistenteClass, "Mysql1");
	}

}
