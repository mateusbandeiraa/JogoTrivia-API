package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Rafael Mota
 *
 */
@Entity
public class Interacao {
	@Id
	@GeneratedValue
	private int id;
	/**
	 * Questão que está sendo exibida no momento atual. Pode ser nula.
	 */
	@ManyToOne
	private Questao questao;
	/**
	 * A resposta que o Objeto Participante escolhe
	 */
	@ManyToOne
	private ConjuntoAlternativas conjuntoSolucao;
	/**
	 * Objeto Date que representa o momento em que a questão foi respondida
	 */
	private Date dataCriacao;
	/**
	 * O objeto Partida que representa a partida atual
	 */
	@ManyToOne
	private Partida partida;

	@ManyToOne
	private Participante participante;

	/**
	 * <pre>
	 * Testa a opção escolhida pelo participante e a opção correta
	 * </pre>
	 * 
	 * @return Se a questao teve exito ou nao
	 */
	public boolean solucaoTeveExito() {
		return (this.conjuntoSolucao.equals(questao.getConjuntoSolucao()));
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
			long tempoGasto = tempoGastoNaInteracao();
			int porcentagemUsada = (int) ((tempoGasto / 1000) * 100) / questao.getTempoDisponivel();// Alterado
			int pontuacao = 100 - porcentagemUsada;
			return pontuacao;
		}
	}

	/**
	 * @return tempo Gasto na interação (em MS)
	 */
	public long tempoGastoNaInteracao() {
		long tempoGastoNaInteracao = (dataCriacao.getTime() - partida.getDataQuestaoAtual().getTime()) / 1000;
		return tempoGastoNaInteracao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public ConjuntoAlternativas getConjuntoSolucao() {
		return conjuntoSolucao;
	}

	public void setConjuntoSolucao(ConjuntoAlternativas conjuntoSolucao) {
		this.conjuntoSolucao = conjuntoSolucao;
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

	public Interacao() {

	}

	public Interacao(Questao questao, Participante participante, ConjuntoAlternativas conjuntoSolucao, Date dataCriacao,
			Partida partida) throws TempoEsgotadoException {
		if (partida.obterDataMaximaParaResposta().before(dataCriacao))
			throw new TempoEsgotadoException(
					"Não é possível instanciar a Interação após o término do tempo estipulado na questão.");
		this.questao = questao;
		this.participante = participante;
		this.conjuntoSolucao = conjuntoSolucao;
		this.dataCriacao = dataCriacao;
		this.partida = partida;

	}

	@Override
	public String toString() {
		return "Interacao [questao=" + questao.getId() + ", conjuntoSolucao=" + conjuntoSolucao + ", dataCriacao="
				+ dataCriacao + ", partida=" + partida.getId() + ", solucaoTeveExito()=" + solucaoTeveExito()
				+ ", calcularPontuacao()=" + calcularPontuacao() + "]";
	}

}
