package br.com.jeb.dao.jpa;

import br.com.jeb.dao.generic.jpa.IGenericJapDAO;
import br.com.jeb.domain.jpa.Persistente;

public interface IClienteJpaDAO<T extends Persistente> extends IGenericJapDAO<T, Long>{

}
