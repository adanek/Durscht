package durscht.core;

import durscht.contracts.ui.IBar;

public class Bar implements IBar {

	private int id;
	private String name;
	private double distance;
	
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

}
