package net.mehrad.mapinfo2kml.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ParseExcelUtils {

	public ParseExcelUtils() {

	}

	public static ArrayList<ArrayList<String>> genExcelRows2(
			InputStream excelInput) throws IOException {
		POIFSFileSystem fs = new POIFSFileSystem(excelInput);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
		boolean rowResolved = false;
		for (Iterator rit = sheet.rowIterator(); rit.hasNext();) {
			HSSFRow row = (HSSFRow) rit.next();
			ArrayList<String> cells = new ArrayList<String>();
			for (Iterator cit = row.cellIterator(); cit.hasNext();) {
				HSSFCell cell = (HSSFCell) cit.next();
				cells.add(cell.toString());
			}
			rows.add(cells);
		}
		return rows;
	}
	
	public static ArrayList<ArrayList<String>> genExcelRows(InputStream excelInput) throws IOException
	{
		POIFSFileSystem fs      =
            new POIFSFileSystem(excelInput);
		 HSSFWorkbook wb = new HSSFWorkbook(fs);
		 HSSFSheet sheet = wb.getSheetAt(0);
		 ArrayList<ArrayList<String>> rows=new ArrayList<ArrayList<String>>();
		 boolean rowResolved=false;
		 for (Iterator rit = sheet.rowIterator(); rit.hasNext(); ) {
				HSSFRow row = (HSSFRow)rit.next();
				if (!rowResolved)
				{
					HSSFRow row1 = (HSSFRow)rit.next();					
					ArrayList<String> headerRow=getHeaderRow(row, row1);
					rows.add(headerRow);
					rowResolved=true;
				}
				else
				{
					ArrayList<String> cells=new ArrayList<String>();
					for (Iterator cit = row.cellIterator(); cit.hasNext(); ) {
						HSSFCell cell = (HSSFCell)cit.next();
						cells.add(cell.toString());
					}
					rows.add(cells);
				}
			}	
		 return rows;
		 }

	private static ArrayList<String> getHeaderRow(HSSFRow row0, HSSFRow row1) {
		int i=0;
		String topCellStr="";
		String botCellStr="";
		ArrayList<String> header=new ArrayList<String>();
		for (Iterator cit = row0.cellIterator(); cit.hasNext(); ) {
			HSSFCell cellTop = (HSSFCell)cit.next();
			HSSFCell cellBottom = (HSSFCell)row1.getCell(i++);
			if (cellTop.toString()!=null && !cellTop.toString().equals(""))
				topCellStr=cellTop.toString();
			botCellStr=cellBottom.toString();
//			System.out.println(botCellStr);
			String h=topCellStr+" "+botCellStr;
			header.add(h);
		}		
		return header;
	}
	
	
}
