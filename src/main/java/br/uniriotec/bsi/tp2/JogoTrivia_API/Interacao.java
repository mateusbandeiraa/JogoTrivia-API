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
	 * <pre>
	 * Testa a opção escolhida pelo participante e a opção correta
	 * </pre>
	 * 
	 * @return Se a questao teve exito ou nao
	 */
	public boolean solucaoTeveExito() {
		return (this.opcaoSelecionada.equals(questao.getOpcaoCerta()));
	}

	/**
	 * Calcula a formula da pontuação de cada resposta correta
	 * 
	 * <pre>
	 * pontuacao = porcentagem do tempo restante em ralação ao tempo total
	 * </pre>
	 * 
	 * @return A pontuação que o participante ganhou ao responder a questão correta
	 */
	public int calcularPontuacao() {
		if (!solucaoTeveExito())
			return 0;
		else {
			long tempoGasto = (dataCriacao.getTime() - partida.getDataQuestaoAtual().getTime()) / 1000;
			int porcentagemUsada = (int) (tempoGasto * 100) / questao.getTempoDisponivel();
			int pontuacao = 100 - porcentagemUsada;
			return pontuacao;
		}
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public Opcao getOpcaoSelecionada() {
		return opcaoSelecionada;
	}

	public void setOpcaoSelecionada(Opcao opcaoSelecionada) {
		this.opcaoSelecionada = opcaoSelecionada;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Interacao(Questao questao, Opcao opcaoSelecionada, Date dataCriacao, Partida partida) {
		super();
		this.questao = questao;
		this.opcaoSelecionada = opcaoSelecionada;
		this.dataCriacao = dataCriacao;
		this.partida = partida;
	}

	@Override
	public String toString() {
		return "Interacao [questao=" + questao.getId() + ", opcaoSelecionada=" + opcaoSelecionada + ", dataCriacao="
				+ dataCriacao + ", partida=" + partida.getId() + ", solucaoTeveExito()=" + solucaoTeveExito()
				+ ", calcularPontuacao()=" + calcularPontuacao() + "]";
	}

}
