package rmi.core;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import rmi.message.InvokeRequest;
import rmi.message.InvokeResponse;
import rmi.net.SocketRequest;

/**
 * This handler is asscociated with a Proxy object that serves as a client-side
 * stub for a remote object. All method invocations are converted to a
 * InvokeRequest object. The request object holds the unique name of the method
 * owner, the name of the method, method parameter types and method parameter.
 * The Request * object will then will be sent to corresponding host to invoke
 * the 'real' method.
 *
 * @author Chao Xin, Chao Zhang
 */
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

    /**
     * Implement the method in Interface InvocationHandler. Convert the
     * invocation to a {@link InvokeRequest} object and forward it the corresponding
     * host.
     *
     * @param proxy
     * @param method
     * @param args
     */
    public Object invoke(Object proxy, Method method, Object[] args)
            throws RemoteException {

        //Converts the invocation to a InvokeRequest Object
        InvokeRequest req = new InvokeRequest(ref.getKey(), method.getName(),
                method.getParameterTypes(), args);

        //Forward it to corresponding host and get response
        InvokeResponse response = (InvokeResponse) SocketRequest.request(
                ref.host, ref.port, req);

        //Something goes wrong?
        if (!InvokeResponse.valid(response))
            throw response.e;

        return response.result;
    }
}
