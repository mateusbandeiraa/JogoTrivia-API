package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 * Classe principal, responsável por reger o funcionamento do jogo.
 * 
 * @author Mateus Bandeira
 *
 */
public class Jogo {

	private int id;
	/**
	 * Nome do jogo. Será exibido para o usuário.
	 */
	private String nome;
	/**
	 * Data e hora prevista para o início do jogo.
	 */
	private Date dataAbertura;
	/**
	 * Descreve o estado que o jogo está (FUTURO, EM_ANDAMENTO_ENCERRADO).
	 */
	private EstadoJogo estado;
	/**
	 * Objeto referente à questão ativa.
	 */
	private Questao questaoAtual;
	/**
	 * Índice no ArrayList sessoes referente à questão ativa.
	 */
	private int numeroQuestaoAtual = 0;
	/**
	 * Data e hora em que a questão atual foi ativa.
	 */
	private Date dataQuestaoAtual;
	/**
	 * Limite de ajudas específicas que o usuário pode utilizar.
	 */
	int limiteAjudasTempoBonus;
	int limiteAjudasRemoverOpcoes;

	ArrayList<Sessao> sessoes;
	ArrayList<Questao> questoes;

	/**
	 * Tempo de tolerância entre o prazo oficial de envio de resposta e o
	 * encerramento efetivo dos recebimentos.
	 */
	private static final int TOLERANCIA_EM_MILISSEGUNDOS = 750;

	/**
	 * Tempo entre o encerramento de uma pergunta e o início de outra.
	 */
	private static final int TEMPO_ENTRE_PERGUNTAS_EM_SEGUNDOS = 8;

	/**
	 * Constroi um novo objeto Jogo. Um novo jogo não pode ser marcado em tempo
	 * passado e inicia com <tt>estado</tt> em <tt>EstadoJogo.FUTURO</tt>
	 * 
	 * @param nome
	 *            Nome do Jogo, que pode ser exibido ao usuário.
	 * @param dataAbertura
	 *            Data prevista para o início do jogo.
	 * @param limiteAjudasTempoBonus
	 * @param limiteAjudasRemoverOpcoes
	 */
	public Jogo(String nome, Date dataAbertura, int limiteAjudasTempoBonus, int limiteAjudasRemoverOpcoes) {
		this.setNome(nome);
		this.setDataAbertura(dataAbertura);
		this.setlimiteAjudasTempoBonus(limiteAjudasTempoBonus);
		this.setLimiteAjudasRemoverOpcoes(limiteAjudasRemoverOpcoes);
		this.setEstado(EstadoJogo.FUTURO);
	}

	/**
	 * Constroi um novo objeto Jogo. Um novo jogo não pode ser marcado em tempo
	 * passado e inicia com <tt>estado</tt> em <tt>EstadoJogo.FUTURO</tt>
	 * 
	 * @param nome
	 *            Nome do Jogo, que pode ser exibido ao usuário.
	 * @param dataAbertura
	 *            Data prevista para o início do jogo.
	 * @param limiteAjudasTempoBonus
	 * @param questoes
	 *            <tt>ArrayList</tt> contendo as questões.
	 */
	public Jogo(String nome, Date dataAbertura, int limiteAjudasTempoBonus, int limiteAjudasRemoverOpcoes,
			ArrayList<Questao> questoes) {
		this(nome, dataAbertura, limiteAjudasTempoBonus, limiteAjudasRemoverOpcoes);
		this.setQuestoes(questoes);
	}

	/**
	 * Adiciona uma questão ao jogo.
	 * O jogo precisa ter estado FUTURO para adicionar uma questão.
	 * Adicionar uma questão duplicada resultará em uma exceção.
	 * 
	 * @param q
	 *            Questão a adicionar.
	 */
	public void adicionarQuestao(Questao q) {
		if (questoes == null)
			questoes = new ArrayList<>();
		if (estado != EstadoJogo.FUTURO)
			throw new EstadoDeJogoInvalidoException(this,
					"Tentativa de adição de questão em jogo com estado diferente de FUTURO. Ação ignorada.",
					new Object[] { q });
		if (questoes.contains(q)) {
			throw new ItemDuplicadoException(this, "Tentativa de adição de questão duplicada. Ação ignorada.",
					new Object[] { q });
		} else {
			questoes.add(q);
		}
	}

	/**
	 * Adiciona uma sessão ao jogo.
	 * 
	 * @param s
	 *            Sessão
	 */
	public void adicionarSessao(Sessao s) {
		if (sessoes == null)
			sessoes = new ArrayList<>();
		if (estado == EstadoJogo.ENCERRADO)
			throw new EstadoDeJogoInvalidoException(this,
					"Tentativa de adição de sessão em um jogo encerrado. Ação ignorada.", new Object[] { s });
		if (sessoes.contains(s)) {
			throw new ItemDuplicadoException(this, "Tentativa de adição de sessão duplicada. Ação ignorada.");
		} else {
			sessoes.add(s);
		}
	}

	/**
	 * Inicia o jogo. O jogo só pode ser iniciado se estiver dentro do horário de
	 * abertura e o estado seja <tt>FUTURO</tt>
	 */
	public void iniciarJogo() {
		if (this.estado != EstadoJogo.FUTURO)
			throw new EstadoDeJogoInvalidoException(this,
					"Não foi possível iniciar o jogo. O estado não está marcado como FUTURO. Ação ignorada.");
		if (this.dataAbertura.after(new Date()))
			throw new HorarioException(this,
					"Não foi possível iniciar o jogo. O horário de abertura ainda não foi alcançado. Ação ignorada.");
		setEstado(EstadoJogo.EM_ANDAMENTO);
		proximaQuestao();
	}

