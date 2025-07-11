package com.jeb.dao;

import java.util.List;

import com.jeb.dao.generic.IGenericDAO;
import com.jeb.domain.Cliente;

public interface IClienteDAO extends IGenericDAO<Cliente, Long> {

	List<Cliente> filtrarClientes(String query);

}
