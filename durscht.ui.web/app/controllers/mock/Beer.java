package controllers.mock;

import durscht.contracts.ui.IBeer;

public class Beer implements IBeer {

	private final int id;
	private String description;
	private String type;
	private String brand;

	public Beer(int id, String brand, String type, String description) {
		this.id = id;
		this.brand = brand;
		this.type = type;
		this.description = description;
	}

	@Override
	public Integer getId() {
		return this.id;
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
