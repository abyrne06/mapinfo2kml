package net.mehrad.mapinfo2kml.mif;

/**
 * This class can be used to draw a line. It basically will be used by MifLine class. 
 * @author Mehrad
 *
 */
public class MifPen {

	private int width;
	private int pattern;
	private int color;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getPattern() {
		return pattern;
	}
	public void setPattern(int pattern) {
		this.pattern = pattern;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	
	
	
}
