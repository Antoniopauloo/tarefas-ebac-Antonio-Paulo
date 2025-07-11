package mod11.colecoes_parte2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SepararGrupos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> grupoMasculino = new ArrayList<>();
        List<String> grupoFeminino = new ArrayList<>();

        System.out.println("Digite os dados (nome,sexo) separados por vírgula. Digite 'sair' para terminar:");

        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("sair")) {
                break;
            }

            String[] dados = entrada.split(",");
            if (dados.length == 2) {
                String nome = dados[0].trim();
                String sexo = dados[1].trim().toLowerCase();

                if (sexo.equals("masculino")) {
                    grupoMasculino.add(nome);
                } else if (sexo.equals("feminino")) {
                    grupoFeminino.add(nome);
                } else {
                    System.out.println("Sexo inválido. Informe 'masculino' ou 'feminino'.");
                }
            } else {
                System.out.println("Formato inválido. Use: nome,sexo");
            }
        }

        System.out.println("\nGrupo Masculino:");
        for (String nome : grupoMasculino) {
            System.out.println(nome);
        }

        System.out.println("\nGrupo Feminino:");
        for (String nome : grupoFeminino) {
            System.out.println(nome);
        }

        scanner.close();
    }
}

