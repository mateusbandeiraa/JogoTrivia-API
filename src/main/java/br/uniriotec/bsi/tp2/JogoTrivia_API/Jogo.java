package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.LinkedHashSet;

/**
 * Classe agregadora de questões. Um jogo é um modelo que será base para uma
 * partida.
 * 
 * @author Mateus Bandeira
 *
 */
public class Jogo {
	private int id;
	/**
	 * Nome do jogo. Pode ser usado para descrever o tema das perguntas. ex.: Música
	 * Anos 80.
	 */
	private String nome;
	/**
	 * Limites padrão de quantas ajudas de certo tipo podem ser usadas nesse jogo.
	 * Esses limites podem ser sobrescritos pela {@link Partida}.
	 */
	private int limiteAjudasTempoBonus;
	private int limiteAjudasRemoverOpcoes;

	/**
	 * Conjunto de questões que serão apresentadas no jogo. O
	 * {@link LinkedHashSet} foi escolhido devido à persistência da ordem que as
	 * questões foram inseridas e ao tratamento dado à inserções duplicadas (são
	 * ignoradas).
	 */
	private LinkedHashSet<Questao> questoes;

	public Jogo(String nome, int limiteAjudasTempoBonus, int limiteAjudasRemoverOpcoes) {
		setNome(nome);
		setLimiteAjudasTempoBonus(limiteAjudasTempoBonus);
		setLimiteAjudasRemoverOpcoes(limiteAjudasRemoverOpcoes);
	}

	public Jogo(int id, String nome, int limiteAjudasTempoBonus, int limiteAjudasRemoverOpcoes) {
		this(nome, limiteAjudasTempoBonus, limiteAjudasRemoverOpcoes);
		setId(id);
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
		questoes.add(questao);
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

	public void setLimiteAjudasTempoBonus(int limiteAjudasTempoBonus) {
		if (limiteAjudasTempoBonus < 0)
			throw new IllegalArgumentException("O limite não pode ser menor que 0.");
		this.limiteAjudasTempoBonus = limiteAjudasTempoBonus;
	}

	public int getLimiteAjudasRemoverOpcoes() {
		return limiteAjudasRemoverOpcoes;
	}

	public void setLimiteAjudasRemoverOpcoes(int limiteAjudasRemoverOpcoes) {
		if (limiteAjudasRemoverOpcoes < 0) {
			throw new IllegalArgumentException("O limite não pode ser menor que 0.");
		}
		this.limiteAjudasRemoverOpcoes = limiteAjudasRemoverOpcoes;
	}

	public LinkedHashSet<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(LinkedHashSet<Questao> questoes) {
		this.questoes = questoes;
	}

}
