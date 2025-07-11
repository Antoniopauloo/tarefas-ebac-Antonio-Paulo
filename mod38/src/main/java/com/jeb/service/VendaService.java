package com.jeb.service;

import javax.inject.Inject;

import com.jeb.dao.IVendaDAO;
import com.jeb.domain.Venda;
import com.jeb.domain.Venda.Status;
import com.jeb.service.generic.GenericService;

public class VendaService extends GenericService<Venda, Long> implements IVendaService {

	IVendaDAO dao;
	
	@Inject
	public VendaService(IVendaDAO dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public void finalizarVenda(Venda venda){
		venda.setStatus(Status.CONCLUIDA);
		dao.finalizarVenda(venda);
	}

	@Override
	public void cancelarVenda(Venda venda){
		venda.setStatus(Status.CANCELADA);
		dao.cancelarVenda(venda);
	}

	@Override
	public Venda consultarComCollection(Long id) {
		return dao.consultarComCollection(id);
	}

	@Override
	public Venda cadastrar(Venda entity){
		entity.setStatus(Status.INICIADA);
		return super.cadastrar(entity);
	}


}
