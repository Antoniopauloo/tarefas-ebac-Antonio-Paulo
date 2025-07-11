package com.jeb.dao;

import java.util.List;

import com.jeb.dao.generic.IGenericDAO;
import com.jeb.domain.Produto;

public interface IProdutoDAO extends IGenericDAO<Produto, String> {

	List<Produto> filtrarProdutos(String query);

}
