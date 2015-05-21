package durscht.model;

import durscht.contracts.ui.IBar;

public class Bar implements IBar {

	private int id;
	private String name;
	private double distance;
	private double latitude;
	private double longitude;

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getDistance() {
		return this.distance;
	}

	@Override
	public double getLatitude() {
		return 0;
	}

	/**
	 * Set the latitude of the bar to the new value
	 * @param val the new value of the latitude
	 */
	public void setLatitude(double val) {
		this.latitude = val;
	}

	@Override
	public double getLongitude() {
		return 0;
	}

	/**
	 * Set the longitude of the bar to the new value
	 * @param val the new value of the bar
	 */
	public void setLongitude(double val){
		this.longitude = val;
	}

}
