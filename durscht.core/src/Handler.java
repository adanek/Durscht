import java.util.Collection;

import durscht.contracts.data.IBar;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.IHandler;


public class Handler implements IHandler {

	@Override
	public void getNearBars(double latitude, double longitude) {
		IDataHandler dataHandler = null;
		Collection<IBar> dataHandler.getBarsCoordinates(latitude - 5e-4, latitude + 5e-4, longitude - 5e-4, longitude + 5e-4);
		
	}

	@Override
	public void putPosting() {
		// TODO Auto-generated method stub
		
	}

}
