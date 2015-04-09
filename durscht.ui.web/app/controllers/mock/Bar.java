package controllers.mock;

import durscht.contracts.IBar;
import durscht.contracts.IBeer;

public class Bar implements IBar {

	private int id;
	private String name;
	private double distance;
	private IBeer[] beers;
	
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
	public IBeer[] getBeers() {
		// TODO Auto-generated method stub
		return this.beers;
	}

}
