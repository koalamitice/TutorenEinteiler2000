package TutorenEinteiler2000.model;

public class Exercise implements Comparable<Exercise> {
	
	private String day;
	private String time;
	private String room;
	
	public void setDay(String day) {
		this.day = day;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setRoom(String room) {
		this.room = room;
	}
	
	public String getDay() {
		return day;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getRoom() {
		return room;
	}

	@Override
	public int compareTo(Exercise other) {
		if (!this.day.equals(other.day)) {
			return Integer.compare(getDayNumber(this.day), getDayNumber(other.day));
		} else if (!this.time.equals(other.time)) {
			return this.time.compareTo(other.time);
		} else if (!this.room.equals(other.room)) {
			return this.room.compareTo(other.room);
		} else {
			return 0;
		}
	}
	
	public int getDayNumber(String day) {
		if (day.equals("Montag")) return 1;
		if (day.equals("Dienstag")) return 2;
		if (day.equals("Mittwoch")) return 3;
		if (day.equals("Donnerstag")) return 4;
		if (day.equals("Freitag")) return 5;
		return -1;
	}
	
}
