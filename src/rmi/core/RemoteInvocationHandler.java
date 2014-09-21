package rmi.core;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import rmi.message.InvokeRequest;
import rmi.message.InvokeResponse;

public class RemoteInvocationHandler implements Serializable, InvocationHandler {
	/**
	 *
	 */
	private static final long serialVersionUID = 6842830036262560740L;
	private RemoteObjectRef ref;

	public RemoteInvocationHandler(RemoteObjectRef ref) {
		this.ref = ref;
	}

	public void setRef(RemoteObjectRef ref) {
		this.ref = ref;
	}

	public RemoteObjectRef getRef() {
		return this.ref;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws RemoteException {
		InvokeResponse response = (InvokeResponse) SocketHandler.request(
									ref.host, ref.port,
									new InvokeRequest(method.getName(),
										method.getGenericParameterTypes(), args));

		return method.getReturnType().cast(response.result);
	}
}
