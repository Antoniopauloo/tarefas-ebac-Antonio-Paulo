package br.com.jeb.dao.mocks;

import br.com.jeb.dao.IContratoDao;

public class ContratoDaoMock implements IContratoDao {
    private boolean salvarFoiChamado = false;
    private boolean buscarFoiChamado = false;
    private boolean excluirFoiChamado = false;
    private boolean atualizarFoiChamado = false;

    @Override
    public void salvar() {
        System.out.println("Mock: Simulando salvar contrato");
        salvarFoiChamado = true;
    }

    @Override
    public String buscar() {
        System.out.println("Mock: Simulando buscar contrato");
        buscarFoiChamado = true;
        return null;
    }

    @Override
    public void excluir() {
        System.out.println("Mock: Simulando excluir contrato");
        excluirFoiChamado = true;
    }

    @Override
    public void atualizar() {
        System.out.println("Mock: Simulando atualizar contrato");
        atualizarFoiChamado = true;
    }

    public boolean isSalvarFoiChamado() {
        return salvarFoiChamado;
    }

    public boolean isBuscarFoiChamado() {
        return buscarFoiChamado;
    }

    public boolean isExcluirFoiChamado() {
        return excluirFoiChamado;
    }

    public boolean isAtualizarFoiChamado() {
        return atualizarFoiChamado;
    }
}