package br.uniriotec.bsi.tp2.JogoTrivia_API;

public class Opcao {
    private final Questao questao;
    private final int id;
    private String texto;
    private final boolean estaCerto;
    private boolean ehRemovivel;

    public Opcao(Questao questao, int id, String texto, boolean estaCerto, boolean ehRemovivel) {
        this.questao = questao;
        this.id = id;
        this.texto = texto;
        this.estaCerto = estaCerto;
        this.ehRemovivel = ehRemovivel;
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

    public boolean ehRemovivel() {
        return ehRemovivel;
    }

    public void setEhRemovivel(boolean ehRemovivel) {
        this.ehRemovivel = ehRemovivel;
    }
    
    public boolean estaCerto() {
        return estaCerto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
}
