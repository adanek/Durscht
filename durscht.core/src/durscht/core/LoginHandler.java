package durscht.core;

import durscht.contracts.data.IDataHandler;
import durscht.core.config.ServiceLocator;
import durscht.model.User;

/**
 * Created by Mark on 04.05.2015.
 */
public class LoginHandler {

	private IDataHandler dataHandler;

	private IDataHandler getDataHandler() {
		if (dataHandler == null)
			dataHandler = ServiceLocator.getDataHandler();

		return dataHandler;
	}

	public void setDataHandler(IDataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	public User login(String name, String password) {
		IDataHandler dataHandler = getDataHandler();

		User user = new User();
		durscht.contracts.data.IUser iUser = dataHandler.getUserLogin(name, password);
		if (iUser == null) {
			return null;
		}
		user.setId(iUser.getId());
		user.setName(iUser.getName());
		user.setEmail(iUser.getEmail());
		user.setDate(iUser.getJoinedDate());

		return user;
	}

	public User createUser(String name, String password, String email) {
		IDataHandler dataHandler = getDataHandler();

		User user = new User();
		durscht.contracts.data.IUser iUser = dataHandler.createUser(name, email, password, false);
		user.setId(iUser.getId());
		user.setName(iUser.getName());
		user.setEmail(iUser.getEmail());
		user.setDate(iUser.getJoinedDate());
		return user;
	}

}
