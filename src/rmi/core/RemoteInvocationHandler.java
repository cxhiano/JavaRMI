package rmi.core;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import rmi.message.InvokeRequest;
import rmi.message.InvokeResponse;
import rmi.net.SocketRequest;

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

        InvokeRequest req = new InvokeRequest(ref.getKey(), method.getName(),
                method.getParameterTypes(), args);

        InvokeResponse response = (InvokeResponse) SocketRequest.request(
                ref.host, ref.port, req);

        if (!InvokeResponse.valid(response))
            throw response.e;

        return response.result;
    }
}
