package br.com.jeb.streams;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

/**
 * Classe de teste para verificar se a lista filtrada contém apenas mulheres
 */
public class StreamExemploTest {

    /**
     * Testa se a lista filtrada contém apenas pessoas do gênero feminino
     */
    @Test
    public void testFiltrarMulheres() {
        // Obtém a lista filtrada de mulheres
        List<Pessoa> mulheres = StreamExemplo.filtrarMulheres();
        
        // Verifica se a lista não está vazia
        assertFalse("A lista de mulheres não deve estar vazia", mulheres.isEmpty());
        
        // Verifica se todos os elementos da lista são do gênero feminino
        boolean apenasGeneroFeminino = mulheres.stream()
                .allMatch(pessoa -> "Feminino".equals(pessoa.getGenero()));
        
        assertTrue("A lista deve conter apenas pessoas do gênero feminino", apenasGeneroFeminino);
        
        // Verifica se não há nenhum elemento do gênero masculino
        boolean semGeneroMasculino = mulheres.stream()
                .noneMatch(pessoa -> "Masculino".equals(pessoa.getGenero()));
        
        assertTrue("A lista não deve conter pessoas do gênero masculino", semGeneroMasculino);
        
        // Imprime a quantidade de mulheres encontradas
        System.out.println("Quantidade de mulheres na lista: " + mulheres.size());
        
        // Imprime os nomes das mulheres encontradas
        System.out.println("Nomes das mulheres na lista:");
        mulheres.forEach(pessoa -> System.out.println(pessoa.getNome()));
    }
}
