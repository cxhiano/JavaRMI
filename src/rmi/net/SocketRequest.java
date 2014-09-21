package rmi.net;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import rmi.message.Request;
import rmi.message.Response;

public class SocketRequest {
    /**
     *
     * Wrapper function for client to send a request at host:port
     *
     * @param host
     * @param port
     * @param request
     * @return response object
     */
    public static Response request(String host, int port, Request request) {
        Socket sock = null;

        try {
            sock = new Socket(host, port);

            ObjectOutputStream out = new ObjectOutputStream(
                    sock.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(sock.getInputStream());

            out.writeObject(request);
            out.flush();

            Response response = (Response) in.readObject();

            return response;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sock != null)
                    sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
