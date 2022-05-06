package fr.ul.miage;

public class Trou {
	public Double vit_vidage;
	public Baignoire baignoire;
	
	public Trou(Double vit_vidage, Baignoire baignoire) {
		super();
		this.vit_vidage = vit_vidage;
		this.baignoire = baignoire;
	}
	
	public Double getVit_vidage() {
		return vit_vidage;
	}

	public void setVit_vidage(Double vit_vidage) {
		this.vit_vidage = vit_vidage;
	}

	public Baignoire getBaignoire() {
		return baignoire;
	}
	public void setBaignoire(Baignoire baignoire) {
		this.baignoire = baignoire;
	}


	public Double vider() {
		Double added = 0.0;
		if (baignoire.getVolume_gagne() <= 0.0) {
			return added;
		}

		synchronized (baignoire) {
			baignoire.vider(vit_vidage);
		}
		return vit_vidage;
	}
	
}
