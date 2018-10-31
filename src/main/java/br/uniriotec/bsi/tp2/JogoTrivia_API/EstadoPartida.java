package br.uniriotec.bsi.tp2.JogoTrivia_API;
/**
 * Representa os estados possíveis de uma partida e quais ações podem ser tomadas em cada um deles.
 * @author Mateus Bandeira
 *
 */
public enum EstadoPartida {
	DISPONIVEL(true, false, false), EM_ANDAMENTO(false, true, true), ENCERRADO(false, false, false);
	
	final boolean aceitaNovoParticipante;
	final boolean podeMostrarQuestao;
	final boolean podeEncerrarPartida;
	
	private EstadoPartida(boolean aceitaNovoParticipante, boolean podeMostrarQuestao, boolean podeEncerrarPartida) {
		this.aceitaNovoParticipante = aceitaNovoParticipante;
		this.podeMostrarQuestao = podeMostrarQuestao;
		this.podeEncerrarPartida = podeEncerrarPartida;
	}
	
}
