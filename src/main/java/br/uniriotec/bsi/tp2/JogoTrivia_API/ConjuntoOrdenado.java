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
	public int getPontuacao(ConjuntoAlternativas s) {
		// TODO Auto-generated method stub
                // o acerto é considerado se errou por diferenca de um 
                if (this.equals(s))
                    return 1000;
                else if (!(s instanceof ConjuntoOrdenado))
                    return 0;
                else {
                    int PONTUACAO_MAXIMA = 750; //pontuacao maxima para quem cometeu um erro
                    int pontoPorAcerto = PONTUACAO_MAXIMA/opcoes.size();
                    int pontuacaoAcumulada = 0;
                    for (int i = 0; i < opcoes.size(); i++) {
                        for (int j = i-1; j <= i+1; j++) {
                            if (comparaOpcao ((ConjuntoOrdenado) s, this, i, j))
                                pontuacaoAcumulada += pontoPorAcerto;
                        }
                    }
                    return pontuacaoAcumulada;
                }
	}
        
        private static boolean comparaOpcao (ConjuntoOrdenado x, ConjuntoOrdenado y, int i, int j) {
            // compara opcoes entre dois conjuntos ordenados com indices diferentes
            try {
                return x.getOpcoes().get(i).equals(y.getOpcoes().get(j));
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                return false;
            }
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
