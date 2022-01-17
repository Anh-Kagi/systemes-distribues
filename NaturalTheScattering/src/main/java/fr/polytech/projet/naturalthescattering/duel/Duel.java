package fr.polytech.projet.naturalthescattering.duel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Duel {
	private boolean player = false; // true: first player false: second player
	private boolean hasdrawn = false;

	private int hp1 = hpmax;
	private int hp2 = hpmax;

	private static final int hpmax = 20;

	private int summon1 = 0;
	private int summon2 = 0;

	private static final int summonmax = 10;

	private PlayerCard[][] grid = new PlayerCard[5][5];

	private List<Card> deck1 = new ArrayList<Card>();
	private List<Card> deck2 = new ArrayList<Card>();

	protected List<Card> deck(boolean player) {
		return player ? deck2 : deck1;
	}

	private List<Card> hand1 = new ArrayList<Card>();
	private List<Card> hand2 = new ArrayList<Card>();

	protected List<Card> hand(boolean player) {
		return player ? hand2 : hand1;
	}

	public Duel(List<Card> deck1, List<Card> deck2) {
		deck1.addAll(deck1);
		deck2.addAll(deck2);
		Collections.shuffle(deck1);
		Collections.shuffle(deck2);
	}

	public Card draw(boolean player) {
		if (player != this.player)
			return null;
		if (hasdrawn)
			return null;

		List<Card> deck = deck(player);
		List<Card> hand = hand(player);

		Card ret = null;
		if (deck.size() >= 1) {
			ret = deck.remove(0);
			hand.add(ret);
		}
		hasdrawn = true;
		return ret;
	}

	public boolean place(boolean player, int card, Coords dst) {
		if (player != this.player)
			return false;

		List<Card> deck = deck(player);

		// card exists
		if (card >= deck.size())
			return false;
		Card c = deck.remove(card);

		// only on two first rows
		if (dst.y() >= 2)
			return false;

		dst = getCoordsFor(player, dst);

		return setSquare(player, c, dst);
	}

	protected boolean setSquare(boolean player, Card c, Coords dst) {
		if (grid[dst.y()][dst.x()] != null)
			return false;

		grid[dst.y()][dst.x()] = new PlayerCard();
		grid[dst.y()][dst.x()].card(c);
		grid[dst.y()][dst.x()].player(player);

		return true;
	}

	// move/attack card
	public boolean move(boolean player, Coords src, Coords dst, boolean attack) {
		
	}

	// attack player
	public boolean attack(boolean player, Coords src) {

	}

	public boolean endTurn(boolean player) {
		hasdrawn = false;
		player = !player;
	}

	public boolean isEnded() {
		return false;
	}

	public boolean winner() {
		return false;
	}

	public static Coords getCoordsFor(boolean player, Coords c) {
		if (c == null)
			return null;

		Coords ret = new Coords(c);

		c.x(5 - 1 - c.x());
		c.y(5 - 1 - c.y());

		return ret;
	}
}