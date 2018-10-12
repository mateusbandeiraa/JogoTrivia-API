package br.uniriotec.bsi.tp2.util;

/*
 * Classe destinada à criação de logs.
 */
public abstract class Registro {
	private static final String PREFIXO = "---";
	private static final String SUFIXO = "---";
	
	
	/*
	 * Imprime um aviso no console com o seguinte formato:
	 * 
	 * ---
	 * [<nome da classe mandante>: <mensagem>
	 * <toString de todos os objetos passados na lista>
	 * ---
	 */
	public static void aviso(Object mandante, String mensagem, Object[] objetos) {
		System.out.println(PREFIXO);
		System.out.println("[" + obterNomeClasse(mandante) + "]: " + mensagem);
		if (objetos != null) {
			for (Object o : objetos) {
				System.out.println(o.toString());
			}
		}
		System.out.println(SUFIXO);
	}
	
	private static String obterNomeClasse(Object o) {
		return o.getClass().getSimpleName();
	}
}
