package rmi.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rmi.message.InvokeRequest;
import rmi.message.InvokeResponse;
import rmi.message.Request;
import rmi.message.Response;
import rmi.net.SocketRequestHandler;

/**
 * Handles rmi request. Support all types of parameters including primitive
 * types, local objects, remote objects. Upon invoking, all parameters will be
 * checked. If a parameter is a remote object but it exists on localhost, the
 * handler will fetch the local object instead of using the original remote
 * object.
 *
 * @author Chao Xin, Chao Zhang
 */
public class RemoteObjectServerHandler extends SocketRequestHandler {

    /**
     * This map contains all exported object. It will be consulted when
     * determining whether a remote object exists on local host.
     */
    private static Map<String, Object> OBJECTS = new ConcurrentHashMap<String, Object>();
    private Remote obj;

    public RemoteObjectServerHandler(Remote obj, RemoteObjectRef ref) {
        this.obj = obj;
        // Add the object to local object map
        OBJECTS.put(ref.getKey(), obj);
    }

    @Override
    public Response handle(Request request) {
        InvokeResponse resp = new InvokeResponse();

        try {
            InvokeRequest req = (InvokeRequest) request;

            Class<? extends Remote> cls = obj.getClass();

            if (req.args == null) {// No argument
                // Obtain the local method by using java reflection
                Method method = cls.getMethod(req.methodName);
                // Return result of the local invocation
                resp.result = method.invoke(this.obj);
            } else { // One or more arguments
                Method method = cls.getMethod(req.methodName, req.types);

                /*
                 * Check every parameter, if it's a remote object and it exists
                 * on localhost, use the local object instead
                 */
                for (int i = 0; i < req.args.length; ++i) {
                    /*
                     * Check if the parameter is a Proxy object(which means it's
                     * a remote object)
                     */
                    if (!req.types[i].isPrimitive()
                            && Proxy.isProxyClass(req.args[i].getClass())) {
                        // Get the reference of the 'real' object
                        RemoteInvocationHandler invocationHandler = ((RemoteInvocationHandler) Proxy
                                .getInvocationHandler(req.args[i]));
                        // Get the unique key of the 'real' object
                        String key = invocationHandler.getRef().getKey();
                        /*
                         * If it's in the map contains all local exported
                         * object, use the local one as parameter
                         */
                        req.args[i] = OBJECTS.getOrDefault(key, req.args[i]);
                    }
                }
                // Return result of local invocation
                resp.result = method.invoke(this.obj, req.args);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            resp.e = new RemoteException(e.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            resp.e = new RemoteException(e.toString());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            resp.e = new RemoteException(e.toString());
        }
        return resp;
    }
}
