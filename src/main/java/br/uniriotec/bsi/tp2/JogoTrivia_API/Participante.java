package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;

/**
 * 
 * @author Rafael Mota
 *
 */
public class Participante {
	private String nickname;
	private String chave;
	private int ajudasTempoBonusUsadas;
	private int ajudasRemoverOpcoesUsadas;
	private ArrayList<Interacao> interacoes;
	private Partida partida;

	public void adicionarInteracao(Interacao i) {
		if (interacoes == null)
			interacoes = new ArrayList<>();
		this.interacoes.add(i);
	}

	public int pontuacaoTotal() {
		int soma = 0;
		for (Interacao interacao : interacoes) {
			soma += interacao.pontuacao();
		}
		return soma;
	}

	public int ajudasTempoBonusDisponiveis() {
		return (partida.getLimiteAjudasTempoBonus() - ajudasTempoBonusUsadas);
	}

	public boolean podeUsarAjudaTempoBonus() {
		return (ajudasTempoBonusDisponiveis() < 1);
	}

	public int ajudasRemoverOpcoesDisponiveis() {
		return (partida.getLimiteAjudasRemoverOpcoes() - ajudasRemoverOpcoesUsadas);
	}

	public boolean podeUsarAjudaRemoverOpcoes() {
		return (ajudasRemoverOpcoesDisponiveis() < 1);
	}

	public void usarAjudaTempoBonus() {
		// TODO
	}

	public void usarAjudaRemoverOpcoes() {
		// TODO
	}

}
