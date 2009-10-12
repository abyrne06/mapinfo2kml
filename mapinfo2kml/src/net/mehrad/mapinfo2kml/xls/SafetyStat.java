package net.mehrad.mapinfo2kml.xls;

public class SafetyStat {

	private String state;
	private String seat;
	private double safetyLevel;
	
	public void setSafetyLevel(double safetyLevel) {
		this.safetyLevel = safetyLevel;
	}
	public double getSafetyLevel() {
		return safetyLevel;
	}
	public void setSeat(String miscStats) {
		this.seat = miscStats;
	}
	public String getSeat() {
		return seat;
	}
	public void setState(String division) {
		this.state = division;
	}
	public String getState() {
		return state;
	}
	
}
