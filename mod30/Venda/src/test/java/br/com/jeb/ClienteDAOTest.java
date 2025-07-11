package br.com.jeb;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.jeb.dao.ClienteDAO;
import br.com.jeb.dao.IClienteDAO;
import br.com.jeb.domain.Cliente;
import br.com.jeb.exceptions.DAOException;
import br.com.jeb.exceptions.MaisDeUmRegistroException;
import br.com.jeb.exceptions.TableException;
import br.com.jeb.exceptions.TipoChaveNaoEncontradaException;

public class ClienteDAOTest {
	private IClienteDAO clienteDao;
	public ClienteDAOTest() {
		clienteDao = new ClienteDAO();
	}
	
	@After
	public void end() throws DAOException {
		Collection<Cliente> list = clienteDao.buscarTodos();
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli.getCpf());
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	@Test
	public void pesquisarCliente() throws MaisDeUmRegistroException, TableException, TipoChaveNaoEncontradaException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpf(54312387643L);
		cliente.setNome("Jonathan Euzébio Boza");
		cliente.setCidade("Santa Catarina");
		cliente.setEnd("End");
		cliente.setEstado("SC");
		cliente.setCep("88960-000");
		cliente.setNumero(99);
		cliente.setTel(19991170761L);
		clienteDao.cadastrar(cliente);
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
		
		clienteDao.excluir(cliente.getCpf());
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpf(54312387643L);
		cliente.setNome("Jonathan Euzébio Boza");
		cliente.setCidade("Santa Catarina");
		cliente.setEnd("End");
		cliente.setEstado("SC");
		cliente.setCep("88960-000");
		cliente.setNumero(99);
		cliente.setTel(19991170761L);
		Boolean retorno = clienteDao.cadastrar(cliente);
		Assert.assertTrue(retorno);
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
		
		clienteDao.excluir(cliente.getCpf());
	}

	@Test
	public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpf(54312387643L);
		cliente.setNome("Jonathan Euzébio Boza");
		cliente.setCidade("Santa Catarina");
		cliente.setEnd("End");
		cliente.setEstado("SC");
		cliente.setCep("88960-000");
		cliente.setNumero(99);
		cliente.setTel(19991170761L);
		Boolean retorno = clienteDao.cadastrar(cliente);
		Assert.assertTrue(retorno);
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
		
		clienteDao.excluir(cliente.getCpf());
		clienteConsultado = clienteDao.consultar(cliente.getCpf());
		Assert.assertNull(clienteConsultado);
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpf(54312387643L);
		cliente.setNome("Jonathan Euzébio Boza");
		cliente.setCidade("Santa Catarina");
		cliente.setEnd("End");
		cliente.setEstado("SC");
		cliente.setCep("88960-000");
		cliente.setNumero(99);
		cliente.setTel(19991170761L);
		Boolean retorno = clienteDao.cadastrar(cliente);
		Assert.assertTrue(retorno);
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
		
		clienteConsultado.setNome("Amanda Lins");
		clienteDao.alterar(clienteConsultado);
		
		Cliente clienteAlterado = clienteDao.consultar(clienteConsultado.getCpf());
		Assert.assertNotNull(clienteAlterado);
		Assert.assertEquals("Amanda Lins", clienteAlterado.getNome());
		
		clienteDao.excluir(cliente.getCpf());
		clienteConsultado = clienteDao.consultar(cliente.getCpf());
		Assert.assertNull(clienteConsultado);
	}
	
	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpf(54312387643L);
		cliente.setNome("Jonathan Euzébio Boza");
		cliente.setCidade("Santa Catarina");
		cliente.setEnd("End");
		cliente.setEstado("SC");
		cliente.setCep("88960-000");
		cliente.setNumero(99);
		cliente.setTel(19991170761L);
		Boolean retorno = clienteDao.cadastrar(cliente);
		Assert.assertTrue(retorno);

		Cliente cliente1 = new Cliente();
		cliente1.setCpf(12345678911L);
		cliente1.setNome("Jeb");
		cliente1.setCidade("City");
		cliente1.setEnd("END2");
		cliente1.setEstado("State");
		cliente1.setCep("10387-685");
		cliente1.setNumero(110);
		cliente1.setTel(85987651234L);
		Boolean retorno1 = clienteDao.cadastrar(cliente1);
		Assert.assertTrue(retorno1);
		
		Collection<Cliente> list = clienteDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
		
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli.getCpf());
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Collection<Cliente> list1 = clienteDao.buscarTodos();
		assertTrue(list1 != null);
		assertTrue(list1.size() == 0);
	}
}
