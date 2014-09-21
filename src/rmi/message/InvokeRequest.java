package rmi.message;

import java.lang.reflect.Type;

public class InvokeRequest extends Request {
    private static final long serialVersionUID = 3699709985895743657L;
    public String methodName;
    public Type[] types;
    public Object[] args;

    public InvokeRequest(String methodName, Type[] types, Object[] args) {
        this.methodName = methodName;
        this.types = types;
        this.args = args;
    }
}
