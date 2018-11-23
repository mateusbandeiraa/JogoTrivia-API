package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * 
 * Conjunto de Opções onde a ordem não importa.
 * 
 * @author Beto
 * @author Mateus Bandeira
 *
 */

@Entity
public class ConjuntoMultiplo extends ConjuntoAlternativas {
	@OneToMany(fetch = FetchType.EAGER)
	Set<Opcao> opcoes;

	@Override
	public boolean equals(ConjuntoAlternativas s) {
		if (!(s instanceof ConjuntoMultiplo))
			return false;
		return ((ConjuntoMultiplo) s).getOpcoes().equals(this.opcoes);
	}

	@Override
	public int getPontuacao() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void adicionarOpcao(Opcao o) {
		if(this.opcoes == null)
			this.opcoes = new HashSet<>();
		this.opcoes.add(o);
	}

	public ConjuntoMultiplo() {
		super();
	}

	public ConjuntoMultiplo(HashSet<Opcao> opcoes) {
		super();
		this.opcoes = opcoes;
	}

	public ConjuntoMultiplo(int id, HashSet<Opcao> opcoes) {
		super(id);
		this.opcoes = opcoes;
	}

	public Set<Opcao> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(HashSet<Opcao> opcoes) {
		this.opcoes = opcoes;
	}

}
