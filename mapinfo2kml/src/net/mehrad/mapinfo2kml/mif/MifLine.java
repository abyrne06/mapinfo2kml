package net.mehrad.mapinfo2kml.mif;

/**
 * 
 * @author Mehrad
 *
 */
public class MifLine extends MifData{

	private MifCoordinate coordinate1;
	private MifCoordinate coordinate2;
	private MifPen mifPen;
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
	public MifPen getMifPen() {
		return mifPen;
	}
	public void setMifPen(MifPen mifPen) {
		this.mifPen = mifPen;
	}
	
	
}
