package br.com.jeb.service;

import br.com.jeb.dao.IClienteDao;

public class ClienteService {

    private final IClienteDao clienteDao;

    public ClienteService(IClienteDao clienteDao) {
        System.out.println("ClienteService: Iniciando serviço");
        this.clienteDao = clienteDao;
    }

    public String salvar() {
        System.out.println("ClienteService: Tentando salvar cliente");
        clienteDao.salvar();
        System.out.println("ClienteService: Cliente salvo com sucesso");
        return "Sucesso";
    }
}
