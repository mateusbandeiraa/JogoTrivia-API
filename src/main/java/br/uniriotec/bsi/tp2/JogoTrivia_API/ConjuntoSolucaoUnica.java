/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;

/**
 *
 * @author labccet
 */
public class ConjuntoSolucaoUnica implements ConjuntoDeAlternativas<Alternativa> {
    private int id;
    private final tipoQuestao tipo = tipoQuestao.UNICA;
    private ArrayList<Alternativa> alternativas;

    public ConjuntoSolucaoUnica() {
    }

    public ConjuntoSolucaoUnica(ArrayList<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public ConjuntoSolucaoUnica(int id, ArrayList<Alternativa> alternativas) {
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
    public Alternativa getSolucao() {
        for (Alternativa a : alternativas) {
            if (a.getIndice() == -1)
                return a;
        }
        return null;
    }

    @Override
    public int CalcularPontuacaoResposta(Alternativa solucao) {
        if (!solucao.equals(getSolucao()))
            return 0;
        return 100;
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
