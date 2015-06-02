package durscht.core.test;

import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.IBeerHandler;
import durscht.contracts.logic.model.IBeer;
import durscht.core.config.ServiceLocator;
import durscht.core.helper.TrieST;
import durscht.model.Beer;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class BeerHandlerTest {

	private final class TestBeerHandler implements IBeerHandler {

		private IDataHandler dataHandler;
		private TrieST<Beer> beers = new TrieST<Beer>();

		@Override
		public IBeer[] getBeersByPrefix(String prefix) {
			Iterable<String> prefixKeys = beers.keysWithPrefix(prefix);
			if (beers.isEmpty()) {
				throw new NoSuchElementException("couldn't find a beer");
			}
			List<Beer> beersWithPrefix = new LinkedList<Beer>();
			for (String name : prefixKeys) {
				beersWithPrefix.add(beers.get(name));
			}

			return beersWithPrefix.toArray(new Beer[beersWithPrefix.size()]);
		}

		public IBeer[] getAllBeers() {
			Iterable<String> Keys = beers.keys();
			if (beers.isEmpty()) {
				throw new NoSuchElementException("couldn't find a beer");
			}
			List<Beer> beersList = new LinkedList<Beer>();
			for (String name : Keys) {
				beersList.add(beers.get(name));
			}

			return beersList.toArray(new Beer[beersList.size()]);
		}

		@Override
		public IBeer createNewBeer(String brand, String type, String description) throws IllegalStateException {
			/*
			 * IDataHandler dataHandler = getDataHandler();
			 * 
			 * IBeer ibeer = dataHandler.createBeer(brand, type, description);
			 * 
			 * Beer beer = new Beer(); beer.setId(ibeer.getId()); beer.setBrand(ibeer.getBrand());
			 * beer.setType(ibeer.getType()); beer.setDescription(ibeer.getDescription());
			 * 
			 * return beer;
			 */
			return null;
		}

		@Override
		public IBeer[] getAllBeersVerified() {
			return new IBeer[0];
		}

		@Override
		public IBeer[] getAllBeersNotVerified() {
			return new IBeer[0];
		}

		@Override
		public IBeer verifyBeer(int id) {
			return null;
		}

		@Override
		public void deleteBeer(int id) throws IllegalArgumentException {

		}


		public IBeer verifyBeer(IBeer uiBeer) {
			return null;
		}

		public void setDataHandler(IDataHandler dataHandler) {
			this.dataHandler = dataHandler;
		}

		public void setBeers(Collection<Beer> beers) {
			for (Beer beer : beers) {
				this.beers.put(beer.getBrand() + beer.getType(), beer);
			}
		}
	}

	private TestBeerHandler beerHandler;

	@Before
	public void setup() {

		ServiceLocator.setBeerHandler(new TestBeerHandler());
		beerHandler = (TestBeerHandler) ServiceLocator.getBeerHandler();

		Beer stiegl = new Beer();
		stiegl.setBrand("Stiegl");
		stiegl.setType("Goldbräu");
		stiegl.setId(10);

		Beer zipfer = new Beer();
		zipfer.setBrand("Zipfer");
		zipfer.setType("Märzen");
		zipfer.setId(20);

		Collection<Beer> beers = new ArrayList<Beer>();
		beers.add(stiegl);
		beers.add(zipfer);

		beerHandler.setBeers(beers);

	}

	@Test
	public void getBeersByPrefixTest() {

		IBeer[] ibeers = beerHandler.getBeersByPrefix("S");

		assertEquals(1, ibeers.length);

		int beerId = ibeers[0].getId();
		assertEquals(beerId, 10);
		assertEquals(ibeers[0].getBrand(), "Stiegl");
		assertEquals(ibeers[0].getType(), "Goldbräu");
	}

	@Test
	public void getBeersByPrefixEmptyListTest() {
		IBeer[] ibeers = beerHandler.getBeersByPrefix("Y");

		assertEquals(0, ibeers.length);
	}
}
