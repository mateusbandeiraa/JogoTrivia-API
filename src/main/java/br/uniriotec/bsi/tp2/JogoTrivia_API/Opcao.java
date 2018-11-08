package br.uniriotec.bsi.tp2.JogoTrivia_API;

public class Opcao {
    private final Questao questao;
    private final int id;
    private String texto;
    private final boolean estaCerto;

    public Opcao(Questao questao, int id, String texto, boolean estaCerto) {
        this.questao = questao;
        this.id = id;
        this.texto = texto;
        this.estaCerto = estaCerto;
    }

    public Questao getQuestao() {
        return questao;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public boolean estaCerto() {
        return estaCerto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
}
