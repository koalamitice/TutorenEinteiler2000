package TutorenEinteiler2000;

import java.io.File;
import java.io.IOException;

import TutorenEinteiler2000.model.Allocation;
import TutorenEinteiler2000.util.ExercisePlaner;
import TutorenEinteiler2000.util.Exporter;
import TutorenEinteiler2000.util.Parser;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class View {
	
	private Stage primaryStage;
	private File selectedFile;
	private Allocation allocation;
	
	public View(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	private FileChooser fileChooser = createFileChooser();
	private DirectoryChooser directoryChooser = createDirectoryChooser();
	
	public void createLayout() {
		
		Button selectFileButton = new Button("Select File");
		selectFileButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(primaryStage);
            try {
				allocation = new Parser().parseFile(selectedFile);
			} catch (IOException e1) {
				System.err.println("Parser Could Not Find The Selected File :/");
			}
        });
		
		Button startAllocationButton = new Button("Allocate Tutors");
		startAllocationButton.setOnAction(e -> {
			if (allocation == null) return;
			new ExercisePlaner().scheduleExercises(allocation);
        });
		
		Button exportAllocationButton = new Button("Export Allocation");
		exportAllocationButton.setOnAction(e -> {
            File dir = directoryChooser.showDialog(primaryStage);
            try {
				new Exporter().exportAllocationAsCsv(dir.getAbsolutePath(), allocation);
			} catch (IOException e1) {
				System.err.println("Failed to Create File :(");
			}
        });
		
        VBox vBox = new VBox(
        		selectFileButton, 
        		startAllocationButton, 
        		exportAllocationButton);
        
        Scene scene = new Scene(vBox, 960, 600);
        primaryStage.setScene(scene);
	}
	
	private FileChooser createFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Tutoren File");
		return fileChooser;
	}
	
	private DirectoryChooser createDirectoryChooser() {
		DirectoryChooser dc = new DirectoryChooser();
		dc.setTitle("Choose Directory");
		return dc;
	}
	
	public File getSelectedFile() {
		return selectedFile;
	}
	

}
