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
		return allocation;
	}
	
	private void readLine() throws IOException {
		String line = reader.readLine();
		if (line == null) return;
		//System.out.println(line);
		
		if (line.contains("#START: AllTut")) {
			readAllTuts();
		} else if (line.contains("#START: Table")) {
			readTable();
		}
		readLine();
	}
	
	private void readAllTuts() throws IOException {
		String line = reader.readLine();
		//System.out.println(line);
		if (line == null || line.contains("#END")) return;
		if (line.isBlank()) readAllTuts();
		
		Tutor tutor = new Tutor(line);
		allocation.addTutor(tutor);
		
		readAllTuts();
	}
	
	private void readTable() throws IOException {
		String line = reader.readLine();
		//System.out.println(line);
		if (line == null || line.contains("#END")) return;
		if (line.isBlank()) readTable();
		
		String[] entries = line.split(";");
		if (entries.length != 4) System.err.println("line has error: " + line);
		
		Exercise exercise = new Exercise();
		exercise.setDay(entries[0]);
		exercise.setTime(entries[1]);
		exercise.setRoom(entries[2]);
		allocation.addExercise(exercise);
		
		String[] tutorsForExercise = entries[4].split(",");
		for (String tutorName : tutorsForExercise) {
			Tutor tutor = getTutWithName(tutorName);
			if (tutor == null) {
				System.err.println("Tutor does not Exist in List of all Turors: " + tutor);
			}
			allocation.addTutEntry(exercise, tutor);
		}
		
		readTable();
	}
	
	private Tutor getTutWithName(String name) {
		for (Tutor tutor : allocation.getAllTutors()) {
			String name1 = name.replaceAll(" ", "");
			String name2 = tutor.getName().replaceAll(" ", "");
			if (name1.equals(name2)) return tutor;
		}
		return null;
	}

}
