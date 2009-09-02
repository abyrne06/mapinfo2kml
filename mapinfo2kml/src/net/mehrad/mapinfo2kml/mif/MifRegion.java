package net.mehrad.mapinfo2kml.mif;

import java.util.List;

/**
 * This class can be used to specify a region using a collection of points.
 * @author Mehrad
 *
 */
public class MifRegion extends MifData{

	private List<List<MifCoordinate>> points;
	private MifPen pen;
	private MifBrush mifBrush;
	private MifPoint center;
	public List<List<MifCoordinate>> getPoints() {
		return points;
	}
	
	public void setPoints(List<List<MifCoordinate>> points) {
		this.points = points;
	}
	public MifPen getPen() {
		return pen;
	}
	public void setPen(MifPen pen) {
		this.pen = pen;
	}
	public MifBrush getMifBrush() {
		return mifBrush;
	}
	public void setMifBrush(MifBrush mifBrush) {
		this.mifBrush = mifBrush;
	}
	public MifPoint getCenter() {
		return center;
	}
	public void setCenter(MifPoint center) {
		this.center = center;
	}

	

}
