package rmi.core;

import java.io.Serializable;

/**
 * Represent a reference to a object located at some port on some host
 *
 * @author Chao Xin, Chao Zhang
 */
public class RemoteObjectRef implements Serializable {
    /**
	 *
	 */
    private static final long serialVersionUID = 3625861985375739675L;
    public String host;
    public int port;
    public String name;
    public String interfaceName;

    public RemoteObjectRef() {

    }

    public RemoteObjectRef(String host, int port, String name,
            String interfaceName) {
        this.host = host;
        this.port = port;
        this.name = name;
        this.interfaceName = interfaceName;
    }

}
