package br.uniriotec.bsi.tp2.JogoTrivia_API;

public class QuestaoMidia extends Questao {
    private String urlMidia;

    public QuestaoMidia(String urlMidia, int id, String textoPergunta, int tempoDisponivel, int tempoBonus) {
        super(id, textoPergunta, tempoDisponivel, tempoBonus);
        this.urlMidia = urlMidia;
    }

    public String getUrlMidia() {
        return urlMidia;
    }

    public void setUrlMidia(String urlMidia) {
        this.urlMidia = urlMidia;
    }
}
