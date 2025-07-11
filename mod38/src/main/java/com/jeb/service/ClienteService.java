package com.jeb.service;

import java.util.List;

import javax.inject.Inject;

import com.jeb.dao.IClienteDAO;
import com.jeb.domain.Cliente;
import com.jeb.exceptions.DAOException;
import com.jeb.exceptions.MaisDeUmRegistroException;
import com.jeb.exceptions.TableException;
import com.jeb.service.generic.GenericService;

public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {

	private IClienteDAO clienteDAO;

	@Inject
	public ClienteService(IClienteDAO clienteDAO) {
		super(clienteDAO);
		this.clienteDAO = clienteDAO;
	}

	@Override
	public Cliente buscarPorCPF(Long cpf) throws DAOException {
		try {
			return this.dao.consultar(cpf);
		} catch (MaisDeUmRegistroException | TableException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Cliente> filtrarClientes(String query) {
		return clienteDAO.filtrarClientes(query);
	}

}
