package com.sun2oralce.ws;

import javax.jws.WebService;

@WebService
public interface HelloService {
	public String sayHello(String name);
}
