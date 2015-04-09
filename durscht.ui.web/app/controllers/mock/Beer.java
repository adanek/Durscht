package controllers.mock;

import durscht.contracts.IBeer;

public class Beer implements IBeer {

	private String description;
	private String type;
	private String brand;

	public Beer(String brand, String type, String description) {
		this.brand = brand;
		this.type = type;
		this.description = description;
	}

	@Override
	public String getBrand() {
		return this.brand;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

}
