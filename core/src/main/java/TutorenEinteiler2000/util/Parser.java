package TutorenEinteiler2000.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import TutorenEinteiler2000.model.Allocation;
import TutorenEinteiler2000.model.Exercise;
import TutorenEinteiler2000.model.Tutor;

public class Parser {
	
	private Allocation allocation = new Allocation();
	BufferedReader reader;
	
	public Allocation parseFile(File file) throws IOException {
		
		reader = new BufferedReader(new FileReader(file));
		readLine();
		
		reader.close();
		System.out.println("finished parsing");
		//printResult();
		return allocation;
	}
	
	private void readLine() throws IOException {
		String line = reader.readLine();
		while (line != null) {
			if (line.contains("#START: AllTut")) {
				readAllTuts();
			} else if (line.contains("#START: Table")) {
				readTable();
			}
			line = reader.readLine();
		}
	}
	
	private void readAllTuts() throws IOException {
		String line = reader.readLine();
		while (line != null) {
			if (line.contains("#END")) {
				readLine();
				return;
			}
			if (!line.isBlank()) {
				Tutor tutor = new Tutor(line);
				allocation.addTutor(tutor);
			}
			line = reader.readLine();
		}
	}
	
	private void readTable() throws IOException {
		String line = reader.readLine();
		while (line != null) {
			if (line.contains("#END")) {
				readLine();
				return;
			}
			
			if (!line.isBlank()) {
				String[] entries = line.split(";");
				if (entries.length != 4) {
					System.err.println("line has error: " + line);
					break;
				}
				
				Exercise exercise = new Exercise();
				exercise.setDay(entries[0]);
				exercise.setTime(entries[1]);
				exercise.setRoom(entries[2]);
				allocation.addExercise(exercise);
				
				String[] tutorsForExercise = entries[3].split(",");
				for (String tutorName : tutorsForExercise) {
					Tutor tutor = getTutWithName(tutorName);
					if (tutor == null) {
						System.err.println("Tutor does not Exist in List of all Turors: " + tutorName);
					}
					allocation.addTutEntry(exercise, tutor);
				}
			}

			line = reader.readLine();
		}
	}
	
	private Tutor getTutWithName(String name) {
		for (Tutor tutor : allocation.getAllTutors()) {
			String name1 = name.replaceAll("\\s", "");
			String name2 = tutor.getName().replaceAll("\\s", "");
			if (name1.equals(name2)) return tutor;
		}
		return null;
	}
	
	/**
	 * for debugging
	 */
	@SuppressWarnings("unused")
	private void printResult() {
		System.out.println("parsed tutors: ");
		for (Tutor tut : allocation.getAllTutors()) {
			System.out.println("Tutor: " + tut.getName());
		}
		
		System.out.println();
		System.out.println("parsed entries:");
		for (Exercise ex : allocation.getTutEntires().keySet()) {
			System.out.print(ex.getDay() + " " + ex.getTime() + " " + ex.getRoom() + " ");
			for (Tutor tut : allocation.getTutEntires().get(ex)) {
				System.out.print(tut.getName() + " ");
			}
			System.out.println();
		}
	}

}
