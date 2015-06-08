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

	public durscht.contracts.logic.model.IUser login(String name, String password) {
		System.out.println("Beginn LoginHandler " + name + " "  + password);

		IDataHandler dataHandler = getDataHandler();

		durscht.contracts.data.IUser iUser = dataHandler.getUserLogin(name, password);
		System.out.println("LoginHandler returned from datahandler");
		if (iUser == null) {
			System.out.println("user is null");
			return null;
		}
		User user = LoginHandler.convertDBtoUI(iUser);
		System.out.println("Before End LoginHandler " + user.getName());
		return user;
	}

	public durscht.contracts.logic.model.IUser adminLogin(String name, String password) {
		IDataHandler dataHandler = getDataHandler();

		durscht.contracts.data.IUser iUser = dataHandler.getUserLoginAdmin(name, password);
		if (iUser == null) {
			return null;
		}
		User user = LoginHandler.convertDBtoUI(iUser);
		return user;
	}

	public durscht.contracts.logic.model.IUser createUser(String name, String password, String email) {
		IDataHandler dataHandler = getDataHandler();

		User user = new User();
		durscht.contracts.data.IUser iUser = dataHandler.createUser(name, email, password, false);
		user.setId(iUser.getId());
		user.setName(iUser.getName());
		user.setEmail(iUser.getEmail());
		user.setDate(iUser.getJoinedDate());
		return user;
	}

	protected static User convertDBtoUI(durscht.contracts.data.IUser iUser) {
		User user = new User();
		user.setId(iUser.getId());
		user.setName(iUser.getName());
		user.setEmail(iUser.getEmail());
		user.setDate(iUser.getJoinedDate());

		return user;
	}
}
