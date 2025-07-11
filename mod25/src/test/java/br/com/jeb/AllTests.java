package br.com.jeb;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.jeb.dao.generic.SingletonMap;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		ClienteServiceTest.class,
		ClienteDAOTest.class,
		ProdutoServiceTest.class,
		ProdutoDAOTest.class,
		VendaDAOTest.class
})
public class AllTests {
	@BeforeClass
	public static void init() {
		// Limpa a instância do SingletonMap antes de executar todos os testes
		SingletonMap.getInstance().getMap().clear();
		System.out.println("*** Iniciando execução de todos os testes ***");
	}
}
