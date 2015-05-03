package durscht.core.config;

import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.IBeerHandler;
import durscht.contracts.logic.ILogicFacade;
import durscht.contracts.logic.IPostHandler;
import durscht.core.BeerHandler;
import durscht.core.LogicFacade;
import durscht.core.PostHandler;
import durscht.data.handler.DataHandler;

public class ServiceLocator {
	
	private static ILogicFacade logicFacade;
	
	private static IPostHandler postHandler;
	private static IBeerHandler beerHandler;
	
	private static IDataHandler dataHandler;
	
	public static ILogicFacade getLogicFacade() {
		if (logicFacade == null)
			logicFacade = new LogicFacade();
		
		return logicFacade;
	}
	
	public static IPostHandler getPostHandler() {
		
		if (postHandler == null)
			postHandler = new PostHandler();
		
		return postHandler;
	}
	
	public static IBeerHandler getBeerHandler() {
		if (beerHandler == null)
			beerHandler = new BeerHandler();
		
		return beerHandler;
	}
	
	public static IDataHandler getDataHandler() {
		
		if (dataHandler == null)
			dataHandler = new DataHandler();
		
		return dataHandler;
	}
}
