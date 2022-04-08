package TutorenEinteiler2000.util;

import java.util.ArrayList;
import java.util.List;

import TutorenEinteiler2000.model.Allocation;
import TutorenEinteiler2000.model.Exercise;
import TutorenEinteiler2000.model.Tutor;

public class ExercisePlaner {
	
	private Allocation allocation;
	
	/**
	 * changes the passed Allocation object and adds a Schedule to it.
	 * Tutors and Exercises have to be set!
	 * @param allocation
	 */
	public void scheduleExercises(Allocation allocation) {
		this.allocation = allocation;
		if (allocation.getAllTutors().isEmpty()
				|| allocation.getAllExercises().isEmpty()) {
			System.err.println("Received Empty Allocation, can not schedule");
			return;
		}
		
		//clear possible old schedule:
		allocation.getSchedule().clear();
		
		//call schedule algorithm:
		schedule();
		System.out.println("finished exercise planing");
	}
	
	private void schedule() {
		List<Tutor> alreadyUsed = new ArrayList<Tutor>();
		for (Exercise exercise : allocation.getTutEntires().keySet()) {
			List<Tutor> possibleTutors = allocation.getTutEntires().get(exercise);
			for (Tutor tutor : possibleTutors) {
				if (!alreadyUsed.contains(tutor)) {
					allocation.addAllocationToSchedule(exercise, tutor);
					alreadyUsed.add(tutor);
				}
			}
		}
	}

}
