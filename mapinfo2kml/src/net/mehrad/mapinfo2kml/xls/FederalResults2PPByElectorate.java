package net.mehrad.mapinfo2kml.xls;

public class FederalResults2PPByElectorate {

	private String state;
	private int year;
	private String division;
	private String partyHeld;
	private int LNP_Votes;
	private double LNP_Percentage;
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
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getPartyHeld() {
		return partyHeld;
	}
	public void setPartyHeld(String partyHeld) {
		this.partyHeld = partyHeld;
	}
	public int getLNP_Votes() {
		return LNP_Votes;
	}
	public void setLNP_Votes(int lNPVotes) {
		LNP_Votes = lNPVotes;
	}
	public double getLNP_Percentage() {
		return LNP_Percentage;
	}
	public void setLNP_Percentage(double lNPPercentage) {
		LNP_Percentage = lNPPercentage;
	}
	public int getALP_Votes() {
		return ALP_Votes;
	}
	public void setALP_Votes(int aLPVotes) {
		ALP_Votes = aLPVotes;
	}
	public double getALP_Percentage() {
		return ALP_Percentage;
	}
	public void setALP_Percentage(double aLPPercentage) {
		ALP_Percentage = aLPPercentage;
	}
	public int getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}
	public double getSwing() {
		return swing;
	}
	public void setSwing(double swing) {
		this.swing = swing;
	}
	private int ALP_Votes;
	private double ALP_Percentage;
	private int totalVotes;
	private double swing;

}
