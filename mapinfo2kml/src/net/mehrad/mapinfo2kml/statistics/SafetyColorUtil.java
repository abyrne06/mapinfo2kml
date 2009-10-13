package net.mehrad.mapinfo2kml.statistics;

/**
 * responsible for color/contrast of the seat for regions
 * @author Mehrad
 *
 */
public class SafetyColorUtil {

	/**
	 * returns hexadecimal number of a specific code based on how safety is the seat
	 * @param seatSafetyLevel
	 * @return
	 */
	public static String getColorForSafety(SeatSafetyLevel seatSafetyLevel)
	{
		String color="";

		if(seatSafetyLevel==SeatSafetyLevel.Marginal_Seat)
			color="0D25FF";
		else if(seatSafetyLevel==SeatSafetyLevel.Moderately_Safe)
			color="1C93FF";
		else if(seatSafetyLevel==SeatSafetyLevel.Safe)
			color="35E6FF";
		else if(seatSafetyLevel==SeatSafetyLevel.Very_Safe)
			color="5BFF76";
		else if(seatSafetyLevel==SeatSafetyLevel.Rock_Solid)
			color="238C00";
		
		return color;
	}
	
}
