package net.mehrad.mapinfo2kml.mif;

/**
 * The mif model Arc property class type.
 * @author Mehrad
 */
public class MifArc extends MifData{

	private MifCoordinate mifCoordinate1;
	private MifCoordinate mifCoordinate2;
	private double a;
	private double b;
	private MifPen pen;
	
	public MifCoordinate getMifCoordinate1() {
		return mifCoordinate1;
	}
	public void setMifCoordinate1(MifCoordinate mifCoordinate1) {
		this.mifCoordinate1 = mifCoordinate1;
	}
	public MifCoordinate getMifCoordinate2() {
		return mifCoordinate2;
	}
	public void setMifCoordinate2(MifCoordinate mifCoordinate2) {
		this.mifCoordinate2 = mifCoordinate2;
	}
	public double getA() {
		return a;
	}
	public void setA(double a) {
		this.a = a;
	}
	public double getB() {
		return b;
	}
	public void setB(double b) {
		this.b = b;
	}
	public MifPen getPen() {
		return pen;
	}
	public void setPen(MifPen pen) {
		this.pen = pen;
	}
	
	
	
}
