package br.uniriotec.bsi.tp2.JogoTrivia_API;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
/**
 * Representa um conjunto genérico de objetos Opcao.
 * 
 * @author Beto
 * @author Mateus Bandeira
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ConjuntoAlternativas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	public ConjuntoAlternativas() {

	}

	public ConjuntoAlternativas(int id) {
		this.id = id;
	}

	public abstract boolean equals(ConjuntoAlternativas s);

	public abstract int getPontuacao(ConjuntoAlternativas s);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
