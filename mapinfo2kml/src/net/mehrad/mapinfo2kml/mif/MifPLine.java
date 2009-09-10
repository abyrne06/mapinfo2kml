package net.mehrad.mapinfo2kml.mif;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for connection a collection of points to each other.
 * @author Mehrad
 *
 */
public class MifPLine extends MifData{

	private List<List<MifCoordinate>> sections=new ArrayList<List<MifCoordinate>>();
	private MifPen mifPen;
	
	public void addSection(List<MifCoordinate> mifCoordinates)
	{
		sections.add(mifCoordinates);
	}
	
	public List<List<MifCoordinate>> getSections() {
		return sections;
	}
	public void setSections(List<List<MifCoordinate>> sections) {
		this.sections = sections;
	}
	public MifPen getMifPen() {
		return mifPen;
	}
	public void setMifPen(MifPen mifPen) {
		this.mifPen = mifPen;
	}
	
	
}
