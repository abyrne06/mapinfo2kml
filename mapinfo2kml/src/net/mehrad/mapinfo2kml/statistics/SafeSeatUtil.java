package net.mehrad.mapinfo2kml.statistics;

/**
 * 
 * @author Mehrad
 * 
 */
public class SafeSeatUtil {

	public static SeatSafetyLevel getSeatSafetyLevel(Double seatSafetyPercentage) {
		SeatSafetyLevel safetyLevel = null;

		if (seatSafetyPercentage <= 5)
			safetyLevel = SeatSafetyLevel.Marginal_Seat;
		else if (seatSafetyPercentage <= 10)
			safetyLevel = SeatSafetyLevel.Moderately_Safe;
		else if (seatSafetyPercentage <= 15)
			safetyLevel = SeatSafetyLevel.Safe;
		else if (seatSafetyPercentage <= 25)
			safetyLevel = SeatSafetyLevel.Very_Safe;
		else
			safetyLevel = SeatSafetyLevel.Rock_Solid;

		return safetyLevel;
	}

}
