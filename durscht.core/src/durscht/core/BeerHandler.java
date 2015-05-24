package durscht.core;

import java.util.*;

import durscht.contracts.data.IBeer;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.IBeerHandler;
import durscht.core.config.ServiceLocator;
import durscht.core.helper.TrieST;
import durscht.model.Beer;
import sun.nio.cs.ext.IBM037;

public class BeerHandler implements IBeerHandler {

	private TrieST<Beer> beers;

	private IDataHandler dataHandler;

	private IDataHandler getDataHandler() {
		if (dataHandler == null)
			dataHandler = ServiceLocator.getDataHandler();

		return dataHandler;
	}

	public void setDataHandler(IDataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	public BeerHandler() {

		beers = new TrieST<Beer>();
		Collection<IBeer> db_beers = dataHandler.getAllBeersVerified();

		for (IBeer ibeer : db_beers) {
			// Keys contain only lower case letters
			beers.put(ibeer.getBrand().toLowerCase() + ibeer.getType().toLowerCase(), BeerHandler.convertDBtoUI(ibeer));
		}
	}

	@Override
	public durscht.contracts.ui.IBeer[] getBeersByPrefix(String prefix) {
		// Keys contain only lower case letters
		Iterable<String> prefixKeys = beers.keysWithPrefix(prefix.toLowerCase());

		List<Beer> beersWithPrefix = new LinkedList<Beer>();
		for (String name : prefixKeys) {
			beersWithPrefix.add(beers.get(name));
		}

		return beersWithPrefix.toArray(new Beer[beersWithPrefix.size()]);
	}

	@Override
	public durscht.contracts.ui.IBeer createNewBeer(String brand, String type, String description)
			throws IllegalStateException {

		// Verification-flag is per default false
		IBeer ibeer = dataHandler.createBeer(brand, type, description, false);

		Beer beer = new Beer();
		beer.setId(ibeer.getId());
		beer.setBrand(ibeer.getBrand());
		beer.setType(ibeer.getType());
		beer.setDescription(ibeer.getDescription());

		return beer;
	}

	@Override
	public durscht.contracts.ui.IBeer[] getAllBeersVerified() {
		Iterable<String> Keys = beers.keys();

		List<Beer> beersList = new LinkedList<Beer>();
		for (String name : Keys) {
			beersList.add(beers.get(name));
		}

		return beersList.toArray(new Beer[beersList.size()]);
	}

	@Override
	public durscht.contracts.ui.IBeer[] getAllBeersNotVerified() {
		Collection<IBeer> db_beers = dataHandler.getAllBeersUnverified();

		Beer[] beers = new Beer[db_beers.size()];
		int i = 0;

		for (IBeer ibeer : db_beers) {
			beers[i] = BeerHandler.convertDBtoUI(ibeer);
			i++;
		}

		return beers;
	}

	@Override
	public durscht.contracts.ui.IBeer verifyBeer(durscht.contracts.ui.IBeer uiBeer) {
		Beer beer = convertDBtoUI(dataHandler.verifyBeer(uiBeer.getId()));
		// Adds verified beer to cached beers
		beers.put(beer.getBrand().toLowerCase() + beer.getType().toLowerCase(), beer);

		return beer;
	}

	protected static Beer convertDBtoUI(IBeer iBeer) {
		Beer beer = new Beer();
		beer.setBrand(iBeer.getBrand());
		beer.setDescription(iBeer.getDescription());
		beer.setId(iBeer.getId());
		beer.setType(iBeer.getType());

		return beer;
	}

}
