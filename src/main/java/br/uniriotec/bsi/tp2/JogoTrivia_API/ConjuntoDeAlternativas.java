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

/**
 * 
 * @author labccet
 * @param <T> é uma lista, set ou Alternativa única, dependendo da resposta 
 */
public interface ConjuntoDeAlternativas<T> {
   
    public int getId();
    public tipoQuestao getTipo();
    public ArrayList<Alternativa> getAlternativas();
    public T getSolucao();
    public int CalcularPontuacaoResposta (T solucao);
    public ArrayList<Alternativa> getAlternativasRestantes(int i); //i eh o numero a ser removido;
    
}
