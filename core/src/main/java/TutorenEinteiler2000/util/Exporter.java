package TutorenEinteiler2000.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import TutorenEinteiler2000.model.Allocation;
import TutorenEinteiler2000.model.Exercise;
import TutorenEinteiler2000.model.Tutor;

public class Exporter {
	
	private PrintWriter writer;
	private Allocation allocation;
	
	/**
	 * exports allocation as csv to given directory
	 * @throws IOException 
	 */
	public void exportAllocationAsCsv(String directory, Allocation allocation) throws IOException {
		this.allocation = allocation;
		if (allocation == null || directory.isEmpty()) {
			System.out.println("Empty Input, Can not Export :/");
			return;
		}
		File newFile = new File(directory + "/schedule.txt");
		newFile.createNewFile();
		
		writer = new PrintWriter(newFile);
		writeFileInput();
		writer.close();
	}
	
	private void writeFileInput() {
		for (Exercise exercise : allocation.getSchedule().keySet()) {
			//write exercise slot:
			writer.write(exercise.getDay() + "; " 
					+ exercise.getTime() + "; " 
					+ exercise.getRoom() + "; ");
			//write assigned tutor:
			writer.write(allocation.getSchedule().get(exercise).getName());
			writer.write("\n"); //new line
		}
		writer.write("\n");
		writer.write("Unused Exercises: \n");
		for (Exercise exercise : allocation.getUnusedExercises()) {
			writer.write(exercise.getDay() + "; " 
					+ exercise.getTime() + "; " 
					+ exercise.getRoom() + "\n");
		}
		
		writer.write("\n");
		writer.write("Unused Tutors: \n");
		for (Tutor tutor : allocation.getUnusedTutors()) {
			writer.write(tutor.getName() + "\n");
		}
	}

}
