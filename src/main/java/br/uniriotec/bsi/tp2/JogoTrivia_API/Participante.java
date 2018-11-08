package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * @author Rafael Mota
 * @author Mateus Bandeira
 */
public class Participante {
	/**
	 * Titulo que o participante escolhe
	 */
	private String nickname;
	private String chave;
	/**
	 * A quantidade de vezes que o participante usou o bonus de Tempo extra para perguntas
	 */
	private int ajudasTempoBonusUsadas;
	/**
	 * A quantidade de vezes que o participante usou o bonus de Remover Opção para a perguntas  
	 */
	private int ajudasRemoverOpcoesUsadas;
	/**
	 * 
	 */
	private ArrayList<Interacao> interacoes;
	private Partida partida;
	
	/**
	 * Comparator usado para ordenar uma coleção de Participantes usando como critério a pontuação DECRESCENTE.
	 */
	public static final Comparator<Participante> COMPARATOR_PONTUACAO = new Comparator<Participante>() {
		public int compare(Participante o1, Participante o2) {
			return o2.pontuacaoTotal() - o1.pontuacaoTotal();
		}
		
	};

	public void adicionarInteracao(Interacao i) {
		if (interacoes == null)
			interacoes = new ArrayList<>();
		this.interacoes.add(i);
	}

	public int pontuacaoTotal() {
		int soma = 0;
		for (Interacao interacao : interacoes) {
			soma += interacao.calcularPontuacao();
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public int getAjudasTempoBonusUsadas() {
		return ajudasTempoBonusUsadas;
	}

	public void setAjudasTempoBonusUsadas(int ajudasTempoBonusUsadas) {
		this.ajudasTempoBonusUsadas = ajudasTempoBonusUsadas;
	}

	public int getAjudasRemoverOpcoesUsadas() {
		return ajudasRemoverOpcoesUsadas;
	}

	public void setAjudasRemoverOpcoesUsadas(int ajudasRemoverOpcoesUsadas) {
		this.ajudasRemoverOpcoesUsadas = ajudasRemoverOpcoesUsadas;
	}

	public ArrayList<Interacao> getInteracoes() {
		return interacoes;
	}

	public void setInteracoes(ArrayList<Interacao> interacoes) {
		this.interacoes = interacoes;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Participante(String nickname, int ajudasTempoBonusUsadas, int ajudasRemoverOpcoesUsadas, Partida partida) {
		this.nickname = nickname;
		this.ajudasTempoBonusUsadas = ajudasTempoBonusUsadas;
		this.ajudasRemoverOpcoesUsadas = ajudasRemoverOpcoesUsadas;
		this.partida = partida;
	}
	

}
