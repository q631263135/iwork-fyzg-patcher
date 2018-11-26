package com.sun2oralce.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface="com.sun2oralce.ws.HelloService", serviceName="HelloService")
public class HelloServiceImpl implements HelloService {

	@Override
	@WebMethod
	public String sayHello(String name) {
		return "Hello!";
	}

}
