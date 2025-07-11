package br.com.jeb.dao;

public class ContratoDao implements IContratoDao {

    @Override
    public void salvar() {
        System.out.println("DAO: Tentando salvar no banco de dados");
        throw new UnsupportedOperationException("Não funciona com o banco");
    }

    @Override
    public String buscar() {
        System.out.println("DAO: Tentando buscar no banco de dados");
        throw new UnsupportedOperationException("Não funciona com o banco");
    }

    @Override
    public void excluir() {
        System.out.println("DAO: Tentando excluir no banco de dados");
        throw new UnsupportedOperationException("Não funciona com o banco");
    }

    @Override
    public void atualizar() {
        System.out.println("DAO: Tentando atualizar no banco de dados");
        throw new UnsupportedOperationException("Não funciona com o banco");
    }
}
