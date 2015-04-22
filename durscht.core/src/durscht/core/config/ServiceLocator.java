package durscht.core.config;

import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.IPostHandler;
import durscht.core.PostHandler;
import durscht.handler.DataHandler;

public class ServiceLocator {
	
	private static IDataHandler dataHandler;
	private static IPostHandler postHandler;
	
	public static IDataHandler getDataHandler() {
		
		if (dataHandler == null)
			dataHandler = new DataHandler();
		
		return dataHandler;
	}
	
	public static IPostHandler getPostHandler() {
		
		if (postHandler == null)
			postHandler = new PostHandler();
		
		return postHandler;
	}

	
}
