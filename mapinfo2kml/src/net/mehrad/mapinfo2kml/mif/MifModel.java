package net.mehrad.mapinfo2kml.mif;

import java.util.ArrayList;
import java.util.List;

import net.mehrad.mapinfo2kml.DataModel;

/**
 * Model that represents mapInfo mif file in 
 * form of a java-object
 * @author Mehrad
 *
 */
public class MifModel extends DataModel{

	private String version;
	private String charset;
	private String delimiter;
	private List<MifColumn> columns=new ArrayList<MifColumn>();
	private List<MifData> mifDatas=new ArrayList<MifData>();

	
	public void addMifData(MifData mifData)
	{
		mifDatas.add(mifData);
	}

	public void addMifColumn(MifColumn mifColumn)
	{
		columns.add(mifColumn);
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getDelimiter() {
		return delimiter;
	}
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	public List<MifColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<MifColumn> columns) {
		this.columns = columns;
	}
	public List<MifData> getMifDatas() {
		return mifDatas;
	}
	public void setMifDatas(List<MifData> mifDatas) {
		this.mifDatas = mifDatas;
	}

	
	
}
