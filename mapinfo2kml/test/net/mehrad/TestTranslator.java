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
	public void testTranslateAllExamples() {

		try {

			Translator translator = new Translator(new File("testData\\QLD_Federal_Electoral_Boundaries.mid"), new File(
			"testData\\QLD_Federal_Electoral_Boundaries.mif"));
			Kml result = translator.translate();
			result.createKml("TestResults\\QLD_Federal_Electoral_Boundaries.kml");

			//created by textpad
			translator = new Translator(new File("testData\\g56151_r.mid"), new File("testData\\g56151_r.mif"));result = translator.translate();result.createKml("TestResults\\g56151_r.kml");
			translator = new Translator(new File("testData\\g56153_r.mid"), new File("testData\\g56153_r.mif"));result = translator.translate();result.createKml("TestResults\\g56153_r.kml");
			translator = new Translator(new File("testData\\g5615a_p.mid"), new File("testData\\g5615a_p.mif"));result = translator.translate();result.createKml("TestResults\\g5615a_p.kml");
			translator = new Translator(new File("testData\\g5615b_r.mid"), new File("testData\\g5615b_r.mif"));result = translator.translate();result.createKml("TestResults\\g5615b_r.kml");
			translator = new Translator(new File("testData\\g5615c_l.mid"), new File("testData\\g5615c_l.mif"));result = translator.translate();result.createKml("TestResults\\g5615c_l.kml");
			translator = new Translator(new File("testData\\g5615c_r.mid"), new File("testData\\g5615c_r.mif"));result = translator.translate();result.createKml("TestResults\\g5615c_r.kml");
			translator = new Translator(new File("testData\\g5615d_l.mid"), new File("testData\\g5615d_l.mif"));result = translator.translate();result.createKml("TestResults\\g5615d_l.kml");
			translator = new Translator(new File("testData\\g5615dqt.mid"), new File("testData\\g5615dqt.mif"));result = translator.translate();result.createKml("TestResults\\g5615dqt.kml");
			translator = new Translator(new File("testData\\g5615e_p.mid"), new File("testData\\g5615e_p.mif"));result = translator.translate();result.createKml("TestResults\\g5615e_p.kml");
			translator = new Translator(new File("testData\\g5615f_l.mid"), new File("testData\\g5615f_l.mif"));result = translator.translate();result.createKml("TestResults\\g5615f_l.kml");
			translator = new Translator(new File("testData\\g5615f_r.mid"), new File("testData\\g5615f_r.mif"));result = translator.translate();result.createKml("TestResults\\g5615f_r.kml");
			translator = new Translator(new File("testData\\g5615g_p.mid"), new File("testData\\g5615g_p.mif"));result = translator.translate();result.createKml("TestResults\\g5615g_p.kml");
			translator = new Translator(new File("testData\\g5615h_l.mid"), new File("testData\\g5615h_l.mif"));result = translator.translate();result.createKml("TestResults\\g5615h_l.kml");
			translator = new Translator(new File("testData\\g5615l_p.mid"), new File("testData\\g5615l_p.mif"));result = translator.translate();result.createKml("TestResults\\g5615l_p.kml");
			translator = new Translator(new File("testData\\g5615m_l.mid"), new File("testData\\g5615m_l.mif"));result = translator.translate();result.createKml("TestResults\\g5615m_l.kml");
			translator = new Translator(new File("testData\\g5615n_p.mid"), new File("testData\\g5615n_p.mif"));result = translator.translate();result.createKml("TestResults\\g5615n_p.kml");
			translator = new Translator(new File("testData\\g5615o_r.mid"), new File("testData\\g5615o_r.mif"));result = translator.translate();result.createKml("TestResults\\g5615o_r.kml");
			translator = new Translator(new File("testData\\g5615p_l.mid"), new File("testData\\g5615p_l.mif"));result = translator.translate();result.createKml("TestResults\\g5615p_l.kml");
			translator = new Translator(new File("testData\\g5615q_r.mid"), new File("testData\\g5615q_r.mif"));result = translator.translate();result.createKml("TestResults\\g5615q_r.kml");
			translator = new Translator(new File("testData\\g5615r_l.mid"), new File("testData\\g5615r_l.mif"));result = translator.translate();result.createKml("TestResults\\g5615r_l.kml");
			translator = new Translator(new File("testData\\g5615r_p.mid"), new File("testData\\g5615r_p.mif"));result = translator.translate();result.createKml("TestResults\\g5615r_p.kml");
			translator = new Translator(new File("testData\\g5615t_r.mid"), new File("testData\\g5615t_r.mif"));result = translator.translate();result.createKml("TestResults\\g5615t_r.kml");
			translator = new Translator(new File("testData\\g5615u_l.mid"), new File("testData\\g5615u_l.mif"));result = translator.translate();result.createKml("TestResults\\g5615u_l.kml");
			translator = new Translator(new File("testData\\g5615u_p.mid"), new File("testData\\g5615u_p.mif"));result = translator.translate();result.createKml("TestResults\\g5615u_p.kml");
			translator = new Translator(new File("testData\\g5615v_l.mid"), new File("testData\\g5615v_l.mif"));result = translator.translate();result.createKml("TestResults\\g5615v_l.kml");
			translator = new Translator(new File("testData\\g5615v_p.mid"), new File("testData\\g5615v_p.mif"));result = translator.translate();result.createKml("TestResults\\g5615v_p.kml");
			translator = new Translator(new File("testData\\g5615w_l.mid"), new File("testData\\g5615w_l.mif"));result = translator.translate();result.createKml("TestResults\\g5615w_l.kml");
			translator = new Translator(new File("testData\\g5615w_r.mid"), new File("testData\\g5615w_r.mif"));result = translator.translate();result.createKml("TestResults\\g5615w_r.kml");
			translator = new Translator(new File("testData\\g5615y_p.mid"), new File("testData\\g5615y_p.mif"));result = translator.translate();result.createKml("TestResults\\g5615y_p.kml");

			
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
