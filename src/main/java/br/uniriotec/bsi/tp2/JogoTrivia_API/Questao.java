package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;

public class Questao {
    private final int id;
    private String textoPergunta;
    private int tempoDisponivel; //em segundos
    private int tempoBonus; //em segundos
    private ArrayList<Opcao> opcoes;
    private Opcao opcaoCerta;
    private int quantidadeARemover;
    
    public Questao (int id, String textoPergunta, int tempoDisponivel, int tempoBonus) {
        this.id = id;
        this.textoPergunta = textoPergunta;
        this.tempoDisponivel = tempoDisponivel;
        this.tempoBonus = tempoBonus;
    }

    public String getTextoPergunta() {
        return textoPergunta;
    }

    public void setTextoPergunta(String textoPergunta) {
        this.textoPergunta = textoPergunta;
    }

    public int getTempoDisponivel() {
        return tempoDisponivel;
    }

    public void setTempoDisponivel(int tempoDisponivel) {
        this.tempoDisponivel = tempoDisponivel;
    }

    public int getTempoBonus() {
        return tempoBonus;
    }

    public void setTempoBonus(int tempoBonus) {
        this.tempoBonus = tempoBonus;
    }

    public int getId() {
        return id;
    }

    public Opcao getOpcaoCerta() {
        return opcaoCerta;
    }
    
    
    /**
     * Recebe a base de opcoes e armazena
     * Salva no atributo respostaCerta a opção correta
     * @param opcoes
     */
    public void setOpcoes(ArrayList<Opcao> opcoes) {
        this.opcoes = opcoes;
        for (Opcao op : opcoes) {
            if (op.estaCerto())
                this.opcaoCerta = op;
            if (op.ehRemovivel())
                this.quantidadeARemover++;
        }
    }
    
    public double calcularTaxaDeAcerto(ArrayList<Interacao> interacoes) {
        int qtdCertas = 0;
        for (Interacao i : interacoes) {
            if (i.solucaoTeveExito())
                qtdCertas++;
        }
        return qtdCertas/interacoes.size()*1.0;
    }
    
    public ArrayList<Opcao> removerOpcoesIncorretas () {
        ArrayList<Opcao> opcoesARemover = new ArrayList<Opcao>();
        for (Opcao op : opcoes) {
            if (op.ehRemovivel())
                opcoesARemover.add(op);
        }
        return opcoesARemover;
    }
    
}