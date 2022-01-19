package fr.polytech.projet.naturalthescattering.service.result;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import fr.polytech.projet.naturalthescattering.db.Card;

public abstract class BoosterResult {
	public static enum OpenStatus implements GenericStatus {
		OK(HttpStatus.OK),
		NOT_ENOUGH_CARDS_IN_DB(HttpStatus.INTERNAL_SERVER_ERROR);
		
		private HttpStatus status;
		
		private OpenStatus(HttpStatus status) {
			this.status = status;
		}

		@Override
		public HttpStatus getStatus() {
			return this.status;
		}
	}
	
	public static class Open extends GenericResult<OpenStatus>{
		private List<Long> booster = new ArrayList<Long>();
		
		public Open() {}
		
		public Open(OpenStatus reason, List<Card> booster) {
			super(reason);
			setBooster(booster);
		}
		
		public void setBooster(List<Card> booster) {
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