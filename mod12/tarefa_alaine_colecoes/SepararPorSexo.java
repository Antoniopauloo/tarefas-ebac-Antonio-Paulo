package mod12.tarefa_alaine_colecoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SepararPorSexo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> masculino = new ArrayList<>();
        List<String> feminino = new ArrayList<>();

        System.out.println("Digite nome e sexo separados por vírgula (ex: Rodrigo-M, Ana-F):");
        String entrada = scanner.nextLine();

        String[] pessoas = entrada.split(",");

        for (String pessoa : pessoas) {
            String[] dados = pessoa.split("-");

            if (dados.length == 2) {
                String nome = dados[0].trim();
                String sexo = dados[1].trim().toUpperCase();

                if (sexo.equals("M")) {
                    masculino.add(nome);
                } else if (sexo.equals("F")) {
                    feminino.add(nome);
                }
            }
        }

        System.out.println("\nGrupo Masculino:");
        for (String nome : masculino) {
            System.out.println(nome);
        }

        System.out.println("\nGrupo Feminino:");
        for (String nome : feminino) {
            System.out.println(nome);
        }

        scanner.close();
    }
}

