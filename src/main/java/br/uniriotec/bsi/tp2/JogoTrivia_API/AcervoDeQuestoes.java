package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;

public class AcervoDeQuestoes {
    private final int id;
    private String nome;
    private ArrayList<Questao> questoes;

    public AcervoDeQuestoes(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public void adicionarQuestao (Questao q) {
        this.questoes.add(q);
    }
    
    public void removerQuestao (Questao q) {
        this.questoes.remove(q);
    }
    
    public ArrayList<Questao> exportaQuestoes () {
        return this.questoes;
    }
}
