package durscht.core;
import durscht.contracts.ui.IBeer;


public class Beer implements IBeer {
	
	private String brand;
	private String type;
	private String description;

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDescription(String description) {
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
