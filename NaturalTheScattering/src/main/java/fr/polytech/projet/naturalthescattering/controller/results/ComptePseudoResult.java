package fr.polytech.projet.naturalthescattering.controller.results;

public class ComptePseudoResult {
	private String pseudo;
	
	public ComptePseudoResult() {}
	
	public ComptePseudoResult(String pseudo) {
		setPseudo(pseudo);
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getPseudo() {
		return this.pseudo;
	}
}
