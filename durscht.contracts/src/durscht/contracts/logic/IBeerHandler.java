package durscht.contracts.logic;

import durscht.contracts.ui.IBeer;

public interface IBeerHandler {
	
	public IBeer[] getBeersByPrefix(String prefix);

}
