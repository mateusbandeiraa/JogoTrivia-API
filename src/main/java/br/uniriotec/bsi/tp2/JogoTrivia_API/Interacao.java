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
	private Opcao opcaoSelecionada;
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
    public Interacao() {
    }

    public Interacao(Questao questao, ConjuntoDeAlternativas solucao, Date dataCriacao, Partida partida) {
        this.questao = questao;
        this.solucao = solucao;
        this.dataCriacao = dataCriacao;
        this.partida = partida;
    }

    public Interacao(int id, Questao questao, ConjuntoDeAlternativas solucao, Date dataCriacao, Partida partida) {
        this.id = id;
        this.questao = questao;
        this.solucao = solucao;
        this.dataCriacao = dataCriacao;
        this.partida = partida;
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

    public ConjuntoDeAlternativas getSolucao() {
        return solucao;
    }

    public void setSolucao(ConjuntoDeAlternativas solucao) {
        this.solucao = solucao;
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
        
    /**
     * <pre>
     * Testa a opção escolhida pelo participante e a opção correta
     * </pre>
     * 
     * @return Se a questao teve exito ou nao
     */
    public boolean solucaoTeveExito() {
        return (calcularPontuacaoResposta() != 0);
    }

    /**
     * Calcula a formula da pontuação de cada resposta correta
     * 
     * <pre>
     * pontuacaoTempo = porcentagem do tempo restante em ralação ao tempo total
     * calcularPontuacaoResposta() = pontos recebidos depende da quantidade de acertos da questao
     * </pre>
     * 
     * @return A pontuação que o participante ganhou ao responder a questão correta
     */

    public int calcularPontuacaoResposta () {
        return questao.getListaDeAlternativas().CalcularPontuacaoResposta(solucao);
    }


    public int calcularPontuacao() {
            if (!solucaoTeveExito())
                    return 0;
            else {
                    long tempoGasto = tempoGastoNaInteracao();
                    int porcentagemUsada = (int) ((tempoGasto / 1000) * 100) / questao.getTempoDisponivel();// Alterado
                    int pontuacaoTempo = 100 - porcentagemUsada;
                    return pontuacaoTempo + calcularPontuacaoResposta();
            }
    }

    /**
     * @return tempo Gasto na interação (em MS)
     */
    public long tempoGastoNaInteracao() {
            long tempoGastoNaInteracao = (dataCriacao.getTime() - partida.getDataQuestaoAtual().getTime()) / 1000;
            return tempoGastoNaInteracao;
    }

}
