package br.com.jeb.services;

import br.com.jeb.domain.Cliente;
import br.com.jeb.exceptions.DAOException;
import br.com.jeb.services.generic.IGenericService;

public interface IClienteService extends IGenericService<Cliente, Long> {
	Cliente buscarPorCPF(Long cpf) throws DAOException;
}
