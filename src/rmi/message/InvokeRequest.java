package rmi.message;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class InvokeRequest extends Request {
    public String className;
    public String methodName;
    public List<Object> args;

    public InvokeRequest(String className, String methodName) {
        this.className = className;
        this.methodName = methodName;
        args = new ArrayList<Object>();
    }

    public void addArg(Object arg) {
        args.add(arg);
    }

    private static final long serialVersionUID = 3699709985895743657L;
}
