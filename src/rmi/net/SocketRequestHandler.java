package rmi.net;

import rmi.message.Request;
import rmi.message.Response;

/**
 * Abstraction handler to return a Response given a Request
 * 
 * @author Chao
 *
 */
public abstract class SocketRequestHandler {

    public abstract Response handle(Request req);
}
