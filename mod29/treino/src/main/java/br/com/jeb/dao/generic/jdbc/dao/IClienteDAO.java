package br.com.jeb.dao.generic.jdbc.dao;

import br.com.jeb.domin.Cliente;
import java.util.List;

public interface IClienteDAO {

	public Integer cadastrar(Cliente cliente) throws Exception;

	public Integer atualizar(Cliente cliente) throws Exception;

	public Cliente buscar(String codigo) throws Exception;

	public List<Cliente> buscarTodos() throws Exception;

	public Integer excluir(Cliente cliente) throws Exception;
}
