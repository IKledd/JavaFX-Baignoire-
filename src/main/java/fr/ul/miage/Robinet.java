package fr.ul.miage;

public class Robinet {
	public Double vit_remplissage;
	public Baignoire baignoire;
	
	public Robinet(Double vit_remplissage, Baignoire baignoire) {
		super();
		this.vit_remplissage = vit_remplissage;
		this.baignoire = baignoire;
	}
	
	public Double getVit_remplissage() {
		return vit_remplissage;
	}

	public void setVit_remplissage(Double vit_remplissage) {
		this.vit_remplissage = vit_remplissage;
	}

	public Baignoire getBaignoire() {
		return baignoire;
	}
	
	public void setBaignoire(Baignoire baignoire) {
		this.baignoire = baignoire;
	}

	public Double remplir() {
		Double emptied = 0.0;
		if (baignoire.getVolume_gagne() >= baignoire.getVolume_tot()) {
			return emptied;
		}

		synchronized (baignoire) {
			baignoire.remplir(vit_remplissage);
		}
		double vol = baignoire.getVolume_gagne();
		System.out.printf("Robinet: %d", vol);
		return vit_remplissage;
	}
}
