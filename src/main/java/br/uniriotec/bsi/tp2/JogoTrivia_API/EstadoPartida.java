package br.uniriotec.bsi.tp2.JogoTrivia_API;
/**
 * Representa os estados possíveis de uma partida e quais ações podem ser tomadas em cada um deles.
 * @author Mateus Bandeira
 *
 */
public enum EstadoPartida {
	DISPONIVEL(true, false), EM_ANDAMENTO(false, true), ENCERRADO(false, false);
	
	final boolean aceitaNovoParticipante;
	final boolean podeMostrarQuestao;
	
	private EstadoPartida(boolean aceitaNovoParticipante, boolean podeMostrarQuestao) {
		this.aceitaNovoParticipante = aceitaNovoParticipante;
		this.podeMostrarQuestao = podeMostrarQuestao;
	}
	
}
