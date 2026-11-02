import java.io.*;
import java.util.*;

public class TripPoint {
	private int time;
	private double lat;
	private double lon;
	private static ArrayList<TripPoint> trip = new ArrayList<TripPoint>();;
	
	private static final double EARTH_RADIUS = 6371.0;
	
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

	public int getTime() {
		return time;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
	
	public static ArrayList<TripPoint> getTrip() {
		return trip;
	}
	
	public static void readFile(String filename) throws FileNotFoundException {
		trip.clear();
		
		Scanner sc = new Scanner(new File(filename));
		sc.nextLine(); // Trust that it is a real csv formatted properly
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			
			String[] arr = line.split(",");
			
			trip.add(new TripPoint(Integer.parseInt(arr[0]), Double.parseDouble(arr[1]), Double.parseDouble(arr[2])));
		}
	}
	
	public static double totalTime() {
		double totalTime = 0.0;
		
		double lastTime = 0;
		
		for (TripPoint point : trip) {
			double currentTime = point.getTime();
			
			totalTime += currentTime - lastTime;
			lastTime = currentTime;
		}
		
		return totalTime / 60;
	}
	
	public static double haversineDistance(TripPoint a, TripPoint b) {
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
	
	public static double totalDistance() {
		double totalDistance = 0.0;
		
		for (int i = 0; i < trip.size(); i++) {
			if (i < trip.size() - 1) {
				totalDistance += haversineDistance(trip.get(i), trip.get(i + 1));
			}
		}
		
		return totalDistance;
	}
}
