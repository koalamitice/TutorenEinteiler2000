package TutorenEinteiler2000;

import java.io.File;
import java.io.IOException;

import TutorenEinteiler2000.model.Allocation;
import TutorenEinteiler2000.util.ExercisePlaner;
import TutorenEinteiler2000.util.Exporter;
import TutorenEinteiler2000.util.Parser;
import javafx.stage.FileChooser;

public class Dialog {
	
	private File selectedFile;
	private Allocation allocation;
	
	private FileChooser fileChooser = createFileChooser();
	
	public void createAndStart() {
		
		selectedFile = fileChooser.showOpenDialog(null);
		try {
			//get Input file with dialog:
			allocation = new Parser().parseFile(selectedFile);
			//start planer:
			new ExercisePlaner().scheduleExercises(allocation);
			//export file:
			new Exporter().exportAllocationAsCsv(selectedFile.getParent(), allocation);
		} catch (IOException e1) {
			System.err.println("Something went wrong lol");
		}
	}
	
	private FileChooser createFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select File");
		return fileChooser;
	}
	
	public File getSelectedFile() {
		return selectedFile;
	}
	

}
