package com.globallogic.ox.app.services;

import com.globallogic.ox.domain.Account;

public interface ServicesInterface {
	
	void getToken(ServiceListener<Account> listener, Class<Account> clazz);
}
