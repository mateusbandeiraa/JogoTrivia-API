package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;

public class Questao {
	private int id;
	private String textoPergunta;
	private int tempoDisponivel; // em segundos
	private int tempoBonus; // em segundos
	private ArrayList<Opcao> opcoes;
	private Opcao opcaoCerta;
	private int quantidadeARemover;

	public Questao(int id, String textoPergunta, int tempoDisponivel, int tempoBonus) {
		this(textoPergunta, tempoDisponivel, tempoBonus);
		this.id = id;
	}

	public Questao(String textoPergunta, int tempoDisponivel, int tempoBonus) {
		this.textoPergunta = textoPergunta;
		this.tempoDisponivel = tempoDisponivel;
		this.tempoBonus = tempoBonus;
	}	

	@Override
	public String toString() {
		return "Questao [id=" + id + ", textoPergunta=" + textoPergunta + ", tempoDisponivel=" + tempoDisponivel
				+ ", tempoBonus=" + tempoBonus + ", opcoes=" + opcoes + ", opcaoCerta=" + opcaoCerta
				+ ", quantidadeARemover=" + quantidadeARemover + "]";
	}

	public String getTextoPergunta() {
		return textoPergunta;
	}

	public void setTextoPergunta(String textoPergunta) {
		this.textoPergunta = textoPergunta;
	}

	public int getTempoDisponivel() {
		return tempoDisponivel;
	}

	public void setTempoDisponivel(int tempoDisponivel) {
		this.tempoDisponivel = tempoDisponivel;
	}

	public int getTempoBonus() {
		return tempoBonus;
	}

	public void setTempoBonus(int tempoBonus) {
		this.tempoBonus = tempoBonus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Opcao getOpcaoCerta() {
		return opcaoCerta;
	}

	public int getQuantidadeARemover() {
		return quantidadeARemover;
	}

	public void setQuantidadeARemover(int quantidadeARemover) {
		this.quantidadeARemover = quantidadeARemover;
	}

	public ArrayList<Opcao> getOpcoes() {
		return opcoes;
	}

	public void setOpcaoCerta(Opcao opcaoCerta) {
		this.opcaoCerta = opcaoCerta;
	}

	/**
	 * Recebe a base de opcoes e armazena
	 * Salva no atributo respostaCerta a opção correta
	 * 
	 * @param opcoes
	 */
	public void setOpcoes(ArrayList<Opcao> opcoes) {
		this.opcoes = opcoes;
		for (Opcao op : opcoes) {
			if (op.estaCerto())
				this.opcaoCerta = op;
			if (op.ehRemovivel())
				this.quantidadeARemover++;
		}
	}

	public double calcularTaxaDeAcerto(ArrayList<Interacao> interacoes) {
		int qtdCertas = 0;
		for (Interacao i : interacoes) {
			if (i.solucaoTeveExito())
				qtdCertas++;
		}
		return qtdCertas / interacoes.size() * 1.0;
	}

	public ArrayList<Opcao> obterOpcoesARemover() {
		ArrayList<Opcao> opcoesARemover = new ArrayList<Opcao>();
		for (Opcao op : opcoes) {
			if (op.ehRemovivel())
				opcoesARemover.add(op);
		}
		return opcoesARemover;
	}

}