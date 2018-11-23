package br.uniriotec.bsi.tp2.JogoTrivia_API;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Conjunto unitário de opções.
 * 
 * @author Beto
 * @author Mateus Bandeira
 *
 */
@Entity
public class ConjuntoUnitario extends ConjuntoAlternativas {
	@ManyToOne(fetch = FetchType.EAGER)
	private Opcao opcao;

	@Override
	public boolean equals(ConjuntoAlternativas s) {
		if (!(s instanceof ConjuntoUnitario))
			return false;
		return ((ConjuntoUnitario) s).getOpcao().equals(this.opcao);
	}

	@Override
	public int getPontuacao() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ConjuntoUnitario() {
		super();
	}

	public ConjuntoUnitario(Opcao opcao) {
		super();
		this.opcao = opcao;
	}

	public ConjuntoUnitario(int id, Opcao opcao) {
		super(id);
		this.opcao = opcao;
	}

	public Opcao getOpcao() {
		return opcao;
	}

	public void setOpcao(Opcao opcao) {
		this.opcao = opcao;
	}

}
