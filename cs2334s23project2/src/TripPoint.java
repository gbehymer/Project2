import java.util.ArrayList;

public class TripPoint {
	private int time;
	private double lat;
	private double lon;
	private ArrayList<TripPoint> trip;
	
	public TripPoint(int time, double lat, double lon) {
		this.time = time;
		this.lat = lat;
		this.lon = lon;
	}
}
