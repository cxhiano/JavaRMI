package rmi.message;

public class InvokeRequest extends Request {
    private static final long serialVersionUID = 3699709985895743657L;
    public String methodName;
    public Object[] args;

    public InvokeRequest(String methodName, Object[] args) {
        this.methodName = methodName;
        this.args = args;
    }
}
