package br.com.jeb;

import org.junit.Assert;
import org.junit.Test;

public class TesteClienteTest {

	@Test
	public void testeClasseCliente() {
		TesteCliente cli = new TesteCliente();
		cli.adicionarNome("Jeb");
		cli.adicionarNome1("Jeb");

		Assert.assertEquals("Jeb", cli.getNome());
	}
}
