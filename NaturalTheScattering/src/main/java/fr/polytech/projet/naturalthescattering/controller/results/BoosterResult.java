package fr.polytech.projet.naturalthescattering.controller.results;

import java.util.ArrayList;
import java.util.List;

import fr.polytech.projet.naturalthescattering.db.Carte;

public abstract class BoosterResult {
	public static class Open extends GenericResult{
		private List<Long> booster = new ArrayList<Long>();
		
		public Open() {}
		
		public Open(boolean result, String reason, List<Carte> booster) {
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
		
		public List<Long> getBooster() {
			return this.booster;
		}
	}
}