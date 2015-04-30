package durscht.core;

import java.util.*;

import durscht.contracts.data.IBeer;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.IBeerHandler;
import durscht.core.config.ServiceLocator;
import durscht.core.helper.TrieST;

public class BeerHandler implements IBeerHandler {
	
	private TrieST<Beer> beers;

	public BeerHandler() {
		IDataHandler dataHandler = ServiceLocator.getDataHandler();
		
		beers = new TrieST<Beer>();
		
		Collection<IBeer> db_beers = dataHandler.getAllBeers();

		for (IBeer ibeer : db_beers) {
			Beer beer = new Beer();
			beer.setId(ibeer.getId());
			// No distinction between Brand and Type!
			beer.setBrand(ibeer.getName());
			// No distinction between Brand and Type!
			beer.setType(ibeer.getName());
			beer.setDescription(ibeer.getDescription());
			beers.put(ibeer.getName(), beer);
		}
	}

	@Override
	public durscht.contracts.ui.IBeer[] getBeersByPrefix(String prefix) {

		Iterable<String> prefixKeys = beers.keysWithPrefix(prefix);
		if(beers.isEmpty()){
			throw new NoSuchElementException("couldn't find a beer");
		}
		List<Beer> beersWithPrefix = new LinkedList<Beer>();
		for(String name: prefixKeys){
			beersWithPrefix.add(beers.get(name));
		}

		return beersWithPrefix.toArray(new Beer[beersWithPrefix.size()]);
	}
}
