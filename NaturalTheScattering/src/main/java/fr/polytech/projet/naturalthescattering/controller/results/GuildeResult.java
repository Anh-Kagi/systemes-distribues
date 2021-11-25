package fr.polytech.projet.naturalthescattering.controller.results;

import fr.polytech.projet.naturalthescattering.db.Guilde;

public abstract class GuildeResult {
	public static class Create extends GenericResult {
		private Long id;
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public Long getId() {
			return this.id;
		}
	}
	
	public static class Read extends GenericResult {
		private String nom;
		private Long chef;
		
		public void setNom(String nom) {
			this.nom = nom;
		}
		
		public String getNom() {
			return this.nom;
		}
		
		public void setChef(Long chef) {
			this.chef = chef;
		}
		
		public Long getChef() {
			return this.chef;
		}
		
		public void fromGuilde(Guilde guilde) {
			setNom(guilde.getNom());
			setChef(guilde.getChef().getId());
		}
	}
}
