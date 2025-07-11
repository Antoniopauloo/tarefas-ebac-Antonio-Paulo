package br.com.jeb;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.jeb.dao.generic.SingletonMap;

import br.com.jeb.dao.ClienteDAO;
import br.com.jeb.dao.IClienteDAO;
import br.com.jeb.domain.Cliente;
import br.com.jeb.exceptions.TipoChaveNaoEncontradaException;

public class ClienteDAOTest {

	private IClienteDAO clienteDao;

	private Cliente cliente;

	public ClienteDAOTest() {
		clienteDao = new ClienteDAO();
	}

	@Before
	public void init() throws TipoChaveNaoEncontradaException {
		// Limpa a instância do SingletonMap para garantir que cada teste inicie com
		// dados limpos
		br.com.jeb.dao.generic.SingletonMap.getInstance().getMap().clear();

		// Reinicializa o DAO para usar a nova instância do SingletonMap
		clienteDao = new ClienteDAO();

		cliente = new Cliente();
		cliente.setCpf(12312312312L);
		cliente.setNome("Jeb");
		cliente.setCidade("Santa Catarina");
		cliente.setEnd("End");
		cliente.setEstado("SC");
		cliente.setNumero(10);
		cliente.setTel(1199999999L);
		clienteDao.cadastrar(cliente);
	}

	@Test
	public void pesquisarCliente() {
		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
	}

	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException {
		System.out.println("=================== Teste salvarCliente ===================");
		cliente.setCpf(56565656565L);
		System.out.println("CPF do cliente: " + cliente.getCpf());
		System.out.println("Nome do cliente: " + cliente.getNome());
		Boolean retorno = clienteDao.cadastrar(cliente);
		System.out.println("Retorno do cadastro: " + retorno);
		Assert.assertTrue("O método de cadastro deveria retornar true", retorno);
		System.out.println("======================================================");
	}

	@Test
	public void excluirCliente() {
		clienteDao.excluir(cliente.getCpf());
	}

	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		cliente.setNome("Jonathan Euzébio Boza");
		clienteDao.alterar(cliente);
		Assert.assertEquals("Jonathan Euzébio Boza", cliente.getNome());
	}

	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException {
		// Cadastra um segundo cliente para o teste
		Cliente cliente2 = new Cliente();
		cliente2.setCpf(98765432100L);
		cliente2.setNome("Cliente Teste");
		cliente2.setCidade("Rio de Janeiro");
		cliente2.setEnd("Rua Teste");
		cliente2.setEstado("RJ");
		cliente2.setNumero(20);
		cliente2.setTel(2199999999L);
		clienteDao.cadastrar(cliente2);

		// Agora devem haver 2 clientes no total
		Collection<Cliente> list = clienteDao.buscarTodos();
		assertTrue(list != null);
		assertTrue("A lista deveria ter 2 clientes", list.size() == 2);
	}
}
