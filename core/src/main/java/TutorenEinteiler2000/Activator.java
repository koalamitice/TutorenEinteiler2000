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
		primaryStage.setTitle("TutorenEinteiler2000");
		
		View view = new View(primaryStage);
		view.createLayout();
		
        primaryStage.show();
	}
}
