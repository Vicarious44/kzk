package lt.web.service.dao;

import lt.web.service.filter.Authorization;

public class AuthorizationFactory {

	private static final Authorization auth;
	
	static {
		auth = new Authorization();
	}
	
	public static final Authorization getAuth(){
		return auth;
	}
}
