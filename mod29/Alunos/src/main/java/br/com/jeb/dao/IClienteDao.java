package br.com.jeb.dao;

import java.util.List;

import br.com.jeb.domain.Cliente;

public interface IClienteDao {

	public Integer cadastrar(Cliente cliente) throws Exception;

	public Cliente consultar(String codigo) throws Exception;

	public Integer excluir(Cliente clienteBD) throws Exception;

	public List<Cliente> buscarTodos() throws Exception;

	public Integer atualizar(Cliente cliente) throws Exception;
}
