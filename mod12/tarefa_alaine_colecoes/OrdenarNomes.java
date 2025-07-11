package mod12.tarefa_alaine_colecoes;

import java.util.Arrays;
import java.util.Scanner;

public class OrdenarNomes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite nomes separados por vírgula:");
        String entrada = scanner.nextLine();

        String[] nomes = entrada.split(",");

        Arrays.sort(nomes);

        System.out.println("Nomes em ordem alfabética:");
        for (String nome : nomes) {
            System.out.println(nome.trim());
        }

        scanner.close();
    }
}

