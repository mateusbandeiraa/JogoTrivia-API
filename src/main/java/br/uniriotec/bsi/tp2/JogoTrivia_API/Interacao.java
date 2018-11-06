package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.Date;
/**
 * 
 * @author Rafael Mota
 *
 */
public class Interacao {

	private Questao questao;
	private Solucao solucao;
	private Date dataCriacao;
	private Partida partida;

	public boolean solucaoTeveExito() {
		return (this.solucao.equals(questao.getSolucaoOficial()));//TODO
	}

	public int calcularPontuacao() {
		if (!solucaoTeveExito())
			return 0;
		else {
			long tempoGasto = (dataCriacao.getTime() - 
					partida.getDataQuestaoAtual().getTime())/1000;
			int porcentagemUsada = (tempoGasto *100)/questao.getTempoDisponivel();//TODO 
			int pontuacao = 100 - porcentagemUsada;
			return pontuacao;
		}
	}

}
