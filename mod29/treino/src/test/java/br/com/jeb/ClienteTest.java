package br.com.jeb;

import java.util.List;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import br.com.jeb.dao.generic.jdbc.dao.ClienteDAO;
import br.com.jeb.dao.generic.jdbc.dao.IClienteDAO;
import br.com.jeb.domin.Cliente;

public class ClienteTest {

	private IClienteDAO clienteDAO;

	@Test
	public void cadastrarTest() throws Exception {
		clienteDAO = new ClienteDAO();

		Cliente cliente = new Cliente();
		cliente.setCodigo("10");
		cliente.setNome("Jonathan Euzébio Boza");
		Integer countCad = clienteDAO.cadastrar(cliente);
		assertTrue(countCad == 1);

		Cliente clienteBD = clienteDAO.buscar("10");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());

		Integer countDel = clienteDAO.excluir(clienteBD);
		assertTrue(countDel == 1);
	}

	@Test
	public void buscarTest() throws Exception {
		clienteDAO = new ClienteDAO();

		Cliente cliente = new Cliente();
		cliente.setCodigo("10");
		cliente.setNome("Jonathan Euzébio Boza");
		Integer countCad = clienteDAO.cadastrar(cliente);
		assertTrue(countCad == 1);

		Cliente clienteBD = clienteDAO.buscar("10");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());

		Integer countDel = clienteDAO.excluir(clienteBD);
		assertTrue(countDel == 1);
	}

	@Test
	public void excluirTest() throws Exception {
		clienteDAO = new ClienteDAO();

		Cliente cliente = new Cliente();
		cliente.setCodigo("10");
		cliente.setNome("Jonathan Euzébio Boza");
		Integer countCad = clienteDAO.cadastrar(cliente);
		assertTrue(countCad == 1);

		Cliente clienteBD = clienteDAO.buscar("10");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());

		Integer countDel = clienteDAO.excluir(clienteBD);
		assertTrue(countDel == 1);
	}

	@Test
	public void buscarTodosTest() throws Exception {
		clienteDAO = new ClienteDAO();

		Cliente cliente = new Cliente();
		cliente.setCodigo("10");
		cliente.setNome("Jonathan Euzébio Boza");
		Integer countCad = clienteDAO.cadastrar(cliente);
		assertTrue(countCad == 1);

		Cliente clientes = new Cliente();
		clientes.setCodigo("20");
		clientes.setNome("Teste");
		Integer countCad2 = clienteDAO.cadastrar(clientes);
		assertTrue(countCad2 == 1);

		List<Cliente> list = clienteDAO.buscarTodos();
		assertNotNull(list);
		assertEquals(2, list.size());

		int countDel = 0;
		for (Cliente cli : list) {
			clienteDAO.excluir(cli);
			countDel++;
		}
		assertEquals(list.size(), countDel);

		list = clienteDAO.buscarTodos();
		assertEquals(list.size(), 0);

	}

	@Test
	public void atualizarTest() throws Exception {
		clienteDAO = new ClienteDAO();

		Cliente cliente = new Cliente();
		cliente.setCodigo("10");
		cliente.setNome("Jonathan Euzébio Boza");
		Integer countCad = clienteDAO.cadastrar(cliente);
		assertTrue(countCad == 1);

		Cliente clienteBD = clienteDAO.buscar("10");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());

		clienteBD.setCodigo("20");
		clienteBD.setNome("Outro nome");
		Integer countUpdate = clienteDAO.atualizar(clienteBD);
		assertTrue(countUpdate == 1);

		Cliente clienteBD1 = clienteDAO.buscar("10");
		assertNull(clienteBD1);

		Cliente clienteBD2 = clienteDAO.buscar("20");
		assertNotNull(clienteBD2);
		assertEquals(clienteBD.getId(), clienteBD2.getId());
		assertEquals(clienteBD.getCodigo(), clienteBD2.getCodigo());
		assertEquals(clienteBD.getNome(), clienteBD2.getNome());

		List<Cliente> list = clienteDAO.buscarTodos();
		for (Cliente cli : list) {
			clienteDAO.excluir(cli);
		}
	}
}
