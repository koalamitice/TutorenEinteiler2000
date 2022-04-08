package TutorenEinteiler2000.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Allocation {
	
	private Set<Exercise> allExercises = new HashSet<Exercise>();
	private Set<Tutor> allTutors = new HashSet<Tutor>();
	
	private Map<Exercise, List<Tutor>> tutEntries = new HashMap<Exercise, List<Tutor>>();
	
	private Map<Exercise, Tutor> schedule = new HashMap<Exercise, Tutor>();
	
	public Set<Exercise> getAllExercises() {
		return allExercises;
	}
	
	public void addExercise(Exercise e) {
		allExercises.add(e);
	}
	
	public Set<Tutor> getAllTutors() {
		return allTutors;
	}
	
	public void addTutor(Tutor tut) {
		allTutors.add(tut);
	}
	
	public Map<Exercise, List<Tutor>> getTutEntires () {
			return tutEntries;
	}
	
	public void addTutEntry(Exercise ex, Tutor tut) {
		if (tutEntries.keySet().contains(ex)) {
			tutEntries.get(ex).add(tut);
		} else {
			List<Tutor> tuts = new ArrayList<Tutor>();
			tuts.add(tut);
			tutEntries.put(ex, tuts);
		}
	}
	
	public Map<Exercise, Tutor> getSchedule() {
		return schedule;
	}
	
	public void addAllocationToSchedule(Exercise ex, Tutor tut) {
		if (schedule.keySet().contains(ex)) {
			System.err.println("Exercise already has a Tutor assigned");
			return;
		}
		schedule.put(ex, tut);
	}
	
	public List<Exercise> getUnusedExercises() {
		List<Exercise> unused = new ArrayList<Exercise>();
		for (Exercise exercise : getAllExercises()) {
			if (!getSchedule().containsKey(exercise)) {
				unused.add(exercise);
			}
		}
		return unused;
	}
	
	public List<Tutor> getUnusedTutors() {
		List<Tutor> unused = new ArrayList<Tutor>();
		for (Tutor tutor : getAllTutors()) {
			boolean tutFound = false;
			for (Exercise exercise : getSchedule().keySet()) {
				if (getSchedule().get(exercise) == tutor) {
					tutFound = true;
				}
			}
			if (!tutFound) unused.add(tutor);
		}
		
		return unused;
	}

}
