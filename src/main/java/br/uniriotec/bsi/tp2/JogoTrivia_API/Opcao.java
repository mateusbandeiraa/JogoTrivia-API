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
	private boolean estaCerto;
	private boolean ehRemovivel;

	public Opcao() {

	}

	public Opcao(String texto, boolean estaCerto, boolean ehRemovivel) {
		this.texto = texto;
		this.estaCerto = estaCerto;
		this.ehRemovivel = ehRemovivel;
	}

	public Opcao(int id, String texto, boolean estaCerto, boolean ehRemovivel) {
		this(texto, estaCerto, ehRemovivel);
		this.id = id;
	}

	@Override
	public String toString() {
		return "Opcao [id=" + id + ", texto=" + texto + ", estaCerto=" + estaCerto + ", ehRemovivel=" + ehRemovivel
				+ "]";
	}

	public int getId() {
		return id;
	}

	public String getTexto() {
		return texto;
	}

	public boolean ehRemovivel() {
		return ehRemovivel;
	}

	public void setEhRemovivel(boolean ehRemovivel) {
		this.ehRemovivel = ehRemovivel;
	}

	public boolean estaCerto() {
		return estaCerto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
