package br.com.marco.todolist;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();

        int opcao;

        do {
            System.out.println("""
                    ======= GERENCIADOR DE TAREFAS =======
                    1 - Adicionar tarefa
                    2 - Listar tarefas
                    3 - Concluir tarefa
                    4 - Remover tarefa
                    0 - Sair""");
            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1:
                    System.out.print("\nAdicione a descrição da nova tarefa: ");
                    String descricao = scanner.nextLine();
                    gerenciador.adicionarTarefa(descricao);
                    break;

                case 2:
                    gerenciador.listarTarefas();
                    break;

                case 3:
                    gerenciador.listarTarefas();
                    System.out.print("\nInforme qual tarefa deseja concluir: ");
                    int indConcluir = scanner.nextInt() - 1;
                    gerenciador.concluirTarefa(indConcluir);
                    break;

                case 4:
                    gerenciador.listarTarefas();
                    System.out.print("\nInforme qual tarefa deseja remover:");
                    int indRemover = scanner.nextInt() - 1;
                    gerenciador.removerTarefa(indRemover);
                    break;

                case 0:
                    System.out.print("\nSaindo do sistema...");
                    break;

                default:
                    System.out.print("\nOpção inválida.");
            }
        } while (opcao != 0);
        scanner.close();
    }
}