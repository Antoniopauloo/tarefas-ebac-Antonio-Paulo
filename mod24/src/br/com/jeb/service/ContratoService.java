package br.com.jeb.service;

import br.com.jeb.dao.IContratoDao;

public class ContratoService implements IContratoService {

    private IContratoDao contratoDao;

    public ContratoService(IContratoDao dao) {
        this.contratoDao = dao;
    }

    @Override
    public String salvar() {
        contratoDao.salvar();
        return "Sucesso";
    }

    @Override
    public String buscar() {
        contratoDao.buscar();
        return "Contrato encontrado";
    }

    @Override
    public String excluir() {
        contratoDao.excluir();
        return "Contrato excluído";
    }

    @Override
    public String atualizar() {
        contratoDao.atualizar();
        return "Contrato atualizado";
    }
}