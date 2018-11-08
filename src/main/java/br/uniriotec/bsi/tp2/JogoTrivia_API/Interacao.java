package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.Date;
/**
 * 
 * @author Rafael Mota
 *
 */
public class Interacao {
	/**
	 * Questão que está sendo exibida no momento atual. Pode ser nula.
	 */
	private Questao questao;
	/**
	 * A resposta que o Objeto Participante escolhe	
	 */
	private Opcao opcaoSelecionada;
	/**
	 * Objeto Date que representa o momento em que a questão foi respondida
	 */
	private Date dataCriacao;
	/**
	 * O objeto Partida que representa a partida atual
	 */
	private Partida partida;
	/**
	 * <pre>Testa a opção escolhida pelo participante e a opção correta</pre>
	 * @return Se a questao teve exito ou nao
	 */
	public boolean solucaoTeveExito() {
		return (this.opcaoSelecionada.equals(questao.getOpcaoCerta()));
	}
/**
 * Calcula a formula da pontuação de cada resposta correta
 * <pre>pontuacao = porcentagem do tempo restante em ralação ao tempo total</pre>
 * @return A pontuação que o participante ganhou ao responder a questão correta0
 */
	public int calcularPontuacao() {
		if (!solucaoTeveExito())
			return 0;
		else {
			long tempoGasto = (dataCriacao.getTime() - 
					partida.getDataQuestaoAtual().getTime())/1000;
			int porcentagemUsada = (int)(tempoGasto *100)/questao.getTempoDisponivel();//TODO 
			int pontuacao = 100 - porcentagemUsada;
			return pontuacao;
		}
	}

}
