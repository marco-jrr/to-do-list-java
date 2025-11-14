package br.com.marco.todolist;

public class Tarefa {
    private String descricao;
    private boolean concluida;

    public Tarefa(String descricao) {
        setDescricao(descricao);
        setConcluida(false);
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public void marcarComoConcluida() {
        this.concluida = true;
    }

    //Demonstra de maneira estilizada se a tarefa está concluída
    public String toString() {
        return (getConcluida() ? "[X] " : "[] ") + getDescricao();
    }

}
