package durscht.core.test;

import java.util.NoSuchElementException;

import durscht.core.BeerHandler;
import org.junit.Test;
import org.mockito.Mockito;


// HOW TO MOCK CONSTRUCTOR?
public class BeerHandlerTest {

    @Test (expected = NoSuchElementException.class)
    public void getBeersByPrefixNoSuchElementTest() {
        BeerHandler beerHandler = Mockito.mock(BeerHandler.class);
        beerHandler.getBeersByPrefix("s");
    }
}
