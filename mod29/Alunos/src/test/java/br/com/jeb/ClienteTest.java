package br.com.jeb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.jeb.dao.ClienteDao;
import br.com.jeb.dao.IClienteDao;
import br.com.jeb.dao.jdbc.ConnectionFactory;
import br.com.jeb.domain.Cliente;

public class ClienteTest {

	private IClienteDao dao;
	private Cliente cliente;

	@Before
	public void init() throws Exception {
		dao = new ClienteDao();
		cliente = new Cliente();
		cliente.setCodigo("01");
		cliente.setNome("Jonathan Euzébio Boza");
		dao.cadastrar(cliente);
	}

	@After
	public void end() throws Exception {
		Cliente clienteBD = dao.consultar(cliente.getCodigo());
		if (clienteBD != null) {
			dao.excluir(clienteBD);
		}
	}

	@Test
	public void testarConexao() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT 1 FROM tb_cliente_2 LIMIT 1";
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			assertTrue("Tabela tb_cliente_2 não existe", rs.next());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Test
	public void cadastrarTest() throws Exception {
		Cliente clienteBD = dao.consultar(cliente.getCodigo());
		assertNotNull(clienteBD);
		assertNotNull(clienteBD.getId());
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
	}

	@Test
	public void buscarTodosTest() throws Exception {
		List<Cliente> clientes = dao.buscarTodos();
		assertNotNull(clientes);
		assertTrue(clientes.size() > 0);
	}

	@Test
	public void atualizarTest() throws Exception {
		// 1. Primeiro, garantimos que podemos buscar o cliente
		Cliente clienteBD = dao.consultar(cliente.getCodigo());
		assertNotNull("Cliente não encontrado no banco de dados", clienteBD);

		// 2. Alteramos o nome do cliente
		clienteBD.setNome("Jonathan Boza Atualizado");

		// 3. Atualizamos o cliente no banco de dados
		Integer qtde = dao.atualizar(clienteBD);
		System.out.println("Número de registros atualizados: " + qtde);
		assertTrue("A atualização não retornou 1 como esperado. Retorno: " + qtde, qtde == 1);

		// 4. Buscamos o cliente novamente para confirmar a atualização
		Cliente clienteAtualizado = dao.consultar(cliente.getCodigo());
		assertNotNull("Cliente não encontrado após atualização", clienteAtualizado);
		assertEquals("O nome não foi atualizado corretamente", "Jonathan Boza Atualizado", clienteAtualizado.getNome());
		assertEquals("O código foi alterado incorretamente", cliente.getCodigo(), clienteAtualizado.getCodigo());
	}

	@Test
	public void excluirTest() throws Exception {
		Cliente clienteBD = dao.consultar(cliente.getCodigo());
		assertNotNull(clienteBD);

		Integer qtdeDel = dao.excluir(clienteBD);
		assertTrue(qtdeDel == 1);
	}
}
