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
	public int getPontuacao(ConjuntoAlternativas s) {
		// TODO Auto-generated method stub
                if (!(s instanceof ConjuntoMultiplo))
                    return 0;
                else if (s.equals(this))
                    return 1000;
                else {
                    int PONTUACAO_MAXIMA = 750; //pontuacao maxima possivel para quem cometeu um erro
                    int pontoPorAcerto = PONTUACAO_MAXIMA/this.getOpcoes().size();
                    int qtdAcertos =  contaAcertos (this, (ConjuntoMultiplo) s);
                    int qtdErros = Math.abs(qtdAcertos - this.getOpcoes().size());
                    return Math.max(qtdAcertos - qtdErros, 0)*pontoPorAcerto;    
                }
	}
        
        private static int contaAcertos (ConjuntoMultiplo x, ConjuntoMultiplo y) {
            int totalAcertos = 0;
            for ( Opcao opX :x.getOpcoes() ) {
                for (Opcao opY : y.getOpcoes()) {
                    if (opX.equals(opY))
                        totalAcertos ++;
                }
            }
            return totalAcertos;
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
