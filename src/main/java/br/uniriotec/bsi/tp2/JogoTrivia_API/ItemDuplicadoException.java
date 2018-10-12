package br.uniriotec.bsi.tp2.JogoTrivia_API;

public class ItemDuplicadoException extends RegistravelException {
	private static final long serialVersionUID = 1L;
	
	public ItemDuplicadoException(Object mandante, String mensagem, Object[] objetos) {
		super(mandante, mensagem, objetos);
	}
	
	public ItemDuplicadoException(Object mandante, String mensagem) {
		super(mandante, mensagem);
	}
}
