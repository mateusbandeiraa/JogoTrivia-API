package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

@Entity
public class Questao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String textoPergunta;
	private int tempoDisponivel; // em segundos
	private int tempoBonus; // em segundos
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderColumn()
	private ConjuntoDeAlternativas listaDeAlternativas;
	private int quantidadeARemover;

	public Questao() {

	}

	public Questao(int id, String textoPergunta, int tempoDisponivel, int tempoBonus) {
		this(textoPergunta, tempoDisponivel, tempoBonus);
		this.id = id;
	}

	public Questao(String textoPergunta, int tempoDisponivel, int tempoBonus) {
		this.textoPergunta = textoPergunta;
		this.tempoDisponivel = tempoDisponivel;
		this.tempoBonus = tempoBonus;
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

	public ArrayList<Opcao> obterOpcoesARemover() {
		ArrayList<Opcao> opcoesARemover = new ArrayList<Opcao>();
		for (Opcao op : opcoes) {
			if (op.ehRemovivel())
				opcoesARemover.add(op);
		}
		return opcoesARemover;
	}

	public void setListaDeAlternativas(ConjuntoDeAlternativas listaDeAlternativas) {
		this.listaDeAlternativas = listaDeAlternativas;
	}

}