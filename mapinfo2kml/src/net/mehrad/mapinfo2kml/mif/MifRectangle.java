package net.mehrad.mapinfo2kml.mif;

/**
 * 
 * @author Mehrad
 *
 */
public class MifRectangle extends MifData{

	private MifCoordinate coordinate1;
	private MifCoordinate coordinate2;
	private MifBrush brush;
	private MifPen pen;
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
	public MifBrush getBrush() {
		return brush;
	}
	public void setBrush(MifBrush brush) {
		this.brush = brush;
	}
	public MifPen getPen() {
		return pen;
	}
	public void setPen(MifPen pen) {
		this.pen = pen;
	}
	
	
}
