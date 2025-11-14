package br.com.marco.todolist;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeTarefas {
    private List<Tarefa> tarefas;
    private final String NOME_ARQUIVO = "tarefas.txt";

    public GerenciadorDeTarefas() {
        this.tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(String descricao) {
        Tarefa novaTarefa = new Tarefa(descricao);
        tarefas.add(novaTarefa);
        System.out.println("\nTarefa adicionada com sucesso.");
    }

    public void listarTarefas() {
        if (tarefas.isEmpty()){
            System.out.println("\nSem nenhuma tarefa.");
        } else {
            System.out.println("Lista de Tarefas: ");
            for (int i = 0; i < tarefas.size(); i++) {
                System.out.println((i + 1) + " - " + tarefas.get(i));
            }
        }
    }

    public void concluirTarefa(int indice) {
        if (indice >= 0 && indice < tarefas.size()){
            tarefas.get(indice).marcarComoConcluida();
            System.out.println("\nTarefa marcada como concluída.");
        } else {
            System.out.println("\nTarefa não encontrada.");
        }
    }

    public void removerTarefa(int indice) {
        if (indice >= 0 && indice < tarefas.size()){
            tarefas.remove(indice);
            System.out.println("\nTarefa removida com sucesso.");
        } else {
            System.out.println("\nTarefa não encontrada.");
        }
    }

    //Metodo para carregar tarefas do arquivo
    private void carregarTarefas() {
        //Cria um objeto File que representa o caminho/arquivo cujo nome está em NOME_ARQUIVO (ex: "tarefas.txt").
        File arquivo = new File(NOME_ARQUIVO);

        //Sai do metodo caso o arquivo não exista
        if (!arquivo.exists()){
            return;
        }

        //bloco try que declara um recurso (leitor) que será fechado automaticamente ao final do bloco
        //new BufferedReader(...) envolve o FileReader para ler linhas inteiras com mais eficiência
        //new FileReader(arquivo) cria um FileReader para ler bytes do arquivo e convertê-los em caracteres
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            //vai receber cada linha do arquivo
            String linha;

            //Lê uma linha do arquivo com leitor.readLine() e atribui a linha
            while ((linha = leitor.readLine()) != null){
                //Formato: descricao;concluida
                String[] partes = linha.split(";");     //linha.split(";") divide a string em um array de substrings usando ; como separador

                if (partes.length == 2){       //Verifica se o split gerou exatamente 2 partes. Isso evita ArrayIndexOutOfBoundsException caso a linha esteja mal formatada.
                    Tarefa tarefa = new Tarefa(partes[0]);      //Cria uma nova instância de Tarefa usando a primeira parte (partes[0]) como descrição
                    if (Boolean.parseBoolean(partes[1])){       //Boolean.parseBoolean(partes[1]) converte a string da segunda parte para boolean. Aceita "true" (case-insensitive) como true, qualquer outra coisa vira false
                        tarefa.marcarComoConcluida();
                    }
                    tarefas.add(tarefa);
                }
            }
        } catch (IOException e) {       //Se ocorrer qualquer problema na leitura (arquivo bloqueado, permissão negada, etc.), a exceção é capturada e a mensagem de erro é impressa no console, sem encerrar o programa abruptamente
            System.out.println("Erro ao carregar tarefas: " + e.getMessage());
        }
    }

    //Metodo para salvar as tarefas no arquivo
    private void salvarTarefas() {
        //BufferedWriter: classe usada para escrever texto em arquivos de forma eficiente.
        //FileWriter: Cria (ou sobrescreve) o arquivo indicado pela variável NOME_ARQUIVO — que deve ser algo como "tarefas.txt"
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Tarefa tarefa : tarefas) {
                //Escreve no arquivo uma linha contendo:
                //A descrição da tarefa (ex: "Estudar Java")
                //Um ponto e vírgula (;) — serve como separador.
                //E o estado da tarefa
                escritor.write(tarefa.getDescricao() + ";" + tarefa.getConcluida());
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar as tarefas: " + e.getMessage());
        }
    }
}