	/**
	 * Avança para a proxima questão. A questão só pode ser avançada se o tempo
	 * destinado à resposta dela for esgotado, e ela não for a última questão do
	 * jogo.
	 */
	public void proximaQuestao() {
		if (obterDataMaximaParaRespostaDaQuestaoAtual().after(new Date()))
			throw new DateTimeException(
					"Não foi possível avançar para a próxima questão. O horário limite para resposta ainda não foi alcançado. Ação ignorada.");
		int proximoIndice = getNumeroQuestaoAtual() + 1;
		if (proximoIndice < questoes.size()) {
			setQuestaoAtual(questoes.get(proximoIndice));
			setDataQuestaoAtual(new Date());
		} else {
			throw new FimQuestoesException(this, "Não há mais questões a serem exibidas. Ação ignorada.");
		}
	}

	/**
	 * Calcula o horário máximo para o envio da solução da
	 * questão atual. O retorno é calculado com a fórmula:
	 * 
	 * <pre>
	 * dataQuestaoAtual.getTime() + (questaoAtual.getTempoDisponivel() + TOLERANCIA_EM_MILISSEGUNDOS) * 1000
	 * </pre>
	 * 
	 * @return O horário máximo que se deve aceitar novas soluções para a questão
	 *         atual.
	 */
	private Date obterDataMaximaParaRespostaDaQuestaoAtual() {
		Long ms = dataQuestaoAtual.getTime();
		ms += questaoAtual.getTempoDisponivel() * 1000;
		ms += questaoAtual.getTempoBonus() * 1000;
		ms += TOLERANCIA_EM_MILISSEGUNDOS;
		return new Date(ms);
	}

	public void encerrarJogo() {
		if (this.estado != EstadoJogo.EM_ANDAMENTO)
			throw new EstadoDeJogoInvalidoException(this,
					"Não foi possível encerrar um jogo que não está em andamento. Ação ignorada.");
		if (numeroQuestaoAtual != getTotalDeQuestoes())
			throw new EstadoDeJogoInvalidoException(this,
					"Não foi possível encerrar um jogo que não encerrou sua última questão.");
		estado = EstadoJogo.ENCERRADO;
	}

	/**
	 * Ordena a lista de sessões no jogo por pontuação.
	 * 
	 * @return Uma lista com até os 10 primeiros colocados
	 */
	public ArrayList<Sessao> obterClassificacao() {
		this.sessoes.sort(new Comparator<Sessao>() {

			@Override
			public int compare(Sessao o1, Sessao o2) {
				return o2.pontuacaoTotal().compareTo(o1.pontuacaoTotal());
			}

		});

		return new ArrayList<>(sessoes.subList(0, Math.min(10, sessoes.size())));

	}

	@Override
	public String toString() {
		return "Jogo [id=" + id + ", nome=" + nome + ", dataAbertura=" + dataAbertura + ", estado=" + estado
				+ ", questaoAtual=" + questaoAtual + ", dataQuestaoAtual=" + dataQuestaoAtual
				+ ", limiteAjudasTempoBonus=" + limiteAjudasTempoBonus + ", limiteAjudasRemoverOpcoes="
				+ limiteAjudasRemoverOpcoes + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome.isEmpty())
			throw new IllegalArgumentException("Tentativa de inserção de um nome vazio. Ação ignorada.");
		this.nome = nome;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		if (dataAbertura.before(new Date())) {
			throw new IllegalArgumentException("Não é possível marcar um jogo no passado.");
		}
		this.dataAbertura = dataAbertura;
	}

	public EstadoJogo getEstado() {
		return estado;
	}

	public void setEstado(EstadoJogo estado) {
		this.estado = estado;
	}

	public Questao getQuestaoAtual() {
		return questaoAtual;
	}

	private void setQuestaoAtual(Questao questaoAtual) {
		this.questaoAtual = questaoAtual;
	}

	public Date getDataQuestaoAtual() {
		return dataQuestaoAtual;
	}

	private void setDataQuestaoAtual(Date dataQuestaoAtual) {
		this.dataQuestaoAtual = dataQuestaoAtual;
	}

	public int getlimiteAjudasTempoBonus() {
		return limiteAjudasTempoBonus;
	}

	public void setlimiteAjudasTempoBonus(int limiteAjudasTempoBonus) {
		this.limiteAjudasTempoBonus = limiteAjudasTempoBonus;
	}

	public int getLimiteAjudasRemoverOpcoes() {
		return limiteAjudasRemoverOpcoes;
	}

	public void setLimiteAjudasRemoverOpcoes(int limiteAjudasRemoverOpcoes) {
		this.limiteAjudasRemoverOpcoes = limiteAjudasRemoverOpcoes;
	}

	public int getNumeroQuestaoAtual() {
		return numeroQuestaoAtual;
	}

	public int getTotalDeQuestoes() {
		return questoes.size();
	}

	public ArrayList<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(ArrayList<Questao> questoes) {
		this.questoes = questoes;
	}

	public ArrayList<Sessao> getSessoes() {
		return sessoes;
	}

}
