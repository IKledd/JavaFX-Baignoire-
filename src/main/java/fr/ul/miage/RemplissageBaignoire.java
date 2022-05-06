package fr.ul.miage;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;

public class RemplissageBaignoire extends ScheduledService<Double> {
	private Robinet robinet;

	public RemplissageBaignoire(Robinet robinet) {
		super();
		this.robinet = robinet;
		this.setPeriod(Duration.seconds(1));
	}

	@Override
	protected Task<Double> createTask() {
		return new Task<Double>() {
			protected Double call() {
				return robinet.remplir();
			};
		};
	}
}
