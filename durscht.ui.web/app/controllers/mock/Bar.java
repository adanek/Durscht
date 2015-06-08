package controllers.mock;

import durscht.contracts.logic.model.IBar;
import durscht.contracts.logic.model.IBeer;

public class Bar implements IBar {

	private int id;
	private String name;
	private double distance;
	private IBeer[] beers;
	private double latitdue;
	private double longitude;

	public Bar(int id, String name, double distance, IBeer[] beers, double latitdue, double longitude) {
		this.id = id;
		this.name = name;
		this.distance = distance;
		this.beers = beers;
		this.latitdue = latitdue;
		this.longitude = longitude;
	}

	public Bar(int id, String name, double distance, IBeer[] beers) {
		super();
		this.id = id;
		this.name = name;
		this.distance = distance;
		this.beers = beers;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public double getDistance() {
		// TODO Auto-generated method stub
		return this.distance;
	}

	@Override
	public double getLatitude() {
		return this.latitdue;
	}

	@Override
	public double getLongitude() {
		return this.longitude;
	}
}
