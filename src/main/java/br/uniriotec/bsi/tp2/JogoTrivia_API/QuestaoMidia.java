package br.uniriotec.bsi.tp2.JogoTrivia_API;

public class QuestaoMidia extends Questao {
    private String urlMidia;

    public QuestaoMidia() {
		super();
	}



	public QuestaoMidia(int id, String textoPergunta, String urlMidia, int tempoDisponivel, int tempoBonus, ConjuntoOrdenado opcoes,
			ConjuntoAlternativas conjuntoSolucao, ConjuntoMultiplo opcoesARemover) {
		super(id, textoPergunta, tempoDisponivel, tempoBonus, opcoes, conjuntoSolucao, opcoesARemover);
		this.urlMidia = urlMidia;
	}



	public QuestaoMidia(String textoPergunta, String urlMidia, int tempoDisponivel, int tempoBonus, ConjuntoOrdenado opcoes,
			ConjuntoAlternativas conjuntoSolucao, ConjuntoMultiplo opcoesARemover) {
		super(textoPergunta, tempoDisponivel, tempoBonus, opcoes, conjuntoSolucao, opcoesARemover);
		this.urlMidia = urlMidia;
	}



	@Override
	public String toString() {
		return "QuestaoMidia [urlMidia=" + urlMidia + ", super()=" + super.toString() + "]";
	}



	public String getUrlMidia() {
        return urlMidia;
    }

    public void setUrlMidia(String urlMidia) {
        this.urlMidia = urlMidia;
    }
}
