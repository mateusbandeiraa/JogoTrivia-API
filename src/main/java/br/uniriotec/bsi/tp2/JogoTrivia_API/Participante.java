package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 
 * @author Rafael Mota
 * @author Mateus Bandeira
 */
@Entity
public class Participante {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	/**
	 * Titulo que o participante escolhe
	 */
	private String nickname;
	private String chave;
	/**
	 * A quantidade de vezes que o participante usou o bonus de Tempo extra para
	 * perguntas
	 */
	private int ajudasTempoBonusUsadas;
	/**
	 * A quantidade de vezes que o participante usou o bonus de Remover Opção para a
	 * perguntas
	 */
	private int ajudasRemoverOpcoesUsadas;
	/**
	 * 
	 */
	@OneToMany(mappedBy = "participante", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Interacao> interacoes;
	@ManyToOne(fetch=FetchType.EAGER, optional = false)
	private Partida partida;

	/**
	 * Comparator usado para ordenar uma coleção de Participantes usando como
	 * critério a pontuação DECRESCENTE.
	 */
	public static final Comparator<Participante> COMPARATOR_PONTUACAO = new Comparator<Participante>() {
		public int compare(Participante o1, Participante o2) {
			return o2.pontuacaoTotal() - o1.pontuacaoTotal();
		}

	};

	public void adicionarInteracao(Interacao i) {
		if (interacoes == null)
			interacoes = new ArrayList<>();
		System.out.println(this.getPartida());
		System.out.println(i.getDataCriacao());
		if (this.getPartida().obterDataMaximaParaResposta().before(i.getDataCriacao()))
			throw new TempoEsgotadoException(
					"Não é possível instanciar a Interação após o término do tempo estipulado na questão.");
		this.interacoes.add(i);
	}

	public int pontuacaoTotal() {
		int soma = 0;
		for (Interacao interacao : interacoes) {
			soma += interacao.calcularPontuacao();
		}
		return soma;
	}

	public int ajudasTempoBonusDisponiveis() {
		return (partida.getLimiteAjudasTempoBonus() - ajudasTempoBonusUsadas);
	}

	public boolean podeUsarAjudaTempoBonus() {
		return (ajudasTempoBonusDisponiveis() < 1);
	}

	public int ajudasRemoverOpcoesDisponiveis() {
		return (partida.getLimiteAjudasRemoverOpcoes() - ajudasRemoverOpcoesUsadas);
	}

	public boolean podeUsarAjudaRemoverOpcoes() {
		return (ajudasRemoverOpcoesDisponiveis() < 1);
	}

	public void usarAjudaTempoBonus() {
		if (!podeUsarAjudaTempoBonus())
			throw new IllegalStateException("Não pode usar a ajuda.");
		ajudasTempoBonusUsadas++;
	}

	public void usarAjudaRemoverOpcoes() {
		if (!podeUsarAjudaRemoverOpcoes())
			throw new IllegalStateException("Não pode usar a ajuda.");
		ajudasTempoBonusUsadas++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public int getAjudasTempoBonusUsadas() {
		return ajudasTempoBonusUsadas;
	}

	public void setAjudasTempoBonusUsadas(int ajudasTempoBonusUsadas) {
		this.ajudasTempoBonusUsadas = ajudasTempoBonusUsadas;
	}

	public int getAjudasRemoverOpcoesUsadas() {
		return ajudasRemoverOpcoesUsadas;
	}

	public void setAjudasRemoverOpcoesUsadas(int ajudasRemoverOpcoesUsadas) {
		this.ajudasRemoverOpcoesUsadas = ajudasRemoverOpcoesUsadas;
	}

	public List<Interacao> getInteracoes() {
		return interacoes;
	}

	public void setInteracoes(ArrayList<Interacao> interacoes) {
		this.interacoes = interacoes;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Participante() {

	}

	public Participante(String nickname, int ajudasTempoBonusUsadas, int ajudasRemoverOpcoesUsadas, Partida partida) {
		this.nickname = nickname;
		this.ajudasTempoBonusUsadas = ajudasTempoBonusUsadas;
		this.ajudasRemoverOpcoesUsadas = ajudasRemoverOpcoesUsadas;
		this.partida = partida;
	}

	@Override
	public String toString() {
		return "Participante [nickname=" + nickname + ", chave=" + chave + ", ajudasTempoBonusUsadas="
				+ ajudasTempoBonusUsadas + ", ajudasRemoverOpcoesUsadas=" + ajudasRemoverOpcoesUsadas
				+ ", # interacoes=" + interacoes.size() + ", partida=" + partida.getId() + ", pontuacaoTotal() = "
				+ pontuacaoTotal() + "]";
	}

}
