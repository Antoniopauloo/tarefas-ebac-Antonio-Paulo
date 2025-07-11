package br.com.jeb.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que demonstra o uso de streams para filtrar pessoas por gênero
 */
public class StreamExemplo {

    /**
     * Lista de pessoas com diferentes gêneros
     */
    private static final List<Pessoa> pessoas = Arrays.asList(
            new Pessoa("João", "Masculino"),
            new Pessoa("Maria", "Feminino"),
            new Pessoa("Ana", "Feminino"),
            new Pessoa("Pedro", "Masculino"),
            new Pessoa("Carla", "Feminino"),
            new Pessoa("Beatriz", "Feminino"),
            new Pessoa("Lucas", "Masculino"),
            new Pessoa("Fernanda", "Feminino"),
            new Pessoa("Juliana", "Feminino"),
            new Pessoa("Rafael", "Masculino")
    );

    /**
     * Filtra a lista de pessoas e retorna apenas as mulheres
     * @return Lista contendo apenas pessoas do gênero feminino
     */
    public static List<Pessoa> filtrarMulheres() {
        return pessoas.stream()
                .filter(pessoa -> "Feminino".equals(pessoa.getGenero()))
                .collect(Collectors.toList());
    }

    /**
     * Método principal para demonstração
     */
    public static void main(String[] args) {
        List<Pessoa> mulheres = filtrarMulheres();
        System.out.println("Lista de mulheres:");
        mulheres.forEach(System.out::println);
    }
}
