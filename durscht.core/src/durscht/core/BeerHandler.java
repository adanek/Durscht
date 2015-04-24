package durscht.core;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import durscht.contracts.data.IBeer;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.IBeerHandler;
import durscht.core.config.ServiceLocator;

public class BeerHandler implements IBeerHandler {
	
	private Set<Beer> beers;
	
	public static final Comparator<Beer> BY_BRAND = new ByName();
	
	private static class ByName implements Comparator<Beer> {
		public int compare(Beer one, Beer other){
			if (one.getBrand().compareToIgnoreCase(other.getBrand()) == 0) {
				return one.getType().compareToIgnoreCase(other.getType());
			}
			return one.getBrand().compareToIgnoreCase(other.getBrand());
		}
	}
	
	public BeerHandler() {
		IDataHandler dataHandler = ServiceLocator.getDataHandler();
		
		beers = new TreeSet<Beer>(BY_BRAND);
		
		Collection<IBeer> db_beers = dataHandler.getAllBeers();
		for (IBeer ibeer : db_beers) {
			Beer beer = new Beer();
			beer.setId(ibeer.getId());
			beer.setBrand(ibeer.getName());
			beer.setType(ibeer.getName());
			beer.setDescription(ibeer.getDescription());
			beers.add(beer);
		}
		
	}

	@Override
	public durscht.contracts.ui.IBeer[] getBeersByPrefix(String prefix) {
		return beers.toArray(new Beer[beers.size()]);
	}
}
