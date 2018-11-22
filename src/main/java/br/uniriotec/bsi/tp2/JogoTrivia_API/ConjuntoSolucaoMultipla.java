/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 *
 * @author Juliana
 */
public class ConjuntoSolucaoMultipla implements ConjuntoDeAlternativas<HashSet<Alternativa>> {
    private int id;
    private final String tipo = "Multipla";
    private ArrayList<Alternativa> alternativas;

    public ConjuntoSolucaoMultipla() {
    }

    public ConjuntoSolucaoMultipla(ArrayList<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public ConjuntoSolucaoMultipla(int id, ArrayList<Alternativa> alternativas) {
        this.id = id;
        this.alternativas = alternativas;
    }
    
    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public ArrayList<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(ArrayList<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }
    
    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public HashSet<Alternativa> getSolucao() {
        HashSet<Alternativa> solucao = new HashSet<>();
        for (Alternativa a : alternativas) {
            if (a.getIndice() == -1)
                solucao.add(a);
        }
        return solucao;
    }

    @Override
    public ArrayList<Alternativa> getAlternativasRestantes(int i) {
        ArrayList<Alternativa> alternativasRestantes = new ArrayList<>();
        int j = 0;
        for (Alternativa a : alternativas) {
            if (a.ehRemovivel() && j < i)
                j++;
            else
                alternativasRestantes.add(a);
        }
        return alternativasRestantes;
    }

    /**
     * para cada elemento da solucao enviada, é necessário comparar com
     * os elementos da solucao oficial
     * @param solucao
     * @return 
     */
    @Override
    public int CalcularPontuacaoResposta(HashSet<Alternativa> solucao) {
        return 0;
        //TODO calculo
            
    }


    
    
    
    
}
