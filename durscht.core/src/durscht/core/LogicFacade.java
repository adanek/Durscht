package durscht.core;

import durscht.contracts.logic.IBeerHandler;
import durscht.contracts.logic.ILogicFacade;
import durscht.contracts.logic.IPostHandler;
import durscht.core.config.ServiceLocator;

public class LogicFacade implements ILogicFacade {
	
	@Override
	public IPostHandler getPostHandler() {
		return ServiceLocator.getPostHandler();
	}

	@Override
	public IBeerHandler getBeerHandler() {
		return ServiceLocator.getBeerHandler();
	}

}
