package fr.polytech.projet.naturalthescattering.duel;

public class Coords {
	private int x;
	private int y;
	
	public Coords() {}
	
	public Coords(int x, int y) {
		x(x);
		y(y);
	}
	
	public Coords(Coords c) {
		this(c.x(), c.y());
	}

	public void x(int x) {
		this.x = x;
	}

	public int x() {
		return x;
	}

	public void y(int y) {
		this.y = y;
	}

	public int y() {
		return y;
	}
}
