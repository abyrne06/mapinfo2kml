package net.mehrad.mapinfo2kml.mif;

/**
 * Using this class a string can be shown between to points. 
 * @author Mehrad
 *
 */
public class MifText extends MifData{

	private String text;
	private MifCoordinate coordinate1;
	private MifCoordinate coordinate2;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public MifCoordinate getCoordinate1() {
		return coordinate1;
	}
	public void setCoordinate1(MifCoordinate coordinate1) {
		this.coordinate1 = coordinate1;
	}
	public MifCoordinate getCoordinate2() {
		return coordinate2;
	}
	public void setCoordinate2(MifCoordinate coordinate2) {
		this.coordinate2 = coordinate2;
	}
	
	
	
}
