package fr.polytech.projet.naturalthescattering.controller.results;

import java.util.ArrayList;
import java.util.List;

import fr.polytech.projet.naturalthescattering.db.Carte;

public class BoosterOpenResult extends GenericResult {
	private List<Long> booster = new ArrayList<Long>();
	
	public BoosterOpenResult() {}
	
	public BoosterOpenResult(boolean result, String reason, List<Carte> booster) {
		super(result, reason);
		setBooster(booster);
	}
	
	public void setBooster(List<Carte> booster) {
		if (booster == null) {
			this.booster.clear();
		} else {
			booster.forEach((c) -> {
				this.booster.add(c.getId());
			});
		}
	}
	
	public List<Long> getBoster() {
		return this.booster;
	}
}