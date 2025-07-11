package br.com.jeb;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.jeb.dao.generic.SingletonMap;

import br.com.jeb.dao.IProdutoDAO;
import br.com.jeb.dao.ProdutoDAO;
import br.com.jeb.domain.Produto;
import br.com.jeb.exceptions.TipoChaveNaoEncontradaException;

public class ProdutoDAOTest {

	private IProdutoDAO produtoDao;

	private Produto produto;

	public ProdutoDAOTest() {
		produtoDao = new ProdutoDAO();
	}

	@Before
	public void init() throws TipoChaveNaoEncontradaException {
		// Limpa a instância do SingletonMap para garantir que cada teste inicie com
		// dados limpos
		br.com.jeb.dao.generic.SingletonMap.getInstance().getMap().clear();

		// Reinicializa o DAO para usar a nova instância do SingletonMap
		produtoDao = new ProdutoDAO();

		produto = new Produto();
		produto.setCodigo("A1");
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
		produtoDao.cadastrar(produto);
	}

	@Test
	public void pesquisar() {
		Produto produto = this.produtoDao.consultar(this.produto.getCodigo());
		Assert.assertNotNull(produto);
	}

	@Test
	public void salvar() throws TipoChaveNaoEncontradaException {
		System.out.println("=================== Teste salvar Produto ===================");
		produto.setCodigo("A2");
		System.out.println("Código do produto: " + produto.getCodigo());
		System.out.println("Nome do produto: " + produto.getNome());
		System.out.println("Valor do produto: " + produto.getValor());
		Boolean retorno = produtoDao.cadastrar(produto);
		System.out.println("Retorno do cadastro: " + retorno);
		Assert.assertTrue("O método de cadastro deveria retornar true", retorno);
		System.out.println("========================================================");
	}

	@Test
	public void excluir() {
		produtoDao.excluir(produto.getCodigo());
	}

	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		produto.setNome("Jonathan Euzébio Boza");
		produtoDao.alterar(produto);

		Assert.assertEquals("Jonathan Euzébio Boza", produto.getNome());
	}

	@Test
	public void buscarTodos() {
		System.out.println("=================== Teste buscarTodos Produto ===================");
		Collection<Produto> list = produtoDao.buscarTodos();
		System.out.println("Lista de produtos é nula? " + (list == null));
		System.out.println("Tamanho da lista de produtos: " + (list != null ? list.size() : "N/A"));
		if (list != null && !list.isEmpty()) {
			System.out.println("Produtos encontrados:");
			for (Produto p : list) {
				System.out.println("  - Código: " + p.getCodigo() + ", Nome: " + p.getNome());
			}
		}
		assertTrue("A lista de produtos não deveria ser nula", list != null);
		assertTrue("O tamanho da lista deveria ser 1", list.size() == 1); // Alterado de 2 para 1, pois só cadastramos
																			// um produto no init
		System.out.println("=============================================================");
	}
}
