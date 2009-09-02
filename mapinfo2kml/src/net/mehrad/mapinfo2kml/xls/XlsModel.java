package net.mehrad.mapinfo2kml.xls;

import java.util.List;

import net.mehrad.mapinfo2kml.DataModel;

/**
 * model which represents XLS electrion data
 * @author Mehrad
 *
 */
public class XlsModel extends DataModel{

	private List<ElectionResultRow> electionResultRows;

	public List<ElectionResultRow> getElectionResultRows() {
		return electionResultRows;
	}

	public void setElectionResultRows(List<ElectionResultRow> electionResultRows) {
		this.electionResultRows = electionResultRows;
	}
}
