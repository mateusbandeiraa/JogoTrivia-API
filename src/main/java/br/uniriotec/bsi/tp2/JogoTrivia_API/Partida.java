package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * Representa uma partida em andamento.
 * 
 * @author Mateus Bandeira
 *
 */
public class Partida {
	private int id;
	/**
	 * Questão que está sendo exibida no momento atual. Pode ser nula.
	 */
	private Questao questaoAtual;
	/**
	 * Número da questão atual. Inicia em 1, logo, é igual ao índice da
	 * <tt>questaoAtual</tt> no <tt>JOGO_MODELO.questoes</tt> - 1. Inicia em
	 * zero.
	 */
	private int numeroQuestaoAtual = 0;
	/**
	 * Objeto Date que representa o momento em que a questão atual foi aberta.
	 */
	private Date dataQuestaoAtual;
	/**
	 * Limite de ajudas que podem ser usadas no jogo. Inicialmente é igual ao
	 * <tt>JOGO_MODELO.limite...</tt>, mas pode ser personalizado via
	 * construtor.
	 */
	private int limiteAjudasRemoverOpcoes;
	/**
	 * Limite de ajudas que podem ser usadas no jogo. Inicialmente é igual ao
	 * <tt>JOGO_MODELO.limite...</tt>, mas pode ser personalizado via
	 * construtor.
	 */
	private int limiteAjudasTempoBonus;

	/**
	 * Modelo de jogo em que a partida será baseada.
	 */
	private final Jogo JOGO_MODELO;
	/**
	 * Estado atual da partida. Útil para testar se certas ações são permitidas no
	 * momento atual.
	 */
	private EstadoPartida estadoAtual;
	/**
	 * HashSet de todos os participantes da partida.
	 */
	private HashSet<Participante> participantes;

	public Partida(int id, Jogo jogoModelo) {
		this.id = id;
		this.JOGO_MODELO = jogoModelo;
		this.estadoAtual = EstadoPartida.DISPONIVEL;
		participantes = new HashSet<>();
	}

	public Partida(int id, Jogo jogoModelo, int limiteAjudasRemoverOpcoes, int limiteAjudasTempoBonus) {
		this(id, jogoModelo);
		this.setLimiteAjudasRemoverOpcoes(limiteAjudasRemoverOpcoes);
		this.setLimiteAjudasTempoBonus(limiteAjudasTempoBonus);
	}

	/**
	 * Adiciona novo participante à partida.
	 * 
	 * @param participante
	 *            Participante a adicionar.
	 * @throws IllegalArgumentException
	 *             Caso o nickname já estiver em uso nesta partida.
	 */
	public void adicionarParticipante(Participante participante) throws IllegalArgumentException {
		if (!estadoAtual.aceitaNovoParticipante)
			throw new IllegalStateException("O estado atual da partida não permite o ingresso de novos participantes.");

		if (nicknameEstaDisponivel(participante)) {
			participantes.add(participante);
		} else {
			throw new IllegalArgumentException("Nickname já está em uso.");
		}

	}

	/**
	 * Avança para a proxima questão.
	 */
	public void proximaQuestao() {
		if (!estadoAtual.podeMostrarQuestao)
			throw new IllegalStateException("Estado atual não permite mostrar próxima questão.");
		if (estaNaUltimaQuestao()) {
			throw new NoSuchElementException("Não há mais questões a serem exibidas.");
		}
		numeroQuestaoAtual++;
		// O numeroQuestaoAtual é decrescido em 1 porque a variavel representa
		// as questoes iniciando em 1.
		this.questaoAtual = JOGO_MODELO.getQuestaoPorIndice(numeroQuestaoAtual - 1);
		this.dataQuestaoAtual = new Date();
	}
	/**
	 * Verifica se a questão atual é a última da lista.
	 */
	public boolean estaNaUltimaQuestao() {
		return numeroQuestaoAtual == JOGO_MODELO.totalDeQuestoes();
	}

