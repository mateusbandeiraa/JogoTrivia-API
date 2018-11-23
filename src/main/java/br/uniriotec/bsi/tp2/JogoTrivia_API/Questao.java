package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Questao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String textoPergunta;
	private int tempoDisponivel; // em segundos
	private int tempoBonus; // em segundos

	/**
	 * Um ConjuntoOrdenado é usado pois assim é assegurado que a ordem nas
	 * quais as Opções são exibidas sempre será a mesma.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	private ConjuntoOrdenado opcoes;

	/**
	 * Um ConjuntoAlternativas (genérico) é usado pois fica a cargo do autor da
	 * questão decidir qual é a melhor abordagem:
	 * <ol><li>Opção única (ConjuntoUnitario);</li><li>Opções múltiplas (ConjuntoMultiplo);</li><li>Opões ordenadas (ConjuntoOrdenado).</li></ol>
	 */
	@OneToOne(cascade = CascadeType.ALL)
	private ConjuntoAlternativas conjuntoSolucao;
	/**
	 * Um ConjuntoMultiplo é usado pois não importa a ordem que as opções serão removidas.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	private ConjuntoMultiplo opcoesARemover;
	private int quantidadeARemover;

	public Questao() {

	}

	public Questao(String textoPergunta, int tempoDisponivel, int tempoBonus) {
		this.textoPergunta = textoPergunta;
		this.tempoDisponivel = tempoDisponivel;
		this.tempoBonus = tempoBonus;
	}

	public Questao(String textoPergunta, int tempoDisponivel, int tempoBonus, ConjuntoOrdenado opcoes,
			ConjuntoAlternativas conjuntoSolucao, ConjuntoMultiplo opcoesARemover) {
		this(textoPergunta, tempoDisponivel, tempoBonus);
		this.opcoes = opcoes;
		this.conjuntoSolucao = conjuntoSolucao;
		this.opcoesARemover = opcoesARemover;
		this.quantidadeARemover = opcoesARemover.getOpcoes().size();
	}

	public Questao(int id, String textoPergunta, int tempoDisponivel, int tempoBonus, ConjuntoOrdenado opcoes,
			ConjuntoAlternativas conjuntoSolucao, ConjuntoMultiplo opcoesARemover) {
		this(textoPergunta, tempoDisponivel, tempoBonus, opcoes, conjuntoSolucao, opcoesARemover);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ConjuntoOrdenado getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(ConjuntoOrdenado opcoes) {
		this.opcoes = opcoes;
	}

	public ConjuntoAlternativas getConjuntoSolucao() {
		return conjuntoSolucao;
	}

	public void setConjuntoSolucao(ConjuntoAlternativas conjuntoSolucao) {
		this.conjuntoSolucao = conjuntoSolucao;
	}

	public ConjuntoMultiplo getOpcoesARemover() {
		return opcoesARemover;
	}

	public void setOpcoesARemover(ConjuntoMultiplo opcoesARemover) {
		this.opcoesARemover = opcoesARemover;
	}

	public int getQuantidadeARemover() {
		return quantidadeARemover;
	}

	public void setQuantidadeARemover(int quantidadeARemover) {
		this.quantidadeARemover = quantidadeARemover;
	}

	public double calcularTaxaDeAcerto(ArrayList<Interacao> interacoes) {
		int qtdCertas = 0;
		for (Interacao i : interacoes) {
			if (i.solucaoTeveExito())
				qtdCertas++;
		}
		return qtdCertas / interacoes.size() * 1.0;
	}

}