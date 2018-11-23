package br.uniriotec.bsi.tp2.JogoTrivia_API;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Opcao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String texto;

	public Opcao() {

	}

	public Opcao(String texto) {
		this.texto = texto;
	}

	public Opcao(int id, String texto) {
		this(texto);
		this.id = id;
	}

	@Override
	public String toString() {
		return "Opcao [id=" + id + ", texto=" + texto + "]";
	}

	public int getId() {
		return id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
