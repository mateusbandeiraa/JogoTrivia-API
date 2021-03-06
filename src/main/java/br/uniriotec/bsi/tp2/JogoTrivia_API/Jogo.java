package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * Classe agregadora de questões. Um jogo é um modelo que será base para uma
 * partida.
 * 
 * @author Mateus Bandeira
 *
 */
@Entity
public class Jogo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	/**
	 * Nome do jogo. Pode ser usado para descrever o tema das perguntas. ex.:
	 * Música Anos 80.
	 */
	private String nome;
	/**
	 * Limites padrão de quantas ajudas de certo tipo podem ser usadas nesse
	 * jogo. Esses limites podem ser sobrescritos pela {@link Partida}.
	 */
	private int limiteAjudasTempoBonus;
	private int limiteAjudasRemoverOpcoes;

	/**
	 * Conjunto de questões que serão apresentadas no jogo.
	 */
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@OrderBy("id")
	private Set<Questao> questoes;

	public Jogo() {

	}

	public Jogo(String nome, int limiteAjudasTempoBonus, int limiteAjudasRemoverOpcoes) {
		setNome(nome);
		setLimiteAjudasTempoBonus(limiteAjudasTempoBonus);
		setLimiteAjudasRemoverOpcoes(limiteAjudasRemoverOpcoes);
	}

	public Jogo(int id, String nome, int limiteAjudasTempoBonus, int limiteAjudasRemoverOpcoes) {
		this(nome, limiteAjudasTempoBonus, limiteAjudasRemoverOpcoes);
		setId(id);
	}

	@Override
	public String toString() {
		return "Jogo [id=" + id + ", nome=" + nome + ", limiteAjudasTempoBonus=" + limiteAjudasTempoBonus
				+ ", limiteAjudasRemoverOpcoes=" + limiteAjudasRemoverOpcoes + ", questoes=" + questoes + "]";
	}

	/**
	 * Insere uma nova {@link Questao} ao fim do <tt>Jogo</tt>. Se a questão já
	 * estiver presente, nada será acrescentado.
	 * 
	 * @param questao
	 *            {@link Questao} a adicionar.
	 */
	public void adicionarQuestao(Questao questao) {
		if (questoes == null)
			questoes = new LinkedHashSet<>();
		if (questoes.contains(questao))
			throw new IllegalArgumentException("O jogo não pode possuir questões duplicadas.");
		questoes.add(questao);
	}

	/**
	 * Converte o <tt>Set</tt> de questões em Array e retorna a questão do
	 * indice passado.
	 * 
	 * @param numeroQuestaoAtual
	 * @return Questao no indice indicado.
	 */
	public Questao getQuestaoPorIndice(int numeroQuestaoAtual) {
		return (Questao) questoes.toArray()[numeroQuestaoAtual];
	}

	/**
	 * Calcula o número total de questões do jogo.
	 * 
	 * @return total de Questoes
	 */
	public int totalDeQuestoes() {
		return questoes != null ? questoes.size() : 0;
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
		this.nome = nome;
	}

	public int getLimiteAjudasTempoBonus() {
		return limiteAjudasTempoBonus;
	}

	public void setLimiteAjudasTempoBonus(int limiteAjudasTempoBonus) throws IllegalArgumentException {
		if (limiteAjudasTempoBonus < 0)
			throw new IllegalArgumentException("O limite não pode ser menor que 0.");
		this.limiteAjudasTempoBonus = limiteAjudasTempoBonus;
	}

	public int getLimiteAjudasRemoverOpcoes() {
		return limiteAjudasRemoverOpcoes;
	}

	public void setLimiteAjudasRemoverOpcoes(int limiteAjudasRemoverOpcoes) throws IllegalArgumentException {
		if (limiteAjudasRemoverOpcoes < 0) {
			throw new IllegalArgumentException("O limite não pode ser menor que 0.");
		}
		this.limiteAjudasRemoverOpcoes = limiteAjudasRemoverOpcoes;
	}

	public Set<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(LinkedHashSet<Questao> questoes) {
		this.questoes = questoes;
	}

}
