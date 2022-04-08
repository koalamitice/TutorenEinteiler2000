package TutorenEinteiler2000;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * creates the application
 * @author koalamitice
 */
public class Activator extends Application {
	
	public static void main (String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Dialog dialog = new Dialog();
		dialog.createAndStart();
		System.exit(0);
	}
}
