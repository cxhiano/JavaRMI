package rmi.net;

import rmi.message.Request;
import rmi.message.Response;

public abstract class SocketRequestHandler {
    private String token;

    public SocketRequestHandler() {}

    public SocketRequestHandler(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public abstract Response handle(Request req);
}
