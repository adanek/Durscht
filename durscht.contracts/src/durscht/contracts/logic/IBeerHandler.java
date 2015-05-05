package durscht.contracts.logic;

import durscht.contracts.ui.IBeer;

public interface IBeerHandler {

	public IBeer[] getBeersByPrefix(String prefix);

	public IBeer createNewBeer(String brand, String type, String description) throws IllegalStateException;
}
