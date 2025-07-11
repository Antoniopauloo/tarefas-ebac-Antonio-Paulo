package br.com.jeb.dao;

public class ClienteDaoMock implements IClienteDao {

    @Override
    public String salvar() {
        System.out.println("ClienteDaoMock: Simulando salvar cliente");
        return null;
    }
}
