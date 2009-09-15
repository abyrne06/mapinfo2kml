package net.mehrad.mapinfo2kml.parser;

import java.util.Iterator;
import java.util.List;

import net.mehrad.mapinfo2kml.DataModel;
import net.mehrad.mapinfo2kml.xls.XlsModel;

public class XlsParser extends Parser{

	private static final int idColIndex = 0;
	protected XlsModel xlsModel;
	protected List<List<String>> excelRows;
	
	public XlsParser(List<List<String>> excelRows) {
		this.excelRows=excelRows;
		xlsModel=new XlsModel();
	}

	/**
	 * . precondition xlsFile needs to be initialized
	 * . postcondition xlsModel is created
	 * @return DataModel 
	 */
	@Override
	public DataModel parse() {
		if (this.excelRows==null)
			return null;
		List<String> headerRow=excelRows.get(0);
		this.xlsModel.setColheader(headerRow);
		excelRows.remove(0);
		for (Iterator rowIte=excelRows.iterator();rowIte.hasNext();)
		{
			List<String> row=(List<String>) rowIte.next();
			String idStr=row.get(idColIndex);
			this.xlsModel.getElectionResultRows().put(idStr.toLowerCase(), row);
		}
		return this.xlsModel;
			
	}

	public XlsModel getXlsModel() {
		return xlsModel;
	}

}
