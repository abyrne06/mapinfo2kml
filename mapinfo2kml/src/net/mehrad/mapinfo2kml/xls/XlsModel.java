package net.mehrad.mapinfo2kml.xls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mehrad.mapinfo2kml.DataModel;

/**
 * model which represents XLS electrion data
 * @author Mehrad
 *
 */
public class XlsModel extends DataModel{

	private Map<String,List<String>> electionResultRows;
	private List<String> colheader;
	private String id="city";
	private int idCol=0;	
	
	public List<String> getColheader() {
		return colheader;
	}

	public void setColheader(List<String> colheader) {
		this.colheader = colheader;
	}


	public Map<String, List<String>> getElectionResultRows() {
		if (this.electionResultRows==null)
			this.electionResultRows=new HashMap<String, List<String>>();
		return electionResultRows;
	}

	public void setElectionResultRows(Map<String, List<String>> electionResultRows) {
		this.electionResultRows = electionResultRows;
	}
	
	public String getRowStr(String rowId)
	{
		List<String> row=this.electionResultRows.get(rowId);
		StringBuffer rowStr=new StringBuffer();
		for (int i=0;i<row.size();i++)
		{
			String cellHeaderName=this.colheader.get(i);
			String cellValue=(String)row.get(i);
			rowStr.append(cellHeaderName+": "+cellValue+"<br/>");
		}
		return rowStr.toString();
	}
}
