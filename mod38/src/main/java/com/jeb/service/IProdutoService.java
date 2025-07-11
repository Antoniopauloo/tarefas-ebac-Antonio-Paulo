package com.jeb.service;

import java.util.List;

import com.jeb.domain.Produto;
import com.jeb.service.generic.IGenericService;

public interface IProdutoService extends IGenericService<Produto, String> {

	List<Produto> filtrarProdutos(String query);

}
