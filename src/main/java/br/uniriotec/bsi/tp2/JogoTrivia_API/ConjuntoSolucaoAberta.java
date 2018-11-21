/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;

/**
 *
 * @author Beto
 */

public class ConjuntoSolucaoAberta implements ConjuntoDeAlternativas {
    private int id;
    private final String tipo = "Aberta";
    private Alternativa solucao;

    public ConjuntoSolucaoAberta() {
    }

    public ConjuntoSolucaoAberta(Alternativa solucao) {
        this.solucao = solucao;
    }

    public ConjuntoSolucaoAberta(int id, Alternativa solucao) {
        this.id = id;
        this.solucao = solucao;
    }
    
    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public ArrayList<Alternativa> getSolucao() {
        ArrayList<Alternativa> listaSolucao = new ArrayList<>();
        listaSolucao.add(this.solucao);
        return listaSolucao;
    }

    public void setSolucao(Alternativa solucao) {
        this.solucao = solucao;
    }
    
    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public ArrayList<Alternativa> getAlternativas() {
        return null;
    }

    @Override
    public ArrayList<Alternativa> getAlternativasRestantes(int i) {
        return null;
    }
    
    
    
}
