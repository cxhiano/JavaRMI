package rmi.net;

import rmi.message.Request;
import rmi.message.Response;

public abstract class SocketRequestHandler {

    public abstract Response handle(Request req);
}
