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
 * @author Juliana
 */
public class ConjuntoSolucaoBooleana implements ConjuntoDeAlternativas {
    private int id;
    private final String tipo = "Booleana";
    private ArrayList<Alternativa> alternativas;

    public ConjuntoSolucaoBooleana() {
    }

    public ConjuntoSolucaoBooleana(ArrayList<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public ConjuntoSolucaoBooleana(int id, ArrayList<Alternativa> alternativas) {
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
    public ArrayList<Alternativa> getSolucao() {
        ArrayList<Alternativa> solucao = new ArrayList<>();
        for (Alternativa a : alternativas) {
            if (a.getIndice() == -1)
                solucao.add(a);
        }
        Collections.sort(solucao);
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
    
    
    
    
}
