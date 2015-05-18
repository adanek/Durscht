package durscht.contracts.logic;

import durscht.contracts.ui.IBeer;

public interface IBeerHandler {

	IBeer[] getBeersByPrefix(String prefix);

	IBeer createNewBeer(String brand, String type, String description) throws IllegalStateException;

	IBeer[] getAllBeers();
}
