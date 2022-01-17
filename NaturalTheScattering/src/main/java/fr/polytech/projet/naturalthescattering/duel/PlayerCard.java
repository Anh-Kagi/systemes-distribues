package fr.polytech.projet.naturalthescattering.duel;

public class PlayerCard {
	private Card card;
	private boolean player;
	private boolean played;
	
	public void card(Card card) {
		this.card = card;
	}
	
	public Card card() {
		return card;
	}
	
	public void player(boolean player) {
		this.player = player;
	}
	
	public boolean player() {
		return player;
	}
	
	public void played(boolean played) {
		this.played = played;
	}
	
	public boolean played() {
		return played;
	}
}
