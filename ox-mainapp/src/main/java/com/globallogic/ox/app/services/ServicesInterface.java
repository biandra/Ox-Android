package com.globallogic.ox.app.services;

import com.globallogic.ox.domain.User;

public interface ServicesInterface {
	
	void getLogin(ServiceListener<User> listener, Class<User> clazz);
}
