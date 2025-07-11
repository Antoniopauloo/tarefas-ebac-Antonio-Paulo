package br.com.jeb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import br.com.jeb.dao.ClienteDAO;
import br.com.jeb.dao.IClienteDAO;
import br.com.jeb.dao.IProdutoDAO;
import br.com.jeb.dao.IVendaDAO;
import br.com.jeb.dao.ProdutoDAO;
import br.com.jeb.dao.VendaDAO;
import br.com.jeb.domain.Cliente;
import br.com.jeb.domain.Produto;
import br.com.jeb.domain.Venda;
import br.com.jeb.domain.Venda.Status;
import br.com.jeb.exceptions.TipoChaveNaoEncontradaException;

public class VendaDAOTest {

	private IVendaDAO vendaDao;

	private IClienteDAO clienteDao;

	private IProdutoDAO produtoDao;

	// private Venda venda;

	private Cliente cliente;

	private Produto produto;

	public VendaDAOTest() {
		vendaDao = new VendaDAO();
		clienteDao = new ClienteDAO();
		produtoDao = new ProdutoDAO();
	}

	@Before
	public void init() throws TipoChaveNaoEncontradaException {
		System.out.println("\n=========== INICIANDO TESTE ===========");

		// Limpa a instância do SingletonMap para garantir que cada teste inicie com
		// dados limpos
		// Usando método getInstance().getMap().clear() ao invés de limparInstancia()
		br.com.jeb.dao.generic.SingletonMap.getInstance().getMap().clear();

		// Reinicializa os DAOs para que usem a nova instância do SingletonMap
		vendaDao = new VendaDAO();
		clienteDao = new ClienteDAO();
		produtoDao = new ProdutoDAO();

		System.out.println("Cadastrando cliente e produto base para o teste");
		this.cliente = cadastrarCliente();
		this.produto = cadastrarProduto("A1", BigDecimal.TEN);
		System.out.println("Cliente base: " + this.cliente.getNome() + " (CPF: " + this.cliente.getCpf() + ")");
		System.out.println("Produto base: " + this.produto.getNome() + " (Código: " + this.produto.getCodigo()
				+ ", Valor: " + this.produto.getValor() + ")");
		System.out.println("======================================\n");
	}

	@Test
	public void pesquisar() throws TipoChaveNaoEncontradaException {
		Venda venda = criarVenda("A1");
		Boolean retorno = vendaDao.cadastrar(venda);
		assertTrue(retorno);
		Venda vendaConsultada = vendaDao.consultar(venda.getCodigo());
		assertNotNull(vendaConsultada);
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
	}

	@Test
	public void salvar() throws TipoChaveNaoEncontradaException {
		System.out.println("=================== Teste salvar Venda ===================");
		Venda venda = criarVenda("A2");
		System.out.println("Código da venda: " + venda.getCodigo());
		System.out.println("Cliente da venda: " + (venda.getCliente() != null ? venda.getCliente().getNome() : "null"));
		System.out.println("Valor total da venda: " + venda.getValorTotal());
		System.out.println("Status da venda: " + venda.getStatus());
		System.out.println("Quantidade de produtos: " + venda.getQuantidadeTotalProdutos());

		Boolean retorno = vendaDao.cadastrar(venda);
		System.out.println("Retorno do cadastro: " + retorno);

		assertTrue("O método de cadastro deveria retornar true", retorno);
		assertTrue("O valor total da venda deveria ser 20", venda.getValorTotal().equals(BigDecimal.valueOf(20)));
		assertTrue("O status da venda deveria ser INICIADA", venda.getStatus().equals(Status.INICIADA));
		System.out.println("========================================================");
	}

	@Test
	public void cancelarVenda() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A3";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaDao.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());

		vendaDao.cancelarVenda(venda);

