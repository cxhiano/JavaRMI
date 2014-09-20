package rmi.message;

import java.util.ArrayList;
import java.util.List;

public class InvokeRequest extends Request {
    private static final long serialVersionUID = 3699709985895743657L;
    public String methodName;
    public List<Object> args;

    public InvokeRequest(String methodName) {
        this.methodName = methodName;
        this.args = new ArrayList<Object>();
    }

    public void addArg(Object arg) {
        args.add(arg);
    }

}
