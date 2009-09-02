package net.mehrad.mapinfo2kml.xls;

/**
 * class that represents a single row of QLD_FEDERAL_RESULT_BY_ELECTORATE_2004
 * @author Mehrad
 *
 */
class ElectionResultRow
{
	private String division;
	private String state;
	private String ALP;
	private String LP;
	private String NP;
	private String DEM;
	private String GRN;
	private String OTH;
	private String twoPartyPrefferedLNP;
	private String twoPartyPrefferedALP;
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getALP() {
		return ALP;
	}
	public void setALP(String aLP) {
		ALP = aLP;
	}
	public String getLP() {
		return LP;
	}
	public void setLP(String lP) {
		LP = lP;
	}
	public String getNP() {
		return NP;
	}
	public void setNP(String nP) {
		NP = nP;
	}
	public String getDEM() {
		return DEM;
	}
	public void setDEM(String dEM) {
		DEM = dEM;
	}
	public String getGRN() {
		return GRN;
	}
	public void setGRN(String gRN) {
		GRN = gRN;
	}
	public String getOTH() {
		return OTH;
	}
	public void setOTH(String oTH) {
		OTH = oTH;
	}
	public String getTwoPartyPrefferedLNP() {
		return twoPartyPrefferedLNP;
	}
	public void setTwoPartyPrefferedLNP(String twoPartyPrefferedLNP) {
		this.twoPartyPrefferedLNP = twoPartyPrefferedLNP;
	}
	public String getTwoPartyPrefferedALP() {
		return twoPartyPrefferedALP;
	}
	public void setTwoPartyPrefferedALP(String twoPartyPrefferedALP) {
		this.twoPartyPrefferedALP = twoPartyPrefferedALP;
	}
	
	
}
