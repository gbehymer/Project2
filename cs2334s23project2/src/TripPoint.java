import java.util.ArrayList;

public class TripPoint {
	private int time;
	private double lat;
	private double lon;
	private ArrayList<TripPoint> trip;
	
	/**
	 * 
	 * @param time
	 * @param lat
	 * @param lon
	 */
	public TripPoint(int time, double lat, double lon) {
		this.time = time;
		this.lat = lat;
		this.lon = lon;
	}

	// Getters
	public int getTime() {
		return time;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
	
	public ArrayList<TripPoint> getTrip() {
		return trip;
	}
}
