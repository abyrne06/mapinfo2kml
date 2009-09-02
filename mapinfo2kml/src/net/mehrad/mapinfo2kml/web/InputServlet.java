package net.mehrad.mapinfo2kml.web;

import java.io.File;

import org.boehn.kmlframework.kml.Kml;

import net.mehrad.mapinfo2kml.Translator;
import net.mehrad.mapinfo2kml.exception.ParserException;
import net.mehrad.mapinfo2kml.exception.ValidationException;
import net.mehrad.mapinfo2kml.validator.MidValidator;
import net.mehrad.mapinfo2kml.validator.MifValidator;
import net.mehrad.mapinfo2kml.validator.XlsValidator;


/**
 * a simple pseudocode for our workflow 
 * @author Mehrad
 *
 */
public class InputServlet {

	public void dopPost()
	{
		// lets think that we got the files from user and saved it
		// somewhere temporarily. now we want to create the KML
		File excelFile=null;
		File midFile=null;
		File mifFile=null;

		// according our req spec, excel file inst mandatory
		// so the constructor just accepts mid and mif files
		// and excel file should be setted seperately.
		Translator translator=new Translator(midFile, mifFile);
		translator.setXlsFile(excelFile);
		
		// we add validators seperately cuz in our class diagram
		// validator isnt a component inside translator, so we 
		// can use it or not. and translator in validation step,
		// checks for added validations and ignore the ones not
		// setted.
		translator.setXlsValidator(new XlsValidator());
		translator.setMidValidator(new MidValidator());
		translator.setMifValidator(new MifValidator());
		
		try {
			// the files return the Kml Model
			Kml kml = translator.translate();
			
			//now we can create output by calling:
			
			// kml.createKml();
			
			// we save it in temp folder and ask
			// user about download/view options.
			// by saving it in temp folder, we
			// can make sure that everything is
			// working stateless.
			
		} catch (ParserException e) {
			
			// we generate parser exceptions message
			
		} catch (ValidationException e) {
			// we generate validation exception message
		}
		
	}
}
