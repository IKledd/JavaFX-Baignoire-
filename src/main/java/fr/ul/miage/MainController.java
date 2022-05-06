package fr.ul.miage;

import java.util.ArrayList;
import java.util.Timer;

import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private TextField bath_volume;
	@FXML
	private TextField faucet_flow;
	@FXML
	private TextField hole_number;
	@FXML
	private Pane holes_pane;
	@FXML
	private ScrollPane scroll;
	@FXML
	private ProgressBar bath;
	@FXML
	private Timer timer;
	@FXML
	private Button start_btn;
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;

	Baignoire baignoire = new Baignoire();
	Robinet robinet = new Robinet(null, baignoire);
	Trou trou = new Trou(null, baignoire);
	RemplissageBaignoire remplissage = new RemplissageBaignoire(robinet);
	VidageBaignoire vidage = new VidageBaignoire(trou);

	@FXML
	public void initialize() {
		
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		// bath.setProgress(0.0);
		
		remplissage.setOnSucceeded((WorkerStateEvent event) -> {
			System.out.println("Fill started");
			System.out.println(baignoire.volume_gagne);
			System.out.println("Filled with " + robinet.getVit_remplissage());
			bath.setProgress(baignoire.volume_gagne/baignoire.volume_tot);
			if (baignoire.getVolume_gagne() >= baignoire.getVolume_tot()) {
				System.out.println("Fill done.");
				remplissage.cancel();
			}
		});

		vidage.setOnSucceeded((WorkerStateEvent event) -> {
			System.out.println("Emptying started");
			System.out.println(baignoire.volume_gagne);
			System.out.println("Emptied by " + trou.getVit_vidage());
			bath.setProgress(baignoire.volume_gagne/baignoire.volume_tot);
			if (baignoire.getVolume_gagne() >= 0.0) {
				System.out.println("Emptying done.");
				vidage.cancel();
				remplissage.cancel();
			}
		});
	}
	
	@FXML
	public void startFilling() {
		double holes_flow = getHolesFlow();
		double b_volume = 0.0;
		double f_flow = 0.0;
		try {
			b_volume = Double.parseDouble(bath_volume.getText());
			f_flow = Double.parseDouble(faucet_flow.getText());
			
			baignoire.setVolume_tot(b_volume);
			robinet.setVit_remplissage(f_flow);
			trou.setVit_vidage(holes_flow);
			
			remplissage.start();
		} catch (NumberFormatException | NullPointerException e) {
			System.out.println("A format is wrong : " + e);
		}
	}
	
//	public void fillBath() {
//		int flow = 0;
//		try {
//			flow = Integer.parseInt(faucet_flow.getText());
//		} catch (NumberFormatException e) {
//			System.out.println("Wrong format : " + e.getMessage());
//		}
//		Double current_progress = bath.getProgress();
//		bath.setProgress(0.0);
//		for(int i=0; i<10; i++) {
//			bath.setProgress(0.1*(i+1));
//			System.out.println("Bath progress : " + bath.getProgress());
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

	@FXML
	public void addHoles() {
		int holes = 0;
		try {
			holes = Integer.parseInt(hole_number.getText());
		} catch (NumberFormatException e) {
			System.out.println("Wrong format : " + e.getMessage());
		}
		if (holes >= 0 && holes <= 10) {
			System.out.println("There's " + holes + " holes.");
			holes_pane.getChildren().clear();
			for (int i=0; i<holes; i++) {
				// Create text elements
				Text hole_name = new Text();
				hole_name.setText("Trou " + i);
				hole_name.setLayoutY(30*i+30);
				hole_name.setLayoutX(10);
				holes_pane.getChildren().add(hole_name);
				
				// Create text field elements
				TextField text_field = new TextField();
				text_field.setPromptText("Débit trou en L/s");
				text_field.setLayoutY(30*i+15);
				text_field.setLayoutX(50);
				text_field.setPrefWidth(300);
				text_field.setId("hole" + i);
				System.out.println(text_field.getId());
				holes_pane.getChildren().add(text_field);
				
			}
			
		} else {
			System.out.println("There must be between 0 and 10 holes.");
		}
	}
	
	public double getHolesFlow() {
		ArrayList<TextField> hole_list = new ArrayList<TextField>();
		TextField hole0 = new TextField();
		for (int i=0; i<(holes_pane.getChildren().size())/2; i++) {
			try {
				hole0 = (TextField) holes_pane.lookup("#hole" + i);
				hole_list.add(hole0);
				System.out.println("Hole added");
			} catch (NullPointerException e) {
				
			}
		}
		double flow_holes = 0.0;
		for (int i=0; i<hole_list.size()-1; i++) {
			flow_holes += Double.parseDouble(hole_list.get(i).getText());
		}
		
		return flow_holes;
	}
}
