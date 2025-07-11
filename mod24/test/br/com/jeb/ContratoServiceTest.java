package br.com.jeb;

import br.com.jeb.dao.ContratoDao;
import br.com.jeb.dao.IContratoDao;
import br.com.jeb.dao.mocks.ContratoDaoMock;
import br.com.jeb.service.ContratoService;
import br.com.jeb.service.IContratoService;
import org.junit.Assert;
import org.junit.Test;

public class ContratoServiceTest {
    @Test
    public void salvarTest() {
        System.out.println("\n=== Teste de Salvar Contrato ===");
        IContratoDao dao = new ContratoDaoMock();
        IContratoService service = new ContratoService(dao);
        String retorno = service.salvar();
        Assert.assertEquals("Sucesso", retorno);
        System.out.println("Resultado do teste: " + retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroNoSalvarComBancoDeDadosTest() {
        System.out.println("\n=== Teste de Erro ao Salvar no Banco ===");
        IContratoDao dao = new ContratoDao();
        IContratoService service = new ContratoService(dao);
        String retorno = service.salvar();
        Assert.assertEquals("Sucesso", retorno);
        System.out.println("Resultado do teste: " + retorno);
    }

    @Test
    public void buscarTest() {
        System.out.println("\n=== Teste de Buscar Contrato ===");
        IContratoDao dao = new ContratoDaoMock();
        IContratoService service = new ContratoService(dao);
        String retorno = service.buscar();
        Assert.assertEquals("Contrato encontrado", retorno);
        System.out.println("Resultado do teste: " + retorno);
    }

    @Test
    public void excluirTest() {
        System.out.println("\n=== Teste de Excluir Contrato ===");
        IContratoDao dao = new ContratoDaoMock();
        IContratoService service = new ContratoService(dao);
        String retorno = service.excluir();
        Assert.assertEquals("Contrato excluído", retorno);
        System.out.println("Resultado do teste: " + retorno);
    }

    @Test
    public void atualizarTest() {
        System.out.println("\n=== Teste de Atualizar Contrato ===");
        IContratoDao dao = new ContratoDaoMock();
        IContratoService service = new ContratoService(dao);
        String retorno = service.atualizar();
        Assert.assertEquals("Contrato atualizado", retorno);
        System.out.println("Resultado do teste: " + retorno);
    }
}