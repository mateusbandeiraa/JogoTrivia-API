/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Beto
 */
public class ConjuntoSolucaoOrdenada implements ConjuntoDeAlternativas<ArrayList<Alternativa>> {
    
    private int id;
    private final tipoQuestao tipo = tipoQuestao.ORDENADA;
    private ArrayList<Alternativa> alternativas;

    public ConjuntoSolucaoOrdenada() {
    }

    public ConjuntoSolucaoOrdenada(ArrayList<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public ConjuntoSolucaoOrdenada(int id, ArrayList<Alternativa> alternativas) {
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
    public tipoQuestao getTipo() {
        return tipo;
    }
    
    @Override
    public ArrayList<Alternativa> getAlternativasRestantes(int i) {
        return alternativas;
    }
    
    @Override
    public ArrayList<Alternativa> getSolucao() {
        ArrayList<Alternativa> solucao = alternativas;
        Collections.sort(solucao);
        return solucao;
    }

    @Override
    public int CalcularPontuacaoResposta(ArrayList<Alternativa> solucao) {
        if (!solucao.equals(getSolucao()))
            return 0;
        else
            return 100;
    }
    
    
}
