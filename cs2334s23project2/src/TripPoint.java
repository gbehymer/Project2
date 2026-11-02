import java.util.ArrayList;

public class TripPoint {
	private int time;
	private double lat;
	private double lon;
	private ArrayList<TripPoint> trip;
	
	private static final double EARTH_RADIUS = 6371.2; // Taken from the Wikipedia example
	
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
	
	// Helpers
	public double haversineDistance(TripPoint a, TripPoint b) {
		// Wikipedia defines this as: hav(theta) = hav(delta of phi) + cos(phi1) * cos(phi2) * hav(delta of lambda)
		
		// First, we convert the variables from degrees to radians
		double phi1 = Math.toRadians(a.getLat());
		double phi2 = Math.toRadians(b.getLat());
		double deltaPhi = Math.toRadians(b.getLat() - a.getLat());
		double deltaLambda = Math.toRadians(b.getLon() - a.getLon());
		
		// Second, we calculate hav(theta)
		double havTheta = Math.pow(Math.sin(deltaPhi / 2), 2) + Math.cos(phi1) * Math.cos(phi2) * Math.pow(Math.sin(deltaLambda / 2), 2);
		
		// Third, we correct theta from half, find the arcsin, and cap it at 1.0
		double radians = 2 * Math.asin(Math.min(1.0, Math.sqrt(havTheta)));
		
		// Finally, return the distance
		return EARTH_RADIUS * radians;
	}
}
