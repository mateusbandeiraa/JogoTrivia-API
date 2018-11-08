package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;

public class Questao {
    private final int id;
    private String textoPergunta;
    private int tempoDisponivel; //em segundos
    private int tempoBonus; //em segundos
    private Opcao respostaCerta;
    private int qtdAcertos;
    private int qtdTentativas;
    private ArrayList<Opcao> opcoesASeremRemovidas;
    
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
    
    /**
     * Recebe a base de opcoes e filtra quais são as opcoes desta questão especifica
     * Salva no atributo respostaCerta a opção correta
     * Salva no atributo opcoesASeremRemovidas as incorretas
     * @param opcoes
     * @return 
     */
    public ArrayList<Opcao> getOpcoes(ArrayList<Opcao> opcoes) {
        opcoes = new ArrayList<Opcao>();
        for (Opcao op : opcoes) {
            if (op.getQuestao().equals(this)) {
                opcoes.add(op);
                if (op.estaCerto())
                    this.respostaCerta = op;
                else
                    this.opcoesASeremRemovidas.add(op);
            }
        }
        return opcoes;
    }
    
    public boolean checaSolucao(Opcao op) {
        if (op.equals(this.respostaCerta)) {
            this.qtdAcertos++;
            this.qtdTentativas++;
            return true;
        }
        this.qtdTentativas++;
        return false;          
    }
    
    public double taxaDeAcerto() {
        return qtdAcertos*1.0/qtdTentativas;
    }
    
    public ArrayList<Opcao> removerOpcoes (int quantidade) {
        ArrayList<Opcao> opcoesARemover = new ArrayList<Opcao>();
        for (int i = 0; i < quantidade; i++) {
            opcoesARemover.add(this.opcoesASeremRemovidas.get(i));
        }
        return opcoesARemover;
    }
    
}