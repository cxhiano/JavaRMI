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

public class RemoteObjectServerHandler extends SocketRequestHandler {

    private static Map<String, Object> OBJECTS = new ConcurrentHashMap<String, Object>();
    private Remote obj;

    public RemoteObjectServerHandler(Remote obj, RemoteObjectRef ref) {
        this.obj = obj;
        OBJECTS.put(ref.getKey(), obj);
    }

    @Override
    public Response handle(Request request) {
        InvokeResponse resp = new InvokeResponse();
        try {
            InvokeRequest req = (InvokeRequest) request;
            Class<? extends Remote> cls = obj.getClass();
            if (req.args == null) {
                // No argument
                Method method = cls.getMethod(req.methodName);
                resp.result = method.invoke(this.obj);
            } else {
                // One or more arguments
                Method method = cls.getMethod(req.methodName, req.types);
                for (int i = 0; i < req.args.length; ++i) {
                    if (!req.types[i].isPrimitive()
                            && Proxy.isProxyClass(req.args[i].getClass())) {
                        RemoteInvocationHandler invocationHandler = ((RemoteInvocationHandler) Proxy
                                .getInvocationHandler(req.args[i]));
                        String key = invocationHandler.getRef().getKey();
                        req.args[i] = OBJECTS.getOrDefault(key, req.args[i]);
                    }
                }
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
