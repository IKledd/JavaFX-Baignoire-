package fr.ul.miage;

public class Baignoire {

	public Double volume_tot;
	public Double volume_gagne = 0.0;
//	private static final Logger LOG = Logger.getLogger(Baignoire.class.getName());
//	private static final Random r = new Random(Instant.now().getEpochSecond());
	
	public Baignoire() {
		super();
		this.volume_tot = 0.0;
	}
	
	public Double getVolume_tot() {
		return volume_tot;
	}

	public void setVolume_tot(Double volume_tot) {
		this.volume_tot = volume_tot;
	}
	public Double getVolume_gagne() {
		return volume_gagne;
	}

	public void setVolume_gagne(Double volume_gagne) {
		this.volume_gagne = volume_gagne;
	}
	
	public Double remplir(Double add_water) {
		volume_gagne += add_water;
		if (volume_gagne >= volume_tot) {
			return 0.0;
		}
		
		return volume_gagne;
	}
	public Double vider(Double remove_water) {
		volume_gagne -= remove_water;
		if (volume_gagne <= 0.0) {
			return 0.0;
		}
		
		return volume_gagne;
	}
}
