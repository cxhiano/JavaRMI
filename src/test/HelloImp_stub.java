package test;

import java.io.Serializable;

import rmi.core.BaseStub;
import rmi.message.InvokeRequest;

public class HelloImp_stub extends BaseStub implements Hello, Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 978638176150234989L;

	public final String host = "localhost";
	public final int port = 12345;

	public String sayHello(Integer a, Character c) {
		setupSocket(host, port);

		InvokeRequest request = new InvokeRequest("sayHello");

		request.addArg(a);
		request.addArg(c);

		return (String) invoke(request);
	}

	public void sayHello() {
		setupSocket(host, port);

		InvokeRequest request = new InvokeRequest("sayHello");

		invoke(request);
	}
}