		Venda vendaConsultada = vendaDao.consultar(codigoVenda);
		assertEquals(codigoVenda, vendaConsultada.getCodigo());
		assertEquals(Status.CANCELADA, vendaConsultada.getStatus());
	}

	@Test
	public void adicionarMaisProdutosDoMesmo() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A4";
		Venda venda = criarVenda(codigoVenda);
		boolean retorno = vendaDao.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());

		Venda vendaConsultada = vendaDao.consultar(codigoVenda);
		vendaConsultada.adicionarProduto(produto, 1);

		assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
		assertTrue(vendaConsultada.getValorTotal().equals(BigDecimal.valueOf(30)));
		assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
	}

	@Test
	public void adicionarMaisProdutosDiferentes() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A5";
		Venda venda = criarVenda(codigoVenda);
		boolean retorno = vendaDao.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());

		Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoVenda, prod.getCodigo());

		Venda vendaConsultada = vendaDao.consultar(codigoVenda);
		vendaConsultada.adicionarProduto(prod, 1);

		assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
		assertTrue(vendaConsultada.getValorTotal().equals(BigDecimal.valueOf(70)));
		assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
	}

	@Test
	public void removerProduto() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A6";
		Venda venda = criarVenda(codigoVenda);
		boolean retorno = vendaDao.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());

		Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoVenda, prod.getCodigo());

		Venda vendaConsultada = vendaDao.consultar(codigoVenda);
		vendaConsultada.adicionarProduto(prod, 1);
		assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
		assertTrue(vendaConsultada.getValorTotal().equals(BigDecimal.valueOf(70)));

		vendaConsultada.removerProduto(prod, 1);
		assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 2);
		assertTrue(vendaConsultada.getValorTotal().equals(BigDecimal.valueOf(20)));
		assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
	}

	@Test
	public void removerApenasUmProduto() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A7";
		Venda venda = criarVenda(codigoVenda);
		boolean retorno = vendaDao.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());

		Venda vendaConsultada = vendaDao.consultar(codigoVenda);
		vendaConsultada.removerProduto(this.produto, 1);

		assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 1);
		assertTrue(vendaConsultada.getValorTotal().equals(BigDecimal.valueOf(10)));
		assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
	}

	@Test
	public void removerTodosProdutos() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A8";
		Venda venda = criarVenda(codigoVenda);
		boolean retorno = vendaDao.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());

		Venda vendaConsultada = vendaDao.consultar(codigoVenda);
		vendaConsultada.removerTodosProdutos();

		assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 0);
		assertTrue(vendaConsultada.getValorTotal().equals(BigDecimal.valueOf(0)));
		assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
	}

	@Test
	public void finalizarVenda() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A9";
		Venda venda = criarVenda(codigoVenda);
		boolean retorno = vendaDao.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());

		vendaDao.finalizarVenda(venda);

		Venda vendaConsultada = vendaDao.consultar(codigoVenda);
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
		assertEquals(Status.CONCLUIDA, vendaConsultada.getStatus());
	}

	@Test
	public void tentarAdicionarProdutosVendaFinalizada() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A10";
		Venda venda = criarVenda(codigoVenda);
		boolean retorno = vendaDao.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());

		vendaDao.finalizarVenda(venda);
		Venda vendaConsultada = vendaDao.consultar(codigoVenda);
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
		assertEquals(Status.CONCLUIDA, vendaConsultada.getStatus());

		try {
			vendaConsultada.adicionarProduto(this.produto, 1);
			assertTrue(false);
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
			assertEquals("IMPOSSÍVEL ALTERAR VENDA FINALIZADA", e.getMessage());
		}
	}

	@Test
	public void tentarRemoverProdutosVendaFinalizada() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A11";
		Venda venda = criarVenda(codigoVenda);
		boolean retorno = vendaDao.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());

		vendaDao.finalizarVenda(venda);
		Venda vendaConsultada = vendaDao.consultar(codigoVenda);
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
		assertEquals(Status.CONCLUIDA, vendaConsultada.getStatus());

		try {
			vendaConsultada.removerProduto(this.produto, 1);
			assertTrue(false);
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
			assertEquals("IMPOSSÍVEL ALTERAR VENDA FINALIZADA", e.getMessage());
		}
	}

	private Cliente cadastrarCliente() throws TipoChaveNaoEncontradaException {
		Cliente cliente = new Cliente();
		cliente.setCpf(12345678901L);
		cliente.setNome("Jonathan");
		cliente.setCidade("São Paulo");
		cliente.setEnd("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(1199999999L);
		clienteDao.cadastrar(cliente);
		return cliente;
	}

	private Produto cadastrarProduto(String codigo, BigDecimal valor) throws TipoChaveNaoEncontradaException {
		Produto produto = new Produto();
		produto.setCodigo(codigo);
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(valor);
		produtoDao.cadastrar(produto);
		return produto;
	}

	private Venda criarVenda(String codigo) {
		Venda venda = new Venda();
		venda.setCodigo(codigo);
		venda.setDataVenda(Instant.now());
		venda.setCliente(this.cliente);
		venda.setStatus(Status.INICIADA);
		venda.adicionarProduto(this.produto, 2);
		return venda;
	}
}
