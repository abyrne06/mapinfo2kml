package net.mehrad.mapinfo2kml.mid;

import java.util.List;

import net.mehrad.mapinfo2kml.DataModel;

/**
 * Model that represents mapInfo mid file in 
 * form of a java-object
 * @author Mehrad
 *
 */
public class MidModel extends DataModel{

	private List<String> midFileLines;

	public List<String> getMidFileLines() {
		return midFileLines;
	}

	public void setMidFileLines(List<String> midFileLines) {
		this.midFileLines = midFileLines;
	}
	
	
}
