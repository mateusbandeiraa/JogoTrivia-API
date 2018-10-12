package br.uniriotec.bsi.tp2.JogoTrivia_API;

public class FimQuestoesException extends RegistravelException {

	private static final long serialVersionUID = 1L;

	public FimQuestoesException(Object mandante, String mensagem, Object[] objetos) {
		super(mandante, mensagem, objetos);
	}

	public FimQuestoesException(Object mandante, String mensagem) {
		super(mandante, mensagem);
	}

}
