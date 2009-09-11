package net.mehrad.mapinfo2kml.mif;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class is the super class of all presentable parts in mif model.
 * 
 * @author Mehrad
 * 
 */
public class MifData {


	protected Map<String,Object> objectData=new HashMap<String, Object>();

	public Map<String, Object> getObjectData() {
		return objectData;
	}

	public void setObjectData(Map<String, Object> objectData) {
		this.objectData = objectData;
	}
	
	
	
	/*
	 * This method translates itself to its mapped property in the kml model. i
	 * think the parser and model should be independent from translate, so this
	 * so the method bellow shouldnt be called
	 */
	/*
	 * public Object tranlate() { throw new
	 * UnsupportedOperationException("Method not implemented"); };
	 */

}
