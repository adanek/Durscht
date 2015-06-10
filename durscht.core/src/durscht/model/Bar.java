package durscht.model;

import durscht.contracts.logic.model.IBar;

public class Bar implements IBar {

	private int id;
	private String name;
	private String url;
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
		return this.latitude;
	}

	/**
	 * Set the latitude of the bar to the new value
	 * 
	 * @param val the new value of the latitude
	 */
	public void setLatitude(double val) {
		this.latitude = val;
	}

	@Override
	public double getLongitude() {
		return this.longitude;
	}

	/**
	 * Set the longitude of the bar to the new value
	 * 
	 * @param val the new value of the bar
	 */
	public void setLongitude(double val) {
		this.longitude = val;
	}

	@Override
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
