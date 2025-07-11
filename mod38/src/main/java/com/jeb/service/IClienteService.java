package com.jeb.service;

import java.util.List;

import com.jeb.domain.Cliente;
import com.jeb.exceptions.DAOException;
import com.jeb.service.generic.IGenericService;

public interface IClienteService extends IGenericService<Cliente, Long> {

	Cliente buscarPorCPF(Long cpf) throws DAOException;

	List<Cliente> filtrarClientes(String query);

}