	/**
	 * Calcula o tempo para registro de resposta para a questão atual.
	 * 
	 * @return Date com o tempo máximo de registro.
	 */
	public Date obterDataMaximaParaResposta() {
		Long tempoEmMS = dataQuestaoAtual.getTime() + (questaoAtual.getTempoDisponivel() * 1000);
		return new Date(tempoEmMS);
	}

	/**
	 * Percorre toda a lista de participantes e verifica se o nickname do
	 * parâmetro é único.
	 * 
	 * @param participante
	 *            Objeto com o nickname a pesquisar.
	 * @return Verdadeiro se o nickname estiver disponível para uso. Falso se já
	 *         estiver em uso.
	 */
	private boolean nicknameEstaDisponivel(Participante participante) {
		for (Participante p : participantes) {
			if (p.getNickname().equals(participante.getNickname()))
				return false;
		}
		return true;
	}
	
	/**
	 * Inicia a partida e avança para a primeira Questão.
	 */
	public void iniciarPartida() {
		if(estadoAtual != EstadoPartida.DISPONIVEL)
			throw new IllegalStateException("Estado atual não permite o início da partida.");
		estadoAtual = EstadoPartida.EM_ANDAMENTO;
		proximaQuestao();
	}

	/**
	 * Encerra a partida. Para encerrar, o estado da partida precisa ser
	 * <tt>EM_ANDAMENTO</tt> e a última questão precisa estar fechada.
	 */
	public void encerrarPartida() {
		if (!estadoAtual.podeEncerrarPartida)
			throw new IllegalStateException("Estado atual não permite o encerramento da partida.");
		if (numeroQuestaoAtual != JOGO_MODELO.totalDeQuestoes()
				&& obterDataMaximaParaResposta().before(new Date())) // Checa se a data máxima para a resposta 
																	 // da última questão aconteceu antes do momento atual.
			throw new IllegalStateException("Não é possível encerrar um jogo que não encerrou todas as questões.");
		this.estadoAtual = EstadoPartida.ENCERRADO;
	}

	/**
	 * Ordena uma ArrayList de todos os participantes baseados na pontuação de cada e retorna os 10 melhores colocados.
	 * @return ArrayList com os 10 melhores colocados, com o melhor na posição zero e o pior na posição 10.
	 */
	public ArrayList<Participante> obterClassificacao() {
		ArrayList<Participante> classificacao = new ArrayList<Participante>(participantes);
		classificacao.sort(Participante.COMPARATOR_PONTUACAO);
		classificacao = (ArrayList<Participante>) classificacao.subList(0, Math.min(10, classificacao.size() + 1));
		return classificacao;
	}

	public int getId() {
		return id;
	}

	public Questao getQuestaoAtual() {
		return questaoAtual;
	}	

	public int getNumeroQuestaoAtual() {
		return numeroQuestaoAtual;
	}

	public Date getDataQuestaoAtual() {
		return dataQuestaoAtual;
	}

	public int getLimiteAjudasRemoverOpcoes() {
		return limiteAjudasRemoverOpcoes;
	}

	private void setLimiteAjudasRemoverOpcoes(int limiteAjudasRemoverOpcoes) {
		if (limiteAjudasRemoverOpcoes < 0)
			throw new IllegalArgumentException("O limite não pode ser menor que zero.");
		this.limiteAjudasRemoverOpcoes = limiteAjudasRemoverOpcoes;
	}

	public int getLimiteAjudasTempoBonus() {
		return limiteAjudasTempoBonus;
	}

	private void setLimiteAjudasTempoBonus(int limiteAjudasTempoBonus) {
		if (limiteAjudasTempoBonus < 0)
			throw new IllegalArgumentException("O limite não pode ser menor que zero.");
		this.limiteAjudasTempoBonus = limiteAjudasTempoBonus;
	}

	public Jogo getJogoModelo() {
		return JOGO_MODELO;
	}

}
