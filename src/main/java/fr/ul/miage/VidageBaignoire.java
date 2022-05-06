package fr.ul.miage;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;

public class VidageBaignoire extends ScheduledService<Double> {
	private Trou trou;

	public VidageBaignoire(Trou trou) {
		super();
		this.trou = trou;
		this.setPeriod(Duration.seconds(1));
	}

	@Override
	protected Task<Double> createTask() {
		return new Task<Double>() {
			protected Double call() {
				return trou.vider();
			};
		};
	}
}
