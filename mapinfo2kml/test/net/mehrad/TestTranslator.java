/**
 * 
 */
package net.mehrad;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import net.mehrad.mapinfo2kml.Translator;
import net.mehrad.mapinfo2kml.exception.ParserException;
import net.mehrad.mapinfo2kml.exception.ValidationException;

import org.boehn.kmlframework.kml.Kml;
import org.boehn.kmlframework.kml.KmlException;
import org.junit.Test;

/**
 * @author Mehrad
 * 
 */
public class TestTranslator {

	/**
	 * Test method for {@link net.mehrad.mapinfo2kml.Translator#translate()}.
	 */
	@Test
	public void testTranslate() {

		Translator translator = new Translator(null, new File(
				"QLD_Federal_Electoral_Boundaries.mif"));
		try {
			Kml result = translator.translate();
			result.createKml("result.kml");

			assertNotNull(result);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
