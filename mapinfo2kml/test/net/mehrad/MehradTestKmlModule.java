package net.mehrad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.boehn.kmlframework.kml.Document;
import org.boehn.kmlframework.kml.Kml;
import org.boehn.kmlframework.kml.KmlException;
import org.boehn.kmlframework.kml.LinearRing;
import org.boehn.kmlframework.kml.Placemark;
import org.boehn.kmlframework.kml.Point;

public class MehradTestKmlModule {

		public static void main(String[] args) throws KmlException, IOException {
			
			Kml kml = new Kml();
			Placemark ifi = new Placemark("Department of Informatics");
			ifi.setDescription("Web: http://www.ifi.uio.no<br/>Phone: +47 22852410");
			ifi.setLocation(10.717344, 59.943355);
			Document document = new Document();
			kml.setFeature(document);
			List<Point> coordinates=new ArrayList<Point>();
			for(int i=0;i<12;i++)
				coordinates.add(new Point(10+Math.random(),59+Math.random()));
			LinearRing linearRing=new LinearRing();
			linearRing.setCoordinates(coordinates);
			ifi.setGeometry(linearRing);
			document.addFeature(ifi);
			kml.createKml("mehrad.kml");
		}


	
}
