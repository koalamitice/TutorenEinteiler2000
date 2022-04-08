package TutorenEinteiler2000.util;

import java.util.Map;
import java.util.TreeMap;

import TutorenEinteiler2000.model.Allocation;
import TutorenEinteiler2000.model.Exercise;
import TutorenEinteiler2000.model.Tutor;

/**
 * Sorts Exercises based on Weekday, Time, Room
 * @author koalamitice
 *
 */
public class SortingUtil {
	
	public static Map<Exercise, Tutor> getSortedSchedule(Allocation allocation) {
		Map<Exercise, Tutor> sortedSchedule = new TreeMap<Exercise, Tutor>();
		for (Exercise ex : allocation.getSchedule().keySet()) {
			sortedSchedule.put(ex, allocation.getSchedule().get(ex));
		}
		return sortedSchedule;
	}

}
