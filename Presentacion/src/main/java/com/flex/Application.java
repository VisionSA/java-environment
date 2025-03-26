package com.flex;

import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;


public class Application extends ApplicationAdapter {

	public Application() {
		super();
	}


	@Override
	public synchronized boolean connect(IConnection conn, IScope scope,
			Object[] params) {

		if (params.length == 2 && (params[0] != null && params[1] != null) && params[0].toString().equals("tarjeta")
				&& params[1].toString().equals("fiel")) {
			return super.connect(conn, scope, params);
		}
		return false;
	}

}
