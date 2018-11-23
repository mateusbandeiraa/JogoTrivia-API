package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

/**
 * 
 * Conjunto de Opcoes onde a ordem é importante.
 * 
 * @author Beto
 * @author Mateus Bandeira
 *
 */
@Entity
public class ConjuntoOrdenado extends ConjuntoAlternativas {
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderColumn()
	List<Opcao> opcoes;

	@Override
	public boolean equals(ConjuntoAlternativas s) {
		if (!(s instanceof ConjuntoOrdenado))
			return false;
		return ((ConjuntoOrdenado) s).getOpcoes().equals(this.opcoes);
	}

	@Override
	public int getPontuacao() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void adicionarOpcao(Opcao o) {
		if(this.opcoes == null)
			this.opcoes = new ArrayList<>();
		this.opcoes.add(o);
	}

	public ConjuntoOrdenado() {

	}

	public ConjuntoOrdenado(ArrayList<Opcao> opcoes) {
		super();
		this.opcoes = opcoes;
	}

	public ConjuntoOrdenado(int id, ArrayList<Opcao> opcoes) {
		super(id);
		this.opcoes = opcoes;
	}

	public List<Opcao> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(ArrayList<Opcao> opcoes) {
		this.opcoes = opcoes;
	}

}
