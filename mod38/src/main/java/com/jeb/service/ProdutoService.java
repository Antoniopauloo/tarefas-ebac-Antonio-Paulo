package com.jeb.service;

import java.util.List;

import javax.inject.Inject;

import com.jeb.dao.IProdutoDAO;
import com.jeb.domain.Produto;
import com.jeb.service.generic.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

	private IProdutoDAO produtoDao;

	@Inject
	public ProdutoService(IProdutoDAO produtoDao) {
		super(produtoDao);
		this.produtoDao = produtoDao;
	}

	@Override
	public List<Produto> filtrarProdutos(String query) {
		return produtoDao.filtrarProdutos(query);
	}

}
