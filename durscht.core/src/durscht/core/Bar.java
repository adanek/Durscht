package durscht.core;

import durscht.contracts.ui.IBar;
import durscht.contracts.ui.IBeer;

public class Bar implements IBar {

	private int id;
	private String name;
	private double distance;
	private IBeer[] beers;
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setBeers(IBeer[] beers) {
		this.beers = beers;
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
	public IBeer[] getBeers() {
		return this.beers;
	}

}
