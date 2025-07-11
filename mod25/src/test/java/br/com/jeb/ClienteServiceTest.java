package br.com.jeb;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.jeb.dao.ClienteDaoMock;
import br.com.jeb.dao.IClienteDAO;
import br.com.jeb.domain.Cliente;
import br.com.jeb.exceptions.TipoChaveNaoEncontradaException;
import br.com.jeb.services.ClienteService;
import br.com.jeb.services.IClienteService;

public class ClienteServiceTest {
	
	private IClienteService clienteService;
	
	private Cliente cliente;
	
	public ClienteServiceTest() {
		IClienteDAO dao = new ClienteDaoMock();
		clienteService = new ClienteService(dao);
	}
	
	@Before
	public void init() {
		cliente = new Cliente();
		cliente.setCpf(12312312312L);
		cliente.setNome("Jeb");
		cliente.setCidade("Santa Catarina");
		cliente.setEnd("End");
		cliente.setEstado("SC");
		cliente.setNumero(10);
		cliente.setTel(1199999999L);
		
	}
	
	@Test
	public void pesquisarCliente() {
		Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException {
		Boolean retorno = clienteService.cadastrar(cliente);
		
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void excluirCliente() {
		clienteService.excluir(cliente.getCpf());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		cliente.setNome("Jonathan Euzébio Boza");
		clienteService.alterar(cliente);
		
		Assert.assertEquals("Jonathan Euzébio Boza", cliente.getNome());
	}
}
