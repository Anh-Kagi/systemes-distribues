package fr.polytech.projet.naturalthescattering.duel;

public class Card {
	private String name;
	private int hp;
	private final int hpmax;
	private int summon;
	private int atk;
	
	public Card(fr.polytech.projet.naturalthescattering.db.Card card) {
		name = card.getName();
		hp = card.getHp();
		hpmax = card.getHp();
		summon = card.getSummonCost();
		atk = card.getATK();
	}
	
	public String getName() {
		return name;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getHpmax() {
		return hpmax;
	}
	
	public int getSummonCost() {
		return summon;
	}
	
	public int getATk() {
		return atk;
	}
	
	public boolean isDead() {
		return hp <= 0;
	}
}
