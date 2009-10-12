package net.mehrad.mapinfo2kml.xls;

public class FederalElectionResults {

	  private String state;
	  private int year;
	  private String seat;
	  public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public String getMP() {
		return MP;
	}
	public void setMP(String mP) {
		MP = mP;
	}
	public int getHeldSince() {
		return heldSince;
	}
	public void setHeldSince(int heldSince) {
		this.heldSince = heldSince;
	}
	public String getPreviouslyHeld() {
		return previouslyHeld;
	}
	public void setPreviouslyHeld(String previouslyHeld) {
		this.previouslyHeld = previouslyHeld;
	}
	private String party;
	  private double percentage;
	  private String MP;
	  private int heldSince;
	  private String previouslyHeld;

}
