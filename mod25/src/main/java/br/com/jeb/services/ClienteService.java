package br.com.jeb.services;

import br.com.jeb.dao.IClienteDAO;
import br.com.jeb.domain.Cliente;
import br.com.jeb.services.generic.GenericService;

public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {

	public ClienteService(IClienteDAO clienteDAO) {
		super(clienteDAO);
	}

	@Override
	public Cliente buscarPorCPF(Long cpf) {
		return this.dao.consultar(cpf);
	}

}
